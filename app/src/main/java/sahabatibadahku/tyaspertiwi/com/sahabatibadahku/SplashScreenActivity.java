package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new DatabaseInitialize().execute();
    }

    private class DatabaseInitialize extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            new Database(SplashScreenActivity.this);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mainIntent = new Intent(SplashScreenActivity.this,MainActivity.class);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            SplashScreenActivity.this.startActivity(mainIntent);
            SplashScreenActivity.this.finish();
        }
    }
}
