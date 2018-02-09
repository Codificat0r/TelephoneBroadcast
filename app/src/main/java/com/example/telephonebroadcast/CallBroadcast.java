package com.example.telephonebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by usuario on 9/02/18.
 */

public class CallBroadcast extends BroadcastReceiver {

    //DECLARAMOS EL RECEIVER EN EL MANIFEST Y NO HACE FALTA HACER NADA MAS YA
    //EMPIEZA A RECIBIR. EL INTENT DE LLAMADA.

    private static final String TAG = "com.example.Telephone";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"Se ha recogido el intent");
    }
}
