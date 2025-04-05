package lumaautomation.pages.compare;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import lumaautomation.engine.EngineHelperData;
import lumaautomation.pages.site.LumaBaseComponent;

import java.time.Duration;

public class CompareItemsWidget extends LumaBaseComponent {
    public static final String COMPARE_ITEMS_LIST = "//ol[contains(@id,'compare-items')]";
    public static final String COMPARE_ITEMS_LIST_PRODUCT = "//ol[contains(@id,'compare-items')]//li[contains(@class,'product-item odd last')]";
    public static final String COMPARE_ITEMS_WIDGET_CLEAR = "//a[contains(@id, 'compare-clear-all')]";
    public static final String BUTTON_COMPARE_ITEMS_PATH = "//a[contains(@class, 'action compare primary')]";
    public static final String CLEAR_LIST_SUCCESS_PATH = "//div[contains(@class, 'message-success success message')]//div";

    public static Target getCompareItemProduct() {
        return Target.the("Compare Product Item")
                .located(By.xpath(COMPARE_ITEMS_LIST_PRODUCT))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getCompareItemProduct(int inForNthProduct) {
        String xpathBuilder = String.format("//ol[contains(@id,'compare-items')]//li[%d][contains(@class,'product-item odd last')]", inForNthProduct);
        return Target.the(String.format("Compare Item Product %d", inForNthProduct))
                .located(By.xpath(xpathBuilder))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getCompareItemProductName(int inForNthProduct) {
        String xpathBuilder = String.format("//ol[contains(@id,'compare-items')]//li[%d][contains(@class,'product-item odd last')]//strong[contains(@class, 'product-item-name')]", inForNthProduct);
        return Target.the(String.format("Compare Item Product %d Name", inForNthProduct))
                .located(By.xpath(xpathBuilder))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getCompareItemDeleteButton(int inForNthProduct) {
        String xpathBuilder = String.format("//ol[contains(@id,'compare-items')]//li[%d][contains(@class,'product-item odd last')]//a[contains(@class, 'action delete')]", inForNthProduct);
        return Target.the(String.format("Compare Item Product %d Name", inForNthProduct))
                .located(By.xpath(xpathBuilder))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getCompareWidgetClearButton() {
        return Target.the("Compare Items Widget Clear Button")
                .located(By.xpath(COMPARE_ITEMS_WIDGET_CLEAR))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getCompareWidgetCompareButton() {
        return Target.the("Compare Items Widget Compare Button")
                .located(By.xpath(BUTTON_COMPARE_ITEMS_PATH))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getSuccessAlert() {
        return Target.the("Comparison List Cleared Message")
                .located(By.xpath(CLEAR_LIST_SUCCESS_PATH))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }
}
