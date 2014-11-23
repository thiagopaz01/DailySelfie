package br.com.thiagopaz.dailyselfie.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import br.com.thiagopaz.dailyselfie.R;
import br.com.thiagopaz.dailyselfie.entity.Selfie;

/**
 * Created by thiago on 23/11/14.
 */
public class MyArrayAdapter extends ArrayAdapter<Selfie> {
    public MyArrayAdapter(Context context, List<Selfie> objects) {
        super(context, R.layout.rowlayout, objects);
    }
}
