package commom;

public class SQLQuery {
    public static final String NUMBER_RECORD_OF_FLUCTUATIONS = "SELECT count(\"id\") FROM \"profile_v2\".\"collector_limits_fluctuations\"";
    public static final String CONDITION_RECORD_FLUCTUATIONS = "SELECT * FROM \"profile_v2\".\"$Table$\" WHERE ";

}
