package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Brian on 2014-04-09.
 */
public class GameActivity extends Activity {
    public static final String TAG = "GameActivity";
    private MainActivity core;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        core = MainActivity.getDefaultInstance();
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
