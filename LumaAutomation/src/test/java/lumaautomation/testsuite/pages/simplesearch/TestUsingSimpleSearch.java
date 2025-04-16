package lumaautomation.testsuite.pages.simplesearch;

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
import lumaautomation.behaviors.search.DoSimpleSearch;
import lumaautomation.pages.search.SimpleSearchResultsPage;
import lumaautomation.pages.site.LumaHomePage;
import lumaautomation.testsuite.BaseTestCase;

@ExtendWith(SerenityJUnit5Extension.class)
@Description("This Test Suite provides coverage to Simple Search functionality")
public class TestUsingSimpleSearch extends BaseTestCase {
    private final String testProduct = "Aero Tee";

    @BeforeEach
    void setUp() {
        user.can(BrowseTheWeb.with(driver));
        user.attemptsTo(
                LaunchTo.theLumaHomePage(),
                AwaitFor.thePageWithTitleToLoad(LumaHomePage.PAGE_TITLE),
                HandlePrivacyPolicyPopup.selectingDisagree(),
                LogTest.Log("Pre-Test Steps Completed.")
        );
    }

    @Test
    @DisplayName("Should search with the simple search, and get results")
    void canSearchBySimpleProductText() {
        user.attemptsTo(
                DoSimpleSearch.WithSimpleSearchBar(testProduct),
                AwaitFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                LogTest.Log("Validating Simple Search Results UI"),
                LogTest.Log("Validating Content Title").then(Ensure.that(SimpleSearchResultsPage.CONTENT_TITLE).textContent().contains(SimpleSearchResultsPage.getPageTitle(testProduct))),
                LogTest.Log("Validating Side Bar Compare Widget").then(Ensure.that(SimpleSearchResultsPage.SIDE_BAR_COMPARE).isDisplayed()),
                LogTest.Log("Validating Side Bar Wishlist Widget").then(Ensure.that(SimpleSearchResultsPage.SIDE_BAR_WISHLIST).isDisplayed())
        );
    }
}
