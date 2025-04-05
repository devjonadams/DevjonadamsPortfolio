package lumaautomation.pages.itemlistings;

import lumaautomation.engine.EngineHelperData;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import lumaautomation.pages.site.LumaBaseComponent;

import java.time.Duration;

public class ItemListingWidget extends LumaBaseComponent {
    public static Target PRODUCT_WIDGET = Target.the("{0} Product Item Widget").located(By.xpath("//li[{0}][contains(@class, \'item product product-item\')]")).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    public static Target PRODUCT_NAME_BLOCK = Target.the("Product Name Block").located(By.xpath("//strong[contains(@class, \'product name product-item-name\')]")).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    public static Target PRODUCT_PRICE_BLOCK = Target.the("Product Price Block").locatedBy("//div[contains(@data-role, 'priceBox')]").waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    public static Target PRODUCT_PRICE = Target.the("Product Price").locatedBy("/span[contains(@data-price-type, 'finalPrice')]").waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));

    public static Target getNthProduct(int inForNthProduct) {
        String xpathBuilder = String.format("//li[%d][contains(@class, \'item product product-item\')]", inForNthProduct);
        return Target.the("{0} Product Item Widget").located(By.xpath(xpathBuilder)).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getProductName(int inForNthProduct) {
        String xpathBuilder = String.format("//li[%d][contains(@class, 'item product product-item')]//strong[contains(@class, \'product name product-item-name\')]", inForNthProduct);
        return Target.the(String.format("%d Product Item Widget Name", inForNthProduct)).located(By.xpath(xpathBuilder)).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getProductPrice(int inForNthProduct) {
        String xpathBuilder = String.format("//li[%d][contains(@class, 'item product product-item')]//span[contains(@data-price-type, 'finalPrice')]", inForNthProduct);
        return Target.the(String.format("%d Product Item Widget Price", inForNthProduct)).located(By.xpath(xpathBuilder)).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getProductAddToCompareButton(int inForNthProduct) {
        String xpathBuilder = String.format("//li[%d][contains(@class, 'item product product-item')]//a[contains(@class, 'action tocompare')]", inForNthProduct);
        return Target.the(String.format("%d Product Item Widget Add-To-Compare Button", inForNthProduct)).located(By.xpath(xpathBuilder)).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }
}
