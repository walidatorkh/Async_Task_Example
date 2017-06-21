package john.bryce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PrimesFinder.Callbacks{

    private EditText editTextStart;
    private EditText editTextEnd;
    private TextView textViewPrimes;
    private ProgressBar progressBarPercent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStart = (EditText)findViewById(R.id.editTextStart);
        editTextEnd = (EditText)findViewById(R.id.editTextEnd);
        textViewPrimes = (TextView)findViewById(R.id.textViewPrimes);
        progressBarPercent = (ProgressBar)findViewById(R.id.progressBarPercent);
    }

    public void buttonFindPrimes(View view) {

        long start = Long.parseLong(editTextStart.getText().toString());

        long end = Long.parseLong(editTextEnd.getText().toString());

        PrimesFinder primesFinder = new PrimesFinder(this);
        primesFinder.execute(start, end);


    }

    @Override
    public void onAboutToBegin() {
        textViewPrimes.setText("Calculating.......");

    }
    public void  onReportProgress(int percentDone) {
        progressBarPercent.setProgress(percentDone);
        textViewPrimes.setText(percentDone + "%");
    }

    public void onSuccess(String primes) {
            textViewPrimes.setText(primes);
        }
}
