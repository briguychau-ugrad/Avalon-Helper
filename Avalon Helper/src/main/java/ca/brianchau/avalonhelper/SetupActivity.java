package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.Stack;

import ca.brianchau.avalonhelper.fragments.NumberOfPlayersFragment;
import ca.brianchau.avalonhelper.fragments.SelectUsersFragment;

/**
 * Created by Brian on 2014-04-08.
 */
public class SetupActivity extends Activity {
    public static final String TAG = "SetupActivity";
    private MainActivity core;
    private boolean canGoBack;
    private FrameLayout frame;
    private Stack<Fragment> fragmentStack;
    private int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        core = MainActivity.getDefaultInstance();
        frame = (FrameLayout)findViewById(R.id.fl_setup_content);
        fragmentStack = new Stack<Fragment>();

        if (savedInstanceState == null) {
            Fragment numberOfPlayersFragment = new NumberOfPlayersFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fl_setup_content, numberOfPlayersFragment).addToBackStack(NumberOfPlayersFragment.TAG).commit();
            fragmentStack.push(numberOfPlayersFragment);
        }
        numberOfPlayers = 0;
    }

    @Override
    public void onBackPressed() {
        fragmentStack.pop();
        getFragmentManager().popBackStack();
        if (fragmentStack.empty()) {
            finish();
        } else {
            fragmentStack.peek().onResume();
        }
    }

    public void finishNumberOfPlayersFragment() {
        fragmentStack.peek().onPause();
        Fragment selectUsersFragment = new SelectUsersFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fl_setup_content, selectUsersFragment).addToBackStack(SelectUsersFragment.TAG).commit();
        fragmentStack.push(selectUsersFragment);
    }

    public void setNumberOfPlayers(int players) {
        this.numberOfPlayers = players;
    }
}
