import com.danhost.MainApplication;
import com.danhost.models.Path;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes={MainApplication.class})
public class Test7 extends SetupTest {

    @Test
    public void test7(){

        setupTestGraph();

        Path pathCreated = createResultPath();

        Path pathGenerated = new Path();
        pathGenerated.traceRoutes( g.getVertice("A"), g.getVertice("C"), g, 4L);

        assertTrue(comparePaths(pathCreated, pathGenerated));
    }

    public Path createResultPath()
    {
        Path pathCreated = new Path();

        pathCreated.routes.add(new Path.Route("ABC", 2));
        pathCreated.routes.add(new Path.Route("ADC", 2));
        pathCreated.routes.add(new Path.Route("AEBC", 3));
        pathCreated.routes.add(new Path.Route("ADEBC", 4));

        return pathCreated;
    }
}
