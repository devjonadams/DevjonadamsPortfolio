package lumaautomation.behaviors.search;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import lumaautomation.behaviors.utility.LogTest;

public abstract class DoFormInput {

    public static Performable EnterInputAtTargetAndReturn(String inSearchTerms, Target inTargetInputField) {
        return Task.where("{0} " + String.format("searches for '%s' using input field [%s]", inSearchTerms, inTargetInputField.getName()),
                LogTest.Log(String.format("searches for '%s' using input field [%s]", inSearchTerms, inTargetInputField.getName())),
                WaitUntil.the(inTargetInputField, WebElementStateMatchers.isCurrentlyEnabled()).forNoMoreThan(30).seconds(),
                Enter.theValue(inSearchTerms).into(inTargetInputField).thenHit(Keys.ENTER));
    }

    public static Performable EnterInputAtTarget(String inSearchTerms, Target inTargetInputField) {
        return Task.where("{0} " + String.format("searches for '%s' using input field [%s]", inSearchTerms, inTargetInputField.getName()),
                WaitUntil.the(inTargetInputField, WebElementStateMatchers.isCurrentlyEnabled()).forNoMoreThan(30).seconds(),
                Enter.theValue(inSearchTerms).into(inTargetInputField));
    }
}
