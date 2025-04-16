package lumaautomation.pages.itemlistings;

import lumaautomation.engine.EngineHelperData;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import lumaautomation.pages.site.LumaBaseComponent;

import java.time.Duration;

public class ItemListingWidget extends LumaBaseComponent {
    // public static Target PRODUCT_WIDGET = Target.the("{0} Product Item Widget").located(By.xpath("//li[{0}][contains(@class, \'item product product-item\')]")).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    // public static Target PRODUCT_PRICE_BLOCK = Target.the("Product Price Block").locatedBy("//div[contains(@data-role, 'priceBox')]").waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    // public static Target PRODUCT_PRICE = Target.the("Product Price").locatedBy("/span[contains(@data-price-type, 'finalPrice')]").waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));

    protected static String getProductNameBlockPath() {
        return "//strong[contains(@class, \'product name product-item-name\')]";
    }

    public static Target getTargetProductNameBlock() {
        return Target.the("Product Name Block")
                .located(By.xpath(getProductNameBlockPath()))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }


    protected static String getNthProductPath(int inNthProduct) {
        return String.format("//li[%d][contains(@class, \'item product product-item\')]", inNthProduct);
    }

    public static Target getNthProduct(int inNthProduct) {
        return Target.the("{0} Product Item Widget")
                .located(By.xpath(getNthProductPath(inNthProduct)))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }
    
    protected static String getProductNamePath(int inNthProduct) {
        return String.format("//li[%d][contains(@class, 'item product product-item')]//strong[contains(@class, \'product name product-item-name\')]", inNthProduct);
    }

    public static Target getTargetProductName(int inNthProduct) {
        return Target.the(String.format("%d Product Item Widget Name", inNthProduct))
                .located(By.xpath(getProductNamePath(inNthProduct)))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    protected static String getProductPricePath(int inNthProduct) {
        return String.format("//li[%d][contains(@class, 'item product product-item')]//span[contains(@data-price-type, 'finalPrice')]", inNthProduct);
    }

    public static Target getTargetProductPrice(int inNthProduct) {
        return Target.the(String.format("%d Product Item Widget Price", inNthProduct))
                .located(By.xpath(getProductPricePath(inNthProduct)))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    protected static String getProductAddToCompareButtonPath(int inNthProduct) {
        return String.format("//li[%d][contains(@class, 'item product product-item')]//a[contains(@class, 'action tocompare')]", inNthProduct);
    }

    public static Target getTargetProductAddToCompareButton(int inNthProduct) {
        return Target.the(String.format("%d Product Item Widget Add-To-Compare Button", inNthProduct))
                .located(By.xpath(getProductAddToCompareButtonPath(inNthProduct)))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }
}
