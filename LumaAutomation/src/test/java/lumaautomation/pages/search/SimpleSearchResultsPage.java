package lumaautomation.pages.search;

import lumaautomation.engine.EngineHelperData;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import lumaautomation.pages.site.LumaBasePage;

import java.time.Duration;

public class SimpleSearchResultsPage extends LumaBasePage {
    public static final String PAGE_TITLE = "Search results for: '%s'";

    public static Target CONTENT_TITLE = Target.the("Content Title").located(By.xpath("//h1[contains(@class, \'page-title\')]")).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    public static Target SIDE_BAR_COMPARE = Target.the("Compare Products Block").locatedBy("//div[contains(@data-role, 'compare-products-sidebar')]");
    public static Target SIDE_BAR_WISHLIST = Target.the("Compare Products Block").locatedBy("//div[contains(@data-bind, 'wishlist')]");


    public static String getPageTitle(String inIncludeSearchTerms) {
        return String.format(PAGE_TITLE, inIncludeSearchTerms);
    }
}
