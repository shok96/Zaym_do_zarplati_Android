package com.dozarplati.zaim.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;



public class FIrebaseU {
    private static FIrebaseU instance;

    FIrebaseU(){
        this.instance = this;
    }

    public static FIrebaseU getInstance() {
        return instance;
    }

    public void getInstanceId(Context con){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
//                        String msg = con.getString(R.string.msg_token_fmt, token);
//                        log(msg);

                    }
                });
    }

}
