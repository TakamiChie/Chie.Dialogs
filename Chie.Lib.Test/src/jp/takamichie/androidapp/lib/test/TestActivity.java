package jp.takamichie.androidapp.lib.test;

import jp.takamichie.androidapp.lib.Dialogs;
import jp.takamichie.androidapp.lib.Dialogs.DialogCallback;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TestActivity extends FragmentActivity implements DialogCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_test);
    }

    public void normalAlertDialog(View v) {
	Dialogs.showAlertDialog(getSupportFragmentManager(), "タイトル", "メッセージ",
		getParam(1), this);
    }

    public void simpleAlertDialog(View v) {
	Dialogs.showSimpleDialog(getSupportFragmentManager(), "タイトル", "メッセージ",
		getParam(2), this);
    }

    public void normalChoiceDialog(View v) {
	Dialogs.showChoiceDialog(getSupportFragmentManager(), "タイトル",
		getParam(3), this, "候補1", "候補2", "候補3");
    }

    public void normalInputDialog(View v) {
	Dialogs.showInputDialog(getSupportFragmentManager(), "タイトル", "メッセージ",
		getParam(4), this);
    }

    public void normalCheckDialog(View v) {
	Dialogs.showCheckDialog(getSupportFragmentManager(), "タイトル", "メッセージ",
		"このメッセージを表示しない", getParam(5), this);
    }

    public void showCustomDialog(View v) {
	View view = getLayoutInflater().inflate(R.layout.testview, null);
	Button button = (Button) view.findViewById(R.id.button);
	button.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		Toast.makeText(TestActivity.this, "ボタンがクリックされました",
			Toast.LENGTH_SHORT).show();
	    }
	});
	Dialogs.showCustomDialog(getSupportFragmentManager(), "タイトル", view,
		getParam(6), this);
    }

    private Bundle getParam(int i) {
	Bundle params = new Bundle();
	params.putInt(Dialogs.ALERT_ID, i);
	return params;
    }

    @Override
    public void onDialogClosed(DialogFragment owner, Bundle params) {
	StringBuilder builder = new StringBuilder();
	builder.append("ダイアログがクローズされました。\n");
	if (params.containsKey(Dialogs.PARAMS_PRESSBUTTON)) {
	    switch (params.getInt(Dialogs.PARAMS_PRESSBUTTON)) {
	    case DialogInterface.BUTTON_POSITIVE:
		builder.append("肯定ボタンで閉じられました。\n");
		break;
	    case DialogInterface.BUTTON_NEGATIVE:
		builder.append("否定ボタンで閉じられました。\n");
		break;
	    case DialogInterface.BUTTON_NEUTRAL:
		builder.append("中立ボタンで閉じられました。\n");
		break;
	    case Dialogs.CANCEL:
		builder.append("キャンセルされました。\n");
		break;
	    default:
		break;
	    }
	}
	if (params.containsKey(Dialogs.PARAMS_INPUTSTR)) {
	    builder.append("入力/選択文字列:"
		    + params.getString(Dialogs.PARAMS_INPUTSTR) + "\n");
	}
	if (params.containsKey(Dialogs.PARAMS_CHECKED)) {
	    builder.append("チェック:"
		    + (params.getBoolean(Dialogs.PARAMS_CHECKED) ? "あり" : "なし")
		    + "\n");
	}
	builder.append(String.format("ダイアログID：%d\n",
		params.getInt(Dialogs.PARAMS_ID)));

	Toast.makeText(this, builder.substring(0, builder.length() - 1),
		Toast.LENGTH_LONG).show();
    }
}
