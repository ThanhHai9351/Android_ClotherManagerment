package com.example.clotherapp.UI.ProductFragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.clotherapp.ADAPTER.ProductAdapter;
import com.example.clotherapp.DAO.DAOProduct;
import com.example.clotherapp.MODEL.Product;
import com.example.clotherapp.R;
import com.example.clotherapp.UI.Detail;
import com.example.clotherapp.UI.Search;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {
    RecyclerView recyclerViewProduct;

    ImageView imgSearch;
    ProductAdapter productAdapter;
    ArrayList<Product> productList;

    Button all,set,shirt,dress,sneaker;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        controls(view);
        events();
        return view;
    }



    public void controls(View view) {
        recyclerViewProduct = view.findViewById(R.id.recycle_product);
        all = view.findViewById(R.id.btn_all_product);
        set = view.findViewById(R.id.btn_set_product);
        shirt = view.findViewById(R.id.btn_shirt_product);
        dress = view.findViewById(R.id.btn_dress_product);
        sneaker = view.findViewById(R.id.btn_sneaker_product);
        imgSearch = view.findViewById(R.id.img_search_product);

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
    }

    public void events()
    {
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Search.class);
                startActivity(i);
            }
        });

        DAOProduct dao = new DAOProduct(getContext());
        dao.getProductFromData(new DAOProduct.DataCallback() {
            @Override
            public void onSuccess(ArrayList<Product> products) {

                all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        all.setTextColor(Color.WHITE);
                        all.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        shirt.setTextColor(Color.BLACK);
                        shirt.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        dress.setTextColor(Color.BLACK);
                        dress.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        set.setTextColor(Color.BLACK);
                        set.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        sneaker.setTextColor(Color.BLACK);
                        sneaker.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        productList = new ArrayList<>();
                        productList = products;
                        productAdapter = new ProductAdapter(getContext(), productList);
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
                });

                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        set.setTextColor(Color.WHITE);
                        set.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        shirt.setTextColor(Color.BLACK);
                        shirt.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        dress.setTextColor(Color.BLACK);
                        dress.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        all.setTextColor(Color.BLACK);
                        all.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        sneaker.setTextColor(Color.BLACK);
                        sneaker.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        productList = new ArrayList<>();
                        for(Product p : products)
                        {
                            if(p.getCategoryID()==4)
                            {
                                productList.add(p);
                            }
                        }
                        productAdapter = new ProductAdapter(getContext(), productList);
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
                });

                shirt.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        shirt.setTextColor(Color.WHITE);
                        shirt.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        all.setTextColor(Color.BLACK);
                        all.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        dress.setTextColor(Color.BLACK);
                        dress.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        set.setTextColor(Color.BLACK);
                        set.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        sneaker.setTextColor(Color.BLACK);
                        sneaker.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        productList = new ArrayList<>();
                        for(Product p : products)
                        {
                            if(p.getCategoryID()==1)
                            {
                                productList.add(p);
                            }
                        }
                        productAdapter = new ProductAdapter(getContext(), productList);
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
                });

                dress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dress.setTextColor(Color.WHITE);
                        dress.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        shirt.setTextColor(Color.BLACK);
                        shirt.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        all.setTextColor(Color.BLACK);
                        all.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        set.setTextColor(Color.BLACK);
                        set.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        sneaker.setTextColor(Color.BLACK);
                        sneaker.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        productList = new ArrayList<>();
                        for(Product p : products)
                        {
                            if(p.getCategoryID()==2)
                            {
                                productList.add(p);
                            }
                        }
                        productAdapter = new ProductAdapter(getContext(), productList);
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
                });

                sneaker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sneaker.setTextColor(Color.WHITE);
                        sneaker.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        shirt.setTextColor(Color.BLACK);
                        shirt.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        dress.setTextColor(Color.BLACK);
                        dress.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        set.setTextColor(Color.BLACK);
                        set.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        all.setTextColor(Color.BLACK);
                        all.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                        productList = new ArrayList<>();
                        for(Product p : products)
                        {
                            if(p.getCategoryID()==3)
                            {
                                productList.add(p);
                            }
                        }
                        productAdapter = new ProductAdapter(getContext(), productList);
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
                });
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

            }
        });
    }
}