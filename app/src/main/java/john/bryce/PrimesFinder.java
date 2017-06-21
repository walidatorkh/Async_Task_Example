package john.bryce;

import android.os.AsyncTask;
import android.os.Handler;
import android.widget.TextView;


public class PrimesFinder extends AsyncTask<Long, Integer, String> {

    private Callbacks callbacks;

    public PrimesFinder(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    protected void onPreExecute() {
        callbacks.onAboutToBegin();
    }



        protected String doInBackground(Long... arr) {
            long start = arr[0];
            long end = arr[1];
            int lastPercentReported = 0;

            // Run on all numbers in that range (from start to end)
            // any prime number, we should add to the textViewPrimes

            StringBuilder stringBuilder = new StringBuilder();
            for (long i = start; i <= end; i++) {
                if (Helper.isPrime(i)) {
                    stringBuilder.append(i);
                    stringBuilder.append(", ");
                }
                int percentDone = (int)((i - start)* 100.0 / (end - start));
                if(percentDone != lastPercentReported) {
                    publishProgress(percentDone);
                    lastPercentReported = percentDone;
                }
            }
            return stringBuilder.toString();
        }



        protected void onProgressUpdate(Integer... values) {
            int percentDone = values[0];
            callbacks.onReportProgress(percentDone);
        }


    protected void onPostExecute(String primes) {
        callbacks.onSuccess(primes);
    }

    public interface Callbacks {
        void onAboutToBegin();
        void onReportProgress(int percentDone);
        void onSuccess(String primes);
    }
}
