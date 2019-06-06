package com.paygilant.myshop.OnLine;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;


import com.paygilant.myshop.MainActivity;
import com.paygilant.myshop.R;
import com.paygilant.myshop.ResultActivityAmount;

//import com.paygilant.deviceidetification.fingerprintdialog.FingerprintAuthenticationDialogFragment;

import java.util.ArrayList;

import javax.crypto.Cipher;

import static com.paygilant.myshop.MainActivity.forceLTRSupported;


public class ByOnlineActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    SharedPreferences preferences;
    private ArrayList<ImageItem> imageItems;
    SearchView searchView;
    public  boolean isPress =false;
    private  Boolean canAccess = true;

    public boolean isScanFirst = false;
    public boolean isScanFirst() {
        return isScanFirst;
    }

    public void setScanFirst(boolean scanFirst) {
        isScanFirst = scanFirst;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_online);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ui_appBar_start)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        forceLTRSupported(this);
//        bitmap = getImage(Environment.getExternalStorageDirectory()+File.separator+ "test.png");

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.gridView1);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        imageItems = new ArrayList<>();
        imageItems = MainActivity.getData(getResources());


        View v = findViewById(R.id.activity_by_online);
//        purchaseButton = findViewById(R.id.send);

        adapter = new MyRecyclerViewAdapter(this, imageItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setClickListener(ByOnlineActivity.this);


        preferences = PreferenceManager.getDefaultSharedPreferences(this);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

// do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText=newText.toLowerCase();
                ArrayList<ImageItem> newList=new ArrayList<ImageItem>();
                for(ImageItem imageItem:imageItems){
                    String title =imageItem.getTitle().toLowerCase();
                    if (title.startsWith(newText)){
                        newList.add(imageItem);
                    }
                }
                adapter.setFilter(newList);
// do something when text changes
                return true;
            }
        });
//        actionManager.setTouchToAllChildren(v);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.icon_back:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        Log.d("Amount", "onResume");

//        actionManager.resumeListen();

        super.onResume();

    }
    @Override
    public void onStart(){
        Log.d("Amount", "onStart");
        super.onStart();
    }
    @Override
    public void onPause(){
        Log.d("Amount", "onPause");
//        actionManager.pauseListenToSensors();

        super.onPause();
    }

    @Override
    public void onStop(){
        Log.d("Amount", "onStop");

        super.onStop();
    }

    @Override
    public void onDestroy(){
        Log.d("Amount", "onDestory");
//        actionManager.finishScreenListener();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

       // super.onBackPressed();
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.app_name);
        alertDialogBuilder.setMessage("Do You Want To Exit App?");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
           /* Intent intent = new Intent(this, Amount.class);
            startActivity(intent);
            finish();*/
    }
    @Override
    public void onItemClick(View view, int position) {

//        ImageItem item = (ImageItem) adapter.getItem(position);

//            if (item.getTitle().equals(imageItems.get(numRan).getTitle())) {
        if (!isPress) {
            isPress = true;
            Intent intent = new Intent(ByOnlineActivity.this, ResultActivityAmount.class);

            if (canAccess){
                intent.putExtra("RISK_RESULT", 0);

            }else {
                intent.putExtra("RISK_RESULT", 3);
            }
            startActivity(intent);
            finish();

        }

    }
    public class PurchaseButtonClickListener implements View.OnClickListener {

        Cipher mCipher;
        String mKeyName;
        int riskLevel;

        PurchaseButtonClickListener(Cipher cipher, String keyName) {
            mCipher = cipher;
            mKeyName = keyName;
        }

        @Override
        public void onClick(View view) {

        }
    }


}
