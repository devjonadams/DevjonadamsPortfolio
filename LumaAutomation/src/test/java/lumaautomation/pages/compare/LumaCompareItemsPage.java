package lumaautomation.pages.compare;

import lumaautomation.engine.EngineHelperData;
import lumaautomation.pages.site.LumaBasePage;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;

import java.time.Duration;

@DefaultUrl("https://magento.softwaretestingboard.com/catalog/product_compare/index/uenc/")
public class LumaCompareItemsPage extends LumaBasePage {
    public static final String PAGE_TITLE_TEXT = "Products Comparison List - Magento Commerce";
    public static final String CONTENT_TITLE_TEXT = "Compare Products";

    protected static final String LABEL_TEXT_PATH = "//table[contains(@id, 'product-comparison')]//tbody//th[contains(@class, 'cell label')]";
    protected static final String PRODUCT_ATTRIBUTE_VALUE = "//table[contains(@id, 'product-comparison')]//tbody//tr[%d]//td[%d][contains(@class, 'cell product attribute')]";
    protected static final String PRODUCT_INFO_VALUE = "//table[contains(@id, 'product-comparison')]//tbody//td[%d][contains(@class, 'cell product info')]";
    private static final String PRODUCT_COUNT_LOCATOR = "//table[contains(@id, 'product-comparison')]//td[contains(@class, 'cell product info')]";
    protected static final String PRODUCT_INFO_NAME = PRODUCT_INFO_VALUE + "//strong[contains(@class, 'product-item-name')]";
    public static final int idRowSKU = 1;
    public static final int idRowDescription = 2;



    public static Target getContentTitle() {
        return Target.the("Content Title")
                .located(By.xpath("//h1[contains(@class, \'page-title\')]"))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getLabelText(String inMatchingLabel) {
        return Target.the(String.format("Label text for %s", inMatchingLabel))
                .located(By.xpath(LABEL_TEXT_PATH)).containingText(inMatchingLabel)
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getTableData(int rowIndex, int columnIndex) {
        String xpathBuilder = String.format(PRODUCT_ATTRIBUTE_VALUE, rowIndex, columnIndex);
        return Target.the(String.format("Table Data text for Row %d, Col %d", rowIndex, columnIndex))
                .located(By.xpath(xpathBuilder))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getTableProductInfo(int columnIndex) {
        String xpathBuilder = String.format(PRODUCT_INFO_NAME, columnIndex);
        return Target.the(String.format("Table Product Info for Col %d", columnIndex))
                .located(By.xpath(xpathBuilder))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getProductsCompared() {
        return Target.the("Products In Table")
                .located(By.xpath(PRODUCT_COUNT_LOCATOR))
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }
}