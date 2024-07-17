package com.example.clotherapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clotherapp.MODEL.Cart;
import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class Detail extends AppCompatActivity {

    ImageButton btnBack;
    Button decrease,increase,white,black,red,s,m,l,xl,xxl,btnAddtocart;
    ImageView img,heart;
    TextView name,price,quantity,description;

    String ip = DataHolder.getInstance().getIp();
    int idpro,size = 37,sl = 1;
    String imgpro,namepro,despro,color = "white";
    Double pricepro;

    String urlAddFavourite = ip + "/clotherapp/handle/createFavourite.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        controls();
        Intent get = getIntent();
         imgpro = get.getStringExtra("image");
         namepro = get.getStringExtra("name");
         pricepro = get.getDoubleExtra("price",0);
         despro = get.getStringExtra("description");
        idpro =get.getIntExtra("id",0);
        Picasso.with(Detail.this).load(DataHolder.getInstance().getIp()+"assets/images/products/"+imgpro)
                .resize(300, 300)
                .into(img);
        name.setText(namepro);
        price.setText("$ "+String.valueOf(pricepro));
        description.setText(despro);
        events();
    }

    public void controls()
    {
        btnBack = findViewById(R.id.btn_back_home);
        img = findViewById(R.id.img_product_detail);
        name = findViewById(R.id.tw_name_detail);
        price = findViewById(R.id.tw_price_detal);
        quantity = findViewById(R.id.tw_quantity_detail);
        description = findViewById(R.id.tw_description_detail);
        increase = findViewById(R.id.btn_increase);
        decrease = findViewById(R.id.btn_decrease);
        white = findViewById(R.id.btn_white);
        black = findViewById(R.id.btn_black);
        red = findViewById(R.id.btn_red);
        s = findViewById(R.id.btn_s);
        m= findViewById(R.id.btn_m);
        l = findViewById(R.id.btn_l);
        xl = findViewById(R.id.btn_xl);
        xxl = findViewById(R.id.btn_2xl);
        heart = findViewById(R.id.img_heart);
        btnAddtocart = findViewById(R.id.btn_add_to_cart);
    }


    public void events()
    {

        btnAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = new Cart();
                Toast.makeText(getApplicationContext(),"Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
                cart.addCart(getApplicationContext(),DataHolder.getInstance().getId(), idpro,sl,pricepro,color,size);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl++;
                quantity.setText(String.valueOf(sl));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sl > 1)
                {
                    sl = sl -1;
                }
                quantity.setText(String.valueOf(sl));
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "red";
                red.setTextColor(Color.WHITE);
                red.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                black.setTextColor(Color.BLACK);
                black.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                white.setTextColor(Color.BLACK);
                white.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "black";
                black.setTextColor(Color.WHITE);
                black.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                red.setTextColor(Color.BLACK);
                red.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                white.setTextColor(Color.BLACK);
                white.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }
        });
        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "white";
                white.setTextColor(Color.WHITE);
                white.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                red.setTextColor(Color.BLACK);
                red.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                black.setTextColor(Color.BLACK);
                black.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size=37;
                s.setTextColor(Color.WHITE);
                s.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                m.setTextColor(Color.BLACK);
                m.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                l.setTextColor(Color.BLACK);
                l.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                xl.setTextColor(Color.BLACK);
                xl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                xxl.setTextColor(Color.BLACK);
                xxl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size=38;
                m.setTextColor(Color.WHITE);
                m.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                s.setTextColor(Color.BLACK);
                s.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                l.setTextColor(Color.BLACK);
                l.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                xl.setTextColor(Color.BLACK);
                xl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                xxl.setTextColor(Color.BLACK);
                xxl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size=39;
                l.setTextColor(Color.WHITE);
                l.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                m.setTextColor(Color.BLACK);
                m.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                s.setTextColor(Color.BLACK);
                s.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                xl.setTextColor(Color.BLACK);
                xl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                xxl.setTextColor(Color.BLACK);
                xxl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }
        });
        xl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size=40;
                xl.setTextColor(Color.WHITE);
                xl.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                m.setTextColor(Color.BLACK);
                m.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                l.setTextColor(Color.BLACK);
                l.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                s.setTextColor(Color.BLACK);
                s.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                xxl.setTextColor(Color.BLACK);
                xxl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }
        });
        xxl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size=41;
                xxl.setTextColor(Color.WHITE);
                xxl.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                m.setTextColor(Color.BLACK);
                m.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                l.setTextColor(Color.BLACK);
                l.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                xl.setTextColor(Color.BLACK);
                xl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                s.setTextColor(Color.BLACK);
                s.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInsertfavourite(submit(DataHolder.getInstance().getId(),idpro ));
                Toast.makeText(getApplicationContext(), "Bạn đã yêu thích!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private JSONObject submit(int iduser,int idproduct) {
        JSONObject json = new JSONObject();
        try {
            json.put("iduser",iduser );
            json.put("idproduct",idproduct );
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Loi tao json", Toast.LENGTH_SHORT).show();
        }
        return json;
    }


    private void handleInsertfavourite(JSONObject jsonObject) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlAddFavourite, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "That bai: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}