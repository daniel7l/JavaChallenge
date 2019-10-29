import com.danhost.MainApplication;
import com.danhost.entity.Graph;
import com.danhost.models.Path;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(JUnit4.class)
@SpringBootTest(classes={MainApplication.class})
public class SetupTest extends TestCase {

    Graph g = new Graph();

    @BeforeAll
    public void setupTestGraph(){
        //Input graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");

        g.addAresta(g.getVertice("A"), g.getVertice("B"), 5);
        g.addAresta(g.getVertice("B"), g.getVertice("C"), 4);
        g.addAresta(g.getVertice("C"), g.getVertice("D"), 8);
        g.addAresta(g.getVertice("D"), g.getVertice("C"), 8);
        g.addAresta(g.getVertice("D"), g.getVertice("E"), 6);
        g.addAresta(g.getVertice("A"), g.getVertice("D"), 5);
        g.addAresta(g.getVertice("C"), g.getVertice("E"), 2);
        g.addAresta(g.getVertice("E"), g.getVertice("B"), 3);
        g.addAresta(g.getVertice("A"), g.getVertice("E"), 7);

    }

    public Boolean comparePaths(Path a, Path b)
    {
        Boolean foundEqual;

        if(a.routes.size() != b.routes.size())
            return false;

        for(int i = 0 ; i < a.routes.size(); i++)
        {
            foundEqual = false;
            for(int j = 0; j < b.routes.size(); j++)
            {
                if(a.routes.get(i).route.equals(b.routes.get(j).route))
                    foundEqual = true;
            }
            if(!foundEqual)
                return false;
        }
        return true;
    }

    @Test
    public void test(){
    }
}
