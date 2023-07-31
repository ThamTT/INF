package pages;


import commom.SQLQuery;
import commom.SQLServer;
import elements.ElementLichSuThayDoiHanMucPage;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.openqa.selenium.By;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LichSuThayDoiHanMucPage extends ElementLichSuThayDoiHanMucPage {
    CommonPage commonPage = new CommonPage();
    SQLServer sqlServer = new SQLServer();
    public void getSoLuongBanGhi() throws SQLException {
        String ui_numberOfRecord = commonPage.resolveGettext(soLuong,theActorInTheSpotlight());
        List<HashMap<String, String>> resultSQL =  sqlServer.executeQuery(SQLQuery.NUMBER_RECORD_OF_FLUCTUATIONS);
        String db_record = resultSQL.get(0).get("count");
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(ui_numberOfRecord).isEqualTo(db_record)
        );
    }

    public List<HashMap<String, String>> getRecordByID(String table, Map<String, String> conditions) throws SQLException {
        List<String> results = new ArrayList<>();
        conditions.forEach((k, v) -> results.add("\"" + k.trim() + "\""  + "=" + "\'" + v.trim() + "\'" ));
        String sql  = SQLQuery.CONDITION_RECORD_FLUCTUATIONS.replace("$Table$", table) + " " + String.join(" and ", results);
        List<HashMap<String, String>> resultSQL =  sqlServer.executeQuery(sql);
        return  resultSQL;
    }

    public String getValueIntoSpanField(String fields){
        String value = getDriver().findElement(By.xpath(spanValueFields(fields))).getText();
        return value;
    }

    public String getValueIntoInputField(String fields){
        String value = getDriver().findElement(By.xpath(inputValueFields(fields))).getText();
        return value;
    }
}
