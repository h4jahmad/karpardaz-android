package ir.enshaee.karpardaz.main.tankhah;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.enshaee.karpardaz.BuildConfig;
import ir.enshaee.karpardaz.R;
import ir.enshaee.karpardaz.database.SQLKeys;
import ir.enshaee.karpardaz.database.SQLiteHelper;
import ir.enshaee.karpardaz.main.adapter.MainRecyclerAdapter;
import ir.enshaee.karpardaz.main.model.Hazine;
import ir.enshaee.karpardaz.view.dialog.InsertDialogFragment;


public class TankhahFragment extends Fragment
        implements InsertDialogFragment.OnInsertDialogDismissListener {

    public static final String TAG = TankhahFragment.class.getSimpleName();
    private RecyclerView mRvMain;
    private TextView mTvStatus;
    private Context mContext;
    private InsertDialogFragment mDialog;
    private List<Hazine> mHazineList;
    private MainRecyclerAdapter mRvAdapter;

    public TankhahFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mali, container, false);

        mRvMain = v.findViewById(R.id.ml_rv);
        FloatingActionButton fabAdd = v.findViewById(R.id.ml_fab_add);
        mTvStatus = v.findViewById(R.id.tv_status);

        mDialog = new InsertDialogFragment();

        //*********RecyclerView********************************************
        mHazineList = new ArrayList<>();
        mRvAdapter = new MainRecyclerAdapter(mHazineList);
        mRvMain.setLayoutManager(new GridLayoutManager(mContext, 1));
        mRvMain.setItemAnimator(new DefaultItemAnimator());
        mRvMain.setAdapter(mRvAdapter);
        mRvMain.addOnItemTouchListener(new MainRecyclerAdapter(mContext, mRvMain, new MainRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mDialog = new InsertDialogFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction().addToBackStack(null);

                mDialog.setData(mHazineList.get(position));
                mDialog.show(transaction, TAG);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mContext, mHazineList.get(position).toReadableString(), Toast.LENGTH_LONG).show();
            }
        }));
        //*****************************************************************


        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = new InsertDialogFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction().addToBackStack(null);

                mDialog.show(transaction, TAG);
            }
        });

        fillList();

        return v;
    }


    @Override
    public void onInsertDialogDismiss() {
        if (mRvAdapter.getItemCount() != 0)
            mRvAdapter.clearList();
        fillList();
        Toast.makeText(mContext, "Filled Data", Toast.LENGTH_SHORT).show();
    }


    /**
     * Fills List On Startup
     */
    private void fillList() {
        SQLiteHelper db = new SQLiteHelper(mContext);
        List<Hazine> hList = db.getLastThirtyRecords(SQLKeys.TABLE_TANKHAH);
        if (hList.size() > 0) {
            mTvStatus.setVisibility(View.GONE);
            mRvMain.setVisibility(View.VISIBLE);

            mHazineList.clear();
            for (Hazine h : hList) {
                mHazineList.add(h);
                mRvAdapter.notifyDataSetChanged();
                if (BuildConfig.DEBUG)
                    Log.d(TAG, "Items: " + h.toString());
            }
        } else {
            mRvMain.setVisibility(View.GONE);
            mTvStatus.setVisibility(View.VISIBLE);
            mTvStatus.setText(getString(R.string.no_data_title));
        }
    }


}
