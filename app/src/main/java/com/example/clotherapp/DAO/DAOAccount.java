package com.example.clotherapp.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.clotherapp.MODEL.Account;
import com.example.clotherapp.MODEL.DataHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DAOAccount {
    private static final String TAG = "DAOAccount";
    private static final String IP = DataHolder.getInstance().getIp();
    private static final String URL =IP + "clotherapp/handle/getDataAccount.php";
    private RequestQueue requestQueue;

    public DAOAccount(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public interface DataCallback {
        void onSuccess(ArrayList<Account> accounts);
        void onError(String error);
    }

    public void getAccountsFromData(final DataCallback callback) {
        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response received: " + response);
                ArrayList<Account> accounts = parseJsonData(response);
                if (accounts != null) {
                    callback.onSuccess(accounts);
                } else {
                    callback.onError("Failed to parse accounts from response");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error response: " + (error.getMessage() != null ? error.getMessage() : "Unknown error"));
                callback.onError(error.getMessage() != null ? error.getMessage() : "Unknown error");
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        requestQueue.add(request);
    }

    private ArrayList<Account> parseJsonData(String jsonString) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray array = object.optJSONArray("account");
            if (array == null) {
                Log.e(TAG, "No 'account' array in JSON response");
                return null;
            }

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.optJSONObject(i);
                if (obj == null) {
                    Log.e(TAG, "Invalid account object at index " + i);
                    continue;
                }

                int id = obj.optInt("ID", -1);
                String name = obj.optString("Name", null);
                String username = obj.optString("Username", null);
                String password = obj.optString("Password", null);
                String phone = obj.optString("Phone", null);
                String dobString = obj.optString("DOB", null);
                String address = obj.optString("Address", null);
                boolean gender = obj.optBoolean("Gender", false);
                String role = obj.optString("Role", null);

                if (id == -1 || name == null || username == null || password == null || phone == null || dobString == null || address == null || role == null) {
                    Log.e(TAG, "Missing required fields in account object at index " + i);
                    continue;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date dob;
                try {
                    dob = sdf.parse(dobString);
                } catch (ParseException e) {
                    Log.e(TAG, "Date parsing error for DOB: " + dobString, e);
                    continue;
                }

                Account account = new Account(id, name, username, password, phone, address, role, dob, gender);
                accounts.add(account);
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON parsing error: " + e.getMessage(), e);
        }

        return accounts.isEmpty() ? null : accounts;
    }

}
