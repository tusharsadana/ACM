package vat.hacksociety.acm.acm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    DatabaseReference mdatabase, course;
    IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonScan;
        buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(this);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());

                    //setting values to textviews

                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        qrScan.initiateScan();
    }


    public void createPythonClass(){
        String pyStudents[] = new String[]{"179301138", "169105041", "179301016", "179301164", "169108077", "179303171", "179301115", "179303130", "169107036", "179301004", "179301003", "179301201", "171006002", "179301083", "179301056", "179301071", "179302160", "179302140", "179403044", "171006009", "179301194", "179302127", "179301151", "169108039", "179202053", "179202113", "179301065", "179302119", "179303037", "179301158", "179302100", "179401036", "179303089", "179301117", "179302053", "179301034", "169109145", "179202161", "169109002", "179303040", "179301135", "179301070", "179301192", "179202142", "179302148", "179301148", "179402094", "179301175", "179202121", "179301177", "179302138", "179301229", "179301033", "179302052", "179202106", "179202132", "179301128", "179301037", "179301227", "179302108", "179301114", "179301078", "179302050", "179302124", "179301118", "179301072", "179403054", "179301161", "179202088", "179301222", "179303117", "179303136", "179202170", "179303026", "179402069", "179303104", "179303101", "179303118", "169108019", "179301050", "179301086", "179302065", "179301178", "179301125", "179301218", "179303106", "179302130", "179201015", "179303128", "179302152", "179302001", "179302091", "179402092", "179301015", "179302024", "179301206", "179402161", "179301139", "179302003", "179202128", "179202105", "179303096", "179202033", "179201010", "179302068", "179303144", "179303129", "179302028", "179301109", "179303025", "179302142", "179302176", "179403053", "179301108", "179202123", "179301228"};
        mdatabase = FirebaseDatabase.getInstance().getReference();
        course = mdatabase.child("python");



        for (String var : pyStudents) {


            DatabaseReference ref = course.child(var).child("attendance");

            Date date = new Date();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = df.format(date).toString();

            ref.child(dateString).setValue("absent");
        }

    }


}
