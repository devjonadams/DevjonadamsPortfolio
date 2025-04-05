package lumaautomation.behaviors.fetch;

import lumaautomation.engine.EngineHelperData;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import lumaautomation.pages.compare.CompareItemsWidget;

import java.time.Duration;

public class DoItemCompareWidgetFetch {

    public static Question<Integer> forNumberOfProducts() {
        return actor -> BrowseTheWeb.as(actor).findAll(CompareItemsWidget.getCompareItemProduct().waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT))).size();
    }

    public static Question<String> forProductName(int inNthChild) {
        return actor -> BrowseTheWeb.as(actor).find(CompareItemsWidget.getCompareItemProductName(inNthChild)).getText();
    }

    public static Performable clickRemove(int inNthChild) {
        return Task.where("{0} Clicks Remove From Compare List",
                Scroll.to(CompareItemsWidget.getCompareItemProductName(inNthChild)),
                Click.on(CompareItemsWidget.getCompareItemDeleteButton(inNthChild)).afterWaitingUntilEnabled()
        );
    }

    public static Performable clickClearAll() {
        return clickClearAll(true);
    }

    public static Performable clickClearAll(boolean andConfirm) {
        return Task.where("{0} Clicks Clear Entire Compare List",
                Scroll.to(CompareItemsWidget.COMPARE_ITEMS_LIST),
                Click.on(CompareItemsWidget.getCompareWidgetClearButton()).afterWaitingUntilEnabled()
        );
    }

    public static Performable clickCompare() {
        return Task.where("{0} Clicks Compare Button",
                Scroll.to(CompareItemsWidget.COMPARE_ITEMS_LIST),
                Click.on(CompareItemsWidget.getCompareWidgetCompareButton()).afterWaitingUntilEnabled()
        );
    }
}
