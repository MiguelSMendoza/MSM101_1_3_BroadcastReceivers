package es.netrunners.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("Message");   
        Vibrator vib = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);     
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        vib.vibrate(2000);
	}
}
