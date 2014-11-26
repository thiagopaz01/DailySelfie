package br.com.thiagopaz.dailyselfie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by thiago on 25/11/14.
 */
public class SelfieActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfie);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.PHOTO_PATH);
        File imgFile = new  File(message);
        imageView = (ImageView) findViewById(R.id.selfie);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
    }
}
