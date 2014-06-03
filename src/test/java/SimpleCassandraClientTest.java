import cassandra.Cat;
import cassandra.SimpleCassandraClient;
import com.google.common.collect.ImmutableMap;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.scassandra.Scassandra;
import org.scassandra.ScassandraFactory;
import org.scassandra.http.client.ActivityClient;
import org.scassandra.http.client.ColumnTypes;
import org.scassandra.http.client.PrimingClient;
import org.scassandra.http.client.PrimingRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;

/**
 * Created by alexandra on 03/06/2014.
 */
public class SimpleCassandraClientTest {

    private static final String HOST = "localhost";
    private static final String KEYSPACE = "pets";

    private static PrimingClient primingClient;
    private static ActivityClient activityClient;
    private static Scassandra scassandra;

    private SimpleCassandraClient simpleCassandraClient;


    @BeforeClass
    public static void startScassandraServer() throws Exception {
        scassandra = ScassandraFactory.createServer();
        scassandra.start();
        primingClient = scassandra.primingClient();
        activityClient = scassandra.activityClient();
    }

    @Before
    public void setup(){

        this.simpleCassandraClient = new SimpleCassandraClient(HOST, KEYSPACE, scassandra.getBinaryPort());

        primingClient.clearAllPrimes();
        activityClient.clearAllRecordedActivity();
    }

    @AfterClass
    public static void shutdown() {
        scassandra.stop();
    }

    @Test
    public void testReturnOneCat(){

        // given

        UUID id = UUID.randomUUID();
        Map<String, ? extends  Object> row = ImmutableMap.of(
                "id", id,
                "name", "Alexandra",
                "nickname", "Puff");

        Map<String, ColumnTypes> columnTypes = ImmutableMap.of(
                "id", ColumnTypes.Uuid
        );
        PrimingRequest singleRowPrime = PrimingRequest.queryBuilder()
                .withQuery("SELECT * FROM cats")
                .withRows(row)
                .withColumnTypes(columnTypes)
                .build();

        primingClient.primeQuery(singleRowPrime);

        Cat cat = new Cat(id, "Alexandra", "Puff");

        // when
        List<Cat> cats = this.simpleCassandraClient.read();

        // then
        assertEquals(1, cats.size());
        assertEquals(cat, cats.get(0));

    }

}
