package com.paygilant.myshop;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.paygilant.myshop.OnLine.ByOnlineActivity;
import com.paygilant.myshop.OnLine.CategoryJewel;
import com.paygilant.myshop.OnLine.ImageItem;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Button button_buy_online, button_send_money;
    public static int NUMBER_TIME= 10;
    public static ArrayList<ImageItem> imageItems;
    public static ImageItem imageItem;
    Switch aSwitch;
//    PaygilantManager paygilantHandler;

    public static final int READ_MAIN_SCREEN = 75;
    Button buttonBIO;
    boolean isPressOne=true;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ui_appBar_light)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        forceLTRSupported(this);
        EditText testSize = (EditText) findViewById(R.id.testSize);
        aSwitch = findViewById(R.id.switchMain);
        if (BuildConfig.DEBUG) {
            aSwitch.setVisibility(View.VISIBLE);
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("FRAUDSTER", aSwitch.isChecked());
            editor.apply();
            testSize.setVisibility(View.GONE);
            aSwitch.setVisibility(View.GONE);

        }else {
            aSwitch.setVisibility(View.GONE);
        }

        button_buy_online = findViewById(R.id.button_buy_online);
        button_send_money = findViewById(R.id.button_send_money);
        buttonBIO = findViewById(R.id.buttonBIO);
        //Singlton.getInstance().setCanAccess(false);

        Intent intent = getIntent();
        boolean isNotFirstTime =  intent.getBooleanExtra("IF_NOT_FIRST_TIME", false);
        if (isNotFirstTime) {
            checkPermission();
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,
//                                Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
//                                Manifest.permission.READ_CALL_LOG,Manifest.permission.BLUETOOTH,Manifest.permission.WRITE_SECURE_SETTINGS,Manifest.permission.CAMERA}
//                        , READ_MAIN_SCREEN);
            }


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("COUNTER",NUMBER_TIME-1);
        editor.apply();
        final String userID = preferences.getString("PHONECONNECT", "");
//        buttonBIO.setText("User id: "+userID);
        buttonBIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPressOne) {
                    isPressOne=false;
                    imageItems = new ArrayList<ImageItem>();
                    imageItems.clear();
                    imageItems = getData(getResources());
                    int min = 0;
                    int max = imageItems.size() - 1;

                    Random r = new Random();
                    int i1 = r.nextInt(max - min + 1) + min;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(MainActivity.this, ByOnlineActivity.class);
                            startActivity(intent);
                            MainActivity.this.finish();
                        }
                    });
                    imageItem = imageItems.get(i1);
                    builder.setTitle("Find :" + imageItems.get(i1).getTitle());

                    TextView title = new TextView(MainActivity.this);
                    title.setText("Training " + (NUMBER_TIME - preferences.getInt("COUNTER", NUMBER_TIME)) + " / " + NUMBER_TIME);

                    title.setPadding(10, 20, 10, 0);
                    title.setGravity(Gravity.START);
                    title.setTextColor(Color.BLACK);
                    title.setTextSize(20);
                    TextView msg = new TextView(MainActivity.this);
                    msg.setText("Search and tab " + MainActivity.imageItems.get(i1).getTitle());
                    msg.setPadding(10, 14, 10, 150);
                    msg.setGravity(Gravity.START);
                    msg.setTextSize(16);
                    msg.setTextColor(Color.GRAY);
                    builder.setCustomTitle(title);
                    builder.setView(msg);
                    builder.setCancelable(false);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // ask permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS}, READ_MAIN_SCREEN);
        } else {
        }
    }





    /**
     * get list of all item with image, name & price with generating items by random categories
     * @return
     */
    public static ArrayList<ImageItem> getData(Resources resources) {
        ArrayList<ImageItem> imageItemAll = new ArrayList<>();

        final int [] TITLEIMG = new int[]
                {R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,
                        R.drawable.w8,R.drawable.w9 ,R.drawable.w10,R.drawable.w11,R.drawable.w12};
        final double [] MONEY = new double[]
                {45.7,56.2,34.45,50.00,25.70,34.15,10.0,45.0,11.99,87.50,55.45,42.50,35.55,65.05,85.99,44.60,55.55};
        ArrayList<ImageItem> imageItemWatch = new ArrayList<>();
        for (int i = 0; TITLEIMG.length > i; i++) {

            imageItemWatch.add(new ImageItem(TITLEIMG[i], "Watch#" + i,""+MONEY[i],CategoryJewel.WATCH));
            imageItemWatch = generateData(imageItemWatch);
        }
        ArrayList<ImageItem> imageItemRing = new ArrayList<>();
        final int [] TITLEIMG2 = new int[]
                {R.drawable.r1,R.drawable.r2,R.drawable.r3,R.drawable.r4,R.drawable.r5,R.drawable.r6,
                        R.drawable.r7,R.drawable.r8,R.drawable.r9,R.drawable.r10,R.drawable.r11,R.drawable.r12};
        final double [] MONEY2 = new double[]
                {87.50,55.45,42.50,35.55,65.05,85.99,55.55,75.25,43.55,25.80,75.99,25.65};
        for (int i = 0; TITLEIMG2.length > i; i++) {
            imageItemRing.add(new ImageItem(TITLEIMG2[i], "Ring #" + i,""+MONEY2[i],CategoryJewel.RING));
            imageItemRing = generateData(imageItemRing);
        }

        ArrayList<ImageItem> imageItemEarrings = new ArrayList<>();

        final int [] TITLEIMG3 = new int[]
                {R.drawable.ea1,R.drawable.ea2,R.drawable.ea3,R.drawable.ea4,R.drawable.ea5,R.drawable.ea6,
                        R.drawable.ea7,R.drawable.ea8,R.drawable.ea9,R.drawable.ea10,R.drawable.ea11,R.drawable.ea12};
        final double [] MONEY3 = new double[]
                {87.50,55.45,42.50,35.55,65.05,85.99,55.55,75.25,43.55,25.80,75.99,5.65};
        for (int i = 0; TITLEIMG3.length > i; i++) {
            imageItemEarrings.add(new ImageItem(TITLEIMG3[i], "Earrings #" + i,""+MONEY3[i],CategoryJewel.EARRINGS));
            imageItemEarrings = generateData(imageItemEarrings);
        }

        ArrayList<ImageItem> imageItemBracelet = new ArrayList<>();
        final int [] TITLEIMG4 = new int[]
                {R.drawable.br1,R.drawable.br2,R.drawable.br3,R.drawable.br4,R.drawable.br5,R.drawable.br6,
                        R.drawable.br7,R.drawable.br8,R.drawable.br9,R.drawable.br10,R.drawable.br11,R.drawable.br12};
        final double [] MONEY4 = new double[]
                {87.50,55.45,42.50,35.55,65.05,85.99,55.55,75.25,43.55,25.80,75.99,25.65};
        for (int i = 0; TITLEIMG4.length > i; i++) {
            imageItemBracelet.add(new ImageItem(TITLEIMG4[i], "Bracelet #" + i,""+MONEY4[i],CategoryJewel.BRACELET));
            imageItemBracelet = generateData(imageItemBracelet);
        }
        ArrayList<ImageItem> imageItemPendant = new ArrayList<>();

        final int [] TITLEIMG5 = new int[]
                {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,
                        R.drawable.p7,R.drawable.p8,R.drawable.p9,R.drawable.p10,R.drawable.p11,R.drawable.p12};
        final double [] MONEY5 = new double[]
                {87.50,55.45,42.50,35.55,65.05,85.99,55.55,75.25,43.55,25.80,75.99,5.65};
        for (int i = 0; TITLEIMG5.length > i; i++) {
            imageItemPendant.add(new ImageItem(TITLEIMG5[i], "Pendant #" + i,""+MONEY5[i],CategoryJewel.PENDANT));
            imageItemPendant = generateData(imageItemPendant);
        }
        ArrayList<CategoryJewel> categoryJewels = new ArrayList<>();
        categoryJewels.add(CategoryJewel.BRACELET);
        categoryJewels.add(CategoryJewel.WATCH);
        categoryJewels.add(CategoryJewel.EARRINGS);
        categoryJewels.add(CategoryJewel.RING);
        categoryJewels.add(CategoryJewel.PENDANT);
        ArrayList<CategoryJewel> categoryJewelTemp= new ArrayList<>();

        categoryJewelTemp.addAll(categoryJewels);
        while (!categoryJewelTemp.isEmpty()) {

            int min = 0;
            int max = categoryJewelTemp.size()-1;
            Random r = new Random();
            int i1 = r.nextInt(max - min + 1) + min;
            switch (categoryJewelTemp.get(i1)){
                case RING:
                    imageItemAll.addAll(imageItemRing);
                    break;
                case WATCH:
                    imageItemAll.addAll(imageItemWatch);
                    break;
                case BRACELET:
                    imageItemAll.addAll(imageItemBracelet);
                    break;
                case EARRINGS:
                    imageItemAll.addAll(imageItemEarrings);
                    break;
                case PENDANT:
                    imageItemAll.addAll(imageItemPendant);
                    break;
            }
            categoryJewelTemp.remove(i1);
        }
        return imageItemAll;
    }

    /**
     * resize bitmap auto, because some model (like xiaomi note 3,samsung s6) get error with bitmap factory
     * @param image
     * @param newHeight
     * @param newWidth
     * @return
     */
//    private static Bitmap getResizedBitmap(Bitmap image, int newHeight, int newWidth) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//        // create a matrix for the manipulation
//        Matrix matrix = new Matrix();
//        // resize the bit map
//        matrix.postScale(scaleWidth, scaleHeight);
//        // recreate the new Bitmap
//        Bitmap resizedBitmap = Bitmap.createBitmap(image, 0, 0, width, height,
//                matrix, false);
//        return resizedBitmap;
//    }
    /**
     * get list & return random mix list
     * @param imageItems
     * @return
     */
    private  static ArrayList<ImageItem> generateData(ArrayList<ImageItem> imageItems) {
        ArrayList<ImageItem> imageItemTemp= new ArrayList<>();
        ArrayList<ImageItem>imageItemsToFull = new ArrayList<>();
        imageItemTemp.addAll(imageItems);
        while (!imageItemTemp.isEmpty()){
            int min = 0;
            int max = imageItemTemp.size()-1;
            Random r = new Random();
            int i1 = r.nextInt(max - min + 1) + min;
            ImageItem imageItem = imageItemTemp.get(i1);
            imageItemsToFull.add(imageItem);
            imageItemTemp.remove(i1);
        }
        return imageItemsToFull;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void forceLTRSupported(AppCompatActivity activity)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

}