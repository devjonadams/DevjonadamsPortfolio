package lumaautomation.pages.site;

import lumaautomation.engine.EngineHelperData;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.PageElement;

import java.time.Duration;

public abstract class LumaBasePage extends PageObject {
    public static final String LUMA_PAGE_WARPPER = "";

    protected static String getMainContentPath() {
        return "//main[@id='maincontent']";
    }

    public static Target getTargetMainContent() {
        return Target.the("Main Content Block")
                .located(By.xpath(getMainContentPath()))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.LONG_WAIT));
    }

    protected static String getPageWrapperPath() {
        return "//div[@class='page-wrapper']";
    }

    public static Target getTargetPageWrapper() {
        return Target.the("Page Wrapper Div")
                .located(By.xpath(getPageWrapperPath()))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.LONG_WAIT));
    }
}
