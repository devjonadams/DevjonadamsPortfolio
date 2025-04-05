package lumaautomation.behaviors.navigation;

import lumaautomation.engine.EngineHelperData;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.PageElement;
import net.serenitybdd.screenplay.waits.WaitUntil;
import lumaautomation.behaviors.utility.LogTest;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public abstract class AwaitNavigationFor {
    private static final Target MAIN_CONTENT = PageElement.locatedBy("//main[@id='maincontent']");
    private static final Target PAGE_TITLE = PageElement.locatedBy("//main[@id='maincontent']");


    public static Performable thePageWithTitleToLoad(String inPageTitle) {
        return thePageWithTitleToLoadTarget(inPageTitle, MAIN_CONTENT);
    }

    public static Performable thePageWithTitleToLoadTarget(String inPageTitle, Target inTargetContainer) {
        if (inTargetContainer == null) {
            return Task.where("{0} " + String.format("awaits the page with title %s to be loaded", inPageTitle),
                    LogTest.Log(String.format("Wait for page to load with title \"%s\"", inPageTitle)),
                    thePageToLoadMainContent(),
                    Ensure.that(TheWebPage.title()).containsIgnoringCase(inPageTitle));
        }

        return Task.where("{0} " + String.format("awaits the page with title %s to be loaded", inPageTitle),
                LogTest.Log(String.format("Wait for page to load with title \"%s\"", inPageTitle)),
                WaitUntil.the(inTargetContainer, isVisible()).forNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT)),
                Ensure.that(TheWebPage.title()).containsIgnoringCase(inPageTitle));
    }

    public static Performable thePageToLoadMainContent() {
        return thePageToLoadTarget(MAIN_CONTENT);
    }

    public static Performable thePageToLoadTarget(Target inTargetContainer) {
        return Task.where("{0} " + String.format("awaits the %s to be loaded", inTargetContainer),
                LogTest.Log(String.format("Wait for %s to be loaded", inTargetContainer)),
                WaitUntil.the(inTargetContainer, isVisible()).forNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT)),
                Ensure.that(inTargetContainer).isEnabled(),
                Ensure.that(inTargetContainer).isDisplayed());
    }



}
