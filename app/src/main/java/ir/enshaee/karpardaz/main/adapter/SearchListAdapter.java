package ir.enshaee.karpardaz.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import ir.enshaee.karpardaz.R;
import ir.enshaee.karpardaz.main.model.Hazine;


public class SearchListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context mContext;
    private ArrayList<Hazine> mHazineList;

    public SearchListAdapter(Context context, ArrayList<Hazine> hazineList) {
        this.mContext = context;
        this.mHazineList = hazineList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (view == null) {
            v = inflater.inflate(R.layout.main_list_item, viewGroup, false);

        }

        return null;
    }
}
