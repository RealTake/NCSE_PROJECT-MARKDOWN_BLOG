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
			dtos.add(parseJson(temp, category));
		}
		
		return dtos;
		
	}
	
	// ��ť��Ʈ �Ľ�
	public boardDTO parseJson(String temp, String cate)
	{
		boardDTO dto = new boardDTO();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(temp);
			String array = jsonObject.get("PJ_board").toString();
			System.out.println(cate + " �迭d: " + array);
			array = array.substring(1, array.length()-1);
			
			//�迭�� �����Ǿ��ִ� �۵��� �Ľ��ϱ����� ���� �Ľ�����
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