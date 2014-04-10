package ca.brianchau.avalonhelper.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ca.brianchau.avalonhelper.MainActivity;
import ca.brianchau.avalonhelper.R;
import ca.brianchau.avalonhelper.SetupActivity;

/**
 * Created by Brian on 2014-04-09.
 */
public class SelectCardsFragment extends Fragment {
    public static final String TAG = "SelectCardsFragment";
    private MainActivity core;
    private SetupActivity activity;
    private RelativeLayout layout;

    private boolean merlinSelected;
    private boolean percivalSelected;
    private boolean rickJamesSelected;
    private boolean jheriSelected;
    private boolean infiltratorSelected;
    private boolean mordredSelected;
    private boolean morganaSelected;
    private boolean lancelotsSelected;
    private boolean oberonSelected;
    private boolean assassinSelected;
    private int servantsSelected;
    private int minionsSelected;
    private int servantsMax;
    private int minionsMax;

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
        layout = (RelativeLayout)activity.findViewById(R.id.rl_select_cards_fragment);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        merlinSelected = false;
        percivalSelected = false;
        rickJamesSelected = false;
        jheriSelected = false;
        infiltratorSelected = false;
        mordredSelected = false;
        morganaSelected = false;
        lancelotsSelected = false;
        oberonSelected = false;
        assassinSelected = false;
        servantsSelected = 0;
        minionsSelected = 0;
        minionsMax = core.numberOfPlayers / 3 + (core.numberOfPlayers % 3 == 0 ? 0 : 1);
        servantsMax = core.numberOfPlayers - minionsMax;
        ((TextView)activity.findViewById(R.id.tv_card_selected_good)).setText("Good: " + servantsMax);
        ((TextView)activity.findViewById(R.id.tv_card_selected_evil)).setText("Evil: " + minionsMax);
        ((TextView)activity.findViewById(R.id.tv_card_servant)).setText("Other Loyal Servants of Arthur: " + (servantsMax - servantsSelected));
        ((TextView)activity.findViewById(R.id.tv_card_minion)).setText("Other Minions of Mordred: " + (minionsMax - minionsSelected));

        activity.findViewById(R.id.btn_card_merlin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}
