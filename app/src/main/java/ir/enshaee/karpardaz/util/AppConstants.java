package ir.enshaee.karpardaz.util;


import ir.enshaee.karpardaz.main.tankhah.TankhahFragment;
import ir.enshaee.karpardaz.main.tojibi.TojibiFragment;
import ir.enshaee.karpardaz.view.dialog.InsertDialogFragment;

public class AppConstants {

    /**
     * Tag for using in {@link InsertDialogFragment} for Editing a record from {@link TojibiFragment}
     */
    public static final String TOJIBI_EDIT_TAG = TojibiFragment.TAG + "_edit";

    /**
     * Tag for using in {@link InsertDialogFragment} for Editing a record from {@link TankhahFragment}
     */
    public static final String TANKHAH_EDIT_TAG = TankhahFragment.TAG + "_edit";

    public static final int REQUEST_TOJIBI = 0;
    public static final int REQUEST_TANKHAH = 1;

}
