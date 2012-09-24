package es.netrunners.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FirstReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int value = intent.getIntExtra("Random", -1);

		Bundle bundle = getResultExtras(true);
		bundle.putInt("FirstReceiver", value * 13);
		setResultExtras(bundle);

		if (value < 0 || value % 2 != 0)
			abortBroadcast();
	}

}
