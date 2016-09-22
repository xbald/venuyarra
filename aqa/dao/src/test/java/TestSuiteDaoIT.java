import com.venuyarra.aqa.TestSuiteDao;
import com.venuyarra.aqa.dto.TestSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

/**
 * Created by NIKOLAI on 21.09.2016.
 */

@Rollback
public class TestSuiteDaoIT extends BaseDaoTest {

    @Autowired
    private TestSuiteDao testSuiteDao;

    @Test
    @Rollback
    public void testSuiteGetting() {
        final TestSuite testSuiteById = testSuiteDao.getTestSuiteById(27L);

        StringWriter sw = new StringWriter();
        JAXB.marshal(testSuiteById, sw);
        System.out.println(sw.toString());
    }

}
