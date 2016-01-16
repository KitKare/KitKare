package kitkare.kitkare.app.common;

public class GlobalConstants {
    private static final String baseApiUrl = "http://kitkare.apphb.com/";//"http://localhost:1656/api/"; //10.0.2.2
    public static final String REGISTER = baseApiUrl + "api/account/register";
    public static final String LOGIN = baseApiUrl + "Token";
    public static final String CATCARETIPS = baseApiUrl + "api/CatCareTips/AllTips";
    public static final String GIVEFOOD = baseApiUrl + "api/Device/GiveFood";

    // public static final String TEST = "http://spa2014.bgcoder.com/api/trips";
}
