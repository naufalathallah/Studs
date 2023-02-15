package com.example.studs;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText mViewUser, mViewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mViewUser=findViewById(R.id.username);
        mViewPassword =findViewById(R.id.password);

        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.btlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Register.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(), com.example.studs.MainActivity.class));
            finish();
        }
    }


    private void razia(){
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;


        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();


        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        }else if(!cekUser(user)){
            mViewUser.setError("This Username is not found");
            fokus = mViewUser;
            cancel = true;
        }


        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password)){
            mViewPassword.setError("This password is incorrect");
            fokus = mViewPassword;
            cancel = true;
        }


        if (cancel) fokus.requestFocus();
        else masuk();
    }


    private void masuk(){
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(), com.example.studs.MainActivity.class));finish();
    }


    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}
