package com.member.dao;



import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class findAccount {
	private final int PORT = 27017;
	private final String DB_NAME = "plugliquid";
	private final String COL = "personal_imp";
	private String mode;
	private String name, email, id;
	private MongoClient mongoClient;
	private MongoDatabase DB;
	private MongoCollection<Document> documentMongoCollection;
	
	
	
	public findAccount(String name, String email, String id, String mode) 
	{
		this.name = name;
		this.email = email;
		this.id = id;
		this.mode = mode;
		//DB����
		if(connectMongoDB())
			System.out.println("DB���� ����");
	}
		
	//DB����
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
	
	public String inpuiryAccount() {
		
		Document doc = null;
		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		
		try {
				System.out.println("mode: "+mode);
				
				if(mode.equals("id"))
				{
					System.out.println("id ��ȸ ����");
					obj.add(new BasicDBObject("name", name));
					obj.add(new BasicDBObject("user_email", email));
					System.out.println("��ȸ ���: "+obj);
				}
				else if(mode.equals("pwd"))
				{	
					System.out.println("pw ��ȸ ����");
					obj.add(new BasicDBObject("user_id", id));
					obj.add(new BasicDBObject("name", name));
					obj.add(new BasicDBObject("user_email", email));
					System.out.println("��ȸ ���: "+obj);
				}
				else
					System.out.println("mode ���ڰ� �ַ�");
				// ���� ����
				andQuery.put("$and", obj);
				// ������ ��ȸ �� ��ť��Ʈ ��ȯ
				doc = documentMongoCollection.find(andQuery).first();
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc.getString("user_" + mode);
	}
}