package jp.takamichie.androidapp.lib;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * ダイアログ関連の汎用クラスです
 *
 * @author 高見知英
 */
public class Dialogs {

    /**
     * ダイアログに肯定ボタンを表示します
     */
    public static int DIALOGBUTTON_POSITIVE = 1;
    /**
     * ダイアログに否定ボタンを表示します
     */
    public static int DIALOGBUTTON_NEGATIVE = 2;
    /**
     * ダイアログに中立ボタンを表示します
     */
    public static int DIALOGBUTTON_NEUTRAL = 4;
    /**
     * ダイアログに肯定ボタンと否定ボタンを表示します
     */
    private static int DIALOGBUTTON_POSITIVE_NEGATIVE = DIALOGBUTTON_POSITIVE + DIALOGBUTTON_NEGATIVE;

    /**
     * ダイアログがキャンセルされた際、{@link #PARAMS_PRESSBUTTON}に格納される値です。
     */
    public static int CANCEL = 0x10;

    /**
     * ダイアログに表示するタイトル文字列を指定します(String)。
     */
    public static String ALERT_TITLE = "title";

    /**
     * ダイアログに表示するメッセージ文字列を指定します(String)。
     * この値を指定すると、ダイアログは単一のメッセージを表示するダイアログとなります。 任意のビューを表示させたいときは値を指定しないでください。
     */
    public static String ALERT_MESSAGE = "messagestr";

    /**
     * ダイアログに表示するメッセージ配列を指定します(String[])。
     * この値を指定するとダイアログは複数項目からどれか一つを選ぶ形式のダイアログを表示します。
     * 任意のビューを表示させたいときは値を指定しないでください。
     */
    public static String ALERT_MESSAGEARRAY = "messagearray";

    /**
     * ダイアログに表示するボタンを示す定数を指定します(int)。次のパラメータの和を指定します。
     * <ul>
     * <li>{@link #DIALOGBUTTON_POSITIVE}</li>
     * <li>{@link #DIALOGBUTTON_NEGATIVE}</li>
     * <li>{@link #DIALOGBUTTON_NEUTRAL}</li>
     * </ul>
     */
    public static String ALERT_BUTTONS = "buttons";

    /**
     * ダイアログに表示する肯定ボタンの表示文字列を指定します(String)。 指定しない場合は、Android標準の値となります。
     */
    public static String ALERT_POSCAPTION = "pos-caption";

    /**
     * ダイアログに表示する否定ボタンの表示文字列を指定します(String)。 指定しない場合は、Android標準の値となります。
     */
    public static String ALERT_NEGCAPTION = "neg-caption";

    /**
     * ダイアログに表示する中立ボタンの表示文字列を指定します(String)。
     */
    public static String ALERT_NEUCAPTION = "neu-caption";

    /**
     * 複数選択ダイアログの場合、そこで選択した項目の文字列が、 単一行入力ダイアログの場合、そこで入力した文字列が格納されています(String)。
     */
    public static String PARAMS_INPUTSTR = "inputstr";

    /**
     * ダイアログクローズ時、押下されたボタンを示します(int)。
     * <dl>
     * <dt>{@link DialogInterface#BUTTON_POSITIVE}</dr>
     * <dd>ダイアログの肯定ボタンが押された</dd>
     * <dt>{@link DialogInterface#BUTTON_NEUTRAL}</dr>
     * <dd>ダイアログの中立ボタンが押された</dd>
     * <dt>{@link DialogInterface#BUTTON_NEGATIVE}</dr>
     * <dd>ダイアログの否定ボタンが押された</dd>
     * <dt>{@link #CANCEL}</dr>
     * <dd>ダイアログがキャンセルされた</dd>
     * </dl>
     */
    public static String PARAMS_PRESSBUTTON = "pressbutton";

    /**
     * ダイアログ処理のコールバックインターフェースです
     */
    public interface DialogCallback {
	/**
	 * ダイアログの肯定ボタンが押されたときのコールバックメソッドです
	 *
	 * @param owner
	 *            内部的に使用している{@link DialogFragment}オブジェクト。
	 * @param params
	 *            ダイアログの出力するパラメータ。次の値が格納されます。
	 *            <ul>
	 *            <li>{@link Dialogs#PARAMS_INPUTSTR}</li>
	 *            <li>{@link Dialogs#PARAMS_PRESSBUTTON}</li>
	 *            </ul>
	 */
	void onDialogClosed(DialogFragment owner, Bundle params);
    }

    private Dialogs() {
    }

    /**
     * 標準的なアラートダイアログを表示します
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static void showAlertDialog(FragmentManager manager, String title,
	    String message, DialogCallback callback) {
	Bundle args = new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	showAlertDialog(manager, args, callback, null);
    }

    /**
     * 標準的なアラートダイアログを表示します
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示するオブジェクト。 文字列を指定した場合は、通常のメッセージとなります。
     *            文字配列の場合、そのうちのいずれかを選択するリストが表示されます。 それ以外のオブジェクトを指定した場合、
     *            {@link Object#toString()}の値を表示するメッセージボックスを表示します。
     *            また、文字列を指定し、文字列の末尾に:を指定した場合は、文字列の入力を行うダイアログを表示します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static void showAlertDialog(FragmentTransaction transaction,
	    String title, String message, DialogCallback callback) {
	Bundle args = new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	showAlertDialog(transaction, args, callback, null);
    }

    /**
     * 標準的なアラートダイアログを表示します
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param params
     *            ダイアログのパラメータを指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param v
     *            ダイアログに表示されるビューを指定します。
     */
    public static void showAlertDialog(FragmentManager manager, Bundle params,
	    DialogCallback callback, View v) {
	FragmentTransaction transaction = manager.beginTransaction();
	showAlertDialog(transaction, params, callback, v);
    }

    /**
     * 標準的なアラートダイアログを表示します
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param params
     *            ダイアログのパラメータを指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param v
     *            ダイアログに表示されるビューを指定します。
     */
    public static void showAlertDialog(FragmentTransaction transaction,
	    Bundle params, DialogCallback callback, View v) {
	DialogKicker kick = new DialogKicker(transaction, params, callback, v);
	kick.showDialog();
    }

    private static class DialogKicker implements
	    DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
	View mView;
	DialogCallback mCallback;
	Bundle mParams;
	FragmentTransaction mTransaction;
	DialogFragment mDialog;

	public DialogKicker(FragmentTransaction transaction, Bundle params,
		DialogCallback callback, View view) {
	    super();
	    this.mView = view;
	    this.mCallback = callback;
	    this.mParams = params;
	    this.mTransaction = transaction;
	}

	public void showDialog() {
	    mDialog = new DialogFragment() {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		    Bundle args = getArguments();
		    AlertDialog.Builder dialog = new AlertDialog.Builder(
			    getActivity());
		    // タイトル設定
		    dialog.setTitle(args.getString(ALERT_TITLE));
		    // メッセージ設定
		    if (args.containsKey(ALERT_MESSAGE)) {
			dialog.setMessage(args.getString(ALERT_MESSAGE));
		    } else if (args.containsKey(ALERT_MESSAGEARRAY)) {
			dialog.setItems(args.getStringArray(ALERT_MESSAGE),
				DialogKicker.this);
		    } else if (mView != null) {
			dialog.setView(mView);
		    }
		    // ボタン設定
		    int buttons = args.getInt(ALERT_BUTTONS);
		    if ((buttons & DIALOGBUTTON_POSITIVE) != 0) {
			dialog.setPositiveButton(
				args.containsKey(ALERT_POSCAPTION) ? args
					.getString(ALERT_POSCAPTION)
					: getActivity().getString(
						android.R.string.ok),
				DialogKicker.this);
		    }
		    if ((buttons & DIALOGBUTTON_NEGATIVE) != 0) {
			dialog.setNegativeButton(
				args.containsKey(ALERT_NEGCAPTION) ? args
					.getString(ALERT_NEGCAPTION)
					: getActivity().getString(
						android.R.string.no),
				DialogKicker.this);
		    }
		    if ((buttons & DIALOGBUTTON_NEUTRAL) != 0) {
			dialog.setNegativeButton(
				args.getString(ALERT_NEUCAPTION),
				DialogKicker.this);
		    }
		    dialog.setOnCancelListener(DialogKicker.this);

		    return dialog.create();
		}
	    };
	    mDialog.setArguments(mParams);
	    mDialog.show(mTransaction, "");
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
	    Bundle args = new Bundle();
	    switch (which) {
	    case DialogInterface.BUTTON_POSITIVE:
	    case DialogInterface.BUTTON_NEGATIVE:
	    case DialogInterface.BUTTON_NEUTRAL:
		// 肯定・否定・中立のボタンが押された
		args.putInt(PARAMS_PRESSBUTTON, which);
		break;
	    default:
		// それ以外の項目
		String[] candidate = mParams.getStringArray(ALERT_MESSAGEARRAY);
		args.putString(PARAMS_INPUTSTR, candidate[which]);
		break;
	    }
	    mCallback.onDialogClosed(mDialog, args);
	}

	@Override
	public void onCancel(DialogInterface dialog) {
	    // キャンセルをコールバック
	    Bundle args = new Bundle();
	    args.putInt(PARAMS_PRESSBUTTON, Dialogs.CANCEL);
	    mCallback.onDialogClosed(mDialog, args);
	}

    }
}
