package es.netrunners.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

public class CallnSMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// check what happened
		String action = intent.getAction();
		// Check if there´s an incoming call
		if (action.equalsIgnoreCase("android.intent.action.PHONE_STATE")) {
			if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
					TelephonyManager.EXTRA_STATE_RINGING)) {
				String IncomingNumber = intent
						.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
				// Do Something
			}
		} else { // If not it must be a new SMS received
			Bundle bundle = intent.getExtras();
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0]);
			if (!message.isEmail()) {
				String number = message.getOriginatingAddress();
				// Do Something
			}
		}
	}
}
