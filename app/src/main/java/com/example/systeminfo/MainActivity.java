package com.example.systeminfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView ivBattery; TextView tvBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBattery = findViewById(R.id.ivBattery);
        tvBattery = findViewById(R.id.tvBattery);
        this.registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            tvBattery.setText("BATTERY: "+level+"%");

            if (level>75){
                ivBattery.setImageResource(R.drawable.battery_100);
            }
            if (level>50 && level<=75){
                ivBattery.setImageResource(R.drawable.battery_75);
            }
            if (level>25 && level<=50){
                ivBattery.setImageResource(R.drawable.battery_50);
            }
            if (level>5 && level<=25){
                ivBattery.setImageResource(R.drawable.battery_25);
            }
            if (level<=5){
                ivBattery.setImageResource(R.drawable.battery_0);
            }
        }
    };
}
