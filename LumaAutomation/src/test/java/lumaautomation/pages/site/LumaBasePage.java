package lumaautomation.pages.site;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public abstract class LumaBasePage extends PageObject {
    public static final String LUMA_PAGE_WARPPER = "page-wrapper";

    public static Target usingPageWrapper() {
        return Target.the("Page Wrapper Div").located(By.className(LUMA_PAGE_WARPPER));
    }
}
