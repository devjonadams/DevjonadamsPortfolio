package lumaautomation.testsuite;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.annotations.CastMember;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@ExtendWith(SerenityJUnit5Extension.class)
public abstract class BaseTestCase {

    protected static final Level defaultLogLevel = Level.INFO;

    @CastMember(name = "The User")
    protected Actor user;

    @Managed()
    protected WebDriver driver;

    @BeforeAll
    public static void classSetUp() {
        Logger logger = Logger.getLogger("");
        logger.setLevel(defaultLogLevel);
        Arrays.stream(logger.getHandlers()).forEach(handler -> {
            handler.setLevel(defaultLogLevel);
        });
    }


}
