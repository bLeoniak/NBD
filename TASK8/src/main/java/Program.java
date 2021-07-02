import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.UpdateValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public class Program {
    public static  void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        RiakCluster cluster = setUpCluster();

        RiakClient client = new RiakClient(cluster);
        Person person = new Person("Bartłomiej", "Leoniak", 23, "male");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(person);


        //add object
        RiakObject quoteObject = new RiakObject()
                .setContentType("application/json")
                .setValue(BinaryValue.create(json));
        Namespace quotesBucket = new Namespace("s23904");
        Location quoteObjectLocation = new Location(quotesBucket, "6");
        StoreValue storeOp = new StoreValue.Builder(quoteObject)
                .withLocation(quoteObjectLocation)
                .build();

        StoreValue.Response response = client.execute(storeOp);
        System.out.println("object created");

        //read object
        FetchValue fetchOp = new FetchValue.Builder(quoteObjectLocation).build();
        RiakObject fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        assert(fetchedObject.getValue().equals(quoteObject.getValue()));
        System.out.println("Read object: " + fetchedObject.getValue());

        //update object
        person.age = 24;
        json = ow.writeValueAsString(person);
        fetchedObject.setValue(BinaryValue.create(json));

        StoreValue updateOp = new StoreValue.Builder(fetchedObject)
                .withLocation(quoteObjectLocation)
                .build();
        StoreValue.Response updateOpResp = client.execute(updateOp);
        System.out.println("object updated");

        //read object again
        fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        assert(fetchedObject.getValue().equals(quoteObject.getValue()));

        System.out.println("Im getting older :" +  fetchedObject.getValue());

        //delete object
        DeleteValue deleteOp = new DeleteValue.Builder(quoteObjectLocation).build();
        client.execute(deleteOp);
        System.out.println("Object deleted");

        //read object
        fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        assert(fetchedObject.getValue().equals(quoteObject.getValue()));

        if(fetchedObject == null){
            System.out.println("Im deleted");
        }else{
            System.out.println("Im not here :" +  fetchedObject.toString());
        }


    }



    private static RiakCluster setUpCluster() throws UnknownHostException {
        // This example will use only one node listening on localhost:10017
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress("localhost")
                .withRemotePort(8087)
                .build();

        // This cluster object takes our one node as an argument
        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();

        // The cluster must be started to work, otherwise you will see errors
        cluster.start();

        return cluster;
    }
}


