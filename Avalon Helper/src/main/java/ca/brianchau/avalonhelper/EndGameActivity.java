package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ca.brianchau.avalonhelper.cards.Card;

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
        boolean goodWin = false;
        if (core.missionWin) {
            if (core.gameCards.contains(Card.MERLIN)) {
                if (core.merlinGuess) {
                    findViewById(R.id.tv_end_game_evil).setVisibility(View.VISIBLE);
                    findViewById(R.id.tv_end_game_merlin_guess_correct).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.tv_end_game_good).setVisibility(View.VISIBLE);
                    findViewById(R.id.tv_end_game_merlin_guess_incorrect).setVisibility(View.VISIBLE);
                    goodWin = true;
                }
            } else {
                findViewById(R.id.tv_end_game_good).setVisibility(View.VISIBLE);
                goodWin = true;
            }
        } else {
            findViewById(R.id.tv_end_game_evil).setVisibility(View.VISIBLE);
        }
        if (core.missionResults.size() >= 1)
            findViewById(R.id.view_end_game_mission1).setBackgroundColor(core.missionResults.get(0) ? Color.rgb(0, 51, 255) : Color.rgb(255, 0, 0));
        if (core.missionResults.size() >= 2)
            findViewById(R.id.view_end_game_mission2).setBackgroundColor(core.missionResults.get(1) ? Color.rgb(0, 51, 255) : Color.rgb(255, 0, 0));
        if (core.missionResults.size() >= 3)
            findViewById(R.id.view_end_game_mission3).setBackgroundColor(core.missionResults.get(2) ? Color.rgb(0, 51, 255) : Color.rgb(255, 0, 0));
        if (core.missionResults.size() >= 4)
            findViewById(R.id.view_end_game_mission4).setBackgroundColor(core.missionResults.get(3) ? Color.rgb(0, 51, 255) : Color.rgb(255, 0, 0));
        if (core.missionResults.size() == 5)
            findViewById(R.id.view_end_game_mission5).setBackgroundColor(core.missionResults.get(4) ? Color.rgb(0, 51, 255) : Color.rgb(255, 0, 0));

        ((TextView)findViewById(R.id.tv_end_game_minions)).setText(core.getMinions());

        findViewById(R.id.btn_end_game_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for (int i = 0; i < core.gameUsers.size(); i++) {
            User u = core.gameUsers.get(i);
            Card c = core.gameCards.get(i);
            u.setGameResult(goodWin == c.good);
        }
        core.saveUsers(core.gameUsers);
    }
}
