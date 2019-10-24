import com.danhost.MainApplication;
import com.danhost.models.PathDistance;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes={MainApplication.class})
public class Test5 extends SetupTest {

    @Test
    public void test5(){

        setupTestGraph();
        PathDistance pd = new PathDistance();
        pd.path = new String[]{"A","E","D"};

        assertEquals(pd.findPathDistace(g).getDistance(), -1);
    }


}
