package jp.takamichie.androidapp.lib;

import java.util.HashMap;

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
     * ダイアログに肯定ボタンを表示します。
     */
    public static final int DIALOGBUTTON_POSITIVE = 1;
    /**
     * ダイアログに否定ボタンを表示します。
     */
    public static final int DIALOGBUTTON_NEGATIVE = 2;
    /**
     * ダイアログに中立ボタンを表示します。
     */
    public static final int DIALOGBUTTON_NEUTRAL = 4;
    /**
     * ダイアログに肯定ボタンと否定ボタンを表示します。
     */
    private static final int DIALOGBUTTON_POSITIVE_NEGATIVE = DIALOGBUTTON_POSITIVE
	    + DIALOGBUTTON_NEGATIVE;

    /**
     * ダイアログで肯定ボタンが押された際、{@link #PARAMS_PRESSBUTTON}に格納される値です。
     */
    public static final int BUTTON_POSITIVE = DialogInterface.BUTTON_POSITIVE;

    /**
     * ダイアログで否定ボタンが押された際、{@link #PARAMS_PRESSBUTTON}に格納される値です。
     */
    public static final int BUTTON_NEGATIVE = DialogInterface.BUTTON_NEGATIVE;

    /**
     * ダイアログで中立ボタンが押された際、{@link #PARAMS_PRESSBUTTON}に格納される値です。
     */
    public static final int BUTTON_NEUTRAL = DialogInterface.BUTTON_NEUTRAL;

    /**
     * ダイアログがキャンセルされた際、{@link #PARAMS_PRESSBUTTON}に格納される値です。
     */
    public static final int CANCEL = 0x10;

    /**
     * ダイアログに表示するタイトル文字列を指定します(String)。
     */
    public static final String ALERT_TITLE = "title";

    /**
     * ダイアログに表示するタイトル文字列のリソースIDを指定します(int)。
     */
    public static final String ALERT_TITLEID = "titleid";

    /**
     * ダイアログに表示するメッセージ文字列を指定します(String)。
     * この値を指定すると、ダイアログは単一のメッセージを表示するダイアログとなります。
     * 多肢選択式ダイアログを表示させたいときは値を指定しないでください。
     */
    public static final String ALERT_MESSAGE = "messagestr";

    /**
     * ダイアログに表示するメッセージ文字列のリソースIDを指定します(int)。
     * この値を指定すると、ダイアログは単一のメッセージを表示するダイアログとなります。
     * 多肢選択式ダイアログを表示させたいときは値を指定しないでください。
     */
    public static final String ALERT_MESSAGEID = "messageid";

    /**
     * ダイアログに表示する追加文字列を指定します(String)。 現時点では内部的にのみ用いられています。
     */
    static final String ALERT_APPENDMESSAGE = "appendmessage";

    /**
     * ダイアログに表示する追加文字列のリソースIDを指定します(int)。 現時点では内部的にのみ用いられています。
     */
    static final String ALERT_APPENDMESSAGEID = "appendmessageid";

    /**
     * ダイアログに表示するメッセージ配列を指定します(String[])。
     * この値を指定するとダイアログは複数項目からどれか一つを選ぶ形式のダイアログを表示します。
     * 任意のビューを表示させたいときは値を指定しないでください。
     */
    public static final String ALERT_MESSAGEARRAY = "messagearray";

    /**
     * ダイアログに表示するメッセージ配列のリソースIDを指定します(int)。
     * この値を指定するとダイアログは複数項目からどれか一つを選ぶ形式のダイアログを表示します。
     * 任意のビューを表示させたいときは値を指定しないでください。
     */
    public static final String ALERT_MESSAGEARRAYID = "messagearrayid";

    /**
     * ダイアログに表示するボタンを示す定数を指定します(int)。次のパラメータの和を指定します。
     * <ul>
     * <li>{@link #DIALOGBUTTON_POSITIVE}</li>
     * <li>{@link #DIALOGBUTTON_NEGATIVE}</li>
     * <li>{@link #DIALOGBUTTON_NEUTRAL}</li>
     * </ul>
     */
    public static final String ALERT_BUTTONS = "buttons";

    /**
     * ダイアログに表示する肯定ボタンの表示文字列を指定します(String)。 指定しない場合は、Android標準の値となります。
     */
    public static final String ALERT_POSCAPTION = "pos-caption";

    /**
     * ダイアログに表示する肯定ボタンの表示文字列リソースIDを指定します(int)。 指定しない場合は、Android標準の値となります。
     */
    public static final String ALERT_POSCAPTIONID = "pos-captionid";

    /**
     * ダイアログに表示する否定ボタンの表示文字列を指定します(String)。 指定しない場合は、Android標準の値となります。
     */
    public static final String ALERT_NEGCAPTION = "neg-caption";

    /**
     * ダイアログに表示する否定ボタンの表示文字列リソースIDを指定します(int)。 指定しない場合は、Android標準の値となります。
     */
    public static final String ALERT_NEGCAPTIONID = "neg-captionid";

    /**
     * ダイアログに表示する中立ボタンの表示文字列を指定します(String)。
     */
    public static final String ALERT_NEUCAPTION = "neu-caption";

    /**
     * ダイアログに表示する中立ボタンの表示文字列リソースIDを指定します(String)。
     */
    public static final String ALERT_NEUCAPTIONID = "neu-captionid";

    /**
     * ダイアログのIDです。値はダイアログのコールバックメソッドの引数に引き渡されます(int)。
     */
    public static final String ALERT_ID = "id";

    /**
     * アラートダイアログに指定するビューのIDです(int)。 現時点では内部的にのみ利用されます。
     */
    static final String ALERT_VIEWID = "viewid";

    /**
     * 複数選択ダイアログの場合、そこで選択した項目の文字列が、 単一行入力ダイアログの場合、そこで入力した文字列が格納されています(String)。
     */
    public static final String PARAMS_INPUTSTR = "inputstr";

    /**
     * {@link #showCheckDialog(FragmentManager, String, String, String, Bundle, DialogCallback)}
     * において、チェックボックスの状態が格納されています(boolean)。
     */
    public static final String PARAMS_CHECKED = "checked";

    /**
     * ダイアログのIDです(int)。
     */
    public static final String PARAMS_ID = ALERT_ID;

    /**
     * ダイアログクローズ時、押下されたボタンを示します(int)。
     * <dl>
     * <dt>{@link #BUTTON_POSITIVE}</dr>
     * <dd>ダイアログの肯定ボタンが押された</dd>
     * <dt>{@link #BUTTON_NEUTRAL}</dr>
     * <dd>ダイアログの中立ボタンが押された</dd>
     * <dt>{@link #BUTTON_NEGATIVE}</dr>
     * <dd>ダイアログの否定ボタンが押された</dd>
     * <dt>{@link #CANCEL}</dr>
     * <dd>ダイアログがキャンセルされた</dd>
     * </dl>
     */
    public static final String PARAMS_PRESSBUTTON = "pressbutton";

    /**
     * ダイアログ処理のコールバックインターフェースです。
     */
    public interface DialogCallback {
	/**
	 * ダイアログが閉じたときのコールバックメソッドです。
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

    /**
     * ダイアログ処理のコールバックインターフェースです。
     */
    public interface Result extends DialogCallback{
	/**
	 * ダイアログの肯定ボタンが押されたときのコールバックメソッドです。
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
	void onPositiveResult(DialogFragment owner, Bundle params);
	/**
	 * ダイアログの否定ボタンが押されたときのコールバックメソッドです。
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
	void onNegativeResult(DialogFragment owner, Bundle params);
	/**
	 * ダイアログの中立ボタンが押されたときのコールバックメソッドです。
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
	void onNeutralResult(DialogFragment owner, Bundle params);
	/**
	 * 多肢選択式のダイアログにおいて、項目が選択されダイアログが閉じたときのコールバックメソッドです。
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
	void onSelectItems(DialogFragment owner, Bundle params);
	/**
	 * ダイアログがキャンセルされたときのコールバックメソッドです。
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
	void onCancel(DialogFragment owner, Bundle params);

    }

    /**
     * ダイアログ処理のコールバックインターフェースを実装したオブジェクトです
     */
    public static abstract class ResultReciever implements Result{

	@Override
	public void onDialogClosed(DialogFragment owner, Bundle params) {
	}

	@Override
	public void onPositiveResult(DialogFragment owner, Bundle params) {
	}

	@Override
	public void onNegativeResult(DialogFragment owner, Bundle params) {
	}

	@Override
	public void onNeutralResult(DialogFragment owner, Bundle params) {
	}

	@Override
	public void onSelectItems(DialogFragment owner, Bundle params) {
	}

	@Override
	public void onCancel(DialogFragment owner, Bundle params) {
	}

    }

    /**
     * 内部的に用いるデータを格納する構造体です。
     */
    public static class DialogData {
	View view;
	DialogCallback callback;

	public DialogData(View view, DialogCallback callback) {
	    super();
	    this.view = view;
	    this.callback = callback;
	}
    }

    /**
     * 内部的に用いるダイアログ用データを保存するオブジェクトです。
     */
    static HashMap<String, DialogData> dialogData = new HashMap<String, Dialogs.DialogData>();

    /**
     * 内部的に用いるタグ番号です。
     */
    private static int tag = 0;

    private Dialogs() {
    }

    // FragmentManagerを使うオーバーライド

    /**
     * ボタンがOKのみのシンプルなアラートダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showSimpleDialog(FragmentManager manager,
	    String title, String message, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE);
	}
	showDialog(manager, args, callback, null);
    }

    /**
     * ボタンがOKのみのシンプルなアラートダイアログを表示します。
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
    public static final void showSimpleDialog(FragmentManager manager,
	    String title, String message, DialogCallback callback) {
	showSimpleDialog(manager, title, message, null, callback);
    }

    /**
     * ボタンがOKのみのシンプルなアラートダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showSimpleDialog(FragmentManager manager,
	    int titleid, int messageid, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEID, messageid);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE);
	}
	showDialog(manager, args, callback, null);
    }

    /**
     * ボタンがOKのみのシンプルなアラートダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showSimpleDialog(FragmentManager manager,
	    int titleid, int messageid, DialogCallback callback) {
	showSimpleDialog(manager, titleid, messageid, null, callback);
    }

    /**
     * 標準的なアラートダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showAlertDialog(FragmentManager manager,
	    String title, String message, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(manager, args, callback, null);
    }

    /**
     * 標準的なアラートダイアログを表示します。
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
    public static final void showAlertDialog(FragmentManager manager,
	    String title, String message, DialogCallback callback) {
	showAlertDialog(manager, title, message, null, callback);
    }

    /**
     * 標準的なアラートダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showAlertDialog(FragmentManager manager,
	    int titleid, int messageid, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEID, messageid);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(manager, args, callback, null);
    }

    /**
     * 標準的なアラートダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showAlertDialog(FragmentManager manager,
	    int titleid, int messageid, DialogCallback callback) {
	showAlertDialog(manager, titleid, messageid, null, callback);
    }

    /**
     * 標準的な多肢選択式ダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param choices
     *            ダイアログに表示される選択肢
     */
    public static final void showChoiceDialog(FragmentManager manager,
	    String title, Bundle params, DialogCallback callback,
	    String... choices) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putStringArray(ALERT_MESSAGEARRAY, choices);
	showDialog(manager, args, callback, null);
    }

    /**
     * 標準的な多肢選択式ダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param choices
     *            ダイアログに表示される選択肢
     */
    public static final void showChoiceDialog(FragmentManager manager,
	    String title, DialogCallback callback, String... choices) {
	showChoiceDialog(manager, title, null, callback, choices);
    }

    /**
     * 標準的な多肢選択式ダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param choiceslistid
     *            ダイアログに表示される選択肢を示すリソースID
     */
    public static final void showChoiceDialog(FragmentManager manager,
	    int titleid, Bundle params, DialogCallback callback,
	    int choiceslistid) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEARRAYID, choiceslistid);
	showDialog(manager, args, callback, null);
    }

    /**
     * 標準的な多肢選択式ダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param choiceslistid
     *            ダイアログに表示される選択肢を示すリソースID
     */
    public static final void showChoiceDialog(FragmentManager manager,
	    int titleid, DialogCallback callback, int choiceslistid) {
	showChoiceDialog(manager, titleid, null, callback, choiceslistid);
    }

    /**
     * 標準的な入力式ダイアログを表示します。 入力された値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_INPUTSTR}にて取得可能です。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showInputDialog(FragmentManager manager,
	    String title, String message, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	args.putInt(ALERT_VIEWID, R.layout.inputdialog);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(manager, args, callback, null);
    }

    /**
     * 標準的な入力式ダイアログを表示します。 入力された値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_INPUTSTR}にて取得可能です。
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
    public static final void showInputDialog(FragmentManager manager,
	    String title, String message, DialogCallback callback) {
	showInputDialog(manager, title, message, null, callback);
    }

    /**
     * 標準的な入力式ダイアログを表示します。 入力された値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_INPUTSTR}にて取得可能です。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showInputDialog(FragmentManager manager,
	    int titleid, int messageid, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEID, messageid);
	args.putInt(ALERT_VIEWID, R.layout.inputdialog);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(manager, args, callback, null);
    }

    /**
     * 標準的な入力式ダイアログを表示します。 入力された値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_INPUTSTR}にて取得可能です。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showInputDialog(FragmentManager manager,
	    int titleid, int messageid, DialogCallback callback) {
	showInputDialog(manager, titleid, messageid, null, callback);
    }

    /**
     * チェックボックスのついたダイアログを表示します。 チェックボックスの値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_CHECKED}にて取得可能です。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param checkmessage
     *            チェックボックスに表示するメッセージ
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCheckDialog(FragmentManager manager,
	    String title, String message, String checkmessage, Bundle params,
	    DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	args.putString(ALERT_APPENDMESSAGE, checkmessage);
	args.putInt(ALERT_VIEWID, R.layout.checkdialog);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(manager, args, callback, null);
    }

    /**
     * チェックボックスのついたダイアログを表示します。 チェックボックスの値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_CHECKED}にて取得可能です。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param checkmessage
     *            チェックボックスに表示するメッセージ
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCheckDialog(FragmentManager manager,
	    String title, String message, String checkmessage,
	    DialogCallback callback) {
	showCheckDialog(manager, title, message, checkmessage, null, callback);
    }

    /**
     * チェックボックスのついたダイアログを表示します。 チェックボックスの値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_CHECKED}にて取得可能です。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param checkmessageid
     *            チェックボックスに表示するメッセージのリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCheckDialog(FragmentManager manager,
	    int titleid, int messageid, int checkmessageid, Bundle params,
	    DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEID, messageid);
	args.putInt(ALERT_APPENDMESSAGEID, checkmessageid);
	args.putInt(ALERT_VIEWID, R.layout.checkdialog);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(manager, args, callback, null);
    }

    /**
     * チェックボックスのついたダイアログを表示します。 チェックボックスの値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_CHECKED}にて取得可能です。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param checkmessageid
     *            チェックボックスに表示するメッセージのリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCheckDialog(FragmentManager manager,
	    int titleid, int messageid, int checkmessageid,
	    DialogCallback callback) {
	showCheckDialog(manager, titleid, messageid, checkmessageid, null,
		callback);
    }

    /**
     * 独自ビューを表示するダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param view
     *            表示するビューオブジェクトを指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCustomDialog(FragmentManager manager,
	    String title, View view, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(manager, args, callback, view);
    }

    /**
     * 独自ビューを表示するダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param view
     *            表示するビューオブジェクトを指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCustomDialog(FragmentManager manager,
	    String title, View view, DialogCallback callback) {
	showCustomDialog(manager, title, view, null, callback);
    }

    /**
     * 独自ビューを表示するダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param view
     *            表示するビューオブジェクトを指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCustomDialog(FragmentManager manager,
	    int titleid, View view, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(manager, args, callback, view);
    }

    /**
     * 独自ビューを表示するダイアログを表示します。
     *
     * @param manager
     *            ダイアログの表示に用いる{@link FragmentManager}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param view
     *            表示するビューオブジェクトを指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCustomDialog(FragmentManager manager,
	    int titleid, View view, DialogCallback callback) {
	showCustomDialog(manager, titleid, view, null, callback);
    }

    // FragmentTransactionを使うオーバーライド

    /**
     * ボタンがOKのみのシンプルなアラートダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showSimpleDialog(FragmentTransaction transaction,
	    String title, String message, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * ボタンがOKのみのシンプルなアラートダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showSimpleDialog(FragmentTransaction transaction,
	    String title, String message, DialogCallback callback) {
	showSimpleDialog(transaction, title, message, null, callback);
    }

    /**
     * ボタンがOKのみのシンプルなアラートダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showSimpleDialog(FragmentTransaction transaction,
	    int titleid, int messageid, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEID, messageid);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * ボタンがOKのみのシンプルなアラートダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showSimpleDialog(FragmentTransaction transaction,
	    int titleid, int messageid, DialogCallback callback) {
	showSimpleDialog(transaction, titleid, messageid, null, callback);
    }

    /**
     * 標準的なアラートダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showAlertDialog(FragmentTransaction transaction,
	    String title, String message, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * 標準的なアラートダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showAlertDialog(FragmentTransaction transaction,
	    String title, String message, DialogCallback callback) {
	showAlertDialog(transaction, title, message, null, callback);
    }

    /**
     * 標準的なアラートダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showAlertDialog(FragmentTransaction transaction,
	    int titleid, int messageid, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEID, messageid);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * 標準的なアラートダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showAlertDialog(FragmentTransaction transaction,
	    int titleid, int messageid, DialogCallback callback) {
	showAlertDialog(transaction, titleid, messageid, null, callback);
    }

    /**
     * 標準的な多肢選択式ダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param choices
     *            ダイアログに表示される選択肢
     */
    public static final void showChoiceDialog(FragmentTransaction transaction,
	    String title, Bundle params, DialogCallback callback,
	    String... choices) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putStringArray(ALERT_MESSAGEARRAY, choices);
	showDialog(transaction, args, callback, null);
    }

    /**
     * 標準的な多肢選択式ダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param choices
     *            ダイアログに表示される選択肢
     */
    public static final void showChoiceDialog(FragmentTransaction transaction,
	    String title, DialogCallback callback, String... choices) {
	showChoiceDialog(transaction, title, null, callback, choices);
    }

    /**
     * 標準的な多肢選択式ダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param choiceslistid
     *            ダイアログに表示される選択肢を示すリソースID
     */
    public static final void showChoiceDialog(FragmentTransaction transaction,
	    int titleid, Bundle params, DialogCallback callback,
	    int choiceslistid) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEARRAYID, choiceslistid);
	showDialog(transaction, args, callback, null);
    }

    /**
     * 標準的な多肢選択式ダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     * @param choiceslistid
     *            ダイアログに表示される選択肢を示すリソースID
     */
    public static final void showChoiceDialog(FragmentTransaction transaction,
	    int titleid, DialogCallback callback, int choiceslistid) {
	showChoiceDialog(transaction, titleid, null, callback, choiceslistid);
    }

    /**
     * 文字列入力が可能なダイアログを表示します。 入力された値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_INPUTSTR}にて取得可能です。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showInputDialog(FragmentTransaction transaction,
	    String title, String message, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	args.putInt(ALERT_VIEWID, R.layout.inputdialog);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * 文字列入力が可能なダイアログを表示します。 入力された値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_INPUTSTR}にて取得可能です。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showInputDialog(FragmentTransaction transaction,
	    String title, String message, DialogCallback callback) {
	showInputDialog(transaction, title, message, null, callback);
    }

    /**
     * 文字列入力が可能なダイアログを表示します。 入力された値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_INPUTSTR}にて取得可能です。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showInputDialog(FragmentTransaction transaction,
	    int titleid, int messageid, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEID, messageid);
	args.putInt(ALERT_VIEWID, R.layout.inputdialog);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * 文字列入力が可能なダイアログを表示します。 入力された値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_INPUTSTR}にて取得可能です。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showInputDialog(FragmentTransaction transaction,
	    int titleid, int messageid, DialogCallback callback) {
	showInputDialog(transaction, titleid, messageid, null, callback);
    }

    /**
     * チェックボックスのついたダイアログを表示します。 チェックボックスの値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_CHECKED}にて取得可能です。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param checkmessage
     *            チェックボックスに表示するメッセージ
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCheckDialog(FragmentTransaction transaction,
	    String title, String message, String checkmessage, Bundle params,
	    DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	args.putString(ALERT_MESSAGE, message);
	args.putString(ALERT_APPENDMESSAGE, checkmessage);
	args.putInt(ALERT_VIEWID, R.layout.checkdialog);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * チェックボックスのついたダイアログを表示します。 チェックボックスの値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_CHECKED}にて取得可能です。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param message
     *            メッセージとして表示する文字列を指定します。
     * @param checkmessage
     *            チェックボックスに表示するメッセージ
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCheckDialog(FragmentTransaction transaction,
	    String title, String message, String checkmessage,
	    DialogCallback callback) {
	showCheckDialog(transaction, title, message, checkmessage, null,
		callback);
    }

    /**
     * チェックボックスのついたダイアログを表示します。 チェックボックスの値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_CHECKED}にて取得可能です。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param checkmessageid
     *            チェックボックスに表示するメッセージのリソースID
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCheckDialog(FragmentTransaction transaction,
	    int titleid, int messageid, int checkmessageid, Bundle params,
	    DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	args.putInt(ALERT_MESSAGEID, messageid);
	args.putInt(ALERT_APPENDMESSAGEID, checkmessageid);
	args.putInt(ALERT_VIEWID, R.layout.checkdialog);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * チェックボックスのついたダイアログを表示します。 チェックボックスの値はコールバックメソッドのパラメータ
     * {@link Dialogs#PARAMS_CHECKED}にて取得可能です。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param messageid
     *            メッセージとして表示する文字列のリソースID
     * @param checkmessageid
     *            チェックボックスに表示するメッセージのリソースID
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCheckDialog(FragmentTransaction transaction,
	    int titleid, int messageid, int checkmessageid,
	    DialogCallback callback) {
	showCheckDialog(transaction, titleid, messageid, checkmessageid, null,
		callback);
    }

    /**
     * 独自ビューを表示するダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param view
     *            表示するビューオブジェクトを指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCustomDialog(FragmentTransaction transaction,
	    String title, View view, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putString(ALERT_TITLE, title);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * 独自ビューを表示するダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param title
     *            タイトルとして表示する文字列
     * @param view
     *            表示するビューオブジェクトを指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCustomDialog(FragmentTransaction transaction,
	    String title, View view, DialogCallback callback) {
	showCustomDialog(transaction, title, view, null, callback);
    }

    /**
     * 独自ビューを表示するダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param view
     *            表示するビューオブジェクトを指定します。
     * @param params
     *            その他追加パラメータを格納した{@link Bundle}オブジェクト
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCustomDialog(FragmentTransaction transaction,
	    int titleid, View view, Bundle params, DialogCallback callback) {
	Bundle args = params != null ? new Bundle(params) : new Bundle();
	args.putInt(ALERT_TITLEID, titleid);
	if (!args.containsKey(ALERT_BUTTONS)) {
	    args.putInt(ALERT_BUTTONS, DIALOGBUTTON_POSITIVE_NEGATIVE);
	}
	showDialog(transaction, args, callback, null);
    }

    /**
     * 独自ビューを表示するダイアログを表示します。
     *
     * @param transaction
     *            ダイアログの表示に用いる{@link FragmentTransaction}オブジェクト
     * @param titleid
     *            タイトルとして表示する文字列のリソースID
     * @param view
     *            表示するビューオブジェクトを指定します。
     * @param callback
     *            ダイアログがクローズされた際にコールバックされる{@link DialogCallback}オブジェクトを指定します。
     */
    public static final void showCustomDialog(FragmentTransaction transaction,
	    int titleid, View view, DialogCallback callback) {
	showCustomDialog(transaction, titleid, view, null, callback);
    }

    // 最終的に呼び出されるメソッド

    /**
     * アラートダイアログを表示します。
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
    public static final void showDialog(FragmentManager manager, Bundle params,
	    DialogCallback callback, View v) {
	String tagStr = String.valueOf(tag++);

	// staticなメモリにデータを保存
	dialogData.put(tagStr, new DialogData(v, callback));
	// 表示
	InternalDialogFragment dialog = new InternalDialogFragment();
	dialog.setArguments(params);
	dialog.show(manager, String.valueOf(tagStr));
    }

    /**
     * アラートダイアログを表示します。
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
    public static final void showDialog(FragmentTransaction transaction,
	    Bundle params, DialogCallback callback, View v) {
	String tagStr = String.valueOf(tag++);

	// staticなメモリにデータを保存
	dialogData.put(tagStr, new DialogData(v, callback));
	// 表示
	InternalDialogFragment dialog = new InternalDialogFragment();
	dialog.setArguments(params);
	dialog.show(transaction, String.valueOf(tagStr));
    }

}
