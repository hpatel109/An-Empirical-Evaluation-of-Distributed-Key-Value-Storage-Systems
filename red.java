//package mypack;
import java.util.List;	
import java.util.Random;

import redis.clients.jedis.Jedis; 

public class red {
	
		   public static void main(String[] args) { 
			   Jedis jedis = new Jedis("localhost",6379); 
			      System.out.println("Connection to server sucessfully"); 
			      //set the data in redis string 
			      try {
			    //  jedis.set("tutorial-name", "Redis tutorial"); 
			    	  System.out.println("100K PUT operations...");
			  		String key, value;
			  		int random = new Random().nextInt(10)+1;
			  		int min = (random)*100000-100000;
			  		int max = (random)*100000-1;
			  		long start = System.currentTimeMillis();
			  		System.out.println("here");
			  		// JedisCluster jc = new JedisCluster(jedisClusterNodes);
			  		 System.out.println("ok");
			  		for(int i=min ; i<=max ; i++)
			  		{
			  			key = String.format("%10s", String.valueOf(i)).replace(" ", "*");
			  			value = String.format("%90s", "randomStringForKey-"+i+"-").replace(" ", "*");
			  		    jedis.lpush(key, value);
			  		
			  		}
			  		
			  		long end = System.currentTimeMillis();
					double diff = (end - start);

					System.out.println("100K PUT completed in " + diff/1000 + " seconds.");
					
					
					
					
					
					System.out.println("100K GET operations...");
			  		
			  		 random = new Random().nextInt(10)+1;
			  		 min = (random)*100000-100000;
			  		 max = (random)*100000-1;
			  		 start = System.currentTimeMillis();
			  		//System.out.println("here");
			  		// JedisCluster jc = new JedisCluster(jedisClusterNodes);
			  		 //System.out.println("ok");
			  		for(int i=min ; i<=max ; i++)
			  		{
			  			key = String.format("%10s", String.valueOf(i)).replace(" ", "*");
			  			//value = String.format("%90s", "randomStringForKey-"+i+"-").replace(" ", "*");
			  		    jedis.lpop(key);
			  		   // jedis.del(key);
			  		
			  		}
			  		
			  		 end = System.currentTimeMillis();
					 diff = (end - start);

					System.out.println("100K GET completed in " + diff/1000 + " seconds.");
					
					
					
					
					
					System.out.println("100K DEL operations...");
			  		
			  		 random = new Random().nextInt(10)+1;
			  		 min = (random)*100000-100000;
			  		 max = (random)*100000-1;
			  		 start = System.currentTimeMillis();
			  		//System.out.println("here");
			  		// JedisCluster jc = new JedisCluster(jedisClusterNodes);
			  		 //System.out.println("ok");
			  		for(int i=min ; i<=max ; i++)
			  		{
			  			key = String.format("%10s", String.valueOf(i)).replace(" ", "*");
			  			//value = String.format("%90s", "randomStringForKey-"+i+"-").replace(" ", "*");
			  		   
			  		    jedis.del(key);
			  		
			  		}
			  		
			  		 end = System.currentTimeMillis();
					 diff = (end - start);

					System.out.println("100K DEL completed in " + diff/1000 + " seconds.");
					
					
					
					
					
			   /*  // jedis.lpush("tutorial-list", "Redis");
			//	jedis.lpush("tutorial-list", "Mongodb");
			//		jedis.lpush("tutorial-list", "Mysql");
					// Get the stored data and print it
					List<String> list = jedis.lrange("tutorial-list", 0, 5);
					for (int i = 0; i < list.size(); i++) {
						System.out.println("Stored string in redis:: " + list.get(i));}  */
					
			      }
			      catch(Exception e) {
			    	  e.printStackTrace();
			      }
					System.out.println("It's ready to work.");
			      
			      // Get the stored data and print it 
			   //   System.out.println("Stored string in redis:: "+ jedis.get("tutorialname")); 
			   
		   } 
		}
