package stepdefinitions.web;

import commom.SQLQuery;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import pages.CommonPage;
import pages.LichSuThayDoiHanMucPage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepsLichSuThayDoiHanMuc {
    Map<String, String> mapParams =null;
    Map<String, String> mapInformation =null;
    CommonPage commonPage = new CommonPage();
    @Steps
    LichSuThayDoiHanMucPage lichSuThayDoiHanMucPage = new LichSuThayDoiHanMucPage();

    @Then("Verify so luong ban ghi")
    public void verifySoLuongBanGhi() throws SQLException {
        lichSuThayDoiHanMucPage.getSoLuongBanGhi();
    }

    @Given("Get all record table {string} via condition in DB")
    public void getAllRecordTableViaConditionInDB(String table, DataTable dataTable) throws SQLException {
        mapParams =  new HashMap<>();
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> stringStringMap : list) {
            String column = stringStringMap.get("Column");
            String value = stringStringMap.get("Value");

            if(value.contains("key_")){
                value = commonPage.getValueSection(stringStringMap.get("Value").split("_")[1]);
            }
            mapParams.put(column, value);
        }
//        String db_record = resultSQL.get(0).get("count");
    }

    @And("Get value span field in Chi Tiet form")
    public void getValueSpanFieldInChiTietForm(DataTable dataTable) {
        Map<String, String> mapInformation = new HashMap<>();
        List<String> dataTableCells = dataTable.asList(String.class);

        for (String item : dataTableCells) {
            String value = lichSuThayDoiHanMucPage.getValueIntoSpanField(item);
            mapInformation.put(item, value);
        }
    }

    @And("Get value input fields in Chi Tiet form")
    public void getValueInputFieldsInChiTietForm(DataTable dataTable) {
        List<String> dataTableCells = dataTable.asList(String.class);

        for (String item : dataTableCells) {
            String value = lichSuThayDoiHanMucPage.getValueIntoInputField(item);
            mapInformation.put(item, value);
        }
    }

    @And("Add map record in DB")
    public void addMapRecordInDB() {
    }
}
