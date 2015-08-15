package com.example.jonander.brokenreality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements AsyncResponse{

    public static String authentication_token;

    EditText et_username;
    EditText et_password;
    TextView tv_info;
    LoginUser lu;
    AsyncResponse activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_username = (EditText) this.findViewById(R.id.et_username);
        et_password = (EditText) this.findViewById(R.id.et_password);
        tv_info = (TextView) this.findViewById(R.id.tv_info);

        Button bt_login = (Button) this.findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lu = new LoginUser();
                lu.delegate = activity;
                ArrayList<String> passing = new ArrayList<>();
                passing.add(et_username.getText().toString());
                passing.add(et_password.getText().toString());
                lu.execute(passing);
            }
        });
    }

    public void processFinish(String output){
        tv_info.setText(output);
        if (output == "Login success" && authentication_token != null){
            //Intent intent = new Intent(this, Main2Activity.class);
            //startActivity(intent);
        }
    }
}
