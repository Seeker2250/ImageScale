package com.example.gallery;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Picture extends AppCompatActivity {

    TextView tvTittle, tvArtist;
    ImageView ivPicture;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture);


        tvTittle = findViewById(R.id.title);
        tvArtist = findViewById(R.id.artist);
        ivPicture = findViewById(R.id.picture);

        Intent it = getIntent();
        String tag = it.getStringExtra("it_tag");

        Resources res = getResources();

        int id_title = res.getIdentifier("title"+tag, "string","getPackageName()");
        tvTittle.setText(id_title);
        //String title = res.getString(id_title);
        //tvTittle.setText("title");

        int id_artist = res.getIdentifier("artist"+tag, "string", "getPackageName()");
        tvArtist.setText(id_artist);

        int id_picture = res.getIdentifier("picture"+tag, "string", "getPackageName()");
        String picture = res.getString(id_picture);
        int id_img = res.getIdentifier(picture, "drawable", getPackageName());

        ivPicture.setImageResource(id_img);




    }

    public void closePicture(View view) {
        finish();
    }
}
