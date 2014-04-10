package ca.brianchau.avalonhelper.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach");
        this.activity = (SetupActivity)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        core = MainActivity.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");
        layout = (RelativeLayout)activity.findViewById(R.id.rl_number_of_players_fragment);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        layout.setVisibility(View.VISIBLE);
        activity.findViewById(R.id.btn_number_of_players_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickResponder(5);
            }
        });
        activity.findViewById(R.id.btn_number_of_players_six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickResponder(6);
            }
        });
        activity.findViewById(R.id.btn_number_of_players_seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickResponder(7);
            }
        });
        activity.findViewById(R.id.btn_number_of_players_eight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickResponder(8);
            }
        });
        activity.findViewById(R.id.btn_number_of_players_nine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickResponder(9);
            }
        });
        activity.findViewById(R.id.btn_number_of_players_ten).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickResponder(10);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        layout.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    private void clickResponder(int players) {
        activity.setNumberOfPlayers(players);
        activity.finishNumberOfPlayersFragment();
    }
}
