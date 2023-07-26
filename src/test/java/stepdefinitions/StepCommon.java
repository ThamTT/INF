package stepdefinitions;


import core.ElementBase;
import elements.ElementCommonPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.core.Serenity.getDriver;

public class StepCommon {
  ElementBase elementBase = new ElementBase();
  ElementCommonPage commonPage = new ElementCommonPage();

  public void clickElementWithText(String text) {
    elementBase.clickToElement(commonPage.elementTextDynamic(text));
  }

  @And("Click to the {string}")
  public void clickToThe(String text) {
    clickElementWithText(text);
  }

  @And("Change old key {string} to new key {string}")
  public void changeOldKeyToNewKey(String oldKey, String newKey) {
    Serenity.setSessionVariable(newKey).to(Serenity.sessionVariableCalled(oldKey));
  }

  @When("Reload page")
  public void reloadPage() {
    getDriver().navigate().refresh();
  }


}
