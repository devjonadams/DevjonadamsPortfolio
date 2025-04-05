package lumaautomation.behaviors.search;

import net.serenitybdd.screenplay.Performable;
import lumaautomation.pages.search.SimpleSearchBarForm;

public class DoSimpleSearch extends DoFormInput {
    public static Performable WithSimpleSearchBar(String inSearchText) {
        return EnterInputAtTargetAndReturn(inSearchText, SimpleSearchBarForm.usingSearchBar());
    }
}
