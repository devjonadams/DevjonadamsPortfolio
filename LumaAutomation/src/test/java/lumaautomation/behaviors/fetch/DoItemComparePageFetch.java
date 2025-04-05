package lumaautomation.behaviors.fetch;

import lumaautomation.engine.EngineHelperData;
import lumaautomation.pages.compare.LumaCompareItemsPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import java.time.Duration;

public class DoItemComparePageFetch {

    public static Question<Integer> forNumberOfProducts() {
        return actor -> BrowseTheWeb.as(actor).findAll(LumaCompareItemsPage.getProductsCompared().waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT))).size();
    }

    public static Question<String> forProductName(int inProductNumber) {
        return actor -> BrowseTheWeb.as(actor).find(LumaCompareItemsPage.getTableProductInfo(inProductNumber)
                        .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT)))
                .getText();
    }

    public static Question<String> forProductSKU(int inProductNumber) {
        return actor -> BrowseTheWeb.as(actor)
                .find(LumaCompareItemsPage.getTableData(LumaCompareItemsPage.idRowSKU, inProductNumber)
                        .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT)))
                .getText();
    }

    public static Question<String> forProductDescription(int inProductNumber) {
        return actor -> BrowseTheWeb.as(actor)
                .find(LumaCompareItemsPage.getTableData(LumaCompareItemsPage.idRowDescription, inProductNumber)
                        .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.SHORT_WAIT)))
                .getText();
    }
}
