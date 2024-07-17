package com.example.clotherapp.UI.ShoppingCartFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.clotherapp.ADAPTER.AdapterCart;
import com.example.clotherapp.DAO.DAOCart;
import com.example.clotherapp.MODEL.Cart;
import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.R;
//import com.example.clotherapp.UI.ThanhToan;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCartFragment extends Fragment {

    private ListView lsvCart;
    private TextView tvNumProduct, tvTotalPrice;
    private Cart cart = new Cart();
    private Button btnBuy;
    private ArrayList<Cart> listCarts = new ArrayList<>();
    private AdapterCart adapterCart;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    public static ShoppingCartFragment newInstance(String param1, String param2) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        addControl(view);
        loadData();
        addEvent();
        return view;
    }

    private void addControl(View view) {
        lsvCart = view.findViewById(R.id.lsvCart);
        tvNumProduct = view.findViewById(R.id.tvNumProduct);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        btnBuy = view.findViewById(R.id.btnBuy);
    }

    private void addEvent() {
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the buy button
                double sum = sumTotalPrice();
                if(sum == 0) {
                    Toast.makeText(getContext(), "Vui lòng chọn sản phẩm trước khi thanh toán", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getContext(), "Proceed to buy", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getActivity(), ThanhToan.class);
//                i.putExtra("total",sum);
//                startActivity(i);
            }
        });
    }

    private void loadData() {
        cart.getAllCarts(getContext(), DataHolder.getInstance().getId(), new DAOCart.CartLoadedCallback() {
            @Override
            public void onCartLoaded(ArrayList<Cart> carts) {
                listCarts = carts;
                adapterCart = new AdapterCart(getContext(), R.layout.layout_item_card, carts);
                lsvCart.setAdapter(adapterCart);
                updateCartSummary();
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(getContext(), "Error loading cart data", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateCartSummary() {
        tvNumProduct.setText(String.valueOf(listCarts.size()));
        double sum = sumTotalPrice();
        tvTotalPrice.setText("$" + sum);
        btnBuy.setText("Proceed To Buy (" + listCarts.size() + " Items)");
    }

    private int countProduct() {
        return listCarts.size();
    }

    private double sumTotalPrice() {
        double sum = 0;
        for (Cart cart : listCarts) {
            sum += cart.getPrice() * cart.getQuantity();
        }
        return sum;
    }
}
