package pages;

import elements.ElementLoginPage;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;

import java.lang.annotation.Target;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class LoginPage extends ElementLoginPage {

  CommonPage commonPage = new CommonPage();
  public void openINF(String actor, String env){
    if(env.equals("staging")){
      theActorCalled(actor).attemptsTo(
              Open.url(commonPage.environmentVariables.getProperty("environments.staging.webdriver.base.url"))
      );
    }
  }

  public void inputWithRole(String role){
    String user = "";
    String pass = "";
    if(role.equals("OPS")){
      user = commonPage.environmentVariables.getProperty("role.ops20.account");
      pass = commonPage.environmentVariables.getProperty("role.ops20.password");
    }
    commonPage.inputValueIntoField(username, user);
    commonPage.inputValueIntoField(password, pass);
  }

  public void clickBtnLogin() {
    commonPage.clickElement(btnLogin);
  }

  public void verifyLogin(String text) {
    commonPage.validateMessage(messageSuccess, text);
  }
}
