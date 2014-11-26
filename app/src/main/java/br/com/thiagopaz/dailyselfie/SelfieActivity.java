package br.com.thiagopaz.dailyselfie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class SelfieActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfie);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.PHOTO_PATH);
        File imgFile = new  File(message);
        ImageView imageView = (ImageView) findViewById(R.id.selfie);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
    }
}
