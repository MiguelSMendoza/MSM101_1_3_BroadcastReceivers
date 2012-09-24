package es.netrunners.broadcastreceivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		CharSequence from = "NetRunners";
		CharSequence message = "Broadcast Notification ...";
		Intent in = new Intent(context, BroadcastReceiversActivity.class);
		in.putExtra("Notification", true);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, in,
				PendingIntent.FLAG_CANCEL_CURRENT);
		Notification notification = new Notification(R.drawable.ic_launcher,
				message, System.currentTimeMillis());
		notification.setLatestEventInfo(context, from, message, contentIntent);
		nm.notify(1, notification);
	}

}
