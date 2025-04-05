package lumaautomation.behaviors.popups;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import lumaautomation.engine.EngineHelperData;
import lumaautomation.pages.popups.PrivacyPolicyPopupForm;

public class HandlePrivacyPolicyPopup extends HandlePopup {

    public static Performable selectingAccept() {
        return Task.where("{0} closes popup by selecting Accept",
                WaitUntil.the(PrivacyPolicyPopupForm.onAcceptButton(), WebElementStateMatchers.isClickable()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Click.on(PrivacyPolicyPopupForm.onAcceptButton()).afterWaitingUntilEnabled(),
                WaitUntil.the(PrivacyPolicyPopupForm.onAcceptButton(), WebElementStateMatchers.isNotVisible()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Switch.toDefaultContext()
        );
    }

    public static Performable selectingDisagree() {
        return Task.where("{0} closes popup by selecting Disagree",
                WaitUntil.the(PrivacyPolicyPopupForm.onDisagreeButton(), WebElementStateMatchers.isClickable()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Click.on(PrivacyPolicyPopupForm.onDisagreeButton()).afterWaitingUntilEnabled(),
                WaitUntil.the(PrivacyPolicyPopupForm.onDisagreeButton(), WebElementStateMatchers.isNotVisible()).forNoMoreThan(EngineHelperData.NORMAL_WAIT).seconds(),
                Switch.toDefaultContext()
        );
    }
}
