package com.example.telephonebroadcast;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by usuario on 12/02/18.
 */

public class CallInformation extends AppCompatActivity {

    private TextView txvNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_information_activity);

        txvNumero = findViewById(R.id.txvNumero);
        String number = getIntent().getExtras().getString("number");
        txvNumero.setText(number);
    }
}
