package elements;

import core.ElementBase;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class ElementCommonPage extends ElementBase {
    public WebElementFacade elementTextDynamic(String title) {
        String txtFlag = String.format("//*[normalize-space(text()) = '%s']", title);
        return $(By.xpath(txtFlag));
    }

    public WebElementFacade alertSuccessTitle(String title) {
        String alertSuccessLocator =  String.format("//*[contains(text(), '%s')]", title);
        return findElement(alertSuccessLocator);
    }
}
