package br.com.thiagopaz.dailyselfie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by thiago on 25/11/14.
 */
public class SelfieActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfie);

        imageView = (ImageView) findViewById(R.id.selfie);
    }
}
