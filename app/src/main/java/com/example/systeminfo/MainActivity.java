package com.example.systeminfo;

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
    Handler handler; Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBattery = findViewById(R.id.ivBattery);
        tvBattery = findViewById(R.id.tvBattery);

        runnable = new Runnable() {
            @Override
            public void run() {
                int level = (int) batteryLevel();
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
        handler = new Handler();
        handler.postDelayed(runnable, 0);
    }

    public float batteryLevel(){
        Intent batteryIntent = registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        if (level == -1 || scale == -1){
            return 50.0f;
        }
        return ((float) level / (float) scale) * 100.0f;
    }
}
