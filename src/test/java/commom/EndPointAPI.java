package commom;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;

public class EndPointAPI {
    public static EnvironmentVariables environmentVariables =
            SystemEnvironmentVariables.createEnvironmentVariables();
    private static final EnvironmentSpecificConfiguration environmentConf = EnvironmentSpecificConfiguration.from(environmentVariables);
    public static String URL_BASE = environmentConf.getProperty("environments.apiStaging.api.staging.url");
    public static String URL_LOGIN = environmentConf.getProperty("environments.apiStaging.login.url");

    //    login
    public static final String LOGIN_URL = URL_LOGIN + "/auth-service/v1/login";

    //    Cashing service
    public static final String POST_TAKE_REQUEST_URL = URL_BASE + "/cashing/e/v1/cash-collections/take-request";

    //    Profile
    public static final String  GET_LIST_HISTORY =URL_BASE + "/profile/b/v1/limits/histories";


}
