package jp.takamichie.androidapp.lib;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

/**
 * 内部的に利用されるダイアログ表示フラグメントです。 {@link Dialogs}
 * クラスのメソッドから内部的に呼び出されるため、外部からは利用しないでください。
 *
 * @author 高見知英
 */
public final class InternalDialogFragment extends DialogFragment implements
	OnClickListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
	Bundle args = getArguments();
	View appendView = Dialogs.dialogData.get(getTag()).view;
	AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
	// タイトル設定
	dialog.setTitle(args.getString(Dialogs.ALERT_TITLE));
	// メッセージ設定
	if (args.containsKey(Dialogs.ALERT_VIEWID)) {
	    int viewId = args.getInt(Dialogs.ALERT_VIEWID);
	    dialog.setMessage(args.getString(Dialogs.ALERT_MESSAGE));
	    View v = getActivity().getLayoutInflater().inflate(viewId, null);
	    dialog.setView(v);
	} else if (args.containsKey(Dialogs.ALERT_MESSAGE)) {
	    dialog.setMessage(args.getString(Dialogs.ALERT_MESSAGE));
	} else if (args.containsKey(Dialogs.ALERT_MESSAGEARRAY)) {
	    dialog.setItems(args.getStringArray(Dialogs.ALERT_MESSAGEARRAY),
		    this);
	}
	if (appendView != null) {
	    dialog.setView(appendView);
	}
	// ボタン設定
	int buttons = args.getInt(Dialogs.ALERT_BUTTONS);
	if ((buttons & Dialogs.DIALOGBUTTON_POSITIVE) != 0) {
	    dialog.setPositiveButton(
		    args.containsKey(Dialogs.ALERT_POSCAPTION) ? args
			    .getString(Dialogs.ALERT_POSCAPTION)
			    : getActivity().getString(android.R.string.ok),
		    this);
	}
	if ((buttons & Dialogs.DIALOGBUTTON_NEGATIVE) != 0) {
	    dialog.setNegativeButton(
		    args.containsKey(Dialogs.ALERT_NEGCAPTION) ? args
			    .getString(Dialogs.ALERT_NEGCAPTION)
			    : getActivity().getString(android.R.string.no),
		    this);
	}
	if ((buttons & Dialogs.DIALOGBUTTON_NEUTRAL) != 0) {
	    dialog.setNegativeButton(args.getString(Dialogs.ALERT_NEUCAPTION),
		    this);
	}
	dialog.setOnCancelListener(this);

	return dialog.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
	Bundle args = getArguments();
	Bundle params = new Bundle();
	// ボタン処理
	switch (which) {
	case DialogInterface.BUTTON_POSITIVE:
	case DialogInterface.BUTTON_NEGATIVE:
	case DialogInterface.BUTTON_NEUTRAL:
	    // 肯定・否定・中立のボタンが押された
	    params.putInt(Dialogs.PARAMS_PRESSBUTTON, which);
	    break;
	default:
	    // それ以外の項目
	    String[] candidate = args
		    .getStringArray(Dialogs.ALERT_MESSAGEARRAY);
	    params.putString(Dialogs.PARAMS_INPUTSTR, candidate[which]);
	    break;
	}
	// 入力の取得
	if (getDialog().findViewById(android.R.id.input) instanceof EditText) {
	    EditText ed = (EditText) getDialog().findViewById(
		    android.R.id.input);
	    params.putString(Dialogs.PARAMS_INPUTSTR, ed.getText().toString());
	}
	Dialogs.DialogCallback callback = Dialogs.dialogData.get(getTag()).callback;
	if (callback != null) {
	    callback.onDialogClosed(this, params);
	}
    }

    @Override
    public void onCancel(DialogInterface dialog) {
	// キャンセルをコールバック
	Bundle args = new Bundle();
	args.putInt(Dialogs.PARAMS_PRESSBUTTON, Dialogs.CANCEL);

	Dialogs.DialogCallback callback = Dialogs.dialogData.get(getTag()).callback;
	if (callback != null) {
	    callback.onDialogClosed(this, args);
	}
	super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
	// 閉じ次第メモリデータを削除
	Dialogs.dialogData.put(getTag(), null);
	super.onDismiss(dialog);
    }

}
