package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver netWorkChangeReveiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter=new IntentFilter();
        //When network changes, system send a broadcast whose value is as below;
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkChangeReveiver=new NetworkChangeReceiver();
        //register the receiver
        registerReceiver(netWorkChangeReveiver,intentFilter);

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("com.example.broadcasttest.MY_BROADCAST");
                Log.d("onClick","before sending");
                //Android of high version must contain this sentence
                intent.setComponent(new ComponentName("com.example.broadcasttest",
                        "com.example.broadcasttest.MyBroadcastReceiver"));
                sendBroadcast(intent);
                Log.d("onClick","after sending");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //must unregister
        unregisterReceiver(netWorkChangeReveiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        //It works when network changes
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=(ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            //must declare in AndroidManifest.xml
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
            }

        }
    }

}
