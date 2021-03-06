package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class FormActivity extends AppCompatActivity {

    public static final String CLOUTHING_REMINGDER_TITLE = "Get your laundry now!";
    private EditText pickDate;
    private EditText picPicker;
    private EditText reminderTime;
    private EditText etPrice;
    private EditText etDiscount;
    final Calendar calendar = Calendar.getInstance();
    public static final int PICK_IMAGE = 1;
    public static final int PICK_CONTACT = 2;

    public NativeConnection nativeLib = new NativeConnection();

    public final String FORM_CHANNEL_ID = "FORM_CHANNEL";

    // Text View
    private TextView textViewCountClothes;

    // Button
    private Button cancelButton;
    private Button saveButton;
    private Button uploadButton;

    private NotificationManager mNotificationManager =
            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    private Intent notificationIntent;
    int NOTIFICATION_ID = 0;
    int requestId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        init();

        notificationIntent = new Intent(getApplicationContext(), NotificationResponseActivity.class);

        picPicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(contactPickerIntent, PICK_CONTACT);
            }
        });

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get current date
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                pickDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            };
        });

        reminderTime = findViewById(R.id.et_time);
        reminderTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(FormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        reminderTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();

            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    // save entity
                    Toast.makeText(getApplicationContext(), "Clothing saved!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "save fail! Check your connection.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        uploadButton = findViewById(R.id.bt_upload);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(intent, "Pick ur clothes"),
                        PICK_IMAGE
                );
            }
        });
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int countImage = 1;

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            ClipData clipData = data.getClipData();
            // multiple images
            if (clipData != null) {
                countImage = clipData.getItemCount();
                for (int i = 0; i < clipData.getItemCount(); i++) {

                    Uri uri = clipData.getItemAt(i).getUri();
                    Log.d("IMAGES - Multiple", "URI: "+uri);
                }

            }

            else if (data.getData()!= null) {
                Uri uri = data.getData();
                Log.d("IMAGES - Single", "URI: "+uri);
            }
            textViewCountClothes.setText(countImage + "");

        }

        else if (requestCode == PICK_CONTACT && resultCode == RESULT_OK) {
            Cursor cursor = null;
            String phoneNo = null;
            String name = null;

            Uri uri = data.getData(); // save to DB

            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int  phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);

            picPicker.setText(name);

            Toast.makeText(this, "PIC has been set to " + name, Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        pickDate = findViewById(R.id.et_end_date);
        cancelButton = findViewById(R.id.bt_cancel);
        saveButton = findViewById(R.id.bt_save);
        textViewCountClothes = findViewById(R.id.tv_count_clothes);
        picPicker = findViewById(R.id.et_pic);

        etDiscount = findViewById(R.id.et_discount);
        etPrice = findViewById(R.id.et_amount);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void save(Date date, Laundry laundry, int discount) {
         laundry.setPrice(nativeLib.calculate(laundry.getPrice(),discount)); // final price

        // set notification
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(
                this, requestId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification noti = new NotificationCompat.Builder(getApplicationContext(), FORM_CHANNEL_ID)
                .setWhen(date.getTime())  			//When the event occurred
                .setContentTitle(CLOUTHING_REMINGDER_TITLE)   				//Title message top row.
                .setContentText(laundry.getTitle())  	//message
                .setContentIntent(contentIntent)  				//what activity to open.
                .setAutoCancel(true)   						//allow auto cancel when pressed.
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, noti);
        NOTIFICATION_ID++;
        requestId++;
    }
}
