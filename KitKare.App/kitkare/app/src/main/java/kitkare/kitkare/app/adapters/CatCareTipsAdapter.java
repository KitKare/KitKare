package kitkare.kitkare.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kitkare.kitkare.R;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class CatCareTipsAdapter  extends BaseAdapter {
    private Context mContext;
    private ArrayList<CatCareTipViewModel> mFinishedPackages;

    public CatCareTipsAdapter(Context context, ArrayList<CatCareTipViewModel> finishedPackages) {
        this.mContext = context;
        this.mFinishedPackages = finishedPackages;
    }
    @Override
    public int getCount() {
        return mFinishedPackages.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mFinishedPackages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_cat_care_tip, parent, false);

        CatCareTipViewModel finishedPackage = (CatCareTipViewModel) this.getItem(position);
        TextView tvCatCareTipTitle = (TextView) rootView.findViewById(R.id.tvCatCareTipTitle);
        TextView tvCatCareTipContent = (TextView) rootView.findViewById(R.id.tvCatCareTipContent);
        TextView tvCatCareTipCreatedOn = (TextView) rootView.findViewById(R.id.tvCatCareTipCreatedOn);

        tvCatCareTipTitle.setText(finishedPackage.getTitle());
        tvCatCareTipContent.setText(finishedPackage.getContent());
        tvCatCareTipCreatedOn.setText(finishedPackage.getCreatedon().toString());

        return rootView;
    }
}
