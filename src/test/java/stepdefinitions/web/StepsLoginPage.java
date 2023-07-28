package stepdefinitions.web;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.*;
import net.thucydides.core.annotations.Steps;

import pages.CommonPage;
import pages.LoginPage;

public class StepsLoginPage {
  @Steps
  LoginPage loginPage = new LoginPage();

  @Before
  public void set_the_stage() {
    OnStage.setTheStage(new OnlineCast());
  }

  @Given("{string} Navigate successfully to Login Page on {string}")
  public void openWeb(String tg, String env) {
    loginPage.openINF(tg, env);
  }
//
  @When("Input valid account with {string} role")
  public void inputValidAccount(String role) {
    loginPage.inputWithRole(role);
  }

  @And("User click login button")
  public void userClickLoginButton() {
    loginPage.clickBtnLogin();
  }

  @Then("Verify login successfully with message {string}")
  public void verifyLoginSuccessfully(String text) {
    loginPage.verifyLogin(text);
  }
}
