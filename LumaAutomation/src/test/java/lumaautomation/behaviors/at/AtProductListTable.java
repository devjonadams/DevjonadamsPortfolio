package lumaautomation.behaviors.at;

import lumaautomation.behaviors.utility.LogTest;
import net.serenitybdd.annotations.Description;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.actions.Scroll;
import lumaautomation.pages.itemlistings.ItemListingWidget;

/***
 * The Table of Products, typically displayed on a product category or search results page.
 */
@Description("Behavior for the Product List Table.")
public class AtProductListTable {

    /***
     * Fetches the number of products in the Listed Products table widget.
     * Note that this only fetches the set of products on the currently displayed page.
     * @return Int as Number of Products in the table.
     */
    @Step("{0} retrieves the number of products in the Product List Table.")
    public static Question<Integer> forNumberOfProducts() {
        return actor -> BrowseTheWeb.as(actor)
                .findAll(ItemListingWidget.getTargetProductNameBlock())
                .size();
    }

    /***
     * Fetches the product name text from the Nth product.
     * @param inNthChild the Nth product to get.
     * @return Unformatted string of the product name text.
     */
    @Step("{0} retrieves the name of the {1} product in the Product List Table.")
    public static Question<String> forProductName(int inNthChild) {
        return actor -> BrowseTheWeb.as(actor)
                .find(ItemListingWidget.getTargetProductName(inNthChild))
                .getText();
    }

    /***
     * Fetches the product price text from the Nth product.
     * @param inNthChild the Nth product to get.
     * @return String of the product name text. Dollar symbol is removed.
     */
    @Step("{0} retrieves the price of the {1} product in the Product List Table.")
    public static Question<String> forProductPrice(int inNthChild) {
        return actor -> BrowseTheWeb.as(actor)
                .find(ItemListingWidget.getTargetProductPrice(inNthChild))
                .getText()
                .replaceAll("\\$", "");
    }

    /***
     * Clicks the Nth Product's Add to Compare Items List Button.
     * @param inNthChild the Nth product to get.
     * @return Performable.
     */
    @Step("{0} hover on the {1} product in and click the product's 'Add to Compare' button.")
    public static Performable clickAddToCompare(int inNthChild) {
        return Task.where(
                LogTest.Log(String.format("hover on the %d product in and click the product's 'Add to Compare' button.", inNthChild)),
                Scroll.to(ItemListingWidget.getTargetProductName(inNthChild)),
                MoveMouse.to(ItemListingWidget.getTargetProductName(inNthChild)),
                Click.on(ItemListingWidget.getTargetProductAddToCompareButton(inNthChild)).afterWaitingUntilEnabled()
        );
    }
}
