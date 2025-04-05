package lumaautomation.testsuite.pages.itemcomparison;

import groovy.lang.Tuple3;
import lumaautomation.behaviors.fetch.DoItemComparePageFetch;
import lumaautomation.behaviors.fetch.DoItemCompareWidgetFetch;
import lumaautomation.behaviors.fetch.DoItemListingFetch;
import lumaautomation.behaviors.navigation.AwaitNavigationFor;
import lumaautomation.behaviors.navigation.LaunchTo;
import lumaautomation.behaviors.popups.HandleConfirmClearPopup;
import lumaautomation.behaviors.popups.HandlePrivacyPolicyPopup;
import lumaautomation.behaviors.search.DoSimpleSearch;
import lumaautomation.behaviors.utility.DoHelper;
import lumaautomation.behaviors.utility.LogTest;
import lumaautomation.pages.compare.CompareItemsWidget;
import lumaautomation.pages.compare.LumaCompareItemsPage;
import lumaautomation.pages.popups.ClearCompareListPopupForm;
import lumaautomation.pages.search.SimpleSearchResultsPage;
import lumaautomation.pages.site.LumaHomePage;
import lumaautomation.testsuite.BaseTestCase;
import net.serenitybdd.annotations.Description;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.stream.Stream;

@ExtendWith(SerenityJUnit5Extension.class)
@Description("This Test Suite provides coverage to the Item Comparison Widget and the Item Comparison Page.")
public class TestUsingCompareProducts extends BaseTestCase {
    private final String testProduct = "Aero Tee";
    private final int numberOfProductsToAdd = 3;

    @BeforeEach
    void setUp() {
        user.can(BrowseTheWeb.with(driver));
        user.attemptsTo(
                LaunchTo.theLumaHomePage(),
                AwaitNavigationFor.thePageWithTitleToLoad(LumaHomePage.PAGE_TITLE),
                HandlePrivacyPolicyPopup.selectingDisagree(),
                LogTest.Log("Pre-Test Steps Completed.")
        );
    }

    @Test
    @DisplayName("Can add search results to Compare Items list")
    void canSearchBySimpleProductAndAddToCompare() {
        // Search for a test product using simple search.
        user.attemptsTo(
                LogTest.Log("Search for a test product using simple search"),
                DoSimpleSearch.WithSimpleSearchBar(testProduct),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                LogTest.Log("Validating Content Title").then(Ensure.that(SimpleSearchResultsPage.CONTENT_TITLE).textContent().contains(SimpleSearchResultsPage.getPageTitle(testProduct)))
        );

        // From the results of the search, add two random products to the Compare List Widget.
        int productsDisplayed = user.asksFor(DoItemListingFetch.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log("Validate Item Product Count is greater than 0")
                        .then(Ensure.that(productsDisplayed).isGreaterThan(0)));

        LinkedList<Integer> alreadySelected = new LinkedList<Integer>();
        for (int i = 0; i < numberOfProductsToAdd; ++i) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, productsDisplayed + 1);
            while (alreadySelected.contains(randomNum)) {
                randomNum = ThreadLocalRandom.current().nextInt(1, productsDisplayed + 1);
            }
            alreadySelected.add(randomNum);
            user.attemptsTo(
                    LogTest.Log(String.format("Adding Product %d to Compare List", randomNum)),
                    DoItemListingFetch.clickAddToCompare(randomNum),
                    AwaitNavigationFor.thePageWithTitleToLoadTarget(SimpleSearchResultsPage.getPageTitle(testProduct), CompareItemsWidget.getCompareWidgetClearButton())
            );
        }

        // Validate n-items were selected.
        int itemsInCompare = user.asksFor(DoItemCompareWidgetFetch.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log(String.format("Validate the Compare List has %d Items", numberOfProductsToAdd)).then(Ensure.that(itemsInCompare).isEqualTo(numberOfProductsToAdd))); // <-- two items were added to the list.
    }

    @Test
    @DisplayName("Can add search results to Compare Items list, and then view the Compare Products Page")
    void canAccessCompareProducts() {
        // Add random items to the compare list.
        canSearchBySimpleProductAndAddToCompare();

        // Navigate to the Compare Items Page.
        user.attemptsTo(
                LogTest.Log("Clicking the Compare Button"),
                DoItemCompareWidgetFetch.clickCompare(),
                AwaitNavigationFor.thePageToLoadTarget(LumaCompareItemsPage.getContentTitle()),
                LogTest.Log("Validating Content Title").then(Ensure.that(LumaCompareItemsPage.getContentTitle()).textContent().contains(LumaCompareItemsPage.CONTENT_TITLE_TEXT))
        );

        // Validate Table is populated.
        int displayedProductCount = user.asksFor(DoItemComparePageFetch.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log(String.format("Validate the Displayed Number of Products is %d", numberOfProductsToAdd)).then(Ensure.that(displayedProductCount).isEqualTo(numberOfProductsToAdd)));
    }

    @Test
    @DisplayName("Can add search results to Compare Item List, view the Compare Page, and See Distinct Product Data")
    void canSeeProductData() {
        // Add items to list, and navigate to the compare items page.
        canAccessCompareProducts();

        // Get total number of items displayed.
        int displayedProductCount = user.asksFor(DoItemComparePageFetch.forNumberOfProducts());

        // Get the data from each product.
        ArrayList<Triple> productSet = new ArrayList<>();
        for (int i = 1; i <= displayedProductCount; ++i) {
            String productName = user.asksFor(DoItemComparePageFetch.forProductName(i));
            String productSKU = user.asksFor(DoItemComparePageFetch.forProductSKU(i));
            String productDesc = user.asksFor(DoItemComparePageFetch.forProductDescription(i));
            productSet.add(Triple.of(productName, productSKU, productDesc));
        }

        // Log the Product Data
        LogProductData(productSet);

        // Validate Data for each item is Distinct.
        user.attemptsTo(
                LogTest.Log("Validate Product Data is Distinct"),
                Ensure.that(user.asksFor(DoHelper.areListValuesUnique(productSet.stream()))).isTrue()
        );

    }

    private void LogProductData(ArrayList<Triple> inProductSet) {
        // Print the Product Data Found if Logging Level is set to Fine.
        if (defaultLogLevel == Level.FINE) {
            int ctr = 1;
            for (var t : inProductSet) {
                String logText = String.format("Product Data %d: %n\tProduct Name: %s%n\tProduct SKU: %s%n\tProduct Desc: %n\t\t%s", ctr, t.getLeft(), t.getMiddle(), t.getRight());
                user.attemptsTo(LogTest.Log(logText));
                ctr++;
            }
        }
    }
}
