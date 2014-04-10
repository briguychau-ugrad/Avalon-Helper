package ca.brianchau.avalonhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ca.brianchau.avalonhelper.cards.Card;

/**
 * Created by Brian on 2014-04-08.
 */
public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";
    public static final String SETTINGS = "ca.brianchau.avalonhelper";
    public static final String SETTINGS_USERS = "users";
    public static final String SETTINGS_PREFIX_WIN = "_win_";
    public static final String SETTINGS_PREFIX_LOSS = "_loss_";
    public static final String SETTINGS_PREFIX_GOOD = "_good_";
    public static final String SETTINGS_PREFIX_STREAK = "_streak_";
    public static final String SETTINGS_PREFIX_RECENT = "_recent_";
    private static final int ERROR = 1<<31;

    private static MainActivity defaultInstance;

    public List<User> users;
    public List<User> gameUsers;
    public List<Card> gameCards;
    public int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultInstance = this;
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_main_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SetupActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.btn_main_stats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StatsActivity.class);
                startActivity(i);
            }
        });
        users = new LinkedList<User>();
        SharedPreferences preferences = getSharedPreferences(SETTINGS, 0);
        Set<String> usernames = preferences.getStringSet(SETTINGS_USERS, null);
        if (usernames != null) {
            for (String s : usernames) {
                int wins = preferences.getInt(SETTINGS_PREFIX_WIN + s, ERROR);
                int losses = preferences.getInt(SETTINGS_PREFIX_LOSS + s, ERROR);
                int streak = preferences.getInt(SETTINGS_PREFIX_STREAK + s, ERROR);
                int recent = preferences.getInt(SETTINGS_PREFIX_RECENT + s, ERROR);
                boolean good = preferences.getBoolean(SETTINGS_PREFIX_GOOD + s, true);
                if (wins == ERROR || losses == ERROR || streak == ERROR || recent == ERROR) {
                    Toast.makeText(this, "Unable to retrieve stats for " + s, Toast.LENGTH_LONG).show();
                    continue;
                }
                users.add(new User(s, wins, losses, good, streak, recent));
            }
            sortUsers();
        }
    }

    public boolean addUser(User user) {
        if (users.contains(user)) {
            return false;
        }
        users.add(user);
        List<User> userList = new LinkedList<User>();
        userList.add(user);
        saveUsers(userList);
        sortUsers();
        return true;
    }

    public void saveUsers(List<User> userList) {
        SharedPreferences.Editor editor = getSharedPreferences(SETTINGS, 0).edit();
        Set<String> usernames = new HashSet<String>();
        for (User u : users) {
            usernames.add(u.getName());
        }
        for (User u : userList) {
            String username = u.getName();
            usernames.add(username);
            editor.putInt(SETTINGS_PREFIX_WIN + username, u.getWins());
            editor.putInt(SETTINGS_PREFIX_LOSS + username, u.getLosses());
            editor.putInt(SETTINGS_PREFIX_STREAK + username, u.getStreak());
            editor.putInt(SETTINGS_PREFIX_RECENT + username, u.getRecent());
            editor.putBoolean(SETTINGS_PREFIX_GOOD + username, u.getGood());
        }
        editor.putStringSet(SETTINGS_USERS, usernames);
        editor.commit();
    }

    public void generatePairs() {
        if (gameCards.size() != gameUsers.size())
            throw new RuntimeException("Error: Invalid application state");
        Collections.sort(gameUsers, new Comparator<User>() {
            @Override
            public int compare(User user, User user2) {
                return user.getName().compareTo(user2.getName());
            }
        });
        Collections.shuffle(gameCards, new Random(System.nanoTime()));
    }

    public void sortUsers() {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user, User user2) {
                return user.getName().compareTo(user2.getName());
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    public static MainActivity getDefaultInstance() {
        return defaultInstance;
    }
}
