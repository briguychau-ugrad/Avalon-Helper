package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import ca.brianchau.avalonhelper.dialogs.DeleteUserDialog;

/**
 * Created by Brian on 2014-04-08.
 */
public class StatsActivity extends Activity {
    public static final String TAG = "StatsActivity";
    private MainActivity core;
    private StatisticsListAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        core = MainActivity.getDefaultInstance();

        adapter = new StatisticsListAdapter(this, R.layout.cell_view_stats, R.id.tv_stats_username);
        listView = (ListView)findViewById(R.id.lv_stats_list);
        listView.setAdapter(adapter);
    }

    public class StatisticsListAdapter extends ArrayAdapter<User> {

        public StatisticsListAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
        }

        @Override
        public void add(User object) {
            core.addUser(object);
            notifyDataSetChanged();
        }

        @Override
        public void remove(User object) {
            core.removeUser(object);
            notifyDataSetChanged();
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public int getCount() {
            return core.users.size();
        }

        @Override
        public View getView (final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.cell_view_stats, null);
            }
            final User user = core.users.get(position);
            ((TextView)convertView.findViewById(R.id.tv_stats_username)).setText(user.getName());
            ((TextView)convertView.findViewById(R.id.tv_stats_wins)).setText(Integer.toString(user.getWins()));
            ((TextView)convertView.findViewById(R.id.tv_stats_losses)).setText(Integer.toString(user.getLosses()));
            String streak =
                    user.getStreak() == 0 ? "None" :
                            user.getStreak() > 0 ? Integer.toString(user.getStreak()) + " wins" :
                                    Integer.toString(-1 * user.getStreak()) + " losses";
            ((TextView)convertView.findViewById(R.id.tv_stats_streak)).setText(streak);

            convertView.findViewById(R.id.btn_stats_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DeleteUserDialog(getContext(), new DeleteUserDialog.DeleteUserDialogListener() {
                        @Override
                        public void onButtonClicked() {
                            remove(user);
                        }
                        @Override
                        public void onCancelClicked() {
                            // do nothing
                        }
                    }, user.getName()).show();
                }
            });
            return convertView;
        }
    }
}
