package com.example.studded_shivam.lab;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private  static InternetConnectivityListener internetConnectivityListener;
   @Override
    public void onReceive(final Context context, final Intent intent) {

        // Get system service object.
        Object systemServiceObj = context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Convert the service object to ConnectivityManager instance.
        ConnectivityManager connectivityManager = (ConnectivityManager)systemServiceObj;

        // Get network info object.
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Check whether network is available or not.
        boolean networkIsAvailable = false;

        if(networkInfo!=null)
        {
            if(networkInfo.isAvailable())
            {
                networkIsAvailable = true;
            }
        }
        // Display message based on whether network is available or not.
        int networkMessage;
        if(networkIsAvailable)
        {
            networkMessage =1;
        }else
        {
            networkMessage =0;
        }
        // Use a toast to show network status info.
        internetConnectivityListener.internetStatus(networkMessage);
    }
    public static void bindListener(InternetConnectivityListener listener){
       internetConnectivityListener = listener;
    }
}
