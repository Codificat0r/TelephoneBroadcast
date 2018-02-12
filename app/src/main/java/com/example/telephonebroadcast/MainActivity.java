package com.example.telephonebroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIntent = findViewById(R.id.btnIntent);
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendIntentBroadcasted();
            }
        });
    }

    //Vamos a simular el envio como si fueramos el TelephonyManager que envia un broadcast.
    public void sendIntentBroadcasted() {
        //El action es el nombre que hay que poner en el manifest para obtener el item de un broadcast en un
        //broadcast receiver.
        Intent intent = new Intent("com.example.callbroadcast.intent");
        Bundle bundle = new Bundle();
        bundle.putString(TelephonyManager.EXTRA_STATE, "RINGING");
        bundle.putString(TelephonyManager.EXTRA_INCOMING_NUMBER, "680805424");
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }
}
