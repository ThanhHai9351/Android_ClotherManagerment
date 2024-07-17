package com.example.clotherapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clotherapp.ADAPTER.ProductAdapter;
import com.example.clotherapp.DAO.DAOProduct;
import com.example.clotherapp.MODEL.Product;
import com.example.clotherapp.R;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    Button back;
    EditText txtSearch;
    RecyclerView recyclerSearch;
    ProductAdapter adapter;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        controls();
        events();
    }
    public void controls()
    {
        back = findViewById(R.id.btn_back_search);
        txtSearch = findViewById(R.id.edt_search);
        recyclerSearch = findViewById(R.id.recycle_search);
        productList = new ArrayList<>();

        recyclerSearch.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        DAOProduct dao = new DAOProduct(getApplicationContext());
        dao.getProductFromData(new DAOProduct.DataCallback() {
            @Override
            public void onSuccess(ArrayList<Product> products) {
                productList = products;
                adapter = new ProductAdapter(getApplicationContext(), products);
                recyclerSearch.setAdapter(adapter);

                adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Product product) {
                        Intent intent = new Intent(getApplicationContext(), Detail.class);
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
                Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void events()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        DAOProduct dao = new DAOProduct(getApplicationContext());
        dao.getProductFromData(new DAOProduct.DataCallback() {
            @Override
            public void onSuccess(ArrayList<Product> products) {
                txtSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        String text = txtSearch.getText().toString().trim();
                        productList = new ArrayList<>();
                        for(Product p : products)
                        {
                            if(p.getNameProduct().toLowerCase().contains(text.toLowerCase().trim()))
                            {
                                productList.add(p);
                            }
                        }
                        adapter = new ProductAdapter(getApplicationContext(), productList);
                        recyclerSearch.setAdapter(adapter);
                        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Product product) {
                                Intent intent = new Intent(getApplicationContext(), Detail.class);
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
                    public void afterTextChanged(Editable editable) {

                    }
                });

                adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Product product) {
                        Intent intent = new Intent(getApplicationContext(), Detail.class);
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