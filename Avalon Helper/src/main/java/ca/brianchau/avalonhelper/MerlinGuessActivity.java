package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import ca.brianchau.avalonhelper.cards.Card;

/**
 * Created by Brian on 2014-04-10.
 */
public class MerlinGuessActivity extends Activity {
    public static final String TAG = "MerlinGuessActivity";
    private MainActivity core;
    private GuessMerlinAdapter merlinArrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_merlin_guess);
        core = MainActivity.getDefaultInstance();
        ((TextView)findViewById(R.id.tv_merlin_guess_minions)).setText(core.getMinions());
        listView = (ListView)findViewById(R.id.lv_merlin_guess_list);

        merlinArrayAdapter = new GuessMerlinAdapter(this, R.layout.cell_view_guess_merlin, R.id.btn_guess_merlin_cell_name);
        listView.setAdapter(merlinArrayAdapter);
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    public void verifyMerlin(User user) {
        boolean isMerlin = false;
        for (int i = 0; i < core.gameUsers.size(); i++) {
            if (core.gameUsers.get(i).equals(user)) {
                if (core.gameCards.get(i).equals(Card.MERLIN)) {
                    isMerlin = true;
                    break;
                }
            }
        }
        core.merlinGuess = isMerlin;
        Intent i = new Intent(getApplicationContext(), EndGameActivity.class);
        startActivity(i);
        finish();
    }

    public class GuessMerlinAdapter extends ArrayAdapter<User> {
        public List<User> good;
        private int selectCount;

        public GuessMerlinAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
            good = new LinkedList<User>();
            for (int i = 0; i < core.gameCards.size(); i++) {
                if (core.gameCards.get(i).good) {
                    good.add(core.gameUsers.get(i));
                }
            }
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public int getCount() {
            return good.size();
        }

        @Override
        public View getView (final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.cell_view_guess_merlin, null);
            }
            final User user = good.get(position);
            ((Button)convertView.findViewById(R.id.btn_guess_merlin_cell_name)).setText(user.getName());
            convertView.findViewById(R.id.btn_guess_merlin_cell_name).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    verifyMerlin(user);
                }
            });
            return convertView;
        }
    }
}
