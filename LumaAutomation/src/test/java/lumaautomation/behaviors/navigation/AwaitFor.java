package lumaautomation.behaviors.navigation;

import lumaautomation.behaviors.utility.LogTest;
import lumaautomation.pages.site.LumaHomePage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/***
 * Behavior that allows the Actor to wait for targets and pages to load.
 */
public abstract class AwaitFor {

    /***
     * Waits for a given Target to load on the page.
     * Validates that the Target is: Visible, Enabled, and Displayed.
     * @param inTarget The Target to validate is loaded.
     * @return Performable.
     */
    public static Performable thePageToLoadTarget(Target inTarget) {
        String logText = String.format("Awaits the Target [%s] to be loaded on the page.", inTarget);
        return Task.where("{0} " + logText,
                LogTest.Log(logText),
                WaitUntil.the(inTarget, isVisible()),
                Ensure.that(inTarget).isEnabled(),
                Ensure.that(inTarget).isDisplayed());
    }

    /***
     * Wrapper function to check a page has loaded the Main block.
     * @return Performable.
     */
    public static Performable thePageToLoadMainContent() {
        return thePageToLoadTarget(LumaHomePage.getTargetMainContent());
    }

    /***
     * Checks the Page with a given title loads.
     * Uses the Main html block as the base page target.
     * @param inPageTitle the title of the page to check.
     * @return Performable.
     */
    public static Performable thePageWithTitleToLoad(String inPageTitle) {
        return thePageWithTitleToLoadTarget(inPageTitle, LumaHomePage.getTargetMainContent());
    }

    /***
     * Checks a Page with a Given Title loads a given Target.
     * @param inPageTitle The expected title of the page.
     * @param inTarget The expected Target that should load.
     * @return Performable.
     */
    public static Performable thePageWithTitleToLoadTarget(String inPageTitle, Target inTarget) {
        String logText = String.format("Awaits for page to load with title \"%s\" and Target", inPageTitle);
        if (inTarget == null) {
            return Task.where("{0} " + logText,
                    LogTest.Log(logText),
                    thePageToLoadMainContent(),
                    Ensure.that(TheWebPage.title()).containsIgnoringCase(inPageTitle));
        }
        logText += String.format(" [%s]", inTarget.getName());
        return Task.where("{0} " + logText + " [" + inTarget + "]",
                LogTest.Log(logText),
                AwaitFor.thePageToLoadTarget(inTarget),
                Ensure.that(TheWebPage.title()).containsIgnoringCase(inPageTitle));
    }











}
