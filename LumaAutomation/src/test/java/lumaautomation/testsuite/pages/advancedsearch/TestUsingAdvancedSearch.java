package lumaautomation.testsuite.pages.advancedsearch;

import net.serenitybdd.annotations.Description;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import lumaautomation.behaviors.utility.LogTest;
import lumaautomation.behaviors.navigation.AwaitFor;
import lumaautomation.behaviors.navigation.LaunchTo;
import lumaautomation.behaviors.popups.HandlePrivacyPolicyPopup;
import lumaautomation.behaviors.search.DoAdvancedSearch;
import lumaautomation.pages.search.AdvancedSearchResultsPage;
import lumaautomation.pages.search.LumaAdvancedSearchPage;
import lumaautomation.testsuite.BaseTestCase;


@ExtendWith(SerenityJUnit5Extension.class)
@Description("This Test Suite provides coverage to Advanced Search functionality.")
public class TestUsingAdvancedSearch extends BaseTestCase {

    private final String testProductName = "Tee";
    private final String testProductSKU = "MS01";
    private final String testProductDescription = "Tee";
    private final String testProductShortDescription = "Tee";
    private final String testProductMinPrice = "24.00";
    private final String testProductMaxPrice = "24.01";

    @BeforeEach
    void setUp() {
        user.can(BrowseTheWeb.with(driver));
        user.attemptsTo(
                LaunchTo.theLumaAdvancedSearchPage(),
                AwaitFor.thePageWithTitleToLoad(LumaAdvancedSearchPage.PAGE_TITLE),
                HandlePrivacyPolicyPopup.selectingDisagree(),
                LogTest.Log("Pre-Test Steps Completed.")
        );
    }

    @Test
    @DisplayName("Should Search by Product Name")
    void searchByProductName() {
        user.attemptsTo(
                DoAdvancedSearch.WithProductName(testProductName),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE),
                LogTest.Log("Validating Simple Search Results UI"),
                LogTest.Log("Validating Content Title").then(Ensure.that(AdvancedSearchResultsPage.CONTENT_TITLE).textContent().contains(AdvancedSearchResultsPage.PAGE_CONTENT_TITLE)),
                LogTest.Log("Validating Side Bar Compare Widget").then(Ensure.that(AdvancedSearchResultsPage.SIDE_BAR_COMPARE).isDisplayed()),
                LogTest.Log("Validating Side Bar Wishlist Widget").then(Ensure.that(AdvancedSearchResultsPage.SIDE_BAR_WISHLIST).isDisplayed())
        );
    }

    @Test
    @DisplayName("Should Search by Product SKU")
    void searchByProductSku() {
        user.attemptsTo(
                DoAdvancedSearch.WithProductSKU(testProductSKU),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE)
        );
    }

    @Test
    @DisplayName("Should Search by Product Description")
    void searchByProductDescription() {
        user.attemptsTo(
                DoAdvancedSearch.WithProductDescription(testProductDescription),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE)
        );
    }

    @Test
    @DisplayName("Should Search by Product Short Description")
    void searchByProductShortDescription() {
        user.attemptsTo(
                DoAdvancedSearch.WithProductShortDescription(testProductShortDescription),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE)
        );
    }

    @Test
    @DisplayName("Should Search by Product Min Price")
    void searchByProductMinPrice() {
        user.attemptsTo(
                DoAdvancedSearch.WithMinPrice(testProductMinPrice),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE)
        );
    }

    @Test
    @DisplayName("Should Search by Product Max Price")
    void searchByProductMaxPrice() {
        user.attemptsTo(
                DoAdvancedSearch.WithMaxPrice(testProductMaxPrice),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE)
        );
    }
}
