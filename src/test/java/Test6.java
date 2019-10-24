import com.danhost.MainApplication;
        import com.danhost.models.Path;
        import org.junit.Test;
        import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes={MainApplication.class})
public class Test6 extends SetupTest {

    @Test
    public void test6(){

        setupTestGraph();

        Path pathCreated = createResultPath();

        Path pathGenerated = new Path();
        pathGenerated.traceRoutes( g.getVertice("C"), g.getVertice("C"), g, 3L);

        assertTrue(comparePaths(pathCreated, pathGenerated));
    }

    public Path createResultPath()
    {
        Path pathCreated = new Path();

        pathCreated.routes.add(new Path.Route("CDC", 2));
        pathCreated.routes.add(new Path.Route("CEBC", 3));

        return pathCreated;
    }
}
