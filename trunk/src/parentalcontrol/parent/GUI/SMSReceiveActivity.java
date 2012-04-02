package parentalcontrol.parent.GUI;

import parentalcontrol.parent.R;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;

public class SMSReceiveActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("SMSReceiveActivity", "Start SMSReceiveActivity");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smsreceivelayout);

		TextView view = (TextView) findViewById(R.id.etsmsreceive);//to show msg body

		//String messages = getIntent().getStringExtra("sms");//get data from intent

		//view.setText(messages);//view the data
	}
}
