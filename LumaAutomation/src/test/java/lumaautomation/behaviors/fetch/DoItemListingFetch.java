package lumaautomation.behaviors.fetch;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.actions.Scroll;
import lumaautomation.pages.itemlistings.ItemListingWidget;

public class DoItemListingFetch {

    public static Question<Integer> forNumberOfProducts() {
        return actor -> BrowseTheWeb.as(actor).findAll(ItemListingWidget.PRODUCT_NAME_BLOCK).size();
    }

    public static Question<String> forProductName(int inNthChild) {
        return actor -> BrowseTheWeb.as(actor).find(ItemListingWidget.getProductName(inNthChild)).getText();
    }

    public static Question<String> forProductPrice(int inNthChild) {
        return actor -> BrowseTheWeb.as(actor).find(ItemListingWidget.getProductPrice(inNthChild)).getText().replaceAll("\\$", "");
    }

    public static Performable clickAddToCompare(int inNthChild) {
        return Task.where("{0} Clicks on Add to Compare Button",
                Scroll.to(ItemListingWidget.getProductName(inNthChild)),
                MoveMouse.to(ItemListingWidget.getProductName(inNthChild)),
                Click.on(ItemListingWidget.getProductAddToCompareButton(inNthChild)).afterWaitingUntilEnabled()
        );
    }
}
