package ro.pub.cs.systems.pdsd.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends Activity {

	ButtonListener listener = new ButtonListener();
	
	protected class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			if (view instanceof Button) {
				Intent intentToParent = new Intent();
				switch (((Button) view).getId()) {
				case R.id.okButton:
					setResult(RESULT_OK, intentToParent);
					finish();
					break;
				case R.id.cancelButton:
					setResult(RESULT_CANCELED, intentToParent);
					finish();
					break;
				}
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_secondary);

		Button okButton = (Button)findViewById(R.id.okButton);
		Button cancelButton = (Button)findViewById(R.id.cancelButton);
		
		okButton.setOnClickListener(listener);
		cancelButton.setOnClickListener(listener);
		
		Intent intentFromParent = getIntent();
		Bundle data = intentFromParent.getExtras();
		String sum = data.get("sum")
				.toString();

		TextView sumTextView = (TextView) findViewById(R.id.clickSum);
		sumTextView.setText(sum);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_secondary, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
