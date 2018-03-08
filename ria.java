//package mypack;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;

import java.net.UnknownHostException;

public class ria{
	
	private static RiakCluster setUpCluster() throws UnknownHostException{
		// This example will use only one node listening on localhost:8098--default config
        
		RiakNode node = new RiakNode.Builder()
                .withRemoteAddress("127.0.0.1")
                .withRemotePort(8098)	
                .build();
     // This cluster object takes our one node as an argument
        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();

        // The cluster must be started to work, otherwise you will see errors
        cluster.start();

        return cluster;
	}
	
	private static class RiakFile{
		
		public String filename;
		public byte[] byteData;
	}
	
	public static void saveFile(String filename,byte[] byteData)
	{
	try{
	    System.out.println("Inside Riak handler");
	    RiakCluster cluster = setUpCluster();
            RiakClient client = new RiakClient(cluster);
            System.out.println("done");
            RiakFile newFile = createRiakFile(filename, byteData); 
            System.out.println("Riak file created");
            Namespace fileBucket = new Namespace("files");
            Location fileLocation = new Location(fileBucket, filename);
            StoreValue storeFile = new StoreValue.Builder(newFile).withLocation(fileLocation).build();
            client.execute(storeFile);  
            System.out.println("File saved to riak ");
            cluster.shutdown();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static RiakFile createRiakFile(String filename, byte[] byteData)
	{
		RiakFile file=new RiakFile();
		file.filename=filename;
		file.byteData=byteData;
		return file;
				
	}
	
	
	

}
