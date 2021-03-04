package com.niggas_learn_java.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    public int num_left;
    public int num_right;

    Array array = new Array();
    Random random = new Random();

    public int count = 0;

    int[] progress = {
            R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
            R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Dialog dialog;
        Dialog dialog2;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView level_info = findViewById(R.id.level_info);
        level_info.setText(R.string.level1);

        Button button = findViewById(R.id.button_back_to_levels);



        ImageView image_left = findViewById(R.id.image_left);
        ImageView image_right = findViewById(R.id.image_right);
        TextView text_left = findViewById(R.id.text_left);
        TextView text_right = findViewById(R.id.text_right);

        image_left.setClipToOutline(true);
        image_right.setClipToOutline(true);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.preview);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        dialog2 = new Dialog(this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.level1exit);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.setCancelable(false);
        dialog2.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        TextView btn_close = dialog.findViewById(R.id.btn_dialog_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level1.this, GameLevels.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });

        Button btn_continue = dialog.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView btn_close2 = dialog2.findViewById(R.id.btn_dialog_close);
        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level1.this, GameLevels.class);
                startActivity(intent);
                finish();
                dialog2.dismiss();
            }
        });

        Button btn_continue2 = dialog2.findViewById(R.id.btn_continue);
        btn_continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level1.this, GameLevels.class);
                startActivity(intent);
                finish();
                dialog2.dismiss();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level1.this, GameLevels.class);
                startActivity(intent);
                finish();
            }
        });

        num_left = random.nextInt(10);
        num_right = random.nextInt(10);
        while (num_left == num_right) {
            num_right = random.nextInt(10);
        }

        Animation animation = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

        image_left.setImageResource(array.images[num_left]);
        image_right.setImageResource(array.images[num_right]);
        text_left.setText(array.img_description[num_left]);
        text_right.setText(array.img_description[num_right]);

        image_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    image_right.setEnabled(false);
                    count++;
                    if (num_left > num_right) {
                        image_left.setImageResource(R.drawable.ok);
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_point_green);
                        }
                    } else {
                        image_left.setImageResource(R.drawable.not_ok);
                        if (count > 0){
                            if (count == 1){
                                count = 0;
                            }else{
                                count -= 2;
                            }
                        }
                        for (int i = 0; i < 10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_point);
                        }
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_point_green);
                        }
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (count == 10){
                        dialog2.show();
                    }else {
                        num_left = random.nextInt(10);
                        num_right = random.nextInt(10);
                        while (num_left == num_right) {
                            num_right = random.nextInt(10);
                        }
                        image_left.setImageResource(array.images[num_left]);
                        image_left.startAnimation(animation);
                        image_right.setImageResource(array.images[num_right]);
                        image_right.startAnimation(animation);
                        text_left.setText(array.img_description[num_left]);
                        text_right.setText(array.img_description[num_right]);
                        image_right.setEnabled(true);
                    }
                }
                return true;
            }
        });
        image_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    image_left.setEnabled(false);
                    count++;
                    if (num_right > num_left) {
                        image_right.setImageResource(R.drawable.ok);
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_point_green);
                        }
                    } else {
                        image_right.setImageResource(R.drawable.not_ok);
                        if (count > 0){
                            if (count == 1){
                                count = 0;
                            }else{
                                count -= 2;
                            }
                        }
                        for (int i = 0; i < 10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_point);
                        }
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_point_green);
                        }
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (count == 10){
                        dialog2.show();
                    }else {
                        num_left = random.nextInt(10);
                        num_right = random.nextInt(10);
                        while (num_left == num_right) {
                            num_right = random.nextInt(10);
                        }
                        image_left.setImageResource(array.images[num_left]);
                        image_left.startAnimation(animation);
                        image_right.setImageResource(array.images[num_right]);
                        image_right.startAnimation(animation);
                        text_left.setText(array.img_description[num_left]);
                        text_right.setText(array.img_description[num_right]);
                        image_left.setEnabled(true);
                    }
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Level1.this, GameLevels.class);
        startActivity(intent);
        finish();
    }
}