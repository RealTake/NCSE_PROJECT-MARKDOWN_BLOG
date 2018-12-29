package com.borad.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.board.dto.boardDTO;
import com.member.dao.memberDAO;
import com.member.dto.memberDTO;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.sun.jndi.cosnaming.IiopUrl.Address;


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
			dtos.add(parseJson(temp, category));
		}
		
		return dtos;
		
	}
	
	// 도큐먼트 파싱
	public boardDTO parseJson(String temp, String cate)
	{
		boardDTO dto = new boardDTO();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(temp);
			String array = jsonObject.get("PJ_board").toString();
			System.out.println(cate + " 배열d: " + array);
			array = array.substring(1, array.length()-1);
			
			//배열로 구성되어있는 글들을 파싱하기위해 이중 파싱을함
			jsonObject = (JSONObject)jsonParser.parse(array);
			dto.setTitle(jsonObject.get("board_header").toString());
			dto.setContent(jsonObject.get("board_contents").toString());
			dto.setId(jsonObject.get("board_userID").toString());
			dto.setDate(jsonObject.get("board_date").toString());
			dto.setLike(jsonObject.get("board_like").toString());
			dto.setDisLike(jsonObject.get("board_dislike").toString());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
        
	}
	
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