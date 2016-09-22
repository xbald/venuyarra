import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * Created by NIKOLAI on 21.09.2016.
 */
@ContextConfiguration(locations = "classpath:dao-context.xml")
public class BaseDaoTest extends AbstractTransactionalTestNGSpringContextTests {
}
