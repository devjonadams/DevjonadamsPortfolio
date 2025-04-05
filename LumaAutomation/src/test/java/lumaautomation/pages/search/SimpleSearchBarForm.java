package lumaautomation.pages.search;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.InputField;

public class SimpleSearchBarForm extends SearchForm {
    protected static final String inputSimpleSearchBar = "search";
    protected static final String inputPlaceHolderText = "Search entire store here...";
    protected static final String inputFormID = "search_mini_form";

    public static Target usingSearchBar() {
        return InputField.withNameOrId(inputSimpleSearchBar);
    }

    public static Target usingPlaceHolderText() {
        return InputField.withPlaceholder(inputPlaceHolderText);
    }

    public static Target usingFormID() {
        return InputField.locatedBy(String.format("//form[@id='%s']//input[@id='%s']", inputFormID, inputSimpleSearchBar));
    }

}
