import com.danhost.models.PathDistance;
import org.junit.Test;


public class Test3 extends SetupTest {

    @Test
    public void test3(){

        setupTestGraph();
        PathDistance pd = new PathDistance();
        pd.path = new String[]{"A","D","C"};

        assertEquals(pd.findPathDistace(g).getDistance(), 13);
    }
}