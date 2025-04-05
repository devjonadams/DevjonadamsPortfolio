package lumaautomation.pages.popups;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import lumaautomation.engine.EngineHelperData;

import java.time.Duration;

public class PrivacyPolicyPopupForm {
    protected final static String ACCEPT = "accept-btn";
    protected final static String DISAGREE = "disagree-btn";
    protected final static String MORE_OPTIONS = "more-options-btn";

    public static Target onAcceptButton() {
        return Button.withNameOrId(ACCEPT);
    }

    public static Target onDisagreeButton() {
        return Button.withNameOrId(DISAGREE).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target onMoreOptionsButton() {
        return Button.withNameOrId(MORE_OPTIONS);
    }

}
