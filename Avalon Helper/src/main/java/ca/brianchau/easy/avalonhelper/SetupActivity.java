package ca.brianchau.easy.avalonhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Brian on 2014-04-08.
 */
public class SetupActivity extends Activity {

    private MainActivity core;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        core = MainActivity.getDefaultInstance();
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
