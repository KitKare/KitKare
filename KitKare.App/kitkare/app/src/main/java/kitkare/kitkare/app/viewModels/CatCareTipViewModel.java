package kitkare.kitkare.app.viewModels;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

import java.util.Date;

import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.data.local.models.CatCareTip;

public class CatCareTipViewModel implements Parcelable {
    private long id;
    private String title;
    private String content;
    private Date createdon;
    private int data;

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

    public static CatCareTipViewModel FromModel(String json) {
        try {
            JSONObject jObject = new JSONObject(json);
            CatCareTipViewModel returnedModel = new CatCareTipViewModel(Parcel.obtain());

            returnedModel.id = jObject.getInt("Id");
            returnedModel.title = jObject.getString("Title");
            returnedModel.content = jObject.getString("Content");
            String createdOnAsString = jObject.getString("CreatedOn");
            try {
                returnedModel.createdon = Helper.getDateFormatter().parse(createdOnAsString);
            } catch (Exception e) {
                returnedModel.createdon = Helper.getNoMsDateFormatter().parse(createdOnAsString);
            }

            return returnedModel;
        } catch (Exception e) {
            Log.v("tag", e.getMessage());
            return null;
        }
    }

    public static CatCareTipViewModel FromModel(CatCareTip tip) {
        CatCareTipViewModel returnedModel = new CatCareTipViewModel(Parcel.obtain());
        returnedModel.id = tip.getId();
        returnedModel.title = tip.getTitle();
        returnedModel.content = tip.getContent();
        returnedModel.createdon = tip.getCreatedon();

        return returnedModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(data);
    }

public static final Parcelable.Creator<CatCareTipViewModel> CREATOR
        = new Parcelable.Creator<CatCareTipViewModel>() {
    public CatCareTipViewModel createFromParcel(Parcel in) {
        return new CatCareTipViewModel(in);
    }

    public CatCareTipViewModel[] newArray(int size) {
        return new CatCareTipViewModel[size];
    }
};

    /**
     * recreate object from parcel
     */
    private CatCareTipViewModel(Parcel in) {
        data = in.readInt();
    }
}
