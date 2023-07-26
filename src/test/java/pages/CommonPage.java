package pages;

import elements.ElementCommonPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


public class CommonPage extends ElementCommonPage {
    EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    public void waitVisible(Target targetLocators) {
    theActorInTheSpotlight().attemptsTo(
            WaitUntil.the(targetLocators, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds());
    }
    public void clickElement(Target targetLocators) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(targetLocators, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                Click.on(targetLocators));
    }
    public void enterValueIntoField(Target targetLocators, String value) {
        waitVisible(targetLocators);
        theActorInTheSpotlight().attemptsTo(
                Enter.theValue(value).into(targetLocators));
        getDriver().findElement(By.xpath(targetLocators.getCssOrXPathSelector())).sendKeys(Keys.ENTER);
    }
}
