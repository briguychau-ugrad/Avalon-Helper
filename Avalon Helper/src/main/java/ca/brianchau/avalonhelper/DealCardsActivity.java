package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import ca.brianchau.avalonhelper.cards.Card;

/**
 * Created by Brian on 2014-04-09.
 */
public class DealCardsActivity extends Activity {
    public static final String TAG = "DealCardsActivity";
    private MainActivity core;
    private LinearLayout passDeviceLayout;
    private LinearLayout informationLayout;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_cards);
        core = MainActivity.getDefaultInstance();
        core.generatePairs();
        passDeviceLayout = (LinearLayout)findViewById(R.id.ll_deal_show_device);
        informationLayout = (LinearLayout)findViewById(R.id.ll_deal_show_information);
        findViewById(R.id.btn_deal_pass_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInformation();
            }
        });
        counter = -1;
        findViewById(R.id.btn_deal_info_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passToNextUser();
            }
        });
        passDeviceLayout.setEnabled(false);
        passDeviceLayout.setVisibility(View.GONE);
        informationLayout.setEnabled(false);
        informationLayout.setVisibility(View.GONE);

        passToNextUser();
    }

    public void passToNextUser() {
        passDeviceLayout.setEnabled(false);
        passDeviceLayout.setVisibility(View.GONE);
        informationLayout.setEnabled(false);
        informationLayout.setVisibility(View.GONE);

        counter++;
        if (counter >= core.gameUsers.size()) {
            finish();
            return;
        }

        User currentUser = core.gameUsers.get(counter);

        ((TextView)findViewById(R.id.tv_deal_pass_to)).setText(currentUser.getName());
        passDeviceLayout.setEnabled(true);
        passDeviceLayout.setVisibility(View.VISIBLE);
    }

    public void showInformation() {
        passDeviceLayout.setEnabled(false);
        passDeviceLayout.setVisibility(View.GONE);
        informationLayout.setEnabled(false);
        informationLayout.setVisibility(View.GONE);

        findViewById(R.id.btn_deal_info_ok).setEnabled(false);

        User currentUser = core.gameUsers.get(counter);
        Card currentCard = core.gameCards.get(counter);
        ((TextView)findViewById(R.id.tv_deal_name)).setText(currentUser.getName());
        ((TextView)findViewById(R.id.tv_deal_character)).setTextColor(currentCard.good ? Color.rgb(0, 51, 255) : Color.rgb(255, 0, 0));
        ((TextView)findViewById(R.id.tv_deal_character)).setText(currentCard.name);

        String otherInfo = "";

        ((TextView)findViewById(R.id.tv_deal_other_info)).setText(otherInfo);

        informationLayout.setEnabled(true);
        informationLayout.setVisibility(View.VISIBLE);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                findViewById(R.id.btn_deal_info_ok).setEnabled(true);
            }
        }, 5000);
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
