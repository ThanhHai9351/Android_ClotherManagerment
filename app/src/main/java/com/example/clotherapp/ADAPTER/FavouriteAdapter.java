package com.example.clotherapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.MODEL.Favourite;
import com.example.clotherapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private Context context;
    private List<Favourite> favourites;
    private OnItemClickListener listener;

    // Constructor
    public FavouriteAdapter(Context context, List<Favourite> favourites) {
        this.context = context;
        this.favourites = favourites;
    }

    public interface OnItemClickListener {
        void onItemClick(Favourite favourite);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView name;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_product_favourite);
            name = itemView.findViewById(R.id.tw_name_favourite);
            price = itemView.findViewById(R.id.tw_price_favourite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(favourites.get(position));
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite_product, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Favourite favourite = favourites.get(position);
        Picasso.with(context).load(DataHolder.getInstance().getIp()+"assets/images/products/"+favourite.getImage())
                .resize(300, 300)
                .into(holder.img);
        holder.name.setText(favourite.getNameProduct());
        holder.price.setText("$ " + favourite.getPrice());
    }

    @Override
    public int getItemCount() {
        return favourites.size();
    }
}
