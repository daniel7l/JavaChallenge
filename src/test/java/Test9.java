import com.danhost.MainApplication;
import com.danhost.models.Dijkstra;
import com.danhost.entity.Vertice;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes={MainApplication.class})
public class Test9 extends SetupTest {

    @Test
    public void test9(){

        String result = "[B]";
        int a;

        setupTestGraph();

        Vertice start = g.getVertice("B");
        Vertice end = g.getVertice("B");

        Dijkstra d = new Dijkstra();
        Dijkstra.DijkstraPath list = d.findShortestPath(g,start,end);

        assertTrue(result.equals(list.getPath().toString()) && list.getDistance() == 0);
    }


}
