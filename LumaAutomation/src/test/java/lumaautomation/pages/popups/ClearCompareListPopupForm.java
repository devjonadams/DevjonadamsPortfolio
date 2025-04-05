package lumaautomation.pages.popups;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import lumaautomation.engine.EngineHelperData;

import java.time.Duration;

public class ClearCompareListPopupForm {
    protected final static String OKAY_BUTTON_PATH = "//aside[contains(@role, 'dialog')]//button[contains(@class, 'action-primary action-accept')]";
    protected final static String CANCEL_BUTTON_PATH = "//aside[contains(@role, 'dialog')]//button[contains(@class, 'action-secondary action-dismiss')]";
    protected final static String MODAL_CONTENT_TEXT_PATH = "//aside[contains(@role, 'dialog')]//div[contains(@class, 'modal-content') and contains(@data-role, 'content')]";

    public static Target getOkayButton() {
        return Button.locatedBy(OKAY_BUTTON_PATH).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getCancelButton() {
        return Button.locatedBy(CANCEL_BUTTON_PATH).waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }

    public static Target getModalContentText(String containingExpectedText) {
        return Target.the("Modal Popup Content Text")
                .locatedBy(MODAL_CONTENT_TEXT_PATH).containingText(containingExpectedText)
                .waitingForNoMoreThan(Duration.ofSeconds(EngineHelperData.NORMAL_WAIT));
    }
}
