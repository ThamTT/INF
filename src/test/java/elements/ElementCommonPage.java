package elements;

import core.CoreWeb;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ElementCommonPage extends CoreWeb {
    public WebElementFacade elementTextDynamic(String title) {
        String txtFlag = String.format("//*[normalize-space(text()) = '%s']", title);
        return $(By.xpath(txtFlag));
    }

    public WebElementFacade alertSuccessTitle(String title) {
        String alertSuccessLocator =  String.format("//*[contains(text(), '%s')]", title);
        return findElement(alertSuccessLocator);
    }

    public static Target getMenuHomePage(String[] listMenu) {

        if (listMenu.length < 1) {
            return null;
        }
        StringBuilder xpathMenu = new StringBuilder("//span[text()='" + listMenu[0] + "']");
        for (int indexMenu = 1; indexMenu < listMenu.length; indexMenu++) {
            xpathMenu.append("/following-sibling::ul//*[text()='").append(listMenu[indexMenu]).append("']");
        }

        return Target.the(listMenu[listMenu.length - 1]).locatedBy(xpathMenu.toString());
    }

    public static String elementDynamicText(String text) {
         return "//*[normalize-space(text()) = '" + text + "']";
    }

    public static Target elementDynamicAtr(String att, String text) {
        return Target.the("dynamic element with attribute")
                .locatedBy("//*[normalize-space(@"+ att +") = '" + text + "']");
    }
    public static final Target phanTrang = Target.the("Phan Trang")
            .locatedBy("//*[@class  = 'ant-select-selector']");
    public static Target phanTrangNumber(String number) {
        return Target.the("dynamic element with attribute")
                .locatedBy("//*[@role= 'option']/div[text() = '"+number+" / page']");
    }
    public static final Target trRecord = Target.the("so luong reocord trong bang")
            .locatedBy("//table//tr[@data-row-key]");
    public static List<WebElementFacade> trRecords() {
        return Target.the("so luong reocord trong bang")
                .locatedBy("//table//tr[@data-row-key]").resolveAllFor(theActorInTheSpotlight());
    }

    public static final Target record1 = Target.the("so luong reocord trong bang")
            .locatedBy("(//*[text() = 'Chi tiết '])[1]");
}
