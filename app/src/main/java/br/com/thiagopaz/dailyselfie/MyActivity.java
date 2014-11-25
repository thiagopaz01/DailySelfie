package br.com.thiagopaz.dailyselfie;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.thiagopaz.dailyselfie.adapters.MyArrayAdapter;
import br.com.thiagopaz.dailyselfie.adapters.MyCursorAdapter;


public class MyActivity extends ActionBarActivity {

    private static final String TAG = "MyActivity";
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;
    private AlarmManager mAlarmManager;
    private Intent mNotificationReceiverIntent, mLoggerReceiverIntent;
    private PendingIntent mNotificationReceiverPendingIntent,
            mLoggerReceiverPendingIntent;
    private static final long TWO_MINUTES = 2 * 60 * 1000L;
    private static final long INITIAL_ALARM_DELAY = 60 * 1000L;
    protected static final long JITTER = 5000L;

    private DatabaseOpenHelper mDbHelper;
    private MyCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ListView listView = (ListView) findViewById(R.id.list);
        initAlarm();

        mDbHelper = new DatabaseOpenHelper(this);
        Cursor c = readPhotos();

        mAdapter = new MyCursorAdapter(this,c,0);

        listView.setAdapter(mAdapter);

    }

    private Cursor readPhotos() {
        return mDbHelper.getWritableDatabase().query(DatabaseOpenHelper.TABLE_NAME,
                DatabaseOpenHelper.columns, null, new String[] {}, null, null,
                null);
    }

    private void initAlarm() {
        //mAlarmManager.cancel(mNotificationReceiverPendingIntent);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mNotificationReceiverIntent = new Intent(MyActivity.this,AlarmNotificationReceiver.class);
        mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(
                MyActivity.this, 0, mNotificationReceiverIntent, 0);

        /*mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),
                TWO_MINUTES, mNotificationReceiverPendingIntent);*/

        mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
                TWO_MINUTES,
                mNotificationReceiverPendingIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_picture) {
            openCamera();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_TAKE_PHOTO) {
            if(resultCode == RESULT_OK) {
                if(mCurrentPhotoPath != "") {
                    ContentValues values = new ContentValues();
                    values.put(DatabaseOpenHelper.PHOTO_PATH, mCurrentPhotoPath);
                    values.put(DatabaseOpenHelper.PHOTO_DATE, new Date().toString());
                    mDbHelper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME, null, values);
                }
            }
            else {
                Log.i(TAG,"ERRO1: " + mCurrentPhotoPath);
            }
        }
        else {
            Log.i(TAG,"ERRO2: " + mCurrentPhotoPath);
        }
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e(TAG,ex.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
