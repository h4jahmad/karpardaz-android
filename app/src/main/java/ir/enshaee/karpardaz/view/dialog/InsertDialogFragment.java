package ir.enshaee.karpardaz.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ir.enshaee.karpardaz.R;
import ir.enshaee.karpardaz.database.SQLiteHelper;
import ir.enshaee.karpardaz.main.model.Hazine;
import ir.enshaee.karpardaz.main.tankhah.TankhahFragment;
import ir.enshaee.karpardaz.main.tojibi.TojibiFragment;
import ir.enshaee.karpardaz.util.AppConstants;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class InsertDialogFragment extends DialogFragment
        implements View.OnClickListener {

    public static final String TAG = InsertDialogFragment.class.getSimpleName();

    private Context mContext;

    /**
     * Gets Source Place
     */
    private String mSource;

    private boolean isInsertOp;
    private Hazine mHazine;
    private EditText mEtDate;
    private EditText mEtCost;
    private EditText mEtSubject;
    private OnInsertDialogDismissListener mCallBack;


    public InsertDialogFragment() {
    }

    public static InsertDialogFragment newInstance(@Nullable Bundle args) {

        InsertDialogFragment fragment = new InsertDialogFragment();
        if (args != null)
            fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        setCancelable(false);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_insert, container, false);

        //****************Initializing Widgets********************
        TextView tvTitle = v.findViewById(R.id.tv_title);
        mEtDate = v.findViewById(R.id.et_insertdate);
        mEtDate.setOnClickListener(this);

        mEtCost = v.findViewById(R.id.et_cost);
        mEtSubject = v.findViewById(R.id.et_subject);
        v.findViewById(R.id.iv_close).setOnClickListener(this);
        v.findViewById(R.id.btn_insert).setOnClickListener(this);
        Button btnDelete = v.findViewById(R.id.btn_delete);
        //LinearLayout llButtons = findViewById(R.id.ll_buttons);

        //**********************************************************

        //*********************Checking for Required Operation Based on Source*********
        if (mSource.equals(TojibiFragment.TAG) || mSource.equals(TankhahFragment.TAG)) {
            tvTitle.setText(mContext.getString(R.string.insert));
            btnDelete.setVisibility(View.GONE);
            //btnInsert.setWidth(llButtons.getWidth());
            isInsertOp = true;
        } else if (mSource.equals(AppConstants.TANKHAH_EDIT_TAG)
                || mSource.equals(AppConstants.TOJIBI_EDIT_TAG)) {
            tvTitle.setText(mContext.getString(R.string.edit_delete_title));
            //btnInsert.setWidth();//TODO: Continue Editing Button Layouting
            isInsertOp = false;
        }

        //****************************************************************************


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (OnInsertDialogDismissListener) context;
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ", e);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //Insert Button
            case R.id.btn_insert:
                insert();
                break;

            //Close Button
            case R.id.iv_close:
                InsertDialogFragment.this.dismiss();
                break;

            //Date EditText
            case R.id.et_insertdate:
                initCalendar();
                break;

            default:
                break;
        }

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mCallBack.onInsertDialogDismiss();
    }

    private void insert() {
        //*********Check for Required Fields*****************
        if (mEtCost.getText().toString().isEmpty() ||
                mEtDate.getText().toString().isEmpty() ||
                mEtSubject.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "لطفا تمام کادر های را پر کنید", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteHelper db = new SQLiteHelper(mContext);
            Hazine h = new Hazine(
                    mEtDate.getText().toString(),
                    mEtSubject.getText().toString(),
                    mEtCost.getText().toString());

            if (isInsertOp) {//Insert Operation
                if (mSource.equals(TojibiFragment.TAG)) {
                    //Insert into Tojibi Table
                    db.insertTojibiHazine(h);
                    db.close();
                    Toast.makeText(mContext, "افزوده شد", Toast.LENGTH_SHORT).show();
                    clearFields(mEtDate, mEtCost, mEtSubject);

                } else if (mSource.equals(TankhahFragment.TAG)) {
                    //Insert into Tankhah Table
                    db.insertTankhahHazine(h);
                    db.close();
                    Toast.makeText(mContext, "افزوده شد", Toast.LENGTH_SHORT).show();
                    clearFields(mEtDate, mEtCost, mEtSubject);
                }

            } else {// Update Operation

                //Update operation result
                int retId = 0;

                mEtCost.setText(mHazine.getCost());
                mEtDate.setText(mHazine.getDate());
                mEtSubject.setText(mHazine.getSubject());

                //If Source Was From TankhahFragment.java, Update Tankhah Table,
                //IF Source Was From TojibiFragment.java, Update Tojibi Table
                if (mSource.equals(AppConstants.TANKHAH_EDIT_TAG)) {
                    retId = db.updateTankhahHazine(h);
                    db.close();

                } else if (mSource.equals(AppConstants.TOJIBI_EDIT_TAG)) {
                    retId = db.updateTojibiHazine(h);
                    db.close();
                }

                if (retId > 0)//If Update Was Successful
                    Toast.makeText(mContext, "ویرایش انجام شد", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mContext, "ویرایش انجام نشد", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Clear EditTexts
     */
    private void clearFields(EditText... editTexts) {
        if (editTexts.length > 0) {
            for (EditText et : editTexts) {
                et.setText("");
            }
        }
    }

    private void initCalendar() {
        SolarCalendarDialog dialog = new SolarCalendarDialog(mContext);
        dialog.setPositiveButtonResource(R.string.ok);
        dialog.setNegativeButtonResource(R.string.no);
        dialog.setTodayButtonVisible(true);
        dialog.setTodayButtonResource(R.string.today);
        dialog.setMaxYear(1500);
        dialog.setMinYear(1300);
        dialog.setActionTextColorResource(R.color.colorAccent);

        dialog.setListener(new Listener() {
            @Override
            public void onDateSelected(PersianCalendar persianCalendar) {
                mEtDate.setText(persianCalendar.getPersianShortDate());
            }

            @Override
            public void onDismissed() {
            }
        });

        dialog.show();
    }

    /**
     * Set Required data for Updating
     */
    public void setData(Hazine h) {

        this.mHazine = h;
    }


    public interface OnInsertDialogDismissListener {
        void onInsertDialogDismiss();
    }


}
