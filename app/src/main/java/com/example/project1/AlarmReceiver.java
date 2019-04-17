package com.example.project1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Uri notification= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone r=RingtoneManager.getRingtone(context.getApplicationContext(),notification);
        r.play();
        ///sldknflksdnklfnsklnflknsdlkfndsnflkslknfdsknlf
        Toast.makeText(context,"Alarm Received",Toast.LENGTH_LONG).show();
    }
}
