package lumaautomation.pages.search;

import lumaautomation.engine.EngineHelperData;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import lumaautomation.pages.site.LumaBaseComponent;

import java.time.Duration;

public class SearchResultsSortWidget extends LumaBaseComponent {
    public static Target SORT_WIDGET = Target.the("Sort Widget").located(By.xpath("//div[contains(@class, \'toolbar-sorter sorter\')]")).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    public static Target SELECT_WRAPPER = Target.the("Sort Select").located(By.xpath("//select[contains(@data-role, \'sorter\')]")).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    public static Target SORT_INVERT = Target.the("Sort Invert Button").located(By.xpath("//a[contains(@data-role, \'direction-switcher\')]")).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    public static String SORT_VALUE_NAME = "name";
    public static String SORT_VALUE_PRICE = "price";
    public static String SORT_VALUE_RELEVANCE = "relevance";

    public static Target getSortWidget() {
        return Target.the("Sort Widget").located(By.xpath("//div[contains(@class, \'toolbar-sorter sorter\')]")).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getSortSelect(int inNthChild) {
        return SELECT_WRAPPER.inside(SORT_WIDGET);
    }

    public static Target getSortInvert(int inNthChild) {
        return SORT_INVERT.inside(SORT_WIDGET);
    }
}
