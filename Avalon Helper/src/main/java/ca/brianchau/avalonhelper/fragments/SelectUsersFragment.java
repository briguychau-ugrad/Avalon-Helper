package ca.brianchau.avalonhelper.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import ca.brianchau.avalonhelper.MainActivity;
import ca.brianchau.avalonhelper.R;
import ca.brianchau.avalonhelper.SetupActivity;
import ca.brianchau.avalonhelper.User;
import ca.brianchau.avalonhelper.dialogs.NewUserDialog;

/**
 * Created by Brian on 2014-04-09.
 */
public class SelectUsersFragment extends Fragment {
    public static final String TAG = "SelectUsersFragment";
    private MainActivity core;
    private SetupActivity activity;
    private RelativeLayout layout;
    private ListView usersListView;
    private Button okButton;

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
        layout = (RelativeLayout)activity.findViewById(R.id.rl_select_users_fragment);
        usersListView = (ListView)activity.findViewById(R.id.lv_select_users_all);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        activity.findViewById(R.id.btn_select_users_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewUserDialog dialog = new NewUserDialog(activity, new NewUserDialog.NewUserDialogListener() {
                    @Override
                    public void onButtonClicked(User u) {
                        ((SetupActivity.SelectUsersAdapter)usersListView.getAdapter()).add(u);
                    }

                    @Override
                    public void onCancelClicked() {
                        // Do nothing
                    }
                });
                dialog.show();
            }
        });
        okButton = (Button)activity.findViewById(R.id.btn_select_users_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
        okButton.setEnabled(false);
        activity.updateSelectUsersList(usersListView);
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
