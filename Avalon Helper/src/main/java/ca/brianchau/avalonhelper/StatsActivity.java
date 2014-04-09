package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Brian on 2014-04-08.
 */
public class StatsActivity extends Activity {
    public static final String TAG = "StatsActivity";
    private MainActivity core;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        core = MainActivity.getDefaultInstance();
        String s = "";
        if (core.users.isEmpty()) {
            // TODO remove
            core.users.add(new User("Brian Chau"));
            core.saveUsers();
        }
        for (User u : core.users) {
            s += u.getName();
        }
        ((TextView) findViewById(R.id.tv_stats_temp)).setText(s);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
