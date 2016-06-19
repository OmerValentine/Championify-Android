package net.championify.championify;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {

    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Locale.setDefault(new Locale("en", "US"));

        imgLogo = (ImageView) findViewById(R.id.img_championify_logo_splashscreen);

        ObjectAnimator fadeInLogo = ObjectAnimator.ofFloat(imgLogo, "alpha", 1);
        fadeInLogo.setDuration(1500);
        fadeInLogo.start();

        Thread splashScreenThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent splashScreenIntent = new Intent(getApplicationContext(), TabsActivity.class);
                    startActivity(splashScreenIntent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashScreenThread.start();
    }
}
