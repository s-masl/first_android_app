 package com.niggas_learn_java.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {

     public long back_pressed_time;
     public Toast back_toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_start = findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){

                }
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

     @Override
     public void onBackPressed() {

         if (back_pressed_time + 2000 > System.currentTimeMillis()){
             back_toast.cancel();
             super.onBackPressed();
             return;
         } else {
             back_toast = Toast.makeText(getBaseContext(), "Нажите ещё раз, чтобы выйти", Toast.LENGTH_SHORT);
             back_toast.show();
         }
         back_pressed_time = System.currentTimeMillis();
     }
 }