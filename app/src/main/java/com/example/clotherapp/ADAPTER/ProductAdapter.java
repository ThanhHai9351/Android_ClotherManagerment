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
import com.example.clotherapp.MODEL.Product;
import com.example.clotherapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> products;
    private OnItemClickListener listener;

    // Constructor
    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
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
            img = itemView.findViewById(R.id.img_product_dashboard);
            name = itemView.findViewById(R.id.tw_name_product);
            price = itemView.findViewById(R.id.tw_price_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(products.get(position));
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard_product, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = products.get(position);
        Picasso.with(context).load(DataHolder.getInstance().getIp()+"assets/images/products/"+p.getImage())
                .resize(300, 300)
                .into(holder.img);
        String name = p.getNameProduct().substring(0,20);
        holder.name.setText(name);
        holder.price.setText("$ " + p.getPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
