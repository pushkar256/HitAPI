package com.example.cbluser22.hitapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cbluser22.hitapi.R;
import com.example.cbluser22.hitapi.pojo.PojoBase;
import com.example.cbluser22.hitapi.utils.ConnectionDetector;
import com.example.cbluser22.hitapi.webservices.RestClient;
import com.example.cbluser22.hitapi.webservices.models.SignupModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ConnectionDetector connectionDetector;
    ProgressBar pb;
    Button btnSignUp;
    EditText etName,etEmail,etPassword,etPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignUp=(Button)findViewById(R.id.btn_signup);
        etEmail=(EditText)findViewById(R.id.et_email);
        etPassword=(EditText)findViewById(R.id.et_password);
        etName=(EditText)findViewById(R.id.et_name);
        etPhone=(EditText)findViewById(R.id.et_contact);
        pb=(ProgressBar)findViewById(R.id.pb_progress);

        connectionDetector=new ConnectionDetector(this);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                signup();
            }
        });



    }

    private void signup() {

    //   if (connectionDetector.isConnectingToInternet())
      //  {
            Map<String,String> map=new HashMap<>();
            map.put("name",etName.getText().toString().trim());
            map.put("appVersion","1");
            map.put("deviceToken","23132131");
            map.put("deviceType","ANDROID");
            map.put("email",etEmail.getText().toString().trim());
            map.put("password",etPassword.getText().toString().trim());
            map.put("countryCode","+91");
            map.put("cityId","58930e59731ddfe7477ac95f");
            map.put("phoneNo",etPhone.getText().toString().trim());
            /*SignupModel model=new SignupModel();
            model.appVersion="1";
            model.phoneNo=etPhone.getText().toString().trim();
            model.deviceToken="23132131";
            model.deviceType="c";
            model.email=etEmail.getText().toString().trim();
            model.password=etPassword.getText().toString().trim();
            model.countryCode="+91";
            model.name=etName.getText().toString().trim();
            model.cityId="58930e59731ddfe7477ac95f";*/

            RestClient.getClient().signup(map).enqueue(new Callback<PojoBase>() {
                @Override
                public void onResponse(Response<PojoBase> response, Retrofit retrofit) {
                    if (response.isSuccess())
                    {
                        pb.setVisibility(View.INVISIBLE);
                        PojoBase base=response.body();
                        String name=base.data.data.name;
                        Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
                    }
                    else {
                        try {
                            pb.setVisibility(View.INVISIBLE);
                            JSONObject jsonObject=new JSONObject(response.errorBody().string());
                            Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(MainActivity.this,"failure",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
//}
