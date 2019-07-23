package ir.enshaee.karpardaz.main.report;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ir.enshaee.karpardaz.R;


public class ReportFragment extends Fragment {

    public static final String TAG = ReportFragment.class.getSimpleName();

    private Context mContext;

    public ReportFragment() {
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
        View v = inflater.inflate(R.layout.fragment_report, container, false);

        Spinner sp = v.findViewById(R.id.sp_main);

        return v;
    }


    private void initSpTables(Spinner sp) {
        String[] items = {"", ""};

    }

    private void initSpMain(Spinner sp) {
        String[] items = new String[]{"روزانه", "هفتگی", "ماهانه", "کل", "دلخواه", "نوع گزارش را انتخاب نمایید"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_dropdown_item_1line, items);
        sp.setAdapter(adapter);
        sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;

                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        break;

                    case 5:

                        break;

                }
            }
        });

        sp.setSelection(adapter.getCount());

    }


}
