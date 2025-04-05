package lumaautomation.behaviors.popups;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import lumaautomation.engine.EngineHelperData;
import lumaautomation.pages.popups.ClearCompareListPopupForm;

public class HandleConfirmClearPopup extends HandlePopup {
    public static Performable selectingOK() {
        return Task.where("{0} closes popup by selecting 'OK'",
                WaitUntil.the(ClearCompareListPopupForm.getOkayButton(), WebElementStateMatchers.isClickable()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Click.on(ClearCompareListPopupForm.getOkayButton()).afterWaitingUntilEnabled(),
                WaitUntil.the(ClearCompareListPopupForm.getOkayButton(), WebElementStateMatchers.isNotVisible()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Switch.toDefaultContext()
        );
    }

    public static Performable selectingCancel() {
        return Task.where("{0} closes popup by selecting 'Cancel'",
                WaitUntil.the(ClearCompareListPopupForm.getCancelButton(), WebElementStateMatchers.isClickable()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Click.on(ClearCompareListPopupForm.getCancelButton()).afterWaitingUntilEnabled(),
                WaitUntil.the(ClearCompareListPopupForm.getCancelButton(), WebElementStateMatchers.isNotVisible()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Switch.toDefaultContext()
        );
    }
}
