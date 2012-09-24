package es.netrunners.broadcastreceivers;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BroadcastReceiversActivity extends Activity {

	BroadcastReceiver receiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final EditText etMsg = (EditText) findViewById(R.id.editText1);
		Button btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String msg = etMsg.getText().toString();
				Intent intent = new Intent(getApplicationContext(),
						MyReceiver.class);
				intent.putExtra("Message", msg);
				getApplicationContext().sendBroadcast(intent);
			}
		});

		Button btnSetAlarm = (Button) findViewById(R.id.btnsetAlarm);
		btnSetAlarm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.SECOND, 20);

				Intent intent = new Intent(getApplicationContext(),
						AlarmReceiver.class);
				intent.setAction("Start");
				PendingIntent sender = PendingIntent.getBroadcast(
						getApplicationContext(), 0, intent, 0);

				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
						sender);
			}
		});

		Button btnOrdererBroadcast = (Button) findViewById(R.id.btnOrdererBroadcast);
		btnOrdererBroadcast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent("de.brockhaus.START");
				intent.putExtra("Random", (int) (Math.random() * 10));

				getApplicationContext().sendOrderedBroadcast(intent, null,
						new LastReceiver(), null, Activity.RESULT_OK, null,
						null);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		receiver = new ConnectivityReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver, filter);

		if (this.getIntent().getExtras() != null) {
			Boolean notif = getIntent().getExtras().getBoolean("Notification");
			NotificationManager nm;
			nm = (NotificationManager) this
					.getSystemService(Context.NOTIFICATION_SERVICE);
			if (notif != null && notif)
				nm.cancel(1);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}
}