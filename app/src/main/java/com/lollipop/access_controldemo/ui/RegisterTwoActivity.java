package com.lollipop.access_controldemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lollipop.access_controldemo.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

public class RegisterTwoActivity extends AppCompatActivity {
    TextView login;
    EditText phone;
    EditText gender;
    EditText user;
    EditText pass,pass2;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_two);
        init();
    }

    private void init() {
        login = (TextView)findViewById(R.id.login1);
        phone = (EditText)findViewById(R.id.phone1);
        gender = (EditText)findViewById(R.id.gender);
        user =  (EditText)findViewById(R.id.username1);
        pass = (EditText)findViewById(R.id.pass1);
        pass2 = (EditText)findViewById(R.id.pass2);
        reg = (Button)findViewById(R.id.reg1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterTwoActivity.this,LoginActivity.class));
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.getText().length()!=11){
                    Toast.makeText(RegisterTwoActivity.this,"手机号码长度不合法",Toast.LENGTH_SHORT).show();
                }else{
                    if(user.getText()==null) {
                        Toast.makeText(RegisterTwoActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("pass1",pass.getText().toString());
                        Log.i("pass2",pass2.getText().toString());
                        if(!pass.getText().toString().equals(pass2.getText().toString())){
                            Toast.makeText(RegisterTwoActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            FinalHttp post = new FinalHttp();
                            AjaxParams params = new AjaxParams();
                            params.put("phone", phone.getText().toString());
                            params.put("password", pass.getText().toString());
                            params.put("username", user.getText().toString());
                            params.put("gender", gender.getText().toString());
                            //params.put("entityName",entity.getText().toString());

                            post.post(LoginActivity.IP + "/express/User/register", params, new AjaxCallBack<Object>() {
                                @Override
                                public void onSuccess(Object o) {
                                    super.onSuccess(o);
                                    try {
                                        Gson gson = new Gson();
                                        JsonObject jsonobject = gson.fromJson(o.toString(), JsonObject.class);
                                        if (jsonobject.get("status").toString().equals("1")) {
                                            Toast.makeText(RegisterTwoActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterTwoActivity.this, LoginActivity.class));
                                        } else {
                                            Toast.makeText(RegisterTwoActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                        }
                    }

                }
            }
        });
    }
}
