package lumaautomation.testsuite.pages.itemcomparison;

import net.serenitybdd.annotations.Description;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import lumaautomation.behaviors.at.AtCompareItemsListWidget;
import lumaautomation.behaviors.at.AtProductListTable;
import lumaautomation.behaviors.popups.HandleConfirmClearPopup;
import lumaautomation.behaviors.utility.LogTest;
import lumaautomation.behaviors.navigation.AwaitFor;
import lumaautomation.behaviors.navigation.LaunchTo;
import lumaautomation.behaviors.popups.HandlePrivacyPolicyPopup;
import lumaautomation.behaviors.search.DoSimpleSearch;
import lumaautomation.pages.compare.CompareItemsWidget;
import lumaautomation.pages.popups.ClearCompareListPopupForm;
import lumaautomation.pages.search.SimpleSearchResultsPage;
import lumaautomation.pages.site.LumaHomePage;
import lumaautomation.testsuite.BaseTestCase;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

@ExtendWith(SerenityJUnit5Extension.class)
@Description("This Test Suite provides coverage to the Item Comparison Widget.")
public class TestUsingItemComparisonWidget extends BaseTestCase {
    private final String testProduct = "Aero Tee";
    private final int numberOfProductsToAdd = 3;

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
    @DisplayName("Can add search results to Compare Items list")
    void canSearchBySimpleProductAndAddToCompare() {
        // Search for a test product using simple search.
        user.attemptsTo(
                LogTest.Log("Search for a test product using simple search"),
                DoSimpleSearch.WithSimpleSearchBar(testProduct),
                AwaitFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                LogTest.Log("Validating Content Title").then(Ensure.that(SimpleSearchResultsPage.CONTENT_TITLE).textContent().contains(SimpleSearchResultsPage.getPageTitle(testProduct)))
        );

        // From the results of the search, add two random products to the Compare List Widget.
        int productsDisplayed = user.asksFor(AtProductListTable.forNumberOfProducts());
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
                    AtProductListTable.clickAddToCompare(randomNum),
                    AwaitFor.thePageWithTitleToLoadTarget(SimpleSearchResultsPage.getPageTitle(testProduct), CompareItemsWidget.getCompareWidgetClearButton())
            );
        }

        // Validate n-items were selected.
        int itemsInCompare = user.asksFor(AtCompareItemsListWidget.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log(String.format("Validate the Compare List has %d Items", numberOfProductsToAdd)).then(Ensure.that(itemsInCompare).isEqualTo(numberOfProductsToAdd))); // <-- two items were added to the list.
    }

    @Test
    @DisplayName("Can add search results to Compare Items list, and then clear the list")
    void canClearCompareWidget() {
        // Add random items to the compare list.
        canSearchBySimpleProductAndAddToCompare();

        // Clear the Compare Items Widget List.
        user.attemptsTo(
                LogTest.Log("Clicking the Clear All Button in Compare List"),
                AtCompareItemsListWidget.clickClearAll(),
                AwaitFor.thePageToLoadTarget(ClearCompareListPopupForm.getOkayButton()),
                HandleConfirmClearPopup.selectingOK(),
                AwaitFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                LogTest.Log("Validating Content Title").then(Ensure.that(SimpleSearchResultsPage.CONTENT_TITLE).textContent().contains(SimpleSearchResultsPage.getPageTitle(testProduct)))
        );

        // Validate list is empty.
        int clearedItemCount = user.asksFor(AtCompareItemsListWidget.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log("Validate the Compare List is Empty").then(Ensure.that(clearedItemCount).isEqualTo(0))); // <-- two items were cleared from the list
    }

    @Test
    @DisplayName("Can add search results to Compare Items list, and then remove an item from the list")
    void canRemoveSingleProductInCompareWidget() {
        int endListSize = (numberOfProductsToAdd - 1);
        // Add random items to the compare list.
        canSearchBySimpleProductAndAddToCompare();

        // Select a random product to remove.
        int selectRandomProduct = ThreadLocalRandom.current().nextInt(1, user.asksFor(AtCompareItemsListWidget.forNumberOfProducts()) + 1);

        // Attempt to remove the product.
        user.attemptsTo(
                LogTest.Log(String.format("Clicking the remove button on Product %d in Compare List", selectRandomProduct)),
                AtCompareItemsListWidget.clickRemove(selectRandomProduct),
                AwaitFor.thePageToLoadTarget(ClearCompareListPopupForm.getOkayButton()),
                HandleConfirmClearPopup.selectingOK(),
                AwaitFor.thePageWithTitleToLoad(SimpleSearchResultsPage.getPageTitle(testProduct)),
                LogTest.Log("Validating Content Title").then(Ensure.that(SimpleSearchResultsPage.CONTENT_TITLE).textContent().contains(SimpleSearchResultsPage.getPageTitle(testProduct)))
        );

        // Validate item was removed.
        int clearedItemCount = user.asksFor(AtCompareItemsListWidget.forNumberOfProducts());
        user.attemptsTo(
                LogTest.Log(String.format("Validate the Compare List has %d products", endListSize)).then(Ensure.that(clearedItemCount).isEqualTo(endListSize)));
    }

}
