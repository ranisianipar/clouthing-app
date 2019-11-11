package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LaundryListAdapter extends RecyclerView.Adapter<LaundryListAdapter.LaundryViewHolder> {

    private final Context context;
    private final LayoutInflater mInflater;
    private List<Laundry> laundries; // Cached copy of laundries

    private ItemClickListener itemClickListener;


    public LaundryListAdapter(Context context, List<Laundry> laundries, ItemClickListener itemClickListener) {
        this.context = context;
        this.laundries = laundries;
        this.itemClickListener = itemClickListener;
        mInflater = LayoutInflater.from(context);
    }

    class LaundryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView laundryTitle;
        private final TextView laundryAmount;
        private ItemClickListener itemClickListener;

        private LaundryViewHolder(View itemView, ItemClickListener clickListener) {
            super(itemView);
            this.itemClickListener = clickListener;

            laundryTitle = itemView.findViewById(R.id.tv_laundry_title);
            laundryAmount = itemView.findViewById(R.id.tv_laundry_amount);
            Log.d("LAUNDRY AMOUNT", "LaundryViewHolder: "+laundryAmount);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    @Override
    public LaundryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_laundry, parent, false);

        Log.d("ITEM AMOUNT", "onCreateViewHolder: "+itemView.findViewById(R.id.tv_laundry_amount));
        return new LaundryViewHolder(itemView, itemClickListener);
    }

    @Override
    public void onBindViewHolder(LaundryViewHolder holder, int position) {
        Log.d("LAUNDRIES", "onBindViewHolder: "+ laundries);
        if (laundries != null) {
            Laundry current = laundries.get(position);
            holder.laundryTitle.setText(current.getTitle());

            Log.d("LAUNDRY AMOUNT", "onBindViewHolder, holder: "+ holder.laundryAmount.getText());
            // kenapa not found resource? padahal laundry amountnya dapet.
            holder.laundryAmount.setText("");
        } else {
            // Covers the case of data not being ready yet.
            holder.laundryTitle.setText("No Laundry");
            holder.laundryTitle.setText("0");
        }
    }

    void setLaundries(List<Laundry> laundries){
        this.laundries = laundries;
        notifyDataSetChanged();
    }

    public Laundry getItem(int position) {
        return laundries.get(position);
    }



    // getItemCount() is called many times, and when it is first called,
    // laundries has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (laundries != null)
            return laundries.size();
        else return 0;
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
