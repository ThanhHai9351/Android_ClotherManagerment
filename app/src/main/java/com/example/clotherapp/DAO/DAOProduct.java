package com.example.clotherapp.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.MODEL.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DAOProduct {

    private static final String TAG = "DAOProduct";
    public String ip = DataHolder.getInstance().getIp();
    public String url = ip + "clotherapp/handle/getDataProduct.php";
    private RequestQueue requestQueue;

    public DAOProduct(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public interface DataCallback {
        void onSuccess(ArrayList<Product> products);
        void onError(String error);
    }

    public void getProductFromData(DataCallback callback) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response received: " + response);
                ArrayList<Product> products = parseJsonData(response);
                callback.onSuccess(products);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error response: " + error.getMessage());
                callback.onError(error.getMessage());
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        requestQueue.add(request);
    }

    private ArrayList<Product> parseJsonData(String jsonString) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray array = object.getJSONArray("product");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                int ma = obj.getInt("ID");
                String ten = obj.getString("NameProduct");
                String des = obj.getString("Description");
                String img = obj.getString("Image");
                int star = obj.getInt("Star");
                double price = obj.getDouble("Price");
                int quantity = obj.getInt("Quantity");
                int cateid = obj.getInt("CategoryID");
                products.add(new Product(ma, img, star, quantity, cateid, ten, des, price));
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON parsing error: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }
}
