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
public class DeleteUserDialog extends Dialog {
    public static final String TAG = "DeleteUserDialog";

    public interface DeleteUserDialogListener {
        public abstract void onButtonClicked();
        public abstract void onCancelClicked();
    }

    private DeleteUserDialogListener listener;
    private MainActivity core;
    private TextView warning;
    private String username;

    public DeleteUserDialog(Context context, DeleteUserDialogListener listener, String username) {
        super(context, R.style.AppTheme_DialogBox);
        super.setCanceledOnTouchOutside(false);
        this.listener = listener;
        this.username = username;

        core = MainActivity.getDefaultInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_delete_user);

        ((TextView)findViewById(R.id.tv_delete_user_prompt)).setText(String.format(core.getString(R.string.delete_user_dialog_prompt), username));

        warning = (TextView)findViewById(R.id.tv_new_user_warning);
        findViewById(R.id.btn_delete_user_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onButtonClicked();
                cancel();
            }
        });
        findViewById(R.id.btn_delete_user_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onCancelClicked();
                cancel();
            }
        });
    }
}
