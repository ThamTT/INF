package elements;

import core.CoreWeb;


import net.serenitybdd.screenplay.targets.Target;

public class ElementLoginPage extends CoreWeb {
  public static final Target btnLogin = Target.the("btn Login")
          .locatedBy("//div[text() ='Đăng nhập']/../span");
  public static final Target password = Target.the("Pass Login")
          .locatedBy("//input[@id='password']");
  public static final Target username = Target.the("Username Login")
          .locatedBy("//input[@id='username']");
  public static final Target messageSuccess = Target.the("Message success")
          .locatedBy("//div[@class = 'ant-notification-notice-description']");
}
