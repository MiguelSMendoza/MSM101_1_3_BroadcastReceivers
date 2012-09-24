package es.netrunners.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SecondReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int value = intent.getIntExtra("Random", -1);
		if (value > 0) {
			Bundle bundle = getResultExtras(true);
			bundle.putInt("SecondReceiver", value * 17);
			setResultExtras(bundle);
		}
	}

}
