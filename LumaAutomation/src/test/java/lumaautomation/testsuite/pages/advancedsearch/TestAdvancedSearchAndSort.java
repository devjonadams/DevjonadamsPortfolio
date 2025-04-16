package lumaautomation.testsuite.pages.advancedsearch;

import net.serenitybdd.annotations.Description;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import lumaautomation.behaviors.at.AtProductListTable;
import lumaautomation.behaviors.utility.LogTest;
import lumaautomation.behaviors.navigation.AwaitFor;
import lumaautomation.behaviors.navigation.LaunchTo;
import lumaautomation.behaviors.popups.HandlePrivacyPolicyPopup;
import lumaautomation.behaviors.search.DoAdvancedSearch;
import lumaautomation.behaviors.sorting.DoSort;
import lumaautomation.pages.search.AdvancedSearchResultsPage;
import lumaautomation.pages.search.LumaAdvancedSearchPage;
import lumaautomation.testsuite.BaseTestCase;

@ExtendWith(SerenityJUnit5Extension.class)
@Description("This Test Suite provides coverage to Advanced Search and the Sort Functionality on the Advanced Search Results Page.")
public class TestAdvancedSearchAndSort extends BaseTestCase {
    private final String testProductName = "Tee";

    @CastMember(name = "The User")
    Actor user;

    @Managed()
    WebDriver driver;

    @BeforeEach
    void setUp() {
        user.can(BrowseTheWeb.with(driver));
        // Launch the Advanced Search Page.
        user.attemptsTo(
                LaunchTo.theLumaAdvancedSearchPage(),
                AwaitFor.thePageWithTitleToLoad(LumaAdvancedSearchPage.PAGE_TITLE),
                HandlePrivacyPolicyPopup.selectingDisagree(),
                LogTest.Log("Pre-Test Steps Completed.")
        );
    }


    @Test
    @DisplayName("Should use the Advanced search, and sort results by name from A to Z")
    void searchAndSortByProductNameAtoZ() {
        user.attemptsTo(
                DoAdvancedSearch.WithProductName(testProductName),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE),
                LogTest.Log("Validating Content Title").then(Ensure.that(AdvancedSearchResultsPage.CONTENT_TITLE).textContent().contains(AdvancedSearchResultsPage.PAGE_CONTENT_TITLE)),
                DoSort.byProductName(),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE)
        );
        int productsDisplayed = user.asksFor(AtProductListTable.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log("Validate Item Product Count is greater than 0")
                        .then(Ensure.that(productsDisplayed).isGreaterThan(0)));

        String previousProductName = user.asksFor(AtProductListTable.forProductName(1));
        for (int i = 1; i <= productsDisplayed; i++) {
            String currentProductName = user.asksFor(AtProductListTable.forProductName(i));
            user.attemptsTo(
                    LogTest.Log(String.format("Validate Item %d Name [%s] is in order to [%s]", i, previousProductName, currentProductName))
                            .then(Ensure.that(previousProductName.compareToIgnoreCase(currentProductName)).isLessThanOrEqualTo(0)));
            previousProductName = currentProductName;
        }
    }

    @Test
    @DisplayName("Should use the Advanced search, and sort results by name from Z to A")
    void searchAndSortByProductNameZtoA() {
        user.attemptsTo(
                DoAdvancedSearch.WithProductName(testProductName),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE),
                LogTest.Log("Validating Content Title").then(Ensure.that(AdvancedSearchResultsPage.CONTENT_TITLE).textContent().contains(AdvancedSearchResultsPage.PAGE_CONTENT_TITLE)),
                DoSort.byProductName(),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE),
                DoSort.invertSorting(),
                AwaitFor.thePageWithTitleToLoad(AdvancedSearchResultsPage.PAGE_TITLE)
        );
        int productsDisplayed = user.asksFor(AtProductListTable.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log("Validate Item Product Count is greater than 0")
                        .then(Ensure.that(productsDisplayed).isGreaterThan(0)));

        String previousProductName = user.asksFor(AtProductListTable.forProductName(1));
        for (int i = 1; i <= productsDisplayed; i++) {
            String currentProductName = user.asksFor(AtProductListTable.forProductName(i));
            user.attemptsTo(
                    LogTest.Log(String.format("Validate Item %d Name [%s] is in order to [%s]", i, previousProductName, currentProductName))
                            .then(Ensure.that(previousProductName.compareToIgnoreCase(currentProductName)).isGreaterThanOrEqualTo(0)));
            previousProductName = currentProductName;
        }
    }
}
