package ca.brianchau.avalonhelper.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import ca.brianchau.avalonhelper.MainActivity;
import ca.brianchau.avalonhelper.R;
import ca.brianchau.avalonhelper.User;
import ca.brianchau.avalonhelper.exceptions.InvalidParameterException;

/**
 * Created by Brian on 2014-04-09.
 */
public class NewUserDialog extends Dialog {
    public static final String TAG = "NewUserDialog";

    public interface NewUserDialogListener {
        public abstract void onButtonClicked(User u);
        public abstract void onCancelClicked();
    }

    private NewUserDialogListener listener;
    private MainActivity core;
    private TextView warning;

    public NewUserDialog(Context context, NewUserDialogListener listener) {
        super(context, R.style.AppTheme_DialogBox);
        super.setCanceledOnTouchOutside(false);
        this.listener = listener;

        core = MainActivity.getDefaultInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_new_user);

        warning = (TextView)findViewById(R.id.tv_new_user_warning);
        findViewById(R.id.btn_new_user_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText)findViewById(R.id.et_new_username);
                String name = et.getText().toString();
                try {
                    User user = new User(name);
                    if (core.addUser(user)) {
                        if (listener != null)
                            listener.onButtonClicked(user);
                        cancel();
                    } else {
                        warning.setText("Username already exists.");
                    }
                } catch (InvalidParameterException e) {
                    warning.setText(e.getMessage());
                }
            }
        });
        findViewById(R.id.btn_new_user_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onCancelClicked();
                cancel();
            }
        });
    }
}
