package kitkare.kitkare.app.data.local;

import android.content.Context;
import android.os.AsyncTask;

import kitkare.kitkare.app.data.local.models.CatCareTip;
import kitkare.kitkare.app.data.remote.services.CatCareTipsService;

public class DataSeedTask extends AsyncTask<String, Void, CatCareTip> {
//    private static final String ABOUT_US_TITLE = "About us";
//    private static final String ABOUT_US_CONTENT = "KitKare is a new way to take care of your cat from anywhere in the world. " +
//            "Supply your furry loved ones with food and water, switch on their lights, quickly get in touch with the vet check on them " +
//            "anytime anywhere and learn tips on how to take best care of them.";
//    private static final String ABOUT_US_FOOTER = "Telerik Academy 2016: Android Application project";

    private Context context;
    private CatCareTipsService service;

    public DataSeedTask(Context context){
        this(context, new CatCareTipsService());
    }

    public DataSeedTask(Context context, CatCareTipsService service){
        this.context = context;
        this.service = service;
    }

    @Override
    protected CatCareTip doInBackground(String... params) {
//        try {
//            this.Seed();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    // TODO: check if updated - last modified on?
    //service.getAll
//    private void Seed() throws SQLException {
//        CatCareTipDataSource pageDb = new CatCareTipDataSource(context);
//        pageDb.open();
//        List<Page> allPages = pageDb.getAllPages();
//
//        if (allPages.size() == 0){
//            pageDb.createPage(ABOUT_US_TITLE, ABOUT_US_CONTENT, ABOUT_US_FOOTER);
//        }
//
//        pageDb.close();
//    }
}
