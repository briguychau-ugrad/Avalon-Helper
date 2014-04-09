package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import ca.brianchau.avalonhelper.fragments.NumberOfPlayersFragment;

/**
 * Created by Brian on 2014-04-08.
 */
public class SetupActivity extends Activity {
    public static final String TAG = "SetupActivity";
    private MainActivity core;
    private boolean canGoBack;
    private FrameLayout frame;
    private int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        core = MainActivity.getDefaultInstance();
        frame = (FrameLayout)findViewById(R.id.fl_setup_content);

        if (savedInstanceState == null) {
            Fragment numberOfPlayersFragment = new NumberOfPlayersFragment(this);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fl_setup_content, numberOfPlayersFragment).addToBackStack(NumberOfPlayersFragment.TAG).commit();
        }
        canGoBack = true;
    }

    @Override
    public void onBackPressed() {
        if (canGoBack) {
            finish();
        }
    }

    public void finishNumberOfPlayersFragment() {
        canGoBack = false;
    }

    public void setNumberOfPlayers(int players) {
        this.numberOfPlayers = players;
    }
}
