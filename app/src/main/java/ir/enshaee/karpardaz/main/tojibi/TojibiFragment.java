package ir.enshaee.karpardaz.main.tojibi;

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


public class TojibiFragment extends Fragment implements
        View.OnClickListener,
        InsertDialogFragment.OnInsertDialogDismissListener {

    public static final String TAG = TojibiFragment.class.getSimpleName();

    private Context mContext;
    private InsertDialogFragment mDialog;
    private List<Hazine> mHazineList;
    private MainRecyclerAdapter mRvAdapter;
    private RecyclerView mRvMain;
    private TextView mTvStatus;

    public TojibiFragment() {
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
        View view = inflater.inflate(R.layout.fragment_mali, container, false);


        mRvMain = view.findViewById(R.id.ml_rv);
        final FloatingActionButton fabAdd = view.findViewById(R.id.ml_fab_add);
        mTvStatus = view.findViewById(R.id.tv_status);


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
                mDialog.show(transaction, InsertDialogFragment.TAG);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mContext, mHazineList.get(position).toReadableString(), Toast.LENGTH_LONG).show();
            }
        }));

        //Control FAB Visibility on RecyclerView Scroll.
        mRvMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0)
                    fabAdd.hide();
                else if (dy < 0)
                    fabAdd.show();
            }
        });
        //*****************************************************************

        fabAdd.setOnClickListener(this);

        fillList();

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ml_fab_add:
                mDialog = new InsertDialogFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction().addToBackStack(null);
                mDialog.show(transaction, InsertDialogFragment.TAG);
                break;

            default:
                break;
        }
    }


    @Override
    public void onInsertDialogDismiss() {
        if (mRvAdapter.getItemCount() != 0)
            mRvAdapter.clearList();

        fillList();
        Toast.makeText(mContext, "Dereste! :|", Toast.LENGTH_SHORT).show();
    }


    /**
     * Fills List On Startup
     */
    private void fillList() {
        SQLiteHelper db = new SQLiteHelper(mContext);
        List<Hazine> hList = db.getLastThirtyRecords(SQLKeys.TABLE_TOJIBI);
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