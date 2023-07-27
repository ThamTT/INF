package pages;


import commom.SQLQuery;
import commom.SQLServer;
import elements.ElementLichSuThayDoiHanMucPage;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
}
