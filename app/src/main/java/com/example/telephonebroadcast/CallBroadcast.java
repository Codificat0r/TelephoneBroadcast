package com.example.telephonebroadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallBroadcast extends BroadcastReceiver {

    public static final String TAG = "llamadita";
    public static final int CALLNOTIFICATION = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        String state = "";

        //1. Recogemos la info del intent
        Log.d(TAG, "IntentRecogido");

        //Estado de la llamada
        if (intent.getExtras() != null) {
            Bundle b = intent.getExtras();

            state = b.getString(TelephonyManager.EXTRA_STATE);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                //1.1. Recoger el número del teléfono
                String number = b.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                //2. ¿A quién le pasamos la información?
                Intent newIntent = new Intent(context, CallInformation.class);
                newIntent.putExtra("number", number);
                newIntent.putExtra("idNotification", CALLNOTIFICATION);
                //Pending intent es un objeto que se crea a traves de un metodo y es un intent
                //que esta pendiente de ser lanzado al darle a una notificacion. Va ligado a esta.
                PendingIntent pendingIntent = PendingIntent.getActivity(context, CALLNOTIFICATION, newIntent, PendingIntent.FLAG_UPDATE_CURRENT
                        | PendingIntent.FLAG_ONE_SHOT);

                //3. Crear la notificación. Channels se notificaciones a partir de API 27 al parecer
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Inventory");
                //Personalizamos la notificacion.

                //Título que se desplaza. Obligatoriamente debe tener dos iconos. setSmallIcon y
                //setLargeIcon
                builder.setContentTitle("CallBroadcast");
                builder.setSmallIcon(android.R.drawable.sym_call_incoming);
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), android.R.drawable.sym_call_incoming));

                //3.1 Añadir el PendingIntent a la notificacion
                builder.setContentIntent(pendingIntent);
                NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                //Guardamos la referencia a la notificación construida
                Notification n = builder.build();
                //Le sumamos un flag a los que ya tiene para que se autocancele.
                n.flags |= Notification.FLAG_AUTO_CANCEL;
                nm.notify(CALLNOTIFICATION, n);
            }
        }
    }
}
