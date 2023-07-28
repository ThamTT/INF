package stepdefinitions.web;

import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import pages.LichSuThayDoiHanMucPage;

import java.sql.SQLException;

public class StepsLichSuThayDoiHanMuc {
    @Steps
    LichSuThayDoiHanMucPage lichSuThayDoiHanMucPage = new LichSuThayDoiHanMucPage();

    @Then("Verify so luong ban ghi")
    public void verifySoLuongBanGhi() throws SQLException {
        lichSuThayDoiHanMucPage.getSoLuongBanGhi();
    }
}
