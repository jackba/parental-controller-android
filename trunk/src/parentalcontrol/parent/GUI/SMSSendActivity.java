package parentalcontrol.parent.GUI;

import parentalcontrol.parent.R;
import parentalcontrol.parent.com.Internet.EmailSender;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SMSSendActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smssendlayout);

		final TextView phonenum = (TextView) findViewById(R.id.txphonenumber);
		Button showlocation =  (Button) findViewById(R.id.bgetchildlocation);
		Button getphoto =  (Button) findViewById(R.id.bgetphoto);
		Button record =  (Button) findViewById(R.id.brecord);
		Button runningapp =  (Button) findViewById(R.id.brunningapps);

		showlocation.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				sendSMSParent(phonenum.getText().toString(), "parent:location");
			}
		});
		getphoto.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				sendSMSParent(phonenum.getText().toString(), "parent:photo");
			}
		});
		record.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				sendSMSParent(phonenum.getText().toString(), "parent:record");
			}
		});
		runningapp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				sendSMSParent(phonenum.getText().toString(), "parent:runningapps");
			}
		});
		
		Uri uriSMSURI = Uri.parse("content://sms/sent");
		Cursor cur = getContentResolver().query(uriSMSURI, null, null, null,
				null);
		String sms = "";
		while (cur.moveToNext()) {
			phonenum.setText(cur.getString(2));
			sms += "From :" + cur.getString(2) + " : " + cur.getString(12)
					+ "\n";
		}
		
	}
	private void sendSMSParent(String smsNumberToSend, String smsTextToSend) {

		SmsManager smsManager = SmsManager.getDefault();
		try {
			smsTextToSend += "Generated msg:\"" + smsTextToSend
					+ "\"by childroid";
			smsManager.sendTextMessage(smsNumberToSend, null, smsTextToSend,
					null, null);
		} catch (IllegalArgumentException ix) {

		}
	}
}
