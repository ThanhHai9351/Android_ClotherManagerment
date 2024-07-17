package com.example.clotherapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clotherapp.DAO.DAOAccount;
import com.example.clotherapp.MODEL.Account;
import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.R;

import java.util.ArrayList;
import java.util.Calendar;


public class Login extends AppCompatActivity {

    Button btnLogin;
    EditText edtEmail,edtPass;

    TextView twRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        controls();
        events();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        controls();
        events();
    }

    public void controls()
    {
        btnLogin = findViewById(R.id.btn_login);
        edtEmail = findViewById(R.id.edt_email_login);
        edtPass = findViewById(R.id.edt_password_login);
        twRegister = findViewById(R.id.tw_register);
    }

    public void events()
    {
        DAOAccount dao = new DAOAccount(getApplicationContext());
        dao.getAccountsFromData(new DAOAccount.DataCallback() {
            @Override
            public void onSuccess(ArrayList<Account> accounts) {
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String us = edtEmail.getText().toString().trim();
                        String pw = edtPass.getText().toString().trim();
                        if(us.isEmpty()||pw.isEmpty())
                        {
                            Toast.makeText(getApplicationContext(),"Bạn nhập chưa đủ thông tin",Toast.LENGTH_SHORT).show();
                        }else{
                                    boolean islogin = false;
                                    for(Account ac : accounts)
                                    {
                                        if(ac.getUsername().equals(us)&&ac.getPassword().equals(pw))
                                        {
                                            DataHolder.getInstance().setUsername(us);
                                            DataHolder.getInstance().setId(ac.getId());
                                            DataHolder.getInstance().setName(ac.getName());
                                            islogin = true;
                                        }
                                    }
                                    if(!islogin)
                                    {
                                        Toast.makeText(getApplicationContext(),"Bạn sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Intent i = new Intent(getApplicationContext(),Main.class);
                                        startActivity(i);
                                    }
                        }
                    }
                });

            }

            @Override
            public void onError(String error) {

            }
        });

        twRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
    }

}