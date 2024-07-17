package com.example.clotherapp.DAO;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.MODEL.Favourite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DAOFavourite {
    private static final String TAG = "DAOFavourite";
    public String ip = DataHolder.getInstance().getIp();

    public String url = ip + "clotherapp/handle/getDataFavourite.php";
    private RequestQueue requestQueue;

    public DAOFavourite(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public interface DataCallback {
        void onSuccess(ArrayList<Favourite> favourites);
        void onError(String error);
    }

    public void getFavouriteFromData(DAOFavourite.DataCallback callback) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response received: " + response);
                ArrayList<Favourite> favourites = parseJsonData(response);
                callback.onSuccess(favourites);
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

    private ArrayList<Favourite> parseJsonData(String jsonString) {
        ArrayList<Favourite> favourites = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray array = object.getJSONArray("favourite");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                int id = obj.getInt("ID");
               int idUser = obj.getInt("UserID");
               int idProduct = obj.getInt("ProductID");
               String nameUser = obj.getString("NameUser");
               String namePro = obj.getString("NameProduct");
               String img = obj.getString("Image");
               Double price = obj.getDouble("Price");
                favourites.add(new Favourite(id,idUser,idProduct,nameUser,namePro,img,price));
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON parsing error: " + e.getMessage());
            e.printStackTrace();
        }
        return favourites;
    }
}
