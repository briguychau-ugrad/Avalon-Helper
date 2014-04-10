package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Brian on 2014-04-10.
 */
public class EndGameActivity extends Activity {
    public static final String TAG = "EndGameActivity";
    private MainActivity core;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        core = MainActivity.getDefaultInstance();
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
