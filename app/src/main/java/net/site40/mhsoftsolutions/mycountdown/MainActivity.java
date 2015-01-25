package net.site40.mhsoftsolutions.mycountdown;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity {
    static Thread t;
    static Date dt;
    static  TextView lblDisplay;
    static GregorianCalendar c;
    static long dSec;
    static long dMin;
    static long dHrs;
    static long dDays;
    static long resultDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblDisplay = (TextView) findViewById(R.id.txtDisTime);

        c = new GregorianCalendar();
        c.set(2015, Calendar.APRIL, 9, 16, 10,00);
        t = new Thread(new Runnable() {
            @Override
            public void run() {

                for (;;) {

                    //Log.d("In side the thread", "inside the thread ");
                    dt = new Date();
                    resultDate = c.getTimeInMillis() - dt.getTime();
                    dSec = (TimeUnit.MILLISECONDS.toSeconds(resultDate)% 60);
                    dMin = (TimeUnit.MILLISECONDS.toMinutes(resultDate)%60);
                    dHrs =(TimeUnit.MILLISECONDS.toHours(resultDate)% 24);
                    dDays = (TimeUnit.MILLISECONDS.toDays(resultDate));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                     lblDisplay = (TextView) findViewById(R.id.txtDisTime);
                     lblDisplay.setText(String.valueOf(String.valueOf(dDays + ":"+ dHrs + ":"+ dMin + ":" + dSec)));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void operClose(View view) {
        Button btnClose = (Button) findViewById(R.id.btnClose);
        finish();


    }

}
