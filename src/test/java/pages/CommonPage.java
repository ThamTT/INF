package pages;

import commom.SQLServer;
import elements.ElementCommonPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementsLocated;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CommonPage extends ElementCommonPage {
    EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    SQLServer sqlServer = new SQLServer();
//    CommonPage(){
//        sqlServer.co
//    }

    public static void waitVisible(Target targetLocators) {
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

    public void inputValueIntoField(Target targetLocators, String value) {
        waitVisible(targetLocators);
        theActorInTheSpotlight().attemptsTo(
                Enter.theValue(value).into(targetLocators));
    }

    public void validateMessage(Target xpath, String text) {
        waitVisible(xpath);
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(xpath).text().isEqualTo(text)
        );
    }

    public void clickElementWithText(String text) {
        clickToElement(elementTextDynamic(text));
    }

    public void verifyTextDisplay(String text) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(elementDynamicText(text), WebElementStateMatchers.isVisible()).forNoMoreThan(180).seconds(),
                Ensure.that(ElementsLocated.by(elementDynamicText(text)).waitingForNoMoreThan(Duration.ofSeconds(300)))
                        .isDisplayed()
        );

    }

    public void verifyTextDisplay(String att, String text) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(elementDynamicAtr(att, text), WebElementStateMatchers.isVisible()).forNoMoreThan(180).seconds(),
                Ensure.that(ElementsLocated.by(elementDynamicAtr(att, text)).waitingForNoMoreThan(Duration.ofSeconds(300)))
                        .isDisplayed()
        );
    }

    public static String resolveGettext(Target target, Actor actor) {
        waitVisible(target);
        return target.resolveFor(actor).getText();
    }
}
