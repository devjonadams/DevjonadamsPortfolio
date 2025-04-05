package lumaautomation.pages.search;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.InputField;

public class AdvancedSearchForm extends SearchForm {
    protected static final String fieldName = "name";
    protected static final String fieldSKU = "sku";
    protected static final String fieldDesc = "description";
    protected static final String fieldShortDesc = "short_description";
    protected static final String fieldMinPrice = "price[from]";
    protected static final String fieldMaxPrice = "price[to]";

    public static Target usingFieldName() {
        return InputField.withNameOrId(fieldName);
    }

    public static Target usingFieldSKU() {
        return InputField.withNameOrId(fieldSKU);
    }

    public static Target usingFieldDescription() {
        return InputField.withNameOrId(fieldDesc);
    }

    public static Target usingFieldShortDescription() {
        return InputField.withNameOrId(fieldShortDesc);
    }

    public static Target usingFieldMinPrice() {
        return InputField.withNameOrId(fieldMinPrice);
    }

    public static Target usingFieldMaxPrice() {
        return InputField.withNameOrId(fieldMaxPrice);
    }
}
