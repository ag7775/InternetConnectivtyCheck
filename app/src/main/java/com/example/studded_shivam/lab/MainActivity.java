package com.example.studded_shivam.lab;

import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity{
    IntentFilter intentFilter;
    NetworkChangeReceiver networkChangeReceiver;
    Button button;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkChangeReceiver = new NetworkChangeReceiver();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        intentFilter = new IntentFilter();
         intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
         intentFilter.setPriority(100);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(networkChangeReceiver,intentFilter);
        NetworkChangeReceiver.bindListener(new InternetConnectivityListener() {
            @Override
            public void internetStatus(int status) {
                String message = "";
                if(status==1)
                {
                    message = "Back Online";
                    Snackbar snackbar = Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_LONG);
                    View v = snackbar.getView();
                    v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.backOnline));
                    TextView textView = v.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    }
                    snackbar.show();
                }

                else {
                    message = "No Connection";
                    Snackbar snackbar = Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_LONG);
                    View v = snackbar.getView();
                    TextView textView = v.findViewById(R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    }
                    v.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
            if(this.networkChangeReceiver!=null)
        unregisterReceiver(networkChangeReceiver);
    }
}