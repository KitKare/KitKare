package kitkare.kitkare.app.viewModels;

import org.json.JSONObject;

import java.util.Date;

import kitkare.kitkare.app.common.Helper;

public class CatCareTipViewModel {
    private long id;
    private String title;
    private String content;
    private Date createdon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public static CatCareTipViewModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            CatCareTipViewModel returnedModel = new CatCareTipViewModel();

            returnedModel.id = jObject.getInt("Id");
            returnedModel.title = jObject.getString("Title");
            returnedModel.content = jObject.getString("Content");
            String createdOnAsString = jObject.getString("CreatedOn");
            try {
                returnedModel.createdon = Helper.getDateFormatter().parse(createdOnAsString);
            }
            catch (Exception e) {
                returnedModel.createdon = Helper.getNoMsDateFormatter().parse(createdOnAsString);
            }

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
