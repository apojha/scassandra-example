package cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandra on 01/06/2014.
 */
public class SimpleCassandraClient {

    private Session session;

    public SimpleCassandraClient(String server, String keyspace){
        Cluster cluster = Cluster.builder()
                .addContactPoint(server)
                .build();
        this.session = cluster.connect(keyspace);

    }

    public SimpleCassandraClient(String server, String keyspace, int port){
        Cluster cluster = Cluster.builder()
                .addContactPoint(server)
                .withPort(port)
                .build();

        this.session = cluster.connect(keyspace);

    }

    public List<Cat> read(){

        List<Cat> cats = new ArrayList<Cat>();

        String cqlStatement = "SELECT * FROM cats";
        for (Row row : session.execute(cqlStatement)) {

            Cat kitty = new Cat(row.getUUID("id"), row.getString("name"), row.getString("nickname"));
            cats.add(kitty);

            System.out.println(kitty);
        }

        return cats;

    }


}
