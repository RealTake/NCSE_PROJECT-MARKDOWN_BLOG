package com.DB.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class loginCheck_DB {
	private String id;
	private String pw;
	private String DB_NAME = "plugliquid";
	private String col = "personal_imp";
	private int PORT = 27017;
	private boolean Validity=false;
	MongoClient mongoClient;
	MongoDatabase DB;
	MongoCollection<Document> documentMongoCollection;
	
	public loginCheck_DB(String id, String pw) 
	{
		this.id = id;
		this.pw = pw;
		
		//DB����
		if(connectMongoDB())
			System.out.println("DB���� ����");
		//�α��� ��ȿ��
		if(compareAccount())
			System.out.println("�񱳽���");
	}
	
	//DB����
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
	
	
	public boolean getValidity(){
		return Validity;
	}
	
	//ID,PW and���� ��
	public boolean compareAccount()
	{
		//and������ �ۼ�
		try {
			BasicDBObject andQuery = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			obj.add(new BasicDBObject("user_id", id));
			obj.add(new BasicDBObject("user_pwd", pw));
			andQuery.put("$and", obj);
			//System.out.println(andQuery.toString());
			//DB��ȸ
			FindIterable<Document> cursor = documentMongoCollection.find(andQuery);
			Iterator<Document> i = cursor.iterator();
			while(i.hasNext())
			{
				String temp = i.next().toString();
				System.out.println(temp);
				
				if(temp.toString().substring(0,8).equals("Document"))
				{
					Validity = true;
					System.out.println("���� �߰�");
					break;
				}
				else
				{
					System.out.println("������ �߰� �Ұ�");
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return false;
	}
}