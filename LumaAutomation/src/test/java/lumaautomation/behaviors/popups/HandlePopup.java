package lumaautomation.behaviors.popups;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.HtmlAlert;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opentest4j.TestAbortedException;
import lumaautomation.engine.EngineHelperData;
import lumaautomation.pages.site.LumaBasePage;

import java.time.Duration;
import java.util.*;

import static net.serenitybdd.screenplay.Actor.ErrorHandlingMode.IGNORE_EXCEPTIONS;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public abstract class HandlePopup {

}
