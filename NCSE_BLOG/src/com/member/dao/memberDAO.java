package com.member.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.member.dto.memberDTO;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class memberDAO 
{
	private final String DB_NAME = "plugliquid";
	private final String col = "personal_imp";
	private final int PORT = 27017;
	private static memberDAO instance = null;
	MongoClient mongoClient;
	MongoDatabase DB;
	MongoCollection<Document> documentMongoCollection;
	
	private memberDAO()
	{
		//DB����
		if(connectMongoDB())
			System.out.println("�ν�ź�� ���� ����");
	}
	
	//�̱��� ����
	public static memberDAO getInstance()
	{	
		if(instance == null)
			instance = new memberDAO();
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
	
	//��������
	public boolean userCheck(String id, String pw)
	{
		boolean result = false;
		try {
			BasicDBObject andQuery = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			Iterator<Document> i;
			
			obj.add(new BasicDBObject("user_id", id));
			obj.add(new BasicDBObject("user_pwd", pw));
			andQuery.put("$and", obj);
			//System.out.println(andQuery.toString());
			
			//DB��ȸ
			FindIterable<Document> cursor = documentMongoCollection.find(andQuery);
			i = cursor.iterator();
			
			while(i.hasNext())
			{
				String temp = i.next().toJson();
				System.out.println(temp);
				
				result = checkPermission(temp);
			}
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return result;
	}
	
	//���Ա�ȯ Ȯ��
	private boolean checkPermission(String temp) 
	{
		boolean result = false;
		try
	    {
			System.out.println("�α��α�ȯ Ȯ����...");
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject;
	         
	        jsonObject = (JSONObject) jsonParser.parse(temp);
	        result = (boolean)jsonObject.get("user_authority");
	        
	        System.out.println("dao: " + result);
	        if(result == true)
	        {
	            System.out.println("�α��� ��ȯ ����");
	        }
	        else if(result == false)
	        {
	        	System.out.println("�α��� ��ȯ����");
	        }
	         
	      } 
	      catch (ParseException e)
	      {
	         // TODO Auto-generated catch block
	    	  e.printStackTrace();
	    	  return false;
	      }
		
	      return result;
	 }

	//ȸ������
	public boolean insertDB(memberDTO dto)
	{
		boolean result = false;
		try 
		{
			Document doc = new Document()
			.append("user_id", dto.getId())
			.append("user_pwd", dto.getPw())
			.append("name", dto.getName())
			.append("nick", dto.getNick())
			.append("sex", dto.getSex())
			.append("user_email", dto.getEmail())
			.append("phone_number", dto.getPhone())
			.append("user_authority", false)
			//.append("address", address)
			.append("platform_link", dto.getPlatform_link())
			.append("self_imp", dto.getSelf_imp());
			
			documentMongoCollection.insertOne(doc);
			System.out.println("DB ���� ����");
			result = true;
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return result;
		
	}
	
	//������ȸ
	public boolean inpuiryAccount(String name, String email, String id, String mode) {
		try {
				BasicDBObject andQuery = new BasicDBObject();
				BasicDBObject and = new BasicDBObject("name",name);
				and.put("user_email", email);
				List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
				FindIterable<Document> i;
				Iterator<Document> it;
				
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
				{
					System.out.println("mode ���ڰ� �ַ�");
					return true;
				}
				
				// ���� ����
				andQuery.put("$and", and);
				
				// ������ ��ȸ �� ��ť��Ʈ ��ȯ
				i = documentMongoCollection.find(andQuery);
				it = i.iterator();
				
				//����� ����
				if(getAccount(mode, it))
					System.out.println("getAccount() ����!!!");
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		
		return false;
	}
	
	// ���� ã��
	public boolean getAccount(String mode, Iterator<Document> it) 
	{
		try
		{
			System.out.println("getAccount������6...");
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject;
			
			while(it.hasNext())
			{
				//System.out.println(it.next());
				jsonObject = (JSONObject) jsonParser.parse(it.next().toJson());
				System.out.print(mode+" : " + jsonObject.get("user_"+mode));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return false;
	}
	
	public memberDTO getPersonal_imp(String id, String pw)
	{
		BasicDBObject Query = new BasicDBObject();
		List<BasicDBObject> list = new ArrayList<BasicDBObject>();
		Iterator<Document> it;
		memberDTO dto = null;
		
		list.add(new BasicDBObject("user_id", id));
		list.add(new BasicDBObject("user_pwd", pw));
		
		
		
		System.out.println("��ȸ ���: " + list);
		Query.put("$and", list);
		
		it = documentMongoCollection.find(Query).iterator();
		
		while(it.hasNext())
		{
			String temp = it.next().toJson();
			System.out.println("Ȯ����\n" + temp + "\n==================");
			dto = parseImp(temp);
		}
		
		return dto;
	}
	
	public memberDTO parseImp(String temp)
	{
		memberDTO dto = new memberDTO();
		try
		{
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject;
			
			System.out.println("���������� �����ɴϴ�...");
			
			jsonObject = (JSONObject)jsonParser.parse(temp);
			
			dto.setId((String)jsonObject.get("user_id"));
			dto.setPw((String)jsonObject.get("user_pwd"));
			dto.setName((String)jsonObject.get("name"));
			dto.setNick((String)jsonObject.get("nick"));
			dto.setSex((String)jsonObject.get("sex"));
			dto.setEmail((String)jsonObject.get("user_email"));
			dto.setPhone((String)jsonObject.get("phone_number"));
			dto.setUser_authority((Boolean)jsonObject.get("user_authority"));
			dto.setPlatform_link((String)jsonObject.get("platform_link"));
			dto.setSelf_imp((String)jsonObject.get("self_imp"));
			System.out.println("dto_id: " + dto.getId());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
}
