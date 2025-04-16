package lumaautomation.behaviors.popups;

import lumaautomation.behaviors.utility.LogTest;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import lumaautomation.engine.EngineHelperData;
import lumaautomation.pages.popups.ClearCompareListPopupForm;
import org.openqa.selenium.manager.SeleniumManagerOutput;


public class HandleConfirmClearPopup extends HandlePopup {

    @Step("{0} closes popup by selecting 'OK'")
    public static Performable selectingOK() {
        return Task.where(
                WaitUntil.the(ClearCompareListPopupForm.getOkayButton(), WebElementStateMatchers.isClickable()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Click.on(ClearCompareListPopupForm.getOkayButton()).afterWaitingUntilEnabled(),
                WaitUntil.the(ClearCompareListPopupForm.getOkayButton(), WebElementStateMatchers.isNotVisible()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Switch.toDefaultContext()
        );
    }

    @Step("{0} closes popup by selecting 'Cancel'")
    public static Performable selectingCancel() {
        return Task.where(
                WaitUntil.the(ClearCompareListPopupForm.getCancelButton(), WebElementStateMatchers.isClickable()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Click.on(ClearCompareListPopupForm.getCancelButton()).afterWaitingUntilEnabled(),
                WaitUntil.the(ClearCompareListPopupForm.getCancelButton(), WebElementStateMatchers.isNotVisible()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Switch.toDefaultContext()
        );
    }
}
