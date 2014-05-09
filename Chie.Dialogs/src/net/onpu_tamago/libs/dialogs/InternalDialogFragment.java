package net.onpu_tamago.libs.dialogs;

import net.onpu_tamago.libs.dialogs.Dialogs.Result;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

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
	if (args.containsKey(Dialogs.ALERT_TITLEID)) {
		int id = args.getInt(Dialogs.ALERT_TITLEID);
		if(id != 0){
			dialog.setTitle(id);
		}
	} else {
		String str = args.getString(Dialogs.ALERT_TITLE);
		if(!"".equals(str)){
			dialog.setTitle(str);
		}
	}
	// メッセージ設定
	if (args.containsKey(Dialogs.ALERT_VIEWID)) {
	    int viewId = args.getInt(Dialogs.ALERT_VIEWID);
	    LinearLayout layout = new LinearLayout(getActivity());
	    layout.setOrientation(LinearLayout.VERTICAL);
	    // ビューごとの個別処理
	    if (viewId == Dialogs.VIEWID_INPUTDLG) {
		// EditTextの文字列指定
		EditText editText = new EditText(getActivity());
		editText.setEms(10);
		editText.setId(android.R.id.input);
		editText.setLayoutParams(new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		editText.setInputType(InputType.TYPE_CLASS_TEXT);
		if (args.containsKey(Dialogs.ALERT_DEFAULTEDIT)) {
		    editText.setText(args.getString(Dialogs.ALERT_DEFAULTEDIT));
		}
		layout.addView(editText);
	    } else if (viewId == Dialogs.VIEWID_CHECKDLG) {
		// チェックボックスの文字列指定
		CheckBox checkbox = new CheckBox(getActivity());
		checkbox.setId(android.R.id.checkbox);
		checkbox.setLayoutParams(new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		if (args.containsKey(Dialogs.ALERT_APPENDMESSAGEID)) {
		    checkbox.setText(args.getInt(Dialogs.ALERT_APPENDMESSAGEID));
		} else {
		    checkbox.setText(args
			    .getString(Dialogs.ALERT_APPENDMESSAGE));
		}
		layout.addView(checkbox);
	    }
	    dialog.setView(layout);
	}
	if (args.containsKey(Dialogs.ALERT_MESSAGEID)) {
	    dialog.setMessage(args.getInt(Dialogs.ALERT_MESSAGEID));
	} else if (args.containsKey(Dialogs.ALERT_MESSAGE)) {
	    dialog.setMessage(args.getString(Dialogs.ALERT_MESSAGE));
	} else if (args.containsKey(Dialogs.ALERT_MESSAGEARRAYID)) {
	    dialog.setItems(args.getInt(Dialogs.ALERT_MESSAGEARRAYID), this);
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
	    String[] candidate;
	    if (args.containsKey(Dialogs.ALERT_MESSAGEARRAYID)) {
		candidate = getResources().getStringArray(
			args.getInt(Dialogs.ALERT_MESSAGEARRAYID));
	    } else {
		candidate = args.getStringArray(Dialogs.ALERT_MESSAGEARRAY);
	    }
	    params.putString(Dialogs.PARAMS_INPUTSTR, candidate[which]);
	    params.putInt(Dialogs.PARAMS_INPUTINDEX, which);
	    break;
	}
	// 入力の取得
	if (getDialog().findViewById(android.R.id.input) instanceof EditText) {
	    EditText ed = (EditText) getDialog().findViewById(
		    android.R.id.input);
	    params.putString(Dialogs.PARAMS_INPUTSTR, ed.getText().toString());
	}
	if (getDialog().findViewById(android.R.id.checkbox) instanceof CheckBox) {
	    CheckBox checkbox = (CheckBox) getDialog().findViewById(
		    android.R.id.checkbox);
	    params.putBoolean(Dialogs.PARAMS_CHECKED, checkbox.isChecked());
	}
	// IDの設定
	params.putInt(Dialogs.PARAMS_ID, args.getInt(Dialogs.ALERT_ID));
	Dialogs.DialogCallback callback = Dialogs.dialogData.get(getTag()).callback;
	if (callback != null) {
	    callback.onDialogClosed(this, params);
	    // Resultインターフェース処理
	    if (callback instanceof Result) {
		Result result = (Result) callback;
		switch (which) {
		case DialogInterface.BUTTON_POSITIVE:
		    result.onPositiveResult(this, params);
		    break;
		case DialogInterface.BUTTON_NEGATIVE:
		    result.onNegativeResult(this, params);
		    break;
		case DialogInterface.BUTTON_NEUTRAL:
		    result.onNeutralResult(this, params);
		    break;
		default:
		    result.onSelectItems(this, params);
		    break;
		}
	    }
	}
    }

    @Override
    public void onCancel(DialogInterface dialog) {
	Bundle args = getArguments();
	Bundle params = new Bundle();
	// キャンセルをコールバック
	params.putInt(Dialogs.PARAMS_PRESSBUTTON, Dialogs.CANCEL);
	params.putInt(Dialogs.PARAMS_ID, args.getInt(Dialogs.ALERT_ID));

	Dialogs.DialogCallback callback = Dialogs.dialogData.get(getTag()).callback;
	if (callback != null) {
	    callback.onDialogClosed(this, params);
	    // Resultインターフェース処理
	    if (callback instanceof Result) {
		((Result) callback).onCancel(this, params);
	    }
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
