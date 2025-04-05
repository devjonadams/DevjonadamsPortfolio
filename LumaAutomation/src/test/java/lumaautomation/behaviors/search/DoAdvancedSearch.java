package lumaautomation.behaviors.search;

import net.serenitybdd.screenplay.Performable;
import lumaautomation.pages.search.AdvancedSearchForm;

public class DoAdvancedSearch extends DoFormInput {


    public static Performable WithProductName(String inProductName) {
        return EnterInputAtTargetAndReturn(inProductName, AdvancedSearchForm.usingFieldName());
    }

    public static Performable WithProductSKU(String inProductSKU) {
        return EnterInputAtTargetAndReturn(inProductSKU, AdvancedSearchForm.usingFieldSKU());
    }

    public static Performable WithProductDescription(String inProductDescription) {
        return EnterInputAtTargetAndReturn(inProductDescription, AdvancedSearchForm.usingFieldDescription());
    }

    public static Performable WithProductShortDescription(String inProductShortDescription) {
        return EnterInputAtTargetAndReturn(inProductShortDescription, AdvancedSearchForm.usingFieldShortDescription());
    }

    public static Performable WithMinPrice(String inProductMinPrice) {
        return EnterInputAtTargetAndReturn(inProductMinPrice, AdvancedSearchForm.usingFieldMinPrice());
    }

    public static Performable WithMaxPrice(String inProductMaxPrice) {
        return EnterInputAtTargetAndReturn(inProductMaxPrice, AdvancedSearchForm.usingFieldMaxPrice());
    }
}
