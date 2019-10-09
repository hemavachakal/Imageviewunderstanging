package com.example.imageviewunderstanging;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageButton imageButton;
    Intent intent;
    final static int clickcode = 100;
    Bitmap bitmap;
    Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        imageButton = findViewById(R.id.imageButton);
        InputStream inputStream = getResources().openRawResource(R.drawable.face);
        bitmap = BitmapFactory.decodeStream(inputStream);
        imageButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "imagebutton", Toast.LENGTH_SHORT).show();
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, clickcode);


            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                try {
                    getApplicationContext().setWallpaper(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}
