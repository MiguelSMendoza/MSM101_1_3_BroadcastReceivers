package es.netrunners.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class LastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = getResultExtras(false);
		StringBuilder builder = new StringBuilder();
		builder.append("---Last Receiver---\n");
		builder.append("<<>>" + intent.getIntExtra("Random", -1) + "\n");
		builder.append("<<>>" + bundle.getInt("FirstReceiver") + "\n");
		builder.append("<<>>" + bundle.getInt("SecondReceiver") + "\n");

		Toast.makeText(context, builder.toString(), Toast.LENGTH_LONG).show();
	}

}
