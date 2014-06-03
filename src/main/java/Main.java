import cassandra.SimpleCassandraClient;

/**
 * Created by alexandra on 01/06/2014.
 */
public class Main {

    private static String host = "localhost";
    private static String keyspace = "pets";

    public static void main(String[] args){
        SimpleCassandraClient client = new SimpleCassandraClient(host, keyspace);
        client.read();
    }
}
