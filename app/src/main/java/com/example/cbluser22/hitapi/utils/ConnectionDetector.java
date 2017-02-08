package com.example.cbluser22.hitapi.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

import com.example.cbluser22.hitapi.R;

/**
 * Created by cbluser22 on 8/2/17.
 */

public class ConnectionDetector {

    private Activity context;

    public ConnectionDetector(Activity context)
    {
        this.context=context;
    }

    public boolean isConnectingToInternet()
    {
        ConnectivityManager connectivity=(ConnectivityManager)context.
                getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivity==null)
        {
            NetworkInfo activeNetworkInfo =connectivity.getActiveNetworkInfo();
            boolean isConnected=activeNetworkInfo!=null&&activeNetworkInfo.isConnected();
            return isConnected;
        }
        return  false;
    }

   /* public void createLoginExpiredDialog() {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(context.getResources().getString(R.string.login_expired))
                .setMessage(context.getResources().getString(R.string.login_again))
                .setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PrefsManager.with(context).removeAll();
                        dialog.dismiss();
                        context.startActivity(new Intent(context, LoginActivity.class));
                        context.finishAffinity();
                    }
                }).show();

    }*/
}
