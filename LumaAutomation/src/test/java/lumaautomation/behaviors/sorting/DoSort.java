package lumaautomation.behaviors.sorting;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import lumaautomation.behaviors.utility.LogTest;
import lumaautomation.engine.EngineHelperData;
import lumaautomation.pages.search.SearchResultsSortWidget;

public class DoSort {

    public static Performable byProductName() {
        return Task.where("{0} Sorts By Product Name",
                LogTest.Log("Sorts by Product Name"),
                WaitUntil.the(SearchResultsSortWidget.SORT_WIDGET, WebElementStateMatchers.isCurrentlyEnabled()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                SelectFromOptions.byValue(SearchResultsSortWidget.SORT_VALUE_NAME).from(SearchResultsSortWidget.getSortSelect(1))
        );
    }

    public static Performable byProductPrice() {
        return Task.where("{0} Sorts By Product Price",
                LogTest.Log("Sorts by Product Price"),
                WaitUntil.the(SearchResultsSortWidget.SORT_WIDGET, WebElementStateMatchers.isCurrentlyEnabled()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                SelectFromOptions.byValue(SearchResultsSortWidget.SORT_VALUE_PRICE).from(SearchResultsSortWidget.getSortSelect(1))
        );
    }

    public static Performable invertSorting() {
        return Task.where("{0} Inverts the Sort Order",
                LogTest.Log("Inverts the Sort Order"),
                WaitUntil.the(SearchResultsSortWidget.SORT_WIDGET, WebElementStateMatchers.isCurrentlyEnabled()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Click.on(SearchResultsSortWidget.getSortInvert(1)).afterWaitingUntilEnabled()
        );
    }
}
