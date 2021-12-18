package com.ekcapaper.ndk.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.material.components.R;

public class HelloNdkActivity extends AppCompatActivity {
    private TextView textView_ndk;

    static {
        System.loadLibrary("helloNdk");
    }
    public native String print_ndk(String text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_ndk);

        textView_ndk = (TextView) findViewById(R.id.textView_ndk);
        String print = print_ndk("hello_ndk");
        textView_ndk.setText(print);
    }
}