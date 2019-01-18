import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwdTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(PwdTest.class);

    @Test
    public void encode(){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");

        LOGGER.info(encode);
    }
}
