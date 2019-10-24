import com.danhost.models.PathDistance;
import org.junit.Test;


public class Test2 extends SetupTest {

    @Test
    public void test2(){

        setupTestGraph();
        PathDistance pd = new PathDistance();
        pd.path = new String[]{"A","D"};

        assertEquals(pd.findPathDistace(g).getDistance(), 5);
    }
}
