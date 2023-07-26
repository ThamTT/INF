package elements;

import core.ElementBase;
import net.serenitybdd.core.pages.WebElementFacade;


import net.serenitybdd.screenplay.targets.Target;

public class ElementLoginPage extends ElementBase {

//  public WebElementFacade password() {
//    String txtPass = "//input[@id='password']";
//    return findElement(txtPass);
//  }
  public static Target elementDynamic(String tabName, String idXPath) {
    return Target.the("input or select data")
            .locatedBy("//table[@id='" + tabName + "']//*[@id='" + idXPath + "']");
  }

  public static final Target btnLogin = Target.the("btn Login")
          .locatedBy("//div[text() ='Đăng nhập']/../span");
  public static final Target password = Target.the("Pass Login")
          .locatedBy("//input[@id='password']");
  public static final Target username = Target.the("Pass Login")
          .locatedBy("//input[@id='username']");
}
