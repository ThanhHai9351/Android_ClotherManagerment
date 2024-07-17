package com.example.clotherapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.R;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    EditText name, username, pass, confirmpass;
    Button register;
    CheckBox cbConfirm;
    String IP = DataHolder.getInstance().getIp();
    String urlRegister = IP + "clotherapp/handle/registerAccount.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        controls();
        events();
    }

    public void controls() {
        name = findViewById(R.id.edt_name_register);
        username = findViewById(R.id.edt_username_register);
        pass = findViewById(R.id.edt_password_register);
        confirmpass = findViewById(R.id.edt_confirm_password);
        cbConfirm = findViewById(R.id.cb_confirm);
        register = findViewById(R.id.btn_register);
    }

    public void events() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = name.getText().toString().trim();
                String us = username.getText().toString().trim();
                String pw = pass.getText().toString().trim();
                String confirmpw = confirmpass.getText().toString().trim();

                if (ten.isEmpty() || us.isEmpty() || pw.isEmpty() || confirmpw.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa điền đủ trường!", Toast.LENGTH_SHORT).show();
                } else if (!pw.equals(confirmpw)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else if (!cbConfirm.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Bạn cần chấp nhận điều khoản", Toast.LENGTH_SHORT).show();
                } else {
                    registerAccount(submit());
                }
            }
        });
    }

    private JSONObject submit() {
        String ten = name.getText().toString().trim();
        String us = username.getText().toString().trim();
        String pw = pass.getText().toString().trim();
        JSONObject json = new JSONObject();
        try {
            json.put("Name", ten);
            json.put("Username", us);
            json.put("Password", pw);
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi tạo JSON", Toast.LENGTH_SHORT).show();
        }
        return json;
    }

    private void registerAccount(JSONObject jsonObject) {
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlRegister, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (!response.getBoolean("error")) {
                        Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Register.this, "Thất bại: " + response.getString("error_msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Register.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "Thất bại: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}
