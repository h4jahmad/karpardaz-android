package ir.enshaee.karpardaz.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ir.enshaee.karpardaz.R;
import ir.enshaee.karpardaz.main.model.Hazine;


public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>
        implements Filterable, RecyclerView.OnItemTouchListener {

    private List<Hazine> mHazineList;
    private OnItemClickListener mOnItemClickListener;
    private GestureDetector mGestureDetector;

    private LinearLayout mLlViewsHolder;//ViewGroup Which is the Parent of TextViews

    public MainRecyclerAdapter(List<Hazine> hazineList) {
        this.mHazineList = hazineList;
    }

    public MainRecyclerAdapter(Context context, final RecyclerView recyclerView,
                               OnItemClickListener listener) {
        mOnItemClickListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mOnItemClickListener != null)
                    mOnItemClickListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
            }
        });
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        mLlViewsHolder = holder.llViewsHolder;

        holder.tvDate.setText(mHazineList.get(position).getDate());
        holder.tvCost.setText(mHazineList.get(position).getCost());
        holder.tvSubject.setText(mHazineList.get(position).getSubject());

    }

    @Override
    public int getItemCount() {
        return mHazineList.size();
    }


    @Override
    public Filter getFilter() {
        return null;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mOnItemClickListener != null && mGestureDetector.onTouchEvent(e))
            mOnItemClickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    /**
     * Clear List :|
     */
    public void clearList() {
        if (mHazineList.size() > 0) {
            for (int i = 0; i < mHazineList.size(); i++)
                mHazineList.remove(i);

            notifyItemRangeRemoved(0, mHazineList.size());
        }
    }


    private void addColor() {
        int listSize = mHazineList.size();
        for (int i = 0; i < listSize; i++) {

        }
    }


    /**
     * Interface For Item Click
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView tvCost, tvSubject, tvDate;
        LinearLayout llViewsHolder;

        MainViewHolder(View itemView) {
            super(itemView);
            llViewsHolder = itemView.findViewById(R.id.mli_ll_views_holder);
            tvCost = itemView.findViewById(R.id.mli_tv_cost);
            tvSubject = itemView.findViewById(R.id.mli_tv_subject);
            tvDate = itemView.findViewById(R.id.mli_tv_date);

            this.setIsRecyclable(false);
        }
    }

}
