package jp.takamichie.androidapp.lib.test;

import jp.takamichie.androidapp.lib.Dialogs;
import jp.takamichie.androidapp.lib.Dialogs.DialogCallback;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

public class TestActivity extends FragmentActivity implements DialogCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_test);
    }

    public void normalAlertDialog(View v) {
	Dialogs.showAlertDialog(getSupportFragmentManager(), "タイトル", "メッセージ",
		this);
    }

    public void normalChoiceDialog(View v) {
	Dialogs.showChoiceDialog(getSupportFragmentManager(), "タイトル", this,
		"候補1", "候補2", "候補3");
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
	    case 0x10:
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

	Toast.makeText(this, builder.substring(0, builder.length() - 1), Toast.LENGTH_LONG).show();
    }
}
