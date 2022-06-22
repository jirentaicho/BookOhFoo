[参考](https://volkruss.com/?p=492)

# csv出力

```gradle
implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-csv'
```

1. CSV出力するクラスを作成する

* @JsonPropertyでヘッダ指定

```java
public class SellCsv {
    @JsonProperty(value = "売ID",index = 1)
    private String SellId;

    @JsonProperty(value = "名前",index = 2)
    private String name;

    @JsonProperty(value = "振込方法",index = 3)
    private String way;

    @JsonProperty(value = "キャンペーン適用金額",index = 4)
    private int campaign;

    @JsonProperty(value = "合計買取金額",index = 5)
    private int amt;
}
```

* indexで出力順序を決定できます

2. CsvMapperの利用

```java
public class CsvOutput {

    private final Class targetClass;

    private final CsvMapper csvMapper;

    public CsvOutput(Class clazz){
        this.targetClass = clazz;
        this.csvMapper = new CsvMapper();
        this.csvMapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS,true);
        this.csvMapper.findAndRegisterModules();
    }

    public String getCsv(List<?> data) throws JsonProcessingException {
        CsvSchema csvSchema = this.csvMapper.schemaFor(this.targetClass).withHeader();
        String result = csvMapper.writer(csvSchema).writeValueAsString(data);
        return result;
    }
}
```

3. 利用してみる

```java
public String outcsv() throws JsonProcessingException {
    SellCsv sellCsv = SellCsv.builder()
                    .SellId("SELLID").amt(120).campaign(11).name("御坂美琴").way("CASH").build();
    CsvOutput csvOutput = new CsvOutput(SellCsv.class);
    String hoge = csvOutput.getCsv(List.of(sellCsv));
    System.out.println(hoge);
    return null;
}
```

* 結果

```
"売ID","名前","振込方法","キャンペーン適用金額","合計買取金額"
"SELLID","御坂美琴","CASH",11,120
```

# エラーハンドリング

特定の例外が発生した時に処理を行うおうにする

* 自作の例外を作成する
  * 自作である必要はない
* ResponseEntityExceptionHandlerを継承したクラスを作成する
* ExceptionHandlerアノテーションで処理する例外クラスを指定する


##### RESTにてエラーハンドリングをしたい場合  
[参考](https://github.com/jirentaicho/springboot-transaction-sample/blob/main/src/main/java/com/volkruss/transactiontest/controller/ApiExceptionHandler.java)

##### 画面側でのエラー処理

エラーがあり元の画面に戻したときに、detailedErrorsを使って参照可能
```html
<ul>
  <li th:each="error : ${#fields.detailedErrors()}"
      class="danger" th:text="${error.message}" />
</ul>
```

RequestからPostする前のURLを取得するのは以下の方法
```java
request.getHeader("REFERER");
// → http://localhost:8080/sell/view
```

使ってみると以下のような感じで、この場合はリダイレクトしているので
RedirectAttributesに対してエラーを設定してあげてエラーを画面に表示する

```java
    @ExceptionHandler(InputValidationException.class)
public String validationException(Exception e, WebRequest request, RedirectAttributes redirectAttributes){
  if(e instanceof InputValidationException){
    InputValidationException exception = (InputValidationException) e;
    // 元画面のURLに戻す
    String beforUrl = request.getHeader("REFERER");
    redirectAttributes.addFlashAttribute("test","sampleText");
    return "redirect:/sell/view";
  }
  return "システム例外";
}
```

```html
<P th:text="${test}"></P>
```






# ファイルをダウンロード

特定のファイルをダウンロードするのではなくCSVファイルを作成してダウンロードさせます

