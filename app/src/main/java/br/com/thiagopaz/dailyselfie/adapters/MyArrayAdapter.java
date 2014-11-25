package br.com.thiagopaz.dailyselfie.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.thiagopaz.dailyselfie.R;
import br.com.thiagopaz.dailyselfie.entity.Selfie;

/**
 * Created by thiago on 23/11/14.
 */
public class MyArrayAdapter extends ArrayAdapter<Selfie> {
    private final Activity context;
    private List<Selfie> listPhotos;
    public MyArrayAdapter(Activity context, List<Selfie> objects) {
        super(context, R.layout.rowlayout, objects);
        this.context = context;
        this.listPhotos = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.rowlayout, null, true);
        TextView photo_date = (TextView) rowView.findViewById(R.id.photo_date);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.photo);
        photo_date.setText(listPhotos.get(position).getFormatedDate());
        imageView.setImageURI(Uri.parse(listPhotos.get(position).getImagePath()));
        return rowView;
    }
}
