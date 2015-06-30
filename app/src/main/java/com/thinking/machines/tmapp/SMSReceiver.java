package com.thinking.machines.tmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by Abbas on 4/28/2015.
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] sms = (Object[]) bundle.get("pdus");
            String message = "";
            SmsMessage smsMessage;
            int i = 0;
            while (i < sms.length) {
                smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
                message = message + "From : " + smsMessage.getOriginatingAddress();
                message = message + ", Message : " + smsMessage.getMessageBody().toString();
                message += "\n";
                i++;
            }
            StartupActivity startupActivity = StartupActivity.getStartupActivity();
            startupActivity.setSMSData(message);
        }

    }
}
