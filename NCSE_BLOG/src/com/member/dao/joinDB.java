package com.member.dao;

import com.mongodb.client.*;
import com.mongodb.MongoClient;
import org.bson.*;

public class joinDB {
	private String id;
	private String pw;
	private String name;
	private String nick;
	private String sex;
	private String email;
	private String ph;
	private String address;
	private String platform_link;
	private String self_imp;
	private String DB_NAME = "plugliquid";
	private String col="personal_imp";
	private int PORT=27017;
	private boolean Validity = false;
	MongoClient mongoClient;
	MongoDatabase DB;
	MongoCollection<Document> documentMongoCollection;
	
	public joinDB(String id, String pw, String name, String nick, String sex, String email, String ph,
				   String platform_link, String self_imp)
			{
				this.id = id;
				this.pw = pw;
				this.name = name;
				this.nick = nick;
				this.sex = sex;
				this.email = email;
				this.ph = ph;
				//this.address = address;
				this.platform_link = platform_link;
				this.self_imp = self_imp;
				//DB 연결
				if(connectMongoDB())
					System.out.println("DB연결 실패");
				//도큐먼트 삽입
				if(insertDB())
					System.out.println("삽입실패");
			}
	
//	public joinDB(String a){
//		connectMongoDB();
//		id=a;
//		if(!insertDB())
//			System.out.println("삽입실패");
//	}
	
	//DB연결
	boolean connectMongoDB()
	{
		
		try {
			mongoClient = new MongoClient("localhost",PORT);
			DB = mongoClient.getDatabase(DB_NAME);
			System.out.println("Connected to the database successfully");
			documentMongoCollection = DB.getCollection(col);
			System.out.println("Collection myCollection selected successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		
		return false;
	}
	
	//도큐먼트 삽입
	boolean insertDB()
	{
		try {
			Document doc = new Document()
			.append("user_id", id)
			.append("user_pwd", pw)
			.append("name", name)
			.append("nick", nick)
			.append("sex", sex)
			.append("user_email", email)
			.append("phone_number", ph)
			.append("user_authority", false)
			//.append("address", address)
			.append("platform_link", platform_link)
			.append("self_imp",self_imp);
			
			documentMongoCollection.insertOne(doc);
			System.out.println("DB 삽입 성공");
			Validity = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		
		return false;
		
	}
	
	public boolean getValidity(){
		return Validity;
	}
	
}
