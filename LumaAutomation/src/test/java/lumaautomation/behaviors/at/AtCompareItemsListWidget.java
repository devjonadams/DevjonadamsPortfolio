package lumaautomation.behaviors.at;

import lumaautomation.behaviors.utility.LogTest;
import lumaautomation.engine.EngineHelperData;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import lumaautomation.pages.compare.CompareItemsWidget;

import java.time.Duration;

public class AtCompareItemsListWidget {

    /***
     * Provides the number of products that are saved to the Item Compare Widget's List.
     * @return Integer.
     */
    @Step("{0} retrieves the number of products saved to the Compare Items List Widget.")
    public static Question<Integer> forNumberOfProducts() {
        return actor -> BrowseTheWeb.as(actor)
            .findAll(CompareItemsWidget.getCompareItemProduct()
            .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT))).size();
    }

    /***
     * Removes the Nth item from the Item Compare Widget's List of Items to Compare
     * @param inNthChild the Nth Item in the Compare Item List.
     * @return Performable
     */
    @Step("{0} clicks the remove from compare list button on the {1} item in the Compare Items List Widget.")
    public static Performable clickRemove(int inNthChild) {
        String logText = String.format("clicks the remove from compare list button on the %d item.", inNthChild);
        return Task.where(
            LogTest.Log(logText),
            Scroll.to(CompareItemsWidget.getCompareItemProductName(inNthChild)),
            Click.on(CompareItemsWidget.getCompareItemDeleteButton(inNthChild)).afterWaitingUntilEnabled()
        );
    }

    @Step("{0} clicks the 'Clear' button in the Compare Items List Widget.")
    public static Performable clickClearAll() {
        return Task.where(
                LogTest.Log("clicks the 'Clear' button in the Compare Items List Widget"),
                Scroll.to(CompareItemsWidget.COMPARE_ITEMS_LIST),
                Click.on(CompareItemsWidget.getCompareWidgetClearButton()).afterWaitingUntilEnabled()
        );
    }

    @Step("{0} clicks the 'Compare' button in the Compare Items List Widget.")
    public static Performable clickCompare() {
        return Task.where(
                LogTest.Log("clicks the 'Compare' button in the Compare Items List Widget."),
                Scroll.to(CompareItemsWidget.COMPARE_ITEMS_LIST),
                Click.on(CompareItemsWidget.getCompareWidgetCompareButton()).afterWaitingUntilEnabled()
        );
    }
}
