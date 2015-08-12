package com.example.jonander.brokenreality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static String authentication_token;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("Testing 1 - Send Http GET request");
        NetworkTask n = new NetworkTask();
        n.execute();
    }
}
