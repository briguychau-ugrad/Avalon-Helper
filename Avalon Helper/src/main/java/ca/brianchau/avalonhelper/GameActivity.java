package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

import ca.brianchau.avalonhelper.cards.Card;
import ca.brianchau.avalonhelper.dialogs.LancelotFlipDialog;

/**
 * Created by Brian on 2014-04-09.
 */
public class GameActivity extends Activity {
    public static final String TAG = "GameActivity";
    private MainActivity core;

    private static final int[][] MISSIONS = {
            {},
            {},
            {},
            {},
            {},
            {0, 2, 3, 2, 3, 3},
            {0, 2, 3, 4, 3, 4},
            {0, 2, 3, 3, 4, 4},
            {0, 3, 4, 4, 5, 5},
            {0, 3, 4, 4, 5, 5},
            {0, 3, 4, 4, 5, 5}
    };
    private static final int TWO_FAIL_PLAYERS = 7;

    private RelativeLayout mission1_future;
    private RelativeLayout mission2_future;
    private RelativeLayout mission3_future;
    private RelativeLayout mission4_future;
    private RelativeLayout mission5_future;
    private RelativeLayout mission1_past;
    private RelativeLayout mission2_past;
    private RelativeLayout mission3_past;
    private RelativeLayout mission4_past;
    private RelativeLayout mission5_past;
    private RelativeLayout mission_current;
    private Button rejectButton;
    private Button passButton;
    private Button failButton;
    private TextView twoFailMission;
    private TextView currentMissionNumber;
    private TextView currentMissionPrompt;

    private int fails;
    private int passes;
    private int rejects;
    private int currentMission;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        // TODO DELETE IF NECESSARY
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
        // TODO DELETE IF NECESSARY
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        System.out.println(savedInstanceState);
        setContentView(R.layout.activity_game);
        core = MainActivity.getDefaultInstance();

        mission1_future = (RelativeLayout)findViewById(R.id.rl_game_mission1_future);
        mission2_future = (RelativeLayout)findViewById(R.id.rl_game_mission2_future);
        mission3_future = (RelativeLayout)findViewById(R.id.rl_game_mission3_future);
        mission4_future = (RelativeLayout)findViewById(R.id.rl_game_mission4_future);
        mission5_future = (RelativeLayout)findViewById(R.id.rl_game_mission5_future);
        mission1_past = (RelativeLayout)findViewById(R.id.rl_game_mission1_past);
        mission2_past = (RelativeLayout)findViewById(R.id.rl_game_mission2_past);
        mission3_past = (RelativeLayout)findViewById(R.id.rl_game_mission3_past);
        mission4_past = (RelativeLayout)findViewById(R.id.rl_game_mission4_past);
        mission5_past = (RelativeLayout)findViewById(R.id.rl_game_mission5_past);
        mission_current = (RelativeLayout)findViewById(R.id.rl_game_current_mission);
        rejectButton = (Button)findViewById(R.id.btn_game_reject);
        passButton = (Button)findViewById(R.id.btn_game_pass);
        failButton = (Button)findViewById(R.id.btn_game_fail);
        twoFailMission = (TextView)findViewById(R.id.tv_game_two_fail);
        currentMissionNumber = (TextView)findViewById(R.id.tv_game_current_number);
        currentMissionPrompt = (TextView)findViewById(R.id.tv_game_current_prompt);

        mission1_future.setVisibility(View.INVISIBLE);
        mission2_future.setVisibility(View.VISIBLE);
        mission3_future.setVisibility(View.VISIBLE);
        mission4_future.setVisibility(View.VISIBLE);
        mission5_future.setVisibility(View.VISIBLE);
        mission1_past.setVisibility(View.GONE);
        mission2_past.setVisibility(View.GONE);
        mission3_past.setVisibility(View.GONE);
        mission4_past.setVisibility(View.GONE);
        mission5_past.setVisibility(View.INVISIBLE);
        twoFailMission.setVisibility(View.INVISIBLE);

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleReject();
            }
        });
        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextMission(true);
            }
        });
        failButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextMission(false);
            }
        });

        ((TextView)findViewById(R.id.tv_game_mission2_prompt)).setText(String.format(getString(R.string.game_mission_players), MISSIONS[core.numberOfPlayers][2]));
        ((TextView)findViewById(R.id.tv_game_mission3_prompt)).setText(String.format(getString(R.string.game_mission_players), MISSIONS[core.numberOfPlayers][3]));
        ((TextView)findViewById(R.id.tv_game_mission4_prompt)).setText(String.format(getString(R.string.game_mission_players), MISSIONS[core.numberOfPlayers][4]));
        ((TextView)findViewById(R.id.tv_game_mission5_prompt)).setText(String.format(getString(R.string.game_mission_players), MISSIONS[core.numberOfPlayers][5]));
        findViewById(R.id.tv_game_mission4_two_fail).setVisibility(core.numberOfPlayers >= TWO_FAIL_PLAYERS ? View.VISIBLE : View.GONE);

        fails = 0;
        passes = 0;
        rejects = 0;
        currentMission = 1;

        currentMissionNumber.setText("1");
        currentMissionPrompt.setText(String.format(getString(R.string.game_mission_select), MISSIONS[core.numberOfPlayers][currentMission]));
        ((TextView)findViewById(R.id.tv_game_rejects)).setText(String.format(getString(R.string.game_mission_rejects), rejects));

        core.missionResults = new LinkedList<Boolean>();
        core.shuffleLancelotCards();
    }

    public void handleReject() {
        rejects++;
        if (rejects == 5) {
            endGame(false);
        }
        ((TextView)findViewById(R.id.tv_game_rejects)).setText(String.format(getString(R.string.game_mission_rejects), rejects));
    }

    public void showNextMission(boolean passed) {
        if (passed)
            passes++;
        else
            fails++;
        core.missionResults.add(passed);
        System.out.printf("Now passes %d fails %d\n", passes, fails);
        if (passes == 3) {
            endGame(true);
            return;
        }
        if (fails == 3) {
            endGame(false);
            return;
        }
        currentMission++;
        currentMissionNumber.setText(Integer.toString(currentMission));
        currentMissionPrompt.setText(String.format(getString(R.string.game_mission_select), MISSIONS[core.numberOfPlayers][currentMission]));

        // LADY OF THE LAKE

        switch (currentMission) {
            case 2:
                mission1_past.setVisibility(View.VISIBLE);
                findViewById(R.id.rl_game_mission1_past_inner).setBackgroundColor(passed ? Color.rgb(0, 31, 255) : Color.rgb(255, 0, 0));
                ((TextView)findViewById(R.id.tv_game_mission1_result)).setText(passed ? R.string.game_mission_result_passed : R.string.game_mission_result_failed);
                mission2_future.setVisibility(View.GONE);
                break;
            case 3:
                mission2_past.setVisibility(View.VISIBLE);
                findViewById(R.id.rl_game_mission2_past_inner).setBackgroundColor(passed ? Color.rgb(0, 31, 255) : Color.rgb(255, 0, 0));
                ((TextView)findViewById(R.id.tv_game_mission2_result)).setText(passed ? R.string.game_mission_result_passed : R.string.game_mission_result_failed);
                mission3_future.setVisibility(View.GONE);
                break;
            case 4:
                mission3_past.setVisibility(View.VISIBLE);
                findViewById(R.id.rl_game_mission3_past_inner).setBackgroundColor(passed ? Color.rgb(0, 31, 255) : Color.rgb(255, 0, 0));
                ((TextView)findViewById(R.id.tv_game_mission3_result)).setText(passed ? R.string.game_mission_result_passed : R.string.game_mission_result_failed);
                mission4_future.setVisibility(View.GONE);
                break;
            case 5:
                mission4_past.setVisibility(View.VISIBLE);
                findViewById(R.id.rl_game_mission4_past_inner).setBackgroundColor(passed ? Color.rgb(0, 31, 255) : Color.rgb(255, 0, 0));
                ((TextView)findViewById(R.id.tv_game_mission4_result)).setText(passed ? R.string.game_mission_result_passed : R.string.game_mission_result_failed);
                mission5_future.setVisibility(View.GONE);
                break;
        }
        twoFailMission.setVisibility(currentMission == 4 && core.numberOfPlayers >= TWO_FAIL_PLAYERS ? View.VISIBLE : View.INVISIBLE);

        rejects = 0;
        ((TextView)findViewById(R.id.tv_game_rejects)).setText(String.format(getString(R.string.game_mission_rejects), rejects));

        if (currentMission >= 3 && core.gameCards.contains(Card.GOOD_LANCELOT)) {
            if (core.lancelotSwitchCards.get(currentMission - 3)) {
                lancelotSwitch();
            }
        }
    }

    public void lancelotSwitch() {
        for (int i = 0; i < core.gameCards.size(); i++) {
            if (core.gameCards.get(i).equals(Card.GOOD_LANCELOT)) {
                core.gameCards.set(i, Card.EVIL_LANCELOT);
            } else if (core.gameCards.get(i).equals(Card.EVIL_LANCELOT)) {
                core.gameCards.set(i, Card.GOOD_LANCELOT);
            }
        }
        new LancelotFlipDialog(this).show();
    }

    public void endGame(boolean win) {
        core.missionWin = win;
        core.merlinGuess = false;
        if (!win || !core.gameCards.contains(Card.MERLIN)) {
            Intent i = new Intent(getApplicationContext(), EndGameActivity.class);
            startActivity(i);
            finish();
        } else {
            // MERLIN GUESSING
        }
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
