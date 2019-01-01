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
		//DB����
		if(connectMongoDB())
			System.out.println("�ν�ź�� ���� ����");
	}
	
	//�̱��� ����
	public static boardDAO getInstance()
	{	
		if(instance == null)
			instance = new boardDAO();
		return instance;
	}
	
	//DB����
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
	
	// ī�װ��� �۸�� ��������
	public ArrayList<boardDTO> list(String category)
	{
		ArrayList<boardDTO> dtos = new ArrayList<boardDTO>();
		BasicDBObject cateQ = new BasicDBObject(category, new BasicDBObject("$exists", true)); //false: �� ������ ���������, ture: �� �����ϴ� �͵��� ���
		MongoCursor<Document> it = documentMongoCollection.find(cateQ).iterator();
		while(it.hasNext())
		{
			String temp = it.next().toJson();
			dtos.add(parseList(temp, category));
		}
		
		return dtos;
		
	}
	
	// ��ť��Ʈ �Ľ�
	public boardDTO parseList(String temp, String cate)
	{
		boardDTO dto = new boardDTO();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(temp);
			String array = jsonObject.get(cate).toString();
			System.out.println(cate + " �迭d: " + array);
			String bid = jsonObject.get("_id").toString();
			System.out.println(bid);
			dto.setbId(bid.substring(9, bid.length() - 2));
			
			array = array.substring(1, array.length()-1);
			
			//�迭�� �����Ǿ��ִ� �۵��� �Ľ��ϱ����� ���� �Ľ�����
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
	
	//�ۺ���
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
				System.out.println(cate + " �迭d: " + array);
				
				array = array.substring(1, array.length()-1);
				
				//�迭�� �����Ǿ��ִ� �۵��� �Ľ��ϱ����� ���� �Ľ�����
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
	
	//�۾���
	public void write(boardDTO dto, String cate)
	{
		
		List<BasicDBObject> board = new ArrayList<BasicDBObject>();
		board.add(new BasicDBObject()
		.append("board_header", "�۾��� �׽�Ʈ��")
		.append("board_contents", "�Է��̉糪��?")
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
//			System.out.println(cate + " �迭d: " + array);
//			
//			array = array.substring(1, array.length()-1);
//			
//			//�迭�� �����Ǿ��ִ� �۵��� �Ľ��ϱ����� ���� �Ľ�����
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