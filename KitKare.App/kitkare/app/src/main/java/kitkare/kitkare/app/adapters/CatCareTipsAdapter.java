package kitkare.kitkare.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import kitkare.kitkare.R;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class CatCareTipsAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<CatCareTipViewModel> catCareTips;

    public CatCareTipsAdapter(Context context, ArrayList<CatCareTipViewModel> catCareTips) {
        this.context = context;
        this.catCareTips = catCareTips;
    }
    @Override
    public int getCount() {
        return catCareTips.size();
    }

    @Override
    public Object getItem(int position) {
        return this.catCareTips.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_cat_care_tips, parent, false);

        CatCareTipViewModel catCareTip = (CatCareTipViewModel) this.getItem(position);
        TextView tvCatCareTipTitle = (TextView) rootView.findViewById(R.id.btnCatCareTipTitle);
//        TextView tvCatCareTipTitle = (TextView) rootView.findViewById(R.id.tvCatCareTipTitle);
//        TextView tvCatCareTipContent = (TextView) rootView.findViewById(R.id.tvCatCareTipContent);
//        TextView tvCatCareTipCreatedOn = (TextView) rootView.findViewById(R.id.tvCatCareTipCreatedOn);

        tvCatCareTipTitle.setText(catCareTip.getTitle());
//        tvCatCareTipContent.setText(catCareTip.getContent());
//        tvCatCareTipCreatedOn.setText(catCareTip.getCreatedon().toString());

        return rootView;
    }
}
