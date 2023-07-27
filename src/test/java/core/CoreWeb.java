package core;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CoreWeb extends PageObject {
  public WebElementFacade findElement(String locator) {
    return $(By.xpath(locator));
  }

  public void clickToElement(WebElementFacade element) {
    element.waitUntilVisible().waitUntilClickable().click();
  }

  public void sendKeyToElement(WebElementFacade element, String text) {
    element.waitUntilVisible().sendKeys(text);
  }

  public void waitForElementVisible(WebElementFacade element) {
    waitForCondition().until(ExpectedConditions.visibilityOfAllElements(element));
  }

  public void upLoadFileToElement(WebElementFacade element, String filePath) {
    element.sendKeys(filePath);
  }

  public ListOfWebElementFacades findListElements(String locator) {
    return $$(By.xpath(locator));
  }

  public void waitForAllElementsDisplayed(String locator) {
    waitForCondition()
        .until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                org.openqa.selenium.By.xpath(locator)));
  }

  public void selectElementWithDropdown(ListOfWebElementFacades elements, String textOfElement) {
    for (WebElementFacade e : elements) {
      String text = e.getText();
      if (text.equals(textOfElement)) {
        scrollToElement(e);
        clickToElement(e);
        if (!e.isDisplayed()) {
          break;
        } else {
          try {
            while (e.isDisplayed()) {
              clickToElementWithJS(e);
            }
          } catch (Exception ignore) {
          }
        }
      }
    }
  }

  public void scrollToElement(WebElementFacade element) {
    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public void clickToElementWithJS(WebElementFacade element) {
    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
  }

  public boolean isElementDisplayed(WebElementFacade element) {
    return element.isDisplayed();
  }

  public void waitForAllElementsPresent(String locator) {
    waitForCondition()
        .until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                org.openqa.selenium.By.xpath(locator)));
  }
  // text of Tag H3 when click to Generator button in HomePage and navigate to new Page Object
  // example: "New Sell Order"
  public WebElementFacade headerText(String textOfTagH3) {
    String textOfTagH3Locator = String.format("//h3[text()='%s']", textOfTagH3);
    return findElement(textOfTagH3Locator);
  }

  public String allElementsInDropDownLocator(String textOfValueSelect) {
    return String.format("//span[text()='%s']/ancestor::ul/li/span", textOfValueSelect);
  }

  public ListOfWebElementFacades allElementsInDropDown(String textOfValueSelect) {
    return findListElements(allElementsInDropDownLocator(textOfValueSelect));
  }

  public void waitForAllElementsNotVisible(String locator) {
    waitForCondition().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
  }

  public String getTotalTradeValue(String text) {
    return text.substring(4).trim();
  }

  public String retrieveValueAfterInput(WebElementFacade element) {
    return (String)
        ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].value", element);
  }

  public void clickWithAction(WebElementFacade elementFacade) {
    withAction().moveToElement(elementFacade).click().perform();
  }

  public WebElementFacade elementWithTimeout(String locator, int second) {
    return withTimeoutOf(Duration.ofSeconds(second)).find(org.openqa.selenium.By.xpath(locator));
  }

  public void waitForElementNotVisible(WebElementFacade elementFacade) {
    waitForCondition().until(ExpectedConditions.invisibilityOf(elementFacade));
  }
}
