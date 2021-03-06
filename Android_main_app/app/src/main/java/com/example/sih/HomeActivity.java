package com.example.sih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mapmodule.MapsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    public int ActivityNum = 0;
     Button loadFuel;
     NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    public  static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpBottomNavigationView();
        initialiseWidgets();
        loadFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 fetchIOT retrieve = new fetchIOT();
                 retrieve.execute();
            }
        });
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
    }

    private void initialiseWidgets() {
        loadFuel = findViewById(R.id.loadFuel);
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        data = findViewById(R.id.data);
        if (data.getText().toString()!=null){
            int x = Integer.parseInt(data.getText().toString());
            if(x<50){
                button();
            }
        }
    }

    public void button() {
        notification.setSmallIcon(R.drawable.logo);
        notification.setTicker("Petrol Alert");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Petrol Alert");
        notification.setContentText("Fuel level is low");

        Intent intent = new Intent(this,HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());
    }

    private void setUpBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavViewBar);
        BottomNavigationHelper.enableNavigation(HomeActivity.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ActivityNum);
        menuItem.setChecked(true);
    }

}
