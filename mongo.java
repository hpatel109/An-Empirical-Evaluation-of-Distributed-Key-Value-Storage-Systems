package mypack;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class mongo 
{

private static DBCollection table[];
private static int total;

public static void main(String[] args) {
		try{
		/*	Properties config = new Properties();
			InputStream input=null;
			try
			{
				input=new FileInputStream("/Users/canny/Desktop/config.properties");
				config.load(input);
				*/
			
			// To connect to mongodb
			total=Integer.parseInt("2");
			MongoClient mongoClient[] =  new MongoClient[Integer.parseInt("1")];
			for(int i=1 ; i<=Integer.parseInt("1") ; i++){
				mongoClient[i-1] =  new MongoClient( "127.0.0.1", 27017);
				//mongoClient[1] =  new MongoClient( "127.0.0.1", 27017);
			}// config.getProperty(i+"_IP")
			DB db[] = new DB[Integer.parseInt("1")];
			for(Integer i=1;i<=Integer.parseInt("1");i++)
			{
				db[i-1]= mongoClient[i-1].getDB("AwsMongo");
			}

		  table =new DBCollection[Integer.parseInt("1")];
			for(Integer i=1;i<=Integer.parseInt("1");i++)
			{
				table[i-1]=db[i-1].getCollection("user");
			}
			}
		
		catch(Exception e)
		{
			System.out.print("error--");
		}
			
			
			put();
			get();
		//	del();
			
		}
	

	private static void put() {

		System.out.println("100K PUT operations.");
		String key, value;
		int hashcode;
		int random = new Random().nextInt(10)+1;
		int min = (random)*100000-100000;
		int max = (random)*100000-1;
		long start = System.currentTimeMillis();
		
		for(Integer i=min ; i<=max ; i++){
			key = String.format("%10s", String.valueOf(i)).replace(" ", "*");
			value = String.format("%90s", "randomStringForKey-"+i+"-").replace(" ", "*");
			hashcode=hashFunction(i.toString());
			
			BasicDBObject document = new BasicDBObject();
			document.put(key, value);
			System.out.println(i);
			table[hashcode].insert(document);
			
		}
		long end = System.currentTimeMillis();
		double diff = (end - start);

		System.out.println("100K PUT operations completed in " + diff/1000 + " seconds.");
	}

	private static void get() {
		String key, value;
		int min,max;
		int hashcode;
		System.out.println("100K GET operations.");
		int random = new Random().nextInt(10)+1;
		min = (random)*100000-100000;
		max = (random)*100000-1;
		
		long start = System.currentTimeMillis();

		for(Integer i=min ; i<=max ; i++){
			key = String.format("%10s", String.valueOf(i)).replace(" ", "*");
			value = String.format("%90s", "randomStringForKey-"+i+"-").replace(" ", "*");
			hashcode=hashFunction(i.toString());
			
			BasicDBObject document = new BasicDBObject();
			document.get(key);
		DBCursor cur = table[hashcode].find(document);
			cur.next();
		}
		
	long 	end = System.currentTimeMillis();
	long 	diff = (end - start);
	
		System.out.println("100K GET operations completed in " + diff/1000 + " seconds.");
	}
	private static int hashFunction(String strHash)
	{
		/*int n=0;
		n=i% total;
		return n;*/
		
			int h = 0;
			char ch[] = strHash.toCharArray();
			for(int i = strHash.length() - 1; i>=0; i--) {
				h = (ch[i] + 128*h) % 2;
			}
			
			return h;
		}
	
/*	private static void del() {
		String key, value;
		int min,max;
		int hashcode;
		System.out.println("100K GET operations.");
		int random = new Random().nextInt(10)+1;
		min = (random)*100000-100000;
		max = (random)*100000-1;
		
		long start = System.currentTimeMillis();

		for(int i=min ; i<=max ; i++){
			key = String.format("%10s", String.valueOf(i)).replace(" ", "*");
			value = String.format("%90s", "randomStringForKey-"+i+"-").replace(" ", "*");
			hashcode=hashFunction(i);
			
			BasicDBObject document = new BasicDBObject();
			document.put(key,value);
			System.out.println(" "+i);
			table[hashcode].findAndRemove(document);
		}
		long end = System.currentTimeMillis();
		long diff = (end - start);

		System.out.println("100K DEL operations completed in " + diff/1000 + " seconds.");			
	}*/
}
