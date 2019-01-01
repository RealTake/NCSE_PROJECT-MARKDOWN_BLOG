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
			System.out.println("인스탄스 생성 실패");
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
	
	// 카테고리별 글목록 가져오기
	public ArrayList<boardDTO> list(String category)
	{
		ArrayList<boardDTO> dtos = new ArrayList<boardDTO>();
		BasicDBObject cateQ = new BasicDBObject(category, new BasicDBObject("$exists", true)); //false: 를 제외한 나머지출력, ture: 를 포함하는 것들을 출력
		MongoCursor<Document> it = documentMongoCollection.find(cateQ).iterator();
		while(it.hasNext())
		{
			String temp = it.next().toJson();
			dtos.add(parseList(temp, category));
		}
		
		return dtos;
		
	}
	
	// 도큐먼트 파싱
	public boardDTO parseList(String temp, String cate)
	{
		boardDTO dto = new boardDTO();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(temp);
			String array = jsonObject.get(cate).toString();
			System.out.println(cate + " 배열d: " + array);
			String bid = jsonObject.get("_id").toString();
			System.out.println(bid);
			dto.setbId(bid.substring(9, bid.length() - 2));
			
			array = array.substring(1, array.length()-1);
			
			//배열로 구성되어있는 글들을 파싱하기위해 이중 파싱을함
			jsonObject = (JSONObject)jsonParser.parse(array);
			dto.setTitle(jsonObject.get("board_header").toString());
			//dto.setContent(jsonObject.get("board_contents").toString());
			dto.setId(jsonObject.get("board_userID").toString());
			dto.setDate(jsonObject.get("board_date").toString());
			dto.setLike(new Integer(jsonObject.get("board_like").toString()));
			dto.setDisLike(new Integer(jsonObject.get("board_dislike").toString()));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
        
	}
	
	//글보기
	public boardDTO viewContent(String bid, String cate)
	{
		boardDTO dto = new boardDTO();
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(bid));
		MongoCursor<Document> it = documentMongoCollection.find(query).iterator();
		while(it.hasNext())
		{
			String temp = it.next().toJson();
			
			try {
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject)jsonParser.parse(temp);
				String array = jsonObject.get(cate).toString();
				System.out.println(cate + " 배열d: " + array);
				
				array = array.substring(1, array.length()-1);
				
				//배열로 구성되어있는 글들을 파싱하기위해 이중 파싱을함
				jsonObject = (JSONObject)jsonParser.parse(array);
				dto.setTitle(jsonObject.get("board_header").toString());
				dto.setContent(jsonObject.get("board_contents").toString());
				dto.setId(jsonObject.get("board_userID").toString());
				dto.setDate(jsonObject.get("board_date").toString());
				dto.setLike(new Integer(jsonObject.get("board_like").toString()));
				dto.setDisLike(new Integer(jsonObject.get("board_dislike").toString()));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	//글쓰기
	public void write(boardDTO dto, String cate)
	{
		
		List<BasicDBObject> board = new ArrayList<BasicDBObject>();
		board.add(new BasicDBObject()
		.append("board_header", "글쓰기 테스트중")
		.append("board_contents", "입력이됬나요?")
		.append("board_userID", "choi1234")
		.append("board_date", "2019-01-01")
		.append("board_like", 0)
		.append("board_dislike", 0)
		);
		
		
//		BasicDBObject board = new BasicDBObject()
//		.append("board_header", dto.getTitle())
//		.append("board_contents", dto.getContent())
//		.append("board_userID", dto.getId())
//		.append("like", dto.getLike())
//		.append("board_dislike", dto.getDisLike());
		Document doc = new Document()
		.append(cate, board);
		
		documentMongoCollection.insertOne(doc);
	}
	
	
	
	
	
	
//	public boardDTO parseView(String temp, String cate)
//	{
//		boardDTO dto = new boardDTO();
//		try {
//			JSONParser jsonParser = new JSONParser();
//			JSONObject jsonObject = (JSONObject)jsonParser.parse(temp);
//			String array = jsonObject.get(cate).toString();
//			System.out.println(cate + " 배열d: " + array);
//			
//			array = array.substring(1, array.length()-1);
//			
//			//배열로 구성되어있는 글들을 파싱하기위해 이중 파싱을함
//			jsonObject = (JSONObject)jsonParser.parse(array);
//			dto.setTitle(jsonObject.get("board_header").toString());
//			dto.setContent(jsonObject.get("board_contents").toString());
//			dto.setId(jsonObject.get("board_userID").toString());
//			dto.setDate(jsonObject.get("board_date").toString());
//			dto.setLike(jsonObject.get("board_like").toString());
//			dto.setDisLike(jsonObject.get("board_dislike").toString());
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return dto;
//        
//	}
	
//	public String parseJson2(String temp, String cate)
//	{
//		try {
//			JSONParser jsonParser = new JSONParser();
//			JSONObject jsonObject = (JSONObject)jsonParser.parse(temp);
//			temp = jsonObject.get(cate).toString();
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return temp;
//	}
}