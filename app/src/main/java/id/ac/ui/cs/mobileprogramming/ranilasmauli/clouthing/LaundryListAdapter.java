package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LaundryListAdapter extends RecyclerView.Adapter<LaundryListAdapter.LaundryViewHolder> {

    private final Activity context;
    private final LayoutInflater mInflater;
    private List<Laundry> laundries; // Cached copy of laundries


    public LaundryListAdapter(Activity activity) {
        this.context = activity;
        mInflater = activity.getLayoutInflater();
    }

    class LaundryViewHolder extends RecyclerView.ViewHolder {
        private final TextView laundryItemView;

        private LaundryViewHolder(View itemView) {
            super(itemView);
            laundryItemView = itemView.findViewById(R.id.tv_laundry);
        }
    }

    @Override
    public LaundryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.laundry_item, parent, false);
        return new LaundryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LaundryViewHolder holder, int position) {
        if (laundries != null) {
            Laundry current = laundries.get(position);
            holder.laundryItemView.setText(current.getTitle());
        } else {
            // Covers the case of data not being ready yet.
            holder.laundryItemView.setText("No Word");
        }
    }

    void setLaundries(List<Laundry> laundries){
        laundries = laundries;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // laundries has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (laundries != null)
            return laundries.size();
        else return 0;
    }
}
