package ca.brianchau.avalonhelper.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import ca.brianchau.avalonhelper.MainActivity;
import ca.brianchau.avalonhelper.R;
import ca.brianchau.avalonhelper.SetupActivity;

/**
 * Created by Brian on 2014-04-08.
 */
public class NumberOfPlayersFragment extends Fragment {
    public static final String TAG = "NumberOfPlayersFragment";
    private MainActivity core;
    private SetupActivity activity;
    private RelativeLayout layout;

    public NumberOfPlayersFragment(SetupActivity activity) {
        this.activity = activity;
        core = MainActivity.getDefaultInstance();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layout = (RelativeLayout)activity.findViewById(R.id.rl_number_of_players_fragment);
        layout.setVisibility(View.VISIBLE);
    }

    private void clickResponder(int players) {
        activity.setNumberOfPlayers(players);
        activity.finishNumberOfPlayersFragment();
    }
}
