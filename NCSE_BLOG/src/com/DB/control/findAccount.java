package com.DB.control;

import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class findAccount {
	private final int PORT = 27017;
	private final String DB_NAME = "plugliquid";
	private final String COL = "personal_imp";
	private String mode;
	private String name, email, id, pw;
	private Iterator<Document> it;
	private FindIterable<Document> i;
	private MongoClient mongoClient;
	private MongoDatabase DB;
	private MongoCollection<Document> documentMongoCollection;
	
	
	
	public findAccount(String name, String email, String id, String pw, String mode) 
	{
		this.name = name;
		this.email = email;
		this.id = id;
		this.pw = pw;
		this.mode = mode;
		//DB연결
		if(connectMongoDB())
			System.out.println("DB연결 실패");
		//로그인 유효성
		if(inpuiryAccount())
			System.out.println("조회실패");
	}
	
	//DB연결
	boolean connectMongoDB()
	{
		try {
			mongoClient = new MongoClient("localhost",PORT);
			DB = mongoClient.getDatabase(DB_NAME);
			System.out.println("Connected to the database successfully");
			documentMongoCollection = DB.getCollection(COL);
			System.out.println("Collection myCollection selected successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		
		return false;
	}
	
	public boolean getAccount(String value) 
	{
		try
		{
			System.out.println("getAccount실행중6...");
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject;
			
			while(it.hasNext())
			{
				//System.out.println(it.next());
				jsonObject = (JSONObject) jsonParser.parse(it.next().toJson());
				System.out.print(mode+" : " + jsonObject.get("user_"+value));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return false;
	}
	
	
	
	
	public boolean inpuiryAccount() {
		try {
				BasicDBObject andQuery = new BasicDBObject();
				List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
				
				System.out.println("mode: "+mode);
				
				if(mode.equals("id"))
				{
					System.out.println("id 조회 시작");
					obj.add(new BasicDBObject("name", name));
					obj.add(new BasicDBObject("user_email", email));
					System.out.println("조회 목록: "+obj);
				}
				else if(mode.equals("pwd"))
				{	
					System.out.println("pw 조회 시작");
					obj.add(new BasicDBObject("user_pwd", pw));
					obj.add(new BasicDBObject("name", name));
					obj.add(new BasicDBObject("user_email", email));
					System.out.println("조회 목록: "+obj);
				}
				else
					System.out.println("mode 인자값 애러");
				// 쿼리 저장
				andQuery.put("$and", obj);
				// 쿼리로 조회 및 도큐먼트 반환
				i = documentMongoCollection.find(andQuery);
				it = i.iterator();
				//결과값 도출
				if(getAccount(mode))
					System.out.println("getAccount() 오류!!!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return false;
	}
}