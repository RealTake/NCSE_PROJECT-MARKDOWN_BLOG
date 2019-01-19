package com.borad.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.board.dto.boardDTO;
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
			// TODO Auto-generated catch block
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
			Object array = jsonObject.get(cate);
			if(array == null)
				return null;
			System.out.println(cate + " 배열: " + array);
			String bid = jsonObject.get("_id").toString();
			System.out.println(bid);
			dto.setbId(bid.substring(9, bid.length() - 2));
			
			String tt = array.toString();
			tt = tt.substring(1, tt.length()-1);
			
			//배열로 구성되어있는 글들을 파싱하기위해 이중 파싱을함
			jsonObject = (JSONObject)jsonParser.parse(tt);
			dto.setTitle(jsonObject.get("board_header").toString());
			if(mode == true)
				dto.setContent(jsonObject.get("board_contents").toString());
			dto.setId(jsonObject.get("board_userID").toString());
			dto.setDate(jsonObject.get("board_date").toString());
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
			MongoCursor<Document> it = documentMongoCollection.find(cateQ).iterator();
			
			while(it.hasNext())
			{
				String temp = it.next().toJson();
				dtos.add(parseList(temp, category, false));
			}
			
			return dtos;
			
		}
		
		
	//글보기(추후에 json 배열 파서를 따로 함수로 만들지 고민 위의 list 함수와함께)
	public boardDTO viewContent(String bid)
	{
		boardDTO dto = new boardDTO();
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(bid));
		MongoCursor<Document> it = documentMongoCollection.find(query).iterator();
		
			String temp = it.next().toJson();
			String[] type ={"PJ_board", "FR_board", "ST_board", "ITnews_board"};
			
			for(int i = 0; i < type.length; i++)
			{
				dto = parseList(temp, type[i], true);
				
				if(dto == null)
					continue;
				else
				{
					dto.setType(type[i]);
					break;
				}
				
			}
			return dto;
		
	}
	
	//글쓰기
	public void write(boardDTO dto, String cate)
	{
		
		List<BasicDBObject> board = new ArrayList<BasicDBObject>();
		board.add(new BasicDBObject()
		.append("board_header", dto.getTitle())
		.append("board_contents", dto.getContent())
		.append("board_userID", dto.getId())
		.append("board_date", dto.getDate())
		.append("board_like", 0)
		.append("board_dislike", 0));
		
		Document doc = new Document().append(cate, board);
		
		documentMongoCollection.insertOne(doc);
	}
	
	//검색목록 가져오기
	public ArrayList<boardDTO> find(String searched)
	{
		ArrayList<boardDTO> dtos = new ArrayList<boardDTO>();
		BasicDBObject search = new BasicDBObject("$text",new BasicDBObject("$search",searched));
		MongoCursor<Document> it = documentMongoCollection.find(search).iterator();
		
		while(it.hasNext())
		{
			boardDTO dto = new boardDTO();
			String temp = it.next().toJson();
				
				String[] type ={"PJ_board", "FR_board", "ST_board", "ITnews_board"};
				
				for(int i = 0; i < type.length; i++)
				{
					dto = parseList(temp, type[i], false);
					if(dto == null)
						continue;
					dtos.add(dto);
				}
		
		}
		return dtos;
	}
}
	
