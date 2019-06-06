package com.paygilant.myshop;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.paygilant.myshop.OnLine.ByOnlineActivity;

import java.util.ArrayList;

import static com.paygilant.myshop.MainActivity.forceLTRSupported;

public class ResultActivityAmount extends AppCompatActivity {

    String [] results = {"LOW","MEDUIM","HIGH","VERY HIGH"};
    Button buttonTryAgain;
    ImageView imageResult1,imageResult2,imageResult3,imageResult4,imageResult5,imageSumResult;
    TextView textNumResult, textScoreHigh,textTryAnotherFlow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ui_appBar_start)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        forceLTRSupported(this);

        ArrayList<ImageView> imageViews = new ArrayList<ImageView>();

        textTryAnotherFlow = findViewById(R.id.textTryAnotherFlow);
        buttonTryAgain = findViewById(R.id.buttonTryAgain);
        imageResult1 = findViewById(R.id.imageResult1);
        textNumResult = findViewById(R.id.textNumResult);
        textScoreHigh = findViewById(R.id.textScoreHigh);
        imageSumResult = findViewById(R.id.imageSumResult);

        int result = getIntent().getIntExtra("RISK_RESULT",-1);
        if ((result>=0) && (result<=3)){
            double resultPercent = ((double)(result+1)/(double)results.length)*100;
            textNumResult.setText( result+"");
            textScoreHigh.setText(results[result]);
            switch (result){
                case 0:
                    imageSumResult.setImageResource(R.drawable.approved);
                    break;
                case 1:
                    imageSumResult.setImageResource(R.drawable.approved);
                    break;
                case 2:
                    imageSumResult.setImageResource(R.drawable.rejected);
                    break;
                case 3:
                    TextView paymentAppText = findViewById(R.id.paymentAppText);
                    ImageView imageResult4 = findViewById(R.id.imageResult4);
                    ImageView imageResult5 = findViewById(R.id.imageResult5);
                    imageSumResult.setImageResource(R.drawable.rejected);
                    imageResult4.setImageResource(R.drawable.smallredcross);
                    imageResult5.setImageResource(R.drawable.smallredcross);
                    paymentAppText.setText("REJECTED");

                    break;
                default:
                    imageSumResult.setImageResource(R.drawable.approved);
                    break;
            }
        }else{
            textNumResult.setText("-");
            textScoreHigh.setText("None");
            imageSumResult.setImageResource(R.drawable.rejected);

        }



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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        Intent intent;
//        switch (id) {
////            case R.id.main_screen:
////                intent = new Intent(this,MainActivity.class);
////                startActivity(intent);
////                finish();
////                break;
////            case R.id.send_money:
//////                intent = new Intent(this,Amount.class);
//////                startActivity(intent);
//////                finish();
////            case R.id.log_out:
////                final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
////                SharedPreferences.Editor editor = preferences.edit();
////                editor.putString("PHONECONNECT", "");
////                editor.apply();
////                intent = new Intent(this,ConnectActivity.class);
////                startActivity(intent);
////                finish();
////                break;
////            case R.id.buy_online:
////                intent = new Intent(this,ByOnlineActivity.class);
////                startActivity(intent);
////                finish();
//////                Toast.makeText(getApplicationContext(), "Help menu item pressed", Toast.LENGTH_SHORT).show();
////                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ResultActivityAmount.this, ByOnlineActivity.class);
        startActivity(intent);
        finish();

    }
}
