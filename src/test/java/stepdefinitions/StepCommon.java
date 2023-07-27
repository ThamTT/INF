package stepdefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import pages.CommonPage;

import java.util.List;

import static net.serenitybdd.core.Serenity.getDriver;

public class StepCommon {
    @Steps
    CommonPage commonPage = new CommonPage();

    @And("Click to the {string}")
    public void clickToThe(String text) {
        commonPage.clickElementWithText(text);
    }

    @And("Change old key {string} to new key {string}")
    public void changeOldKeyToNewKey(String oldKey, String newKey) {
        Serenity.setSessionVariable(newKey).to(Serenity.sessionVariableCalled(oldKey));
    }

    @When("Reload page")
    public void reloadPage() {
        getDriver().navigate().refresh();
    }


    @When("Go to menu")
    public void goToMenu(DataTable table) {
        List<String> dataTableCells = table.asList(String.class);
        String listMenu = String.join(",", dataTableCells);
        commonPage.clickElement(commonPage.getMenuHomePage(listMenu.split(",")));
    }

    @Then("Verify item show on the page")
    public void verifyItemShowOnThePage(DataTable table) {
        List<String> dataTableCells = table.asList(String.class);

        for (String item : dataTableCells) {
            if (item.contains("$")) {
                String value = item.trim().split("[$]")[1];
                String att = value.split("_")[0];
                String text = value.split("_")[1];
                commonPage.verifyTextDisplay(att, text);
            } else {
                commonPage.verifyTextDisplay(item);
            }
        }
    }
}
