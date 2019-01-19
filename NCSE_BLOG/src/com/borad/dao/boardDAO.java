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
			System.out.println("�ν��Ͻ� ���� ����");
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
	
	// ��ť��Ʈ �Ľ�
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
			System.out.println(cate + " �迭: " + array);
			String bid = jsonObject.get("_id").toString();
			System.out.println(bid);
			dto.setbId(bid.substring(9, bid.length() - 2));
			
			String tt = array.toString();
			tt = tt.substring(1, tt.length()-1);
			
			//�迭�� �����Ǿ��ִ� �۵��� �Ľ��ϱ����� ���� �Ľ�����
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
	
	
	// ī�װ��� �۸�� ��������
		public ArrayList<boardDTO> list(String category)
		{
			ArrayList<boardDTO> dtos = new ArrayList<boardDTO>();
			BasicDBObject cateQ = new BasicDBObject(category, new BasicDBObject("$exists", true)); //false: �� ������ ���������, ture: �� �����ϴ� �͵��� ���
			MongoCursor<Document> it = documentMongoCollection.find(cateQ).iterator();
			
			while(it.hasNext())
			{
				String temp = it.next().toJson();
				dtos.add(parseList(temp, category, false));
			}
			
			return dtos;
			
		}
		
		
	//�ۺ���(���Ŀ� json �迭 �ļ��� ���� �Լ��� ������ ��� ���� list �Լ����Բ�)
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
	
	//�۾���
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
	
	//�˻���� ��������
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
	
