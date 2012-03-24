package parentalcontrol.parent.Logic;

import parentalcontrol.parent.GUI.SMSReceiveActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/*
 * This message listen for system broadcast messages
 * when msg received. it call the activity SMSReceiveActivity and show the message*/
public class SMSReceiveService extends BroadcastReceiver {

	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(SMS_RECEIVED)) {//chaeck weather sms received

			Bundle bundle = intent.getExtras();//get the additional information add with intent
			if (bundle != null && bundle.containsKey("pdus")) {
				Object[] pdusObj = (Object[]) bundle.get("pdus");//get the msg objects
				SmsMessage[] messages = new SmsMessage[pdusObj.length];

				// getting SMS information from Pdu
				for (int i = 0; i < pdusObj.length; i++) {
					messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
				}
				
				/*create a new activity and call for show details*/
				Intent i = new Intent(context, SMSReceiveActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				String[] a = new String[10];
				int c = 0;
				for (SmsMessage currentMessage : messages) {
					String displayOriginatingAddress = currentMessage
							.getDisplayOriginatingAddress(); // has sender's
																// phone number
					String displayMessageBody = currentMessage
							.getDisplayMessageBody(); // has the actual message
					a[c] = displayOriginatingAddress + ":" + displayMessageBody;
					c++;
				}
				i.putExtra("msgset", a);//adding msg data to send for the activity
				context.startActivity(i);
			}
		}
	}
}
