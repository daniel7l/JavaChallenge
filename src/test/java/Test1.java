import com.danhost.MainApplication;
import com.danhost.models.PathDistance;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes={MainApplication.class})
public class Test1 extends SetupTest {

    @Test
    public void test1(){

        setupTestGraph();
        PathDistance pd = new PathDistance();
        pd.path = new String[]{"A","B","C"};

        assertEquals(pd.findPathDistace(g).getDistance(), 9);
    }


}
