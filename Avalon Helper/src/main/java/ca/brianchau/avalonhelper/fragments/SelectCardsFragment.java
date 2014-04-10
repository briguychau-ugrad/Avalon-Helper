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
import android.widget.ToggleButton;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import ca.brianchau.avalonhelper.MainActivity;
import ca.brianchau.avalonhelper.R;
import ca.brianchau.avalonhelper.SetupActivity;
import ca.brianchau.avalonhelper.cards.Card;

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

    private ToggleButton merlinButton;
    private ToggleButton percivalButton;
    private ToggleButton rickJamesButton;
    private ToggleButton jheriButton;
    private ToggleButton infiltratorButton;
    private ToggleButton mordredButton;
    private ToggleButton morganaButton;
    private ToggleButton lancelotsButton;
    private ToggleButton oberonButton;
    private ToggleButton assassinButton;

    private List<ToggleButton> buttons;

    public SelectCardsFragment() {
    }

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

        merlinButton = (ToggleButton)activity.findViewById(R.id.btn_card_merlin);
        percivalButton = (ToggleButton)activity.findViewById(R.id.btn_card_percival);
        rickJamesButton = (ToggleButton)activity.findViewById(R.id.btn_card_rick_james);
        jheriButton = (ToggleButton)activity.findViewById(R.id.btn_card_jheri);
        infiltratorButton = (ToggleButton)activity.findViewById(R.id.btn_card_infiltrator);
        mordredButton = (ToggleButton)activity.findViewById(R.id.btn_card_mordred);
        morganaButton = (ToggleButton)activity.findViewById(R.id.btn_card_morgana);
        lancelotsButton = (ToggleButton)activity.findViewById(R.id.btn_card_lancelot);
        oberonButton = (ToggleButton)activity.findViewById(R.id.btn_card_oberon);
        assassinButton = (ToggleButton)activity.findViewById(R.id.btn_card_assassin);

        buttons = new LinkedList<ToggleButton>();
        buttons.add(merlinButton);
        buttons.add(percivalButton);
        buttons.add(rickJamesButton);
        buttons.add(jheriButton);
        buttons.add(infiltratorButton);
        buttons.add(mordredButton);
        buttons.add(morganaButton);
        buttons.add(lancelotsButton);
        buttons.add(oberonButton);
        buttons.add(assassinButton);

        core.gameCards = new HashSet<Card>();

        for (ToggleButton b : buttons) {
            b.setChecked(false);
        }

        merlinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                merlinSelected = !merlinSelected;
                toggleCard(merlinSelected, Card.MERLIN);
            }
        });
        percivalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                percivalSelected = !percivalSelected;
                toggleCard(percivalSelected, Card.PERCIVAL);
            }
        });
        rickJamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rickJamesSelected = !rickJamesSelected;
                toggleCard(rickJamesSelected, Card.RICK);
                toggleCard(rickJamesSelected, Card.JAMES);
            }
        });
        jheriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jheriSelected = !jheriSelected;
                toggleCard(jheriSelected, Card.JHERI);
            }
        });
        infiltratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infiltratorSelected = !infiltratorSelected;
                toggleCard(infiltratorSelected, Card.INFILTRATOR);
            }
        });
        mordredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mordredSelected = !mordredSelected;
                toggleCard(mordredSelected, Card.MORDRED);
            }
        });
        morganaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morganaSelected = !morganaSelected;
                toggleCard(morganaSelected, Card.MORGANA);
            }
        });
        lancelotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lancelotsSelected = !lancelotsSelected;
                toggleCard(lancelotsSelected, Card.GOOD_LANCELOT);
                toggleCard(lancelotsSelected, Card.EVIL_LANCELOT);
            }
        });
        oberonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oberonSelected = !oberonSelected;
                toggleCard(oberonSelected, Card.OBERON);
            }
        });
        assassinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assassinSelected = !assassinSelected;
                toggleCard(assassinSelected, Card.ASSASSIN);
            }
        });

        activity.findViewById(R.id.btn_select_cards_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finishSelectCardsFragment();
            }
        });

        drawText();
    }

    public void toggleCard(boolean selected, Card card) {
        Log.i(TAG, "toggleCard");
        if (selected) {
            core.gameCards.add(card);
            servantsSelected += card.good ? 1 : 0;
            minionsSelected += card.good ? 0 : 1;
        } else {
            core.gameCards.remove(card);
            servantsSelected -= card.good ? 1 : 0;
            minionsSelected -= card.good ? 0 : 1;
        }
        drawText();
        disableIfNecessary();
        System.out.println(core.gameCards);
    }

    public void drawText() {
        ((TextView)activity.findViewById(R.id.tv_card_selected_good)).setText("Good: " + servantsMax);
        ((TextView)activity.findViewById(R.id.tv_card_selected_evil)).setText("Evil: " + minionsMax);
        ((TextView)activity.findViewById(R.id.tv_card_servant)).setText("Regular Loyal Servants of Arthur: " + (servantsMax - servantsSelected));
        ((TextView)activity.findViewById(R.id.tv_card_minion)).setText("Regular Minions of Mordred: " + (minionsMax - minionsSelected));
    }

    public void disableIfNecessary() {
        if (minionsSelected + servantsSelected > core.numberOfPlayers) {
            for (ToggleButton b : buttons) {
                if (!b.isChecked()) {
                    b.setEnabled(false);
                }
            }
            activity.findViewById(R.id.btn_select_cards_ok).setEnabled(false);
        } else {
            for (ToggleButton b : buttons) {
                if (!b.isEnabled()) {
                    b.setEnabled(true);
                }
            }
            activity.findViewById(R.id.btn_select_cards_ok).setEnabled(true);
        }
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
