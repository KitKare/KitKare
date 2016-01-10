package kitkare.kitkare.app.common;

public class GlobalConstants {
    private static final String baseApiUrl = "http://kitkare.apphb.com/";//"http://localhost:1656/api/"; //10.0.2.2
    public static final String REGISTER = baseApiUrl + "api/account/register";
    public static final String LOGIN = baseApiUrl + "Token";
    public static final String LOGOUT = baseApiUrl + "api/account/logout";
}
