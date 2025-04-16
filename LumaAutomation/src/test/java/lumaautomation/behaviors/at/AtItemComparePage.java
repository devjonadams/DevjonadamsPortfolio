package lumaautomation.behaviors.at;

import lumaautomation.engine.EngineHelperData;
import lumaautomation.pages.compare.LumaCompareItemsPage;
import net.serenitybdd.annotations.Description;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import java.time.Duration;

/***
 * Behavior for the Item Compare page.
 * This page is reached when the User clicks the "Compare" button from a Compare Items List widget.
 */
@Description("Behavior for the Item Comparison Page.")
public class AtItemComparePage {

    /***
     * Gets the number of products displayed in the table on the Compare Items page.
     * @return The number of products in the table as an int.
     */
    @Step("{0} retrieves the number of products on the Item Comparison Page.")
    public static Question<Integer> forNumberOfProducts() {
        return actor -> BrowseTheWeb.as(actor)
                .findAll(LumaCompareItemsPage.getProductsCompared()
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT))).size();
    }

    /***
     * Gets the Product Name from the Compare Items Product Table for the Nth product.
     * @param inNthProduct the Product in the Compare Items table (note: from left to right).
     * @return Unmodified String of the product name text.
     */
    @Step("{0} retrieves the name of the {1} product on the Item Comparison Page.")
    public static Question<String> forProductName(int inNthProduct) {
        return actor -> BrowseTheWeb.as(actor)
                .find(LumaCompareItemsPage.getTableProductInfo(inNthProduct)
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT)))
                .getText();
    }

    /***
     * Gets the Product Sku from the Compare Items Product Table for the Nth Product.
     * @param inNthProduct the Product in the Compare Items table (note: from left to right).
     * @return Unmodified String of the product sku text.
     */
    @Step("{0} retrieves the SKU of the {1} product on the Item Comparison Page.")
    public static Question<String> forProductSKU(int inNthProduct) {
        return actor -> BrowseTheWeb.as(actor)
            .find(LumaCompareItemsPage.getTableData(LumaCompareItemsPage.idRowSKU, inNthProduct)
            .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT)))
            .getText();
    }

    /***
     * Gets the Product Description from the Compare Items Product Table for the Nth Product.
     * @param inNthProduct the Product in the Compare Items table (note: from left to right).
     * @return Unmodified String of the product description text.
     */
    @Step("{0} retrieves the description of the {1} product on the Item Comparison Page.")
    public static Question<String> forProductDescription(int inNthProduct) {
        return actor -> BrowseTheWeb.as(actor)
            .find(LumaCompareItemsPage.getTableData(LumaCompareItemsPage.idRowDescription, inNthProduct)
            .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT)))
            .getText();
    }
}
