//package com.example.clotherapp.UI;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.clotherapp.DAO.ConnectDB;
//import com.example.clotherapp.MODEL.CreateOrder;
//import com.example.clotherapp.R;
//import com.example.clotherapp.MODEL.DataHolder;
//import com.example.clotherapp.UI.DashboardFragment.DashboardFragment;
//
//import org.json.JSONObject;
//
//import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//import java.util.Map;
//
//import vn.zalopay.sdk.Environment;
//import vn.zalopay.sdk.ZaloPayError;
//import vn.zalopay.sdk.ZaloPaySDK;
//import vn.zalopay.sdk.listeners.PayOrderListener;
//
//public class ThanhToan extends AppCompatActivity {
//    Button btnZalo, btnBack;
//    private int userId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payment);
//
//        Intent i = getIntent();
//        double total = i.getDoubleExtra("total", 0);
//        String totalString = String.format("%.0f", total);
//        userId = DataHolder.getInstance().getId();
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        ZaloPaySDK.init(2553, Environment.SANDBOX);
//
//        addView();
//
//        btnZalo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CreateOrder orderApi = new CreateOrder();
//                try {
//                    JSONObject data = orderApi.createOrder(totalString);
//                    String code = data.getString("return_code");
//                    if (code.equals("1")) {
//                        String token = data.getString("zp_trans_token");
//                        ZaloPaySDK.getInstance().payOrder(ThanhToan.this, token, "demozpdk://app", new PayOrderListener() {
//                            @Override
//                            public void onPaymentSucceeded(String s, String s1, String s2) {
//                                Toast.makeText(ThanhToan.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
//                                moveDataToReceipt();
//                            }
//
//                            @Override
//                            public void onPaymentCanceled(String s, String s1) {
//                                Toast.makeText(ThanhToan.this, "Hủy thanh toán", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
//                                Toast.makeText(ThanhToan.this, "Lỗi thanh toán: " + zaloPayError.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Không thể tạo đơn hàng", Toast.LENGTH_LONG).show();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(ThanhToan.this, "Lỗi khi tạo đơn hàng: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(ThanhToan.this, DashboardFragment.class);
//                startActivity(i);
//            }
//        });
//    }
//
//    void addView() {
//        btnZalo = findViewById(R.id.btnZalo);
//        btnBack= findViewById(R.id.btnBack);
//    }
//
//    private void moveDataToReceipt() {
//        String baseurl = ConnectDB.getUrl();
//        String url = baseurl + "cart/move_to_receipt.php";
//        Map<String, String> params = new HashMap<>();
//        params.put("user_id", String.valueOf(userId));
//        params.put("pay_method", "ZaloPay");
//
//        // Convert params to JSONObject and log it
//        JSONObject jsonParams = new JSONObject(params);
//        Log.d("moveDataToReceipt", "JSON data: " + jsonParams.toString());
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonParams,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.d("moveDataToReceiptResponse", response.toString()); // Log the entire response
//                            if (response.has("success") && response.getBoolean("success")) {
//                                Toast.makeText(ThanhToan.this, "Receipt created successfully", Toast.LENGTH_LONG).show();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Toast.makeText(ThanhToan.this, "Error processing response: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ThanhToan.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
//                        error.printStackTrace(); // This will print to Logcat
//                    }
//                });
//
//        Volley.newRequestQueue(this).add(request);
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        ZaloPaySDK.getInstance().onResult(intent);
//    }
//}
