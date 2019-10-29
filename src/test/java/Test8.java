import com.danhost.MainApplication;
import com.danhost.models.Dijkstra;
import com.danhost.entity.Vertex;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes={MainApplication.class})
public class Test8 extends SetupTest {

    @Test
    public void test8(){

        String result = "[A, B, C]";
        int a;

        setupTestGraph();

        Vertex start = g.getVertice("A");
        Vertex end = g.getVertice("C");

        Dijkstra d = new Dijkstra();
        Dijkstra.DijkstraPath list = d.findShortestPath(g,start,end);

        assertTrue(result.equals(list.getPath().toString()) && list.getDistance() == 9);
    }


}
