package com.paygilant.myshop;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.paygilant.myshop.OnLine.ByOnlineActivity;

import java.io.FileOutputStream;


public class ConnectActivity extends AppCompatActivity {

    Button buttonRegister,buttonLogin,buttonConnect;
    EditText editTextConnect;
//    PaygilantManager paygilantHandler;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
//    private PaygilantScreenListener actionManager;
    public static final String PATH_IMAGE_DIR= "Paygilant_image";

    public static final int READ_PHONE_STATE_PERMISSION = 100;
    boolean isReg;
    boolean isPress = false;
//    ProgressDialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ui_appBar_start)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        isReg = true;

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonConnect = (Button) findViewById(R.id.buttonConnect);
        editTextConnect = (EditText) findViewById(R.id.editTextConnect);
        View v = findViewById(R.id.activity_connect);
        editTextConnect.setInputType(InputType.TYPE_CLASS_PHONE );

        editTextConnect.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() > 0) {
                    buttonConnect.setBackgroundColor(getResources().getColor(R.color.ui_appBar_start));
                } else {
                    buttonConnect.setBackgroundColor(getResources().getColor(R.color.colorConnectButton));
                }
            }
        });

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String userID = preferences.getString("PHONECONNECT", "");
        if (!userID.equals("")) {
//            myDialog = new ProgressDialog(ConnectActivity.this);
//            myDialog.setMessage("Loading...");
//            myDialog.show();

//            paygilantHandler.init(this.getApplicationContext(), SERVER_URL, userID);
//            paygilantHandler = PaygilantManager.getInstance();
//            paygilantHandler.arriveToCheckPoint(new Launch(CheckPoint.CheckPointType.LAUNCH));


            editor = preferences.edit();
            editor.putString("PHONECONNECT", userID);
            editor.apply();
            Intent intent = new Intent(ConnectActivity.this, ByOnlineActivity.class);
            intent.putExtra("IF_NOT_FIRST_TIME" , true);
//            if(actionManager != null){
//                actionManager.finishScreenListener();
//            }
            startActivity(intent);
            finish();

        } else {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.READ_CALL_LOG,Manifest.permission.BLUETOOTH,Manifest.permission.WRITE_SECURE_SETTINGS,Manifest.permission.CAMERA}
                        , READ_PHONE_STATE_PERMISSION);
            }
            long startTime = System.currentTimeMillis();
//            paygilantHandler.init(this.getApplicationContext(), SERVER_URL, null);
//            paygilantHandler = PaygilantManager.getInstance();
//            paygilantHandler.arriveToCheckPoint(new Launch(CheckPoint.CheckPointType.LAUNCH));


//            actionManager = paygilantHandler.startNewScreenListener(PaygilantManager.ScreenListenerType.LOGIN_FORM,10);
//            actionManager.setTouchToAllChildren(v);

            long endTme = System.currentTimeMillis();
            Log.d("Instruction", "time to init paygilant manager :" + (endTme - startTime));
        }

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isReg = true;
                buttonRegister.setBackgroundColor(getResources().getColor(R.color.selected_button));
                buttonLogin.setBackgroundColor(getResources().getColor(R.color.white_Color));
                buttonRegister.setTextColor(getResources().getColor(R.color.white_Color));
                buttonLogin.setTextColor(getResources().getColor(R.color.unselected_text));

//                regProcces();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isReg = false;
                buttonLogin.setBackgroundColor(getResources().getColor(R.color.selected_button));
                buttonRegister.setBackgroundColor(getResources().getColor(R.color.white_Color));
                buttonLogin.setTextColor(getResources().getColor(R.color.white_Color));
                buttonRegister.setTextColor(getResources().getColor(R.color.unselected_text));
//                loginProcces();
            }
        });

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextConnect.length() > 0) {
                    if (!isPress){
                        isPress=true;
                        if (isReg){
                            regProcess();
                        }else{
                            loginProcess();
                        }
                    }
                } else
                    Toast.makeText(ConnectActivity.this, "Insert Phone Number", Toast.LENGTH_LONG).show();
            }
        });

    }



    /**
     * collect data from register status and get risk level with message
     */
    private void regProcess() {
        final long startTime = System.currentTimeMillis();
        Log.d("REGISTER", "START");

        editor = preferences.edit();
        editor.putString("PHONECONNECT", editTextConnect.getText().toString());
        editor.apply();
//        paygilantHandler = PaygilantManager.getInstance();

        final String s = editTextConnect.getText().toString();


        editor = preferences.edit();

        editor.putString("PHONECONNECT", s);

        editor.apply();
        boolean isPress = true;
        Intent intent = new Intent(ConnectActivity.this, ByOnlineActivity.class);
        startActivity(intent);
//                        actionManager.finishScreenListener();
        finish();


//        Registration currReg = new Registration(s);
//        String requestId = paygilantHandler.getRiskForCheckPoint(currReg, new PaygilantCommunication() {
//            @Override
//            public void receiveRisk(final int riskLevel, String signedRisk, final String requestId) {
//                long endTime = System.currentTimeMillis();
//
//                Log.d("REGISTER", "END "+(endTime-startTime));
//
////                AlertDialog.Builder builder = new AlertDialog.Builder(ConnectActivity.this);
////
////                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int id) {
//                    SharedPreferences.Editor editor = preferences.edit();
//                paygilantHandler.setUserId(s);
//
//                editor.putString("PHONECONNECT", s);
//
//                        editor.apply();
//                boolean isPress = true;
//                        Intent intent = new Intent(ConnectActivity.this, MainActivity.class);
//                        startActivity(intent);
////                        actionManager.finishScreenListener();
//                        finish();                        // User clicked OK button
////                    }
////                });
//                // Set other dialog properties
////                builder.setTitle("Paygilant Score is: " + riskLevel);
////                AlertDialog dialog = builder.create();
////                dialog.show();
//            }
//        });
    }
    /**
     * collect data from login status and get risk level with message
     */
    private void loginProcess() {

        final long startTime = System.currentTimeMillis();
        Log.d("LOGIN", "START");
//            paygilantHandler = PaygilantManager.getInstance();
            final String s = editTextConnect.getText().toString();
        editor = preferences.edit();

        editor.putString("PHONECONNECT", s);
        editor.apply();
        Intent intent = new Intent(ConnectActivity.this, ByOnlineActivity.class);
        startActivity(intent);
        finish();
//        Login currLogin = new Login(s);
//            String requestId = paygilantHandler.getRiskForCheckPoint(currLogin, new PaygilantCommunication() {
//                @Override
//                public void receiveRisk(final int riskLevel, String signedRisk, final String requestId) {
//                    long endTime = System.currentTimeMillis();
//
//                    Log.d("LOGIN", "END "+(endTime-startTime));
//
////                    AlertDialog.Builder builder = new AlertDialog.Builder(ConnectActivity.this);
//
////                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                        public void onClick(DialogInterface dialog, int id) {
//                    editor = preferences.edit();
//
//                    editor.putString("PHONECONNECT", s);
//                    paygilantHandler.setUserId(s);
//                    editor.apply();
//                    Intent intent = new Intent(ConnectActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    actionManager.finishScreenListener();
//
//                    finish();                        // User clicked OK button
////                        }
////                    });
////                    // Set other dialog properties
////                    builder.setTitle("Paygilant Score is: " + riskLevel);
////
////                    AlertDialog dialog = builder.create();
////                    dialog.show();
//                }
//            });
//            try {
//                Thread.sleep(100);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {


//        paygilantHandler = PaygilantManager.getInstance();
//
//        if (paygilantHandler != null)
//            paygilantHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    @Override
    protected void onResume(){
//        if (actionManager!=null)
//            actionManager.resumeListen();
        super.onResume();
    }
    @Override
    protected void onPause() {
//        if (actionManager!=null)
//            actionManager.pauseListenToSensors();
        super.onPause();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
     void saveImage(String pthAndFylTtlVar, Bitmap iptBmjVar)
    {
        try
        {
            FileOutputStream fylBytWrtrVar = new FileOutputStream(pthAndFylTtlVar);
            iptBmjVar.compress(Bitmap.CompressFormat.PNG, 100, fylBytWrtrVar);
            fylBytWrtrVar.close();
        }
        catch (Exception errVar) { errVar.printStackTrace(); }
    }
    Bitmap getImage(String pthAndFylTtlVar)
    {
        return BitmapFactory.decodeFile(pthAndFylTtlVar);
    }
}



