package com.insider.travel.japan.wabisabi.common.log;

import static com.insider.travel.japan.wabisabi.common.log.Loggable.UNDEFINED;
import static java.util.Comparator.comparing;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <pre>
 * ログパラメータクラス。
 * {@link LoggableParser#parse(jp.co.saison.sis.hulft.tsubomi.agent.utils.LogParameters) }することを前提にしたログフィールドを持つクラス。
 * 以下のような使い方を想定しています。
 *
 * {@code 
 *  class SampleParameters extends LogParameters {
 *      @Loggable(order = 1)
 *      String id;
 *      @Loggable(order = 2)
 *      String name;
 *  }
 *
 *  class Main {
 *      public static void main(String[] args) {
 *          SampleParameters test = new SampleParameters();
 *          test.id = "sampleId-xxx";
 *          test.name = "sampleName"
 *
 *          // returns "id : [sampleId-xxx], name : [sampleName]"
 *          test.formattedParameters();
 *
 *          test.name = "edit"; // フィールドの値だけ変えても、formattedParameters()の結果は変わりません
 *          // returns "id : [sampleId-xxx], name : [sampleName]"
 *          test.formattedParameters();
 *
 *          test.name = "edit";
 *          test.update("name", "edit"); // formattedParameters()の結果を更新する場合はupdate()を使ってください
 *          // returns "id : [sampleId-xxx], name : [edit]"
 *          test.formattedParameters();
 *      }
 *  }
 * }
 * 
 * このクラスではフィールドの値と、ログ出力用の値を分けて管理しています。
 * これは、できる限りキャッシュされた値を使い、{@link LogParameters#formattedParameters()}が呼ばれる度に文字列内容を組み立て直すのを避ける為です。
 * このクラスを継承クラスでは、getter, setter のアクセサメソッドの内部で{@link LogParameters#update(java.lang.String, java.lang.String) を呼ぶことをオススメします。
 * </pre>
 */

public class LogParameters {
    
    protected String formattedParameters;

    protected final Map<String, Item> loggingFields;

    private boolean initialized = false;
    private boolean updated = false;

    /**
     * コンストラクタ
     */
    public LogParameters() {
        loggingFields = new HashMap<>();
        LoggableParser.parse(this);
        // インスタンス生成後の初回のupdateLoggingParametersでparseさせるため
        initialized = false;
    }

    /**
     * 値の更新があることを通知します。
     *
     * @param key キーとなるフィールド名
     * @param value フィールドに格納する（された）値
     */
    public void update(String key, String value) {
        // フラグだけ更新。引数の値は今後使うかも。
        updated = true;
    }

    void put(String key, Item item) {
        loggingFields.put(key, item);
    }

    /**
     * ログ用にフォーマットした文字列を返す。
     * @return フォーマットされた文字列
     */
    public String formattedParameters() {
        updateLoggingParameters();
        return formattedParameters;
    }

    private void updateLoggingParameters() {

        // 初回と値の更新がある場合は、インスタンスの状態を見直して文字列を作り直す。
        if (!initialized || updated) {
            LoggableParser.parse(this);
            formattedParameters = loggingFields.entrySet().stream()
                    .filter(el -> el.getValue().always || el.getValue().value != null)
                    .sorted(comparing(el -> el.getValue().order))
                    .map(el -> formatting(el))
                    .collect(Collectors.joining(", "));
            initialized = true;
            updated = false;
        }
    }

    private String formatting(Map.Entry<String, Item> entry) {
        return formattedValue(entry.getValue().key, entry.getValue().value == null ? UNDEFINED : entry.getValue().value);
    }

    private String formattedValue(String key, String value) {
        return new StringBuilder(key)
                .append(":")
                .append("[")
                .append(value)
                .append("]")
                .toString();
    }

    /**
     * ログ出力用の項目用のクラス
     */
    public static class Item {

        int order;
        String key;
        String value;
        boolean always;

        public Item(int order, String key, String value, boolean always) {
            this.order = order;
            this.key = key;
            this.value = value;
            this.always = always;
        }
    }
    
}
