package br.com.ews.receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import br.com.ews.broadcastreceiver.BuildConfig;

/**
 * Created by wallace on 06/11/16.
 * <p></p>
 * Para que um SMS consiga trafegar entre celulares mais novos e antigos, as mensagens possuem um
 * formato específico, chamado de PDU (protocol data unit).
 * <br />
 * Quando escrevemos um SMS, cada mensagem deve conter 160 caracteres. Em alguns celulares, quando
 * escrevemos uma quantidade superior a essa, a mensagem será quebrada em duas mensagens ou mais - cada
 * uma terá um pdu. E por isso, teremos um array com vários pdus.
 * <br />
 * No cabeçalho de cada pdu estará um número de telefone. Se não for uma uma "multimensagem",
 * podemos aproveitar o pdu do primeiro SMS no array, ou seja, aquela que ocupar a posição 0.
 *
 */
public class SMSReceiver extends BroadcastReceiver {

    private static final String TAG = "SMSReceiver";

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        //Objem o formato da mensagem
        String formato = (String) intent.getSerializableExtra("format");
        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        Log.d(TAG, "URL --> " +  BuildConfig.URL);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "DisplayOriginatingAddress: " + sms.getDisplayOriginatingAddress());
            Log.d(TAG, "DisplayMessageBody: " + sms.getDisplayMessageBody());
            Log.d(TAG, "MessageBody: " + sms.getMessageBody());
        }

        Toast.makeText(context, "SMSReceiver: " + sms.getDisplayMessageBody(), Toast.LENGTH_SHORT).show();

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

    }

}
