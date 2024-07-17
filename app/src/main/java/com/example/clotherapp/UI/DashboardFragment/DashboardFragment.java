package com.example.clotherapp.UI.DashboardFragment;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clotherapp.ADAPTER.ProductAdapter;
import com.example.clotherapp.DAO.DAOAccount;
import com.example.clotherapp.DAO.DAOProduct;
import com.example.clotherapp.MODEL.Account;
import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.MODEL.Product;
import com.example.clotherapp.R;
import com.example.clotherapp.UI.Detail;
import com.example.clotherapp.UI.Search;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

     RecyclerView recyclerViewProduct;
     ProductAdapter productAdapter;
     ArrayList<Product> productList;
     ImageView imgSearch;

     TextView nameUser;

     String usname;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        controls(view);
        return view;
    }




    public void controls(View view) {
        recyclerViewProduct = view.findViewById(R.id.recycle_dashboard);
        nameUser = view.findViewById(R.id.tw_name_user_dashboard);
        nameUser.setText(DataHolder.getInstance().getName());
        imgSearch = view.findViewById(R.id.img_search);

        DAOAccount daoAccount = new DAOAccount(getContext());
        daoAccount.getAccountsFromData(new DAOAccount.DataCallback() {
            @Override
            public void onSuccess(ArrayList<Account> accounts) {
                for (Account a : accounts)
                {
                }
            }

            @Override
            public void onError(String error) {

            }
        });

        productList = new ArrayList<>();

        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));
        DAOProduct dao = new DAOProduct(getContext());
        dao.getProductFromData(new DAOProduct.DataCallback() {
            @Override
            public void onSuccess(ArrayList<Product> products) {
                productList = products;
                productAdapter = new ProductAdapter(getContext(), products);
                recyclerViewProduct.setAdapter(productAdapter);

                productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Product product) {
                        Intent intent = new Intent(getContext(), Detail.class);
                        intent.putExtra("id",product.getId());
                        intent.putExtra("name",product.getNameProduct());
                        intent.putExtra("image",product.getImage());
                        intent.putExtra("star",product.getStar());
                        intent.putExtra("price",product.getPrice());
                        intent.putExtra("quantity",product.getQuantity());
                        intent.putExtra("description",product.getDescription());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Search.class);
                startActivity(i);
            }
        });
    }



}