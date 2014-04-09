package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ca.brianchau.avalonhelper.fragments.NumberOfPlayersFragment;
import ca.brianchau.avalonhelper.fragments.SelectUsersFragment;

/**
 * Created by Brian on 2014-04-08.
 */
public class SetupActivity extends Activity {
    public static final String TAG = "SetupActivity";
    private MainActivity core;
    private boolean canGoBack;
    private FrameLayout frame;
    private Stack<Fragment> fragmentStack;
    private int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        core = MainActivity.getDefaultInstance();
        frame = (FrameLayout)findViewById(R.id.fl_setup_content);
        fragmentStack = new Stack<Fragment>();

        if (savedInstanceState == null) {
            Fragment numberOfPlayersFragment = new NumberOfPlayersFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fl_setup_content, numberOfPlayersFragment).addToBackStack(NumberOfPlayersFragment.TAG).commit();
            fragmentStack.push(numberOfPlayersFragment);
        }
        numberOfPlayers = 0;
    }

    @Override
    public void onBackPressed() {
        fragmentStack.pop();
        getFragmentManager().popBackStack();
        if (fragmentStack.empty()) {
            finish();
        } else {
            fragmentStack.peek().onResume();
        }
    }

    public void updateSelectUsersList(ListView view) {
        core.sortUsers();
        User[] users = new User[core.users.size()];
        core.users.toArray(users);
        ArrayAdapter<User> userArrayAdapter = new SelectUsersAdapter(this, R.layout.cell_view_select_users, R.id.tv_select_users_cell_name);
        view.setAdapter(userArrayAdapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "onItemClick");
                ((SelectUsersAdapter)adapterView.getAdapter()).updateItem(i);
            }
        });
    }

    public void finishNumberOfPlayersFragment() {
        fragmentStack.peek().onPause();
        Fragment selectUsersFragment = new SelectUsersFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fl_setup_content, selectUsersFragment).addToBackStack(SelectUsersFragment.TAG).commit();
        fragmentStack.push(selectUsersFragment);
    }

    public void setNumberOfPlayers(int players) {
        this.numberOfPlayers = players;
    }

    public class SelectUsersAdapter extends ArrayAdapter<User> {

        Map<User, Boolean> map = new HashMap<User, Boolean>();
        int selectCount;

        public SelectUsersAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
            core.sortUsers();
            for (User u : core.users) {
                map.put(u, false);
            }
        }

        public boolean updateItem(int index) {
            User user = core.users.get(index);
            boolean selected = !map.get(user);
            selectCount += selected ? 1 : -1;
            map.put(user, selected);
            return selected;
        }

        @Override
        public void add(User object) {
            core.addUser(object);
            map.put(object, true);
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
                convertView = getLayoutInflater().inflate(R.layout.cell_view_select_users, null);
            }
            User user = core.users.get(position);
            ((TextView)convertView.findViewById(R.id.tv_select_users_cell_name)).setText(user.getName());
            convertView.findViewById(R.id.cb_select_users_cell_selected).setSelected(map.get(user));
            final CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.cb_select_users_cell_selected);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkBox.setChecked(updateItem(position));
                    findViewById(R.id.btn_select_users_ok).setEnabled(selectCount == numberOfPlayers);
                }
            };
            convertView.setOnClickListener(listener);
            checkBox.setOnClickListener(listener);
            return convertView;
        }
    }
}
