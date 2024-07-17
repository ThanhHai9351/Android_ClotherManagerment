package com.example.clotherapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clotherapp.DAO.ConnectDB;
import com.example.clotherapp.MODEL.Cart;
import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

public class AdapterCart extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<Cart> list=new ArrayList<>();

    public AdapterCart(@NonNull Context context, int resource, @NonNull ArrayList<Cart> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layoutItem=resource;
        this.list=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String url= ConnectDB.getUrl();
        Cart cart=list.get(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);
        }

        ImageView img=(ImageView) convertView.findViewById(R.id.imgCart);
        TextView tvName=(TextView) convertView.findViewById(R.id.tvNameCart);
        TextView tvNameCategory=(TextView) convertView.findViewById(R.id.tvNameCategory);
        TextView tvMoney=(TextView) convertView.findViewById(R.id.tvMoneyCart);
        TextView tvQuantityReview=(TextView) convertView.findViewById(R.id.tvQuantityReviewCart);
        TextView tvQuantity=(TextView) convertView.findViewById(R.id.tvQuantityCart);
        TextView tvNameSize=(TextView) convertView.findViewById(R.id.tvNameSize);
        TextView tvNameColor=(TextView) convertView.findViewById(R.id.tvNameColor);
        TextView tvMinus=(TextView) convertView.findViewById(R.id.tvMinusCart);
        TextView tvPlus=(TextView) convertView.findViewById(R.id.tvPlusCart);
        Button btnRemove=(Button) convertView.findViewById(R.id.btnRemoveCart);


        tvName.setText(cart.getName());
        tvNameCategory.setText(cart.getNameCategory());
        tvMoney.setText("$"+cart.getPrice());
        tvQuantityReview.setText("("+cart.getQuantityReview()+" Review)");
        tvQuantity.setText(""+cart.getQuantity());
        tvNameSize.setText("Size: "+cart.getSize());
        tvNameColor.setText("Color: "+cart.getColor());
        Picasso.with(context).load(DataHolder.getInstance().getIp()+"assets/images/products/"+cart.getImage())
                .resize(300, 300)
                .into(img);
        tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cart.getQuantity()>1){
                    cart.setQuantity(cart.getQuantity()-1);
                    cart.updateQuantity(view.getContext(),cart.getUser(),cart.getIdProduct(),cart.getQuantity(),cart.getColor(),cart.getSize());
                    tvQuantity.setText(""+(cart.getQuantity()));
                }
            }
        });
        tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.setQuantity(cart.getQuantity()+1);
                cart.updateQuantity(view.getContext(),cart.getUser(),cart.getIdProduct(),cart.getQuantity(),cart.getColor(),cart.getSize());
                tvQuantity.setText(""+(cart.getQuantity()));
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.deleteProductInCart(view.getContext(),cart.getUser(),cart.getIdProduct());
                notifyDataSetChanged();
                Toast.makeText(context,"Xóa sản phẩm này khoải giỏ hàng",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}