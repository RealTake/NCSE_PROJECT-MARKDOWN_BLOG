package com.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.board.dto.boardDTO;
import com.board.dto.commentDTO;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class boardDAO 
{
	private final String DB_NAME = "plugliquid";
	private final String col = "board";
	private final int PORT = 27017;
	
	private static boardDAO instance = null;
	
	MongoClient mongoClient;
	MongoDatabase DB;
	MongoCollection<Document> documentMongoCollection;

	private boardDAO()
	{
		//DB연결
		if(connectMongoDB())
			System.out.println("인스턴스 생성 실패");
	}
	
	//싱글톤 패턴
	public static boardDAO getInstance()
	{	
		if(instance == null)
			instance = new boardDAO();
		
		return instance;
	}
	
	//DB연결
	private boolean connectMongoDB()
	{
		try 
		{
			mongoClient = new MongoClient("localhost",PORT);
			DB = mongoClient.getDatabase(DB_NAME);
			System.out.println("Connected to the database successfully");
			documentMongoCollection = DB.getCollection(col);
			System.out.println("Collection myCollection selected successfully");
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
			return true;
		}
			
			return false;
	}
	
	// 도큐먼트 파싱
	public boardDTO parseList(String temp, String cate, boolean mode)
	{
		boardDTO dto = new boardDTO();
		try 
		{
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(temp);
			String bid = jsonObject.get("_id").toString();
			dto.setbId(bid.substring(9, bid.length() - 2));
			
			String array = jsonObject.get(cate).toString();
			array = array.substring(1, array.length()-1);
			
			//배열로 구성되어있는 글들을 파싱하기위해 이중 파싱을함
			jsonObject = (JSONObject)jsonParser.parse(array);
			dto.setTitle(jsonObject.get("board_header").toString());
			if(mode == true)
				dto.setContent(jsonObject.get("board_contents").toString());
			dto.setId(jsonObject.get("board_userID").toString());
			dto.setDate(jsonObject.get("board_date").toString().substring(0,10));
			dto.setLike(new Integer(jsonObject.get("board_like").toString()));
			dto.setDisLike(new Integer(jsonObject.get("board_dislike").toString()));
			
		} 
		
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		return dto;
        
	}
	
	
	// 카테고리별 글목록 가져오기
		public ArrayList<boardDTO> list(String category)
		{
			ArrayList<boardDTO> dtos = new ArrayList<boardDTO>();
			BasicDBObject cateQ = new BasicDBObject(category, new BasicDBObject("$exists", true)); //false: 를 제외한 나머지출력, ture: 를 포함하는 것들을 출력
			long start = System.currentTimeMillis();
			MongoCursor<Document> it = documentMongoCollection.find(cateQ)
					.projection(new BasicDBObject(category + ".board_contents", false))
					.sort(new BasicDBObject(category + ".board_date", -1)).iterator();
			
			while(it.hasNext())
			{
				String temp = it.next().toJson();
				dtos.add(parseList(temp, category, false));
			}
			long end = System.currentTimeMillis();
			System.out.println(end-start);
			return dtos;
			
		}
		
		
	//글보기(추후에 json 배열 파서를 따로 함수로 만들지 고민 위의 list 함수와함께)
	public boardDTO viewContent(String bid)
	{
		ArrayList<commentDTO> comments = new ArrayList<commentDTO>();
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(bid));
		Document it = documentMongoCollection.find(query).first();
		String[] key= it.keySet().toArray(new String[2]);
		
		boardDTO dto = parseList(it.toJson(), key[1], true);
		dto.setType(key[1]);
		
		//댓글 파싱
		List<Document> commentss = (List<Document>) it.get("comments");
		if(commentss != null)
		{
			for (Document comment : commentss) 
			{
				commentDTO dto_c = new commentDTO();
				
				dto_c.setName(comment.getString("name"));
				dto_c.setComment(comment.getString("contents"));
				
				comments.add(dto_c);
			}
			
			dto.setComments(comments);
		}
		
		return dto;
	}
	
	//글쓰기
	public void write(boardDTO dto)
	{
		
		List<BasicDBObject> board = new ArrayList<BasicDBObject>();
		board.add(new BasicDBObject()
		.append("board_header", dto.getTitle())
		.append("board_contents", dto.getContent())
		.append("board_userID", dto.getId())
		.append("board_date", dto.getDate())
		.append("board_like", 0)
		.append("board_dislike", 0));
		
		Document doc = new Document().append(dto.getType(), board).append("comments", new ArrayList());
		
		documentMongoCollection.insertOne(doc);
	}
	
	//검색목록 가져오기
	public ArrayList<boardDTO> find(String searched, String type)
	{
		ArrayList<boardDTO> dtos = new ArrayList<boardDTO>();
		ArrayList<BasicDBObject> Fquery = new ArrayList<BasicDBObject>();
		Fquery.add(new BasicDBObject(type + ".board_contents", Pattern.compile(searched, Pattern.CASE_INSENSITIVE)));
		Fquery.add(new BasicDBObject(type + ".board_header", Pattern.compile(searched, Pattern.CASE_INSENSITIVE)));
		BasicDBObject search = new BasicDBObject(new BasicDBObject("$or", Fquery));
		System.out.println(search);
		MongoCursor<Document> it = documentMongoCollection.find(search)
				.projection(new BasicDBObject("PJ_board" + ".board_contents", false))
				.sort(new BasicDBObject("PJ_board.board_date", -1)).iterator();
		
		while(it.hasNext())
		{
			boardDTO dto = new boardDTO();
			String temp = it.next().toJson();
				System.out.println(temp);
				
					dto = parseList(temp, "PJ_board", false);
					dtos.add(dto);
		
		}
		return dtos;
	}
	
	public void setComments(String bid, String id, String comment)
	{
		documentMongoCollection.updateOne(new BasicDBObject("_id", new ObjectId(bid)), 
				  new BasicDBObject("$push",new BasicDBObject("comments", new BasicDBObject("name", id).append("contents", comment))));
	}
	
	public void delete(boardDTO dto, String id)
	{
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(dto.getbId()));
		Document it = documentMongoCollection.find(query).first();
		
		List<Document> temp = (List<Document>) it.get(dto.getType());
		if(temp != null)
		{
			
			if(temp.get(0).getString("board_userID").equals(id))
				documentMongoCollection.deleteOne(new BasicDBObject("_id", new ObjectId(dto.getbId())));
		}
	}
	
	public void modify(boardDTO dto)
	{
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(dto.getbId()));
		Document it = documentMongoCollection.find(query).first();
		
		List<Document> temp = (List<Document>) it.get(dto.getType());
		if(temp != null)
		{
			
			if(temp.get(0).getString("board_userID").equals(dto.getId()))
			{
				documentMongoCollection.updateOne(new BasicDBObject("_id", new ObjectId(dto.getbId())), 
				new BasicDBObject("$set", new BasicDBObject(dto.getType() + ".0.board_header", dto.getTitle())
				.append(dto.getType() + ".0.board_contents", dto.getContent())));
			}
		}
	}
	
}
	
