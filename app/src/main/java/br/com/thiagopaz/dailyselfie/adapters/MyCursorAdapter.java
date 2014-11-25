package br.com.thiagopaz.dailyselfie.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import br.com.thiagopaz.dailyselfie.R;

/**
 * Created by thiago.fernandes on 25/11/2014.
 */
public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.rowlayout, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        File imgFile = new  File(cursor.getString(1));

        if(imgFile.exists()) {
            TextView txtViewDate = (TextView) view.findViewById(R.id.photo_date);
            txtViewDate.setText(cursor.getString(2));

            ImageView imageView = (ImageView) view.findViewById(R.id.photo);
            imageView.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
        }
    }
}
