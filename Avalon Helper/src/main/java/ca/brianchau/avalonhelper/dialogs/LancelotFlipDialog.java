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
public class LancelotFlipDialog extends Dialog {
    public static final String TAG = "LancelotFlipDialog";

    public LancelotFlipDialog(Context context) {
        super(context, R.style.AppTheme_DialogBox);
        super.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_lancelot_flip);
        findViewById(R.id.btn_lancelot_switch_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }
}
