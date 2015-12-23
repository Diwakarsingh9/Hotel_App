package com.wyndhamgarden.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.ArrayList;

//
public class GCMNotificationIntentService extends GcmListenerService {
//	// Sets an ID for the notification, so it can be updated
	public static final int notifyID = 9001;
	public  static ArrayList<String> message11 = new ArrayList<>();
//	NotificationCompat.Builder builder;
//
//	public GCMNotificationIntentService() {
//		super("GcmIntentService");
//	}
//
//
//	@Override
//	protected void onHandleIntent(Intent intent) {
//		Bundle extras = intent.getExtras();
//		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
//
//		String messageType = gcm.getMessageType(intent);
//
//		if (!extras.isEmpty()) {
//			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
//					.equals(messageType)) {
//				sendNotification("Send error: " + extras.toString());
//			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
//					.equals(messageType)) {
//				sendNotification("Deleted messages on server: "
//						+ extras.toString());
//			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
//					.equals(messageType)) {
//				sendNotification("Message Received from Google GCM Server:\n\n"
//						+ extras.get(ApplicationConstants.MSG_KEY));
//			}
//		}
//		GcmBroadcastReceiver.completeWakefulIntent(intent);
//	}
//


	@Override
	public void onMessageReceived(String from, Bundle data) {
		message11.clear();
		String msg = data.getString("message");
		String[] splited = msg.split("Notification ");

		String msg1 = splited[1];
		Log.e("tatatat", ""+msg1);
		sendNotification(msg1);
	}


	private void sendNotification(String message) {
//		int icon = R.drawable.wyndhamcon;
//		long when = System.currentTimeMillis();
//		NotificationManager notificationManager = (NotificationManager)
//				this.getSystemService(Context.NOTIFICATION_SERVICE);
//		Notification notification = new Notification(icon, message, when);
//
//		String title = this.getString(R.string.app_name);
//
//		Intent notificationIntent = new Intent(this, MainActivity.class);
//		notificationIntent.putExtra("message", message);
//		// set intent so it does not start a new activity
//		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//				Intent.FLAG_ACTIVITY_SINGLE_TOP);
//		PendingIntent intent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//		notification.setLatestEventInfo(this, title, message, intent);
//		notification.flags |= Notification.FLAG_AUTO_CANCEL;
//
//		// Play default notification sound
//		notification.defaults |= Notification.DEFAULT_SOUND;
//
//		// Vibrate if vibrate is enabled
//		notification.defaults |= Notification.DEFAULT_VIBRATE;
//		notificationManager.notify(0, notification);
		Intent resultIntent = new Intent(this, MainActivity.class);
		resultIntent.putExtra("msg", message);
//		MainActivity.activity22.finish();
		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
				resultIntent, PendingIntent.FLAG_ONE_SHOT);

		NotificationCompat.Builder mNotifyBuilder;
		NotificationManager mNotificationManager;

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		mNotifyBuilder = new NotificationCompat.Builder(this)
				.setContentTitle("Wyndham Grantham")
				.setContentText("" + message)
				.setSmallIcon(R.drawable.pnlogo);
		// Set pending intent
		mNotifyBuilder.setContentIntent(resultPendingIntent);

		// Set Vibrate, Sound and Light
		int defaults = 0;
		defaults = defaults | Notification.DEFAULT_LIGHTS;
		defaults = defaults | Notification.DEFAULT_VIBRATE;
		defaults = defaults | Notification.DEFAULT_SOUND;

		mNotifyBuilder.setDefaults(defaults);
		// Set the content for Notification
		mNotifyBuilder.setContentText(""+message);
		// Set autocancel
		mNotifyBuilder.setAutoCancel(true);
		// Post a notification
		mNotificationManager.notify(notifyID, mNotifyBuilder.build());

	}
}
//public class GCMNotificationIntentService extends GcmListenerService {
//
//
//
//}