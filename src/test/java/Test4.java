import com.danhost.models.PathDistance;
import org.junit.Test;


public class Test4  extends SetupTest {

    @Test
    public void test4(){

        setupTestGraph();
        PathDistance pd = new PathDistance();
        pd.path = new String[]{"A","E","B","C","D"};

        assertEquals(pd.findPathDistace(g).getDistance(), 22);
    }
}