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
    private static final int DELAY = 500;// 5000
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

        if (currentCard.equals(Card.MERLIN)) {
            otherInfo = "The Minions of Mordred that you can see are: ";
            for (int i = 0; i < core.gameCards.size(); i++) {
                if (i == counter) continue;
                Card card = core.gameCards.get(i);
                User user = core.gameUsers.get(i);
                if (card.showToMerlin) {
                    otherInfo = otherInfo + user.getName() + ", ";
                }
            }
            otherInfo = otherInfo.trim();
            otherInfo = otherInfo.substring(0, otherInfo.length()-1);
        } else if (currentCard.equals(Card.PERCIVAL)) {
            otherInfo = "Merlin and Morgana are %s and %s.";
            int num = 0;
            String[] names = new String[2];
            for (int i = 0; i < core.gameCards.size(); i++) {
                if (i == counter) continue;
                Card card = core.gameCards.get(i);
                User user = core.gameUsers.get(i);
                if (card.showToPercival) {
                    names[num++] = user.getName();
                }
            }
            otherInfo = String.format(otherInfo, names[0], names[1]);
        } else if (currentCard.equals(Card.MORDRED) || currentCard.equals(Card.MORGANA) || currentCard.equals(Card.ASSASSIN) || currentCard.equals(Card.MINION)) {
            otherInfo = "The other Minions of Mordred are: ";
            for (int i = 0; i < core.gameCards.size(); i++) {
                if (i == counter) continue;
                Card card = core.gameCards.get(i);
                User user = core.gameUsers.get(i);
                if (card.showToMinions) {
                    if (card.isLancelot) {
                        otherInfo = "Evil Lancelot is " + user.getName() + ".\n" + otherInfo;
                    } else {
                        otherInfo = otherInfo + user.getName() + ", ";
                    }
                }
            }
            otherInfo = otherInfo.trim();
            otherInfo = otherInfo.substring(0, otherInfo.length()-1);
        } else if (currentCard.equals(Card.OBERON)) {
            otherInfo = "The other Minions of Mordred are: ";
            for (int i = 0; i < core.gameCards.size(); i++) {
                if (i == counter) continue;
                Card card = core.gameCards.get(i);
                User user = core.gameUsers.get(i);
                if (card.showToMinions) {
                    otherInfo = otherInfo + user.getName() + ", ";
                }
            }
            otherInfo = otherInfo.trim();
            otherInfo = otherInfo.substring(0, otherInfo.length()-1);
        } else if (currentCard.equals(Card.JHERI)) {
            otherInfo = "The two Lancelots are %s and %s.";
            int num = 0;
            String[] names = new String[2];
            for (int i = 0; i < core.gameCards.size(); i++) {
                if (i == counter) continue;
                Card card = core.gameCards.get(i);
                User user = core.gameUsers.get(i);
                if (card.isLancelot) {
                    names[num++] = user.getName();
                }
            }
            otherInfo = String.format(otherInfo, names[0], names[1]);
        } else if (currentCard.equals(Card.RICK)) {
            otherInfo = "James is ";
            for (int i = 0; i < core.gameCards.size(); i++) {
                Card card = core.gameCards.get(i);
                User user = core.gameUsers.get(i);
                if (card.equals(Card.JAMES)) {
                    otherInfo = otherInfo + user.getName();
                    break;
                }
            }
        } else if (currentCard.equals(Card.JAMES)) {
            otherInfo = "Rick is ";
            for (int i = 0; i < core.gameCards.size(); i++) {
                Card card = core.gameCards.get(i);
                User user = core.gameUsers.get(i);
                if (card.equals(Card.RICK)) {
                    otherInfo = otherInfo + user.getName();
                    break;
                }
            }
        }

        ((TextView)findViewById(R.id.tv_deal_other_info)).setText(otherInfo);

        informationLayout.setEnabled(true);
        informationLayout.setVisibility(View.VISIBLE);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                DealCardsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        enableInfoOKButton();
                    }
                });
            }
        }, DELAY);
    }

    public void enableInfoOKButton() {
        findViewById(R.id.btn_deal_info_ok).setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
