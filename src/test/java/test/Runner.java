package test;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty"},
    features = "src/test/resources/features/thayDoiHanMuc",
    glue = {"stepdefinitions"},
        tags = "@LSHM_002"
)
public class Runner {}
