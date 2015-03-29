package ro.pub.cs.systems.pdsd.practicaltest01;

import ro.pub.cs.systems.pdsd.practicaltext01.general.Constants;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01MainActivity extends Activity {
	private static final int ANOTHER_ACTIVITY_REQUEST_CODE = 1;

	protected class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			if (view instanceof Button) {
				TextView firstTextView = (TextView) findViewById(R.id.firstTextView);
				TextView secondTextView = (TextView) findViewById(R.id.secondTextView);
				int firstValue = Integer.parseInt(firstTextView.getText()
						.toString());
				int secondValue = Integer.parseInt(secondTextView.getText()
						.toString());

				switch (((Button) view).getId()) {
				case R.id.firstButton:
					firstValue++;
					firstTextView.setText("" + firstValue);
					break;
				case R.id.secondButton:
					secondValue++;
					secondTextView.setText("" + secondValue);
					break;
				case R.id.navigateButton:
					Intent intent = new Intent(
							"ro.pub.cs.systems.pdsd.intent.action.PracticalTest01SecondaryActivity");
					int sum = firstValue + secondValue;
					intent.putExtra(
							"sum", sum);
					startActivityForResult(intent,
							ANOTHER_ACTIVITY_REQUEST_CODE);
					break;
				}
			}
		}
	}

	protected ButtonListener buttonListener = new ButtonListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_main);
		Button firstButton = (Button) findViewById(R.id.firstButton);
		Button secondButton = (Button) findViewById(R.id.secondButton);
		Button navigateButton = (Button) findViewById(R.id.navigateButton);

		firstButton.setOnClickListener(buttonListener);
		secondButton.setOnClickListener(buttonListener);
		navigateButton.setOnClickListener(buttonListener);

		// restore state if necessary
		TextView firstTextView = (TextView) findViewById(R.id.firstTextView);
		TextView secondTextView = (TextView) findViewById(R.id.secondTextView);
		if (savedInstanceState != null) {
			if (savedInstanceState.getString(Constants.FIRST_TEXTVIEW) != null) {
				firstTextView.setText(savedInstanceState
						.getString(Constants.FIRST_TEXTVIEW));
			}
			if (savedInstanceState.getString(Constants.SECOND_TEXTVIEW) != null) {
				secondTextView.setText(savedInstanceState
						.getString(Constants.SECOND_TEXTVIEW));
			}
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		TextView firstTextView = (TextView) findViewById(R.id.firstTextView);
		TextView secondTextView = (TextView) findViewById(R.id.secondTextView);
		savedInstanceState.putString(Constants.FIRST_TEXTVIEW, firstTextView
				.getText().toString());
		savedInstanceState.putString(Constants.SECOND_TEXTVIEW, secondTextView
				.getText().toString());
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		  switch(requestCode) {
		    case ANOTHER_ACTIVITY_REQUEST_CODE:
		      if (resultCode == Activity.RESULT_OK){ 
		    	  Context context = getApplicationContext();
		    	  CharSequence text = "Operation completed!";
		    	  int duration = Toast.LENGTH_SHORT;
		    	  Toast toast = Toast.makeText(context, text, duration);
		    	  toast.show();
		      }
		      else if(resultCode == Activity.RESULT_CANCELED){
		    	  Context context = getApplicationContext();
		    	  CharSequence text = "Operation canceled!";
		    	  int duration = Toast.LENGTH_SHORT;
		    	  Toast toast = Toast.makeText(context, text, duration);
		    	  toast.show();
		      }
		      break;
		      }
		      // process other request codes
		  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_main, menu);
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
