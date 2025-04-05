package lumaautomation.behaviors.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import lumaautomation.pages.search.LumaAdvancedSearchPage;
import lumaautomation.pages.site.LumaHomePage;

public class LaunchTo {
    public static Performable theLumaHomePage() {
        return Task.where("{0} opens the DuckDuckGo home page",
                Open.browserOn().the(LumaHomePage.class));
    }

    public static Performable theLumaAdvancedSearchPage() {
        return Task.where("{0} opens the Luma Advanced Search Page", Open.browserOn().the(LumaAdvancedSearchPage.class));
    }

    public static Performable thePage(String withURL) {
        return Task.where("{0} opens the DuckDuckGo home page",
                Open.url(withURL));
    }
}
