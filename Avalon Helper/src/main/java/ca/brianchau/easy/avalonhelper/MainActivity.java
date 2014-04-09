package ca.brianchau.easy.avalonhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Brian on 2014-04-08.
 */
public class MainActivity extends Activity {

    private static MainActivity defaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (defaultInstance == null) {
            defaultInstance = this;
        } else {
            throw new RuntimeException("Oops");
        }
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_main_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SetupActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.btn_main_stats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StatsActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    public static MainActivity getDefaultInstance() {
        return defaultInstance;
    }
}
