package lumaautomation.testsuite.pages.simplesearch;

import net.serenitybdd.annotations.Description;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import lumaautomation.behaviors.fetch.DoItemListingFetch;
import lumaautomation.behaviors.utility.LogTest;
import lumaautomation.behaviors.navigation.AwaitNavigationFor;
import lumaautomation.behaviors.navigation.LaunchTo;
import lumaautomation.behaviors.popups.HandlePrivacyPolicyPopup;
import lumaautomation.behaviors.search.DoSimpleSearch;
import lumaautomation.behaviors.sorting.DoSort;
import lumaautomation.pages.search.SimpleSearchResultsPage;
import lumaautomation.pages.site.LumaHomePage;
import lumaautomation.testsuite.BaseTestCase;

@ExtendWith(SerenityJUnit5Extension.class)
@Description("This Test Suite provides coverage to Simple Search and the Sort Functionality on the Search Results Page.")
public class TestSimpleSearchAndSort extends BaseTestCase {
    private final String testProduct = "Aero Tee";

    @BeforeEach
    void setUp() {
        user.can(BrowseTheWeb.with(driver));
        // Launch the Advanced Search Page.
        user.attemptsTo(
                LaunchTo.theLumaHomePage(),
                AwaitNavigationFor.thePageWithTitleToLoad(LumaHomePage.PAGE_TITLE),
                HandlePrivacyPolicyPopup.selectingDisagree(),
                LogTest.Log("Pre-Test Steps Completed.")
        );
    }

    @Test
    @DisplayName("Should use the simple search, and sort results by name from Z to A")
    void canSearchAndSortByNameAtoZ() {
        user.attemptsTo(
                DoSimpleSearch.WithSimpleSearchBar(testProduct),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                LogTest.Log("Validating Content Title").then(Ensure.that(SimpleSearchResultsPage.CONTENT_TITLE).textContent().contains(SimpleSearchResultsPage.getPageTitle(testProduct))),
                DoSort.byProductName(),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct))
        );
        int productsDisplayed = user.asksFor(DoItemListingFetch.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log("Validate Item Product Count is greater than 0")
                        .then(Ensure.that(productsDisplayed).isGreaterThan(0)));

        String previousProductName = user.asksFor(DoItemListingFetch.forProductName(1));
        for (int i = 1; i <= productsDisplayed; i++) {
            String currentProductName = user.asksFor(DoItemListingFetch.forProductName(i));
            user.attemptsTo(
                    LogTest.Log(String.format("Validate Item %d Name [%s] is in order to [%s]", i, previousProductName, currentProductName))
                            .then(Ensure.that(previousProductName.compareToIgnoreCase(currentProductName)).isGreaterThanOrEqualTo(0)));
            previousProductName = currentProductName;
        }
    }

    @Test
    @DisplayName("Should use the simple search, and sort results by name from A to Z")
    void canSearchAndSortByNameZtoA() {
        user.attemptsTo(
                DoSimpleSearch.WithSimpleSearchBar(testProduct),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                LogTest.Log("Validating Content Title").then(Ensure.that(SimpleSearchResultsPage.CONTENT_TITLE).textContent().contains(SimpleSearchResultsPage.getPageTitle(testProduct))),
                DoSort.byProductName(),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                DoSort.invertSorting(),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct))
        );
        int productsDisplayed = user.asksFor(DoItemListingFetch.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log("Validate Item Product Count is greater than 0")
                        .then(Ensure.that(productsDisplayed).isGreaterThan(0)));

        String previousProductName = user.asksFor(DoItemListingFetch.forProductName(1));
        for (int i = 1; i <= productsDisplayed; i++) {
            String currentProductName = user.asksFor(DoItemListingFetch.forProductName(i));
            user.attemptsTo(
                    LogTest.Log(String.format("Validate Item %d Name [%s] is in order to [%s]", i, previousProductName, currentProductName))
                            .then(Ensure.that(previousProductName.compareToIgnoreCase(currentProductName)).isLessThanOrEqualTo(0)));
            previousProductName = currentProductName;
        }
    }

    @Test
    @DisplayName("Should use the simple search, and sort results by price from High to Low")
    void canSearchAndSortByPriceHighToLow() {
        user.attemptsTo(
                DoSimpleSearch.WithSimpleSearchBar(testProduct),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                LogTest.Log("Validating Content Title").then(Ensure.that(SimpleSearchResultsPage.CONTENT_TITLE).textContent().contains(SimpleSearchResultsPage.getPageTitle(testProduct))),
                DoSort.byProductPrice(),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct))
        );
        int productsDisplayed = user.asksFor(DoItemListingFetch.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log("Validate Item Product Count is greater than 0")
                        .then(Ensure.that(productsDisplayed).isGreaterThan(0)));

        float previousPrice = Float.parseFloat(user.asksFor(DoItemListingFetch.forProductPrice(1)));
        for (int i = 1; i <= productsDisplayed; i++) {
            float currentItemPrice = Float.parseFloat(user.asksFor(DoItemListingFetch.forProductPrice(i)));
            user.attemptsTo(
                    LogTest.Log(String.format("Validate Item %d price [%.2f] is less than or equal to previous item price [%.2f]", i, currentItemPrice, previousPrice))
                            .then(Ensure.that(currentItemPrice).isLessThanOrEqualTo(previousPrice)));
            previousPrice = currentItemPrice;
        }
    }

    @Test
    @DisplayName("Should use the simple search, and sort results by price from Low to High")
    void canSearchAndSortByPriceLowToHigh() {
        user.attemptsTo(
                DoSimpleSearch.WithSimpleSearchBar(testProduct),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                LogTest.Log("Validating Content Title").then(Ensure.that(SimpleSearchResultsPage.CONTENT_TITLE).textContent().contains(SimpleSearchResultsPage.getPageTitle(testProduct))),
                DoSort.byProductPrice(),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                DoSort.invertSorting(),
                AwaitNavigationFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct))
        );
        int productsDisplayed = user.asksFor(DoItemListingFetch.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log("Validate Item Product Count is greater than 0")
                        .then(Ensure.that(productsDisplayed).isGreaterThan(0)));

        float previousPrice = Float.parseFloat(user.asksFor(DoItemListingFetch.forProductPrice(1)));
        for (int i = 1; i <= productsDisplayed; i++) {
            float currentItemPrice = Float.parseFloat(user.asksFor(DoItemListingFetch.forProductPrice(i)));
            user.attemptsTo(
                    LogTest.Log(String.format("Validate Item %d price [%.2f] is greater than or equal to previous item price [%.2f]", i, currentItemPrice, previousPrice))
                            .then(Ensure.that(currentItemPrice).isGreaterThanOrEqualTo(previousPrice)));
            previousPrice = currentItemPrice;
        }
    }
}
