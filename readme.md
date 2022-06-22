
# 概要

BookOhFooは本の買取サービスをアプリケーションにしました
データベースはh2を使っているのでそのまま実行できますが
Mapにも変更できます
画面はタイムリーフを使っています

* 本を下取りに出す
* ロジック
  * 本の買取価格を取得します
  * 合計買取価格を計算します
  * キャンペーンがあればキャンペーンの適用をする
  * 合計買取価格を提示します
  * 一旦ワークテーブルに登録します

ワークテーブルのID(hidden)と、買取明細を画面に表示させます。

* 売る(確定)
* ロジック
  * 本の在庫を増やす
  * 買取/買取明細を登録する

  

# ユースケース

複数の処理がある場合は、オーケストレーションクラスを用意して処理を行うことにします。
例：

* ユーザー情報変更処理(通常、これらは分かれたユースケースで表現されることが多いです。あくまで例)
  * ユーザー名の変更
  * パスワードの変更
  * アイコンの変更
　
## アプリケーションのユースケース

売る人

* 本を査定に出す
* 本を売る(確定させる)

管理者

* 本の買取価格を変更する
* キャンペーンの変更を行う→これはまだ
* 買取表を出力する
  * PDF
  * CSV

## タイトル

本を査定に出す
　何が動く？

まずはBookエンティティが働きます
Bookエンティティは自身の買取価格をしっていますので

```java
Book book = bookRepository.findBook(bookId);
BookList list = add(book);
Campan campan = Campan.
hoge.clac(list)
```

## テーブル定義

<details>
<summary>テーブル定義(仮)</summary>
<div>

books

|  型  |  カラム名  |
| ---- | ---- |
| INT  |  ID  |
| STR  |  タイトル  |
| INT  |  買取価格  |

stocks

|  型  |  カラム名  |
| ---- | ---- |
| INT  |  ID  |
| STR  |  タイトル  |
| INT  |  在庫数  |

purchase(買取ヘッダ)

|  型  |  カラム名  |
| ---- | ---- |
| INT  |  ID  |
| INT  |  買取番号  |
| INT  |  ユーザーID  |

purchaseDetail(買取明細)

|  型  |  カラム名  |
| ---- | ---- |
| INT  |  ID  |
| INT  |  買取番号  |
| INT  |  買取明細番号  |
| INT  | 本ID　|


users

|  型  |  カラム名  |
| ---- | ---- |
| INT  |  ID  |
| STR  |  ユーザー名  |
| STR  |  パスワード  |

roles

|  型  |  カラム名  |
| ---- | ---- |
| INT  |  ID  |
| INT  |  ユーザーID  |
| STR  |  ロール  |


receiveway

|  型  |  カラム名  |
| ---- | ---- |
| INT  |  ID  |
| STR  |  受取方法  |

</div>
</details>

※ 現状はデータベースを使わずコードで管理しています。リポジトリを実装して依存を切り替えればデータベースを利用できます。

# Formバインディングの実装手順

コントローラークラスとhtmlファイルを参照

準備
* Formクラスを作成します
  * ここで単項目チェックであればアノテーションベースで記載できます

送信側

フォーム
* Thymeleafの作成
  * th:object="${form変数名}"でFormオブジェクトをHTMLフォームへバインディング
  * th:filedで入力項目とFormオブジェクトのプロパティを紐づけ
  * th:action="/action"にPostのURLを指定する

コントローラー
* 引数に@ModelAttributeを付けて、先ほどのFormクラスを指定する


受信側(POSTで受けるメソッド)
コントローラー
* 引数に@ModelAttributeを付けて、先ほどのFormクラスを指定する
  * この時に、Formクラスの変数に値がバインドされています
  
```java
@PostMapping("/purchase/execute")
public String handle(@ModelAttribute RegisterForm registerForm) {
    return null;
}

@GetMapping("/purchase/view")
public String view(@ModelAttribute RegisterForm registerForm, Model model){
    return "purchase/register";
}
```

```html
<form th:action="@{/purchase/execute}" th:object="${registerForm}" method="post">
    <input th:type="text" th:field="*{title}" />
    <button type="submit">査定に出す</button>
</form>
```

for
```java
Map<String,String> itemCmb = new LinkedHashMap<>();
itemCmb.put("keyA","PHP");
itemCmb.put("keyB","Java");
itemCmb.put("keyC","C#");
model.addAttribute("cmbBoxs",itemCmb);
```
ビューで表示
```html
  <div th:each="cmb : ${cmbBoxs}">
      <p th:text="${cmb}"></p>
  </div>
```

コンボボックス

基本型

Formクラス

```java
public class RegisterForm {
    String title;
    String selectedItem;
}
```
ビュー(利用するリストはforの箇所で記載したもの)
```html
<select th:field="*{selectedItem}">
    <option value="">-</option>
    <option th:each="cmb : ${cmbBoxs}" th:value="${cmb}" th:text="${cmb}">本</option>
</select>
```
選択したコンボボックスの値が取得できます。

コンボボックスのセレクト値をクラスに紐づける

* 専用のクラス

```java
@Getter
@Setter
@AllArgsConstructor
public class Book {
    private int id;
    private String title;
}
```

* Formクラス
  * あくまでView側から受け取る想定の変数名

```java
@Getter
@Setter
public class RegisterForm {
    String title;
    String selectedItem;
}
```

* リストをViewに渡す

```java
@GetMapping("/purchase/view")
public String view(@ModelAttribute RegisterForm registerForm, Model model){
    List<Book> books = List.of(
            new Book(1,"優しいJava"),
            new Book(2,"パーフェクトVBA"),
            new Book(3,"Unityで学ぶC#")
    );
    model.addAttribute("cmbBoxs",books);
    
    return "purchase/register";
}
```

View側

```html
<form th:action="@{/purchase/execute}" th:object="${registerForm}" method="post">
    <input th:type="text" th:field="*{title}" />
    <select th:field="*{selectedItem}"><!-- ここはregisterFormの変数にバインドさせます -->
        <option value="">-</option>
        <option th:each="cmb : ${cmbBoxs}" th:value="${cmb.getId()}" th:text="${cmb.getTitle()}">本</option>
    </select>
    <button type="submit">査定に出す</button>
</form>
```
* th:each="ループ変数名 : addAttributeした名前"
* th:value="${ループ変数名.メソッド名(ゲッター)}"
  * ここの値が送信されます
* th:text="${ループ変数名.メソッド名(ゲッター)}"
  * ここの値がコンボボックスに表示されます
* th:field="*{Formの変数名}"
  * ここで設定された値が送信されます(選択したvalue)


リストを送信する

売る本は可変長です。そのためビュー側でリストを生成する必要があります。
ビュー側で生成したリストをコントローラーが捌く必要があります 

* jsでブラウザ上でラムダを即時実行するにはラムダ式を()で囲います。


* FormクラスにList<String> listを持っている想定
* ビュー側ではth:filed="*{list[index]}

th:eachについての詳細 [Thymeleaf th:each](https://www.ne.jp/asahi/hishidama/home/tech/java/spring/boot/thymeleaf/th_each.html)

■基本型

Formクラス

```java
@Getter
@Setter
public class RegisterForm {
    String title;
    String selectedItem;
    List<String> lists;
}
```

ビュー

```html
<form th:action="@{/purchase/execute}" th:object="${registerForm}" method="post">
    <input th:type="text" th:field="*{title}" />

    <select th:field="*{selectedItem}">
        <option value="">-</option>
        <option th:each="cmb : ${cmbBoxs}" th:value="${cmb.getId()}" th:text="${cmb.getTitle()}">本</option>
    </select>

    <input th:type="text" th:field="*{lists[0]}" /><!-- 変数名[n]でアクセスする -->
    <input th:type="text" th:field="*{lists[1]}" />

    <div id="form-detail"></div>

    <button type="submit">査定に出す</button>
</form>
```

Java/C#で画面から入力した場合以下のようなデータ構造になる

```
registerForm = {RegisterForm@7589} 
 title = ""
 selectedItem = ""
 lists = {ArrayList@7590}  size = 2
  0 = "Java"
  1 = "C#"
```

また、生成されるHTMLは以下の通り

```html
<form action="/purchase/execute" method="post"><input type="hidden" name="_csrf" value="139e2ecf-0a94-4743-b8a6-9e0b254f0061">
  <input type="text" id="title" name="title" value="">
  <select id="selectedItem" name="selectedItem"><!-- ここはregisterFormの変数にバインドさせます -->
      <option value="">-</option>
      <option value="1">優しいJava</option>
      <option value="2">パーフェクトVBA</option>
      <option value="3">Unityで学ぶC#</option>
  </select>
  <input type="text" id="lists0" name="lists[0]" value="">
  <input type="text" id="lists1" name="lists[1]" value="">
  <div id="form-detail"></div>
  <button type="submit">査定に出す</button>
</form>
```
* ここで重要なのはnameでありidは不要(idを消しても登録可能)
* jsで動的フィールドを追加する場合はここを作成する

番号が飛んだ場合はサイズだけ増えるので注意
※nullは表示されません
```
copy = {ArrayList@7557}  size = 6
 0 = "Java"
 1 = "C#"
 4 = "Python"
 5 = "PHP"
```

Listに自作の型を指定して利用する場合  
例：  
　List<ItemDetail> detail;

```java
public class RegisterForm {
    // 依頼者名
    String name;
    // 電話番号
    int phoneNumber;
    // 金額受取方法
    String receiveWay;
    // 品目明細
    List<ItemDetail> detail;
```

Listで管理している明細

```java
// 品物明細です
@Setter
@Getter
public class ItemDetail {
    // 商品ID
    String itemId;
    // 数量
    int count;
}
```


ビュー側

```html
<table>
    <tr>
        <th>商品名</th>
        <th>数量</th>
    </tr>
    <tr>
        <td>
            <select th:field="*{detail[0].itemId}">
                <option value="">-</option>
                <option th:each="cmb : ${cmbBoxs}" th:value="${cmb.getId()}" th:text="${cmb.getTitle()}">本</option>
            </select>
        </td>
        <td>
            <input th:type="number" th:field="*{detail[0].count}" />
        </td>
    </tr>
</table>
```

基本的にはListに対して[n]でアクセスして、あとはそのクラスを操作する感じ
privateでもアクセスできている

* th:field="*{detail[0].itemId}"
  * th:field="*{Listの変数名[n].Listの型の変数名}"

そのためわざわざゲッターメソッドを経由する必要はない

```html
<td>
    <select th:field="*{detail[0].itemId}">
        <option value="">-</option>
        <option th:each="cmb : ${cmbBoxs}" th:value="${cmb.id}" th:text="${cmb.title}">本</option>
    </select>
</td>
```


Enumを配列にしてコンボボックスに表示させる

```java
ReceiveWay[] ways = ReceiveWay.values();
model.addAttribute("ways",ways);
```

HTMLで表示させる

```html
<div>
    <label>金額受取方法</label>
    <select th:field="*{receiveWay}">
        <option th:each="way : ${ways}" th:value="${way.name}" th:text="${way.name}"></option>
    </select>
</div>
```

* nameで値を取得する


# reduce

便利です

```java
public int getTotalAmt() {
    return cart.stream().reduce(
            0,
            (ac,order) -> ac + (order.getAmt() * order.getCount()),
            (ac1,ac2) -> ac1 + ac2);
}
```

[sankou](https://teratail.com/questions/178130)


# パスを受け取る

```java
@GetMapping("/book/detail/{bookId}")
public String bookEdit(@PathVariable("bookId") String bookId,Model model){
```
* book/detail/hogehoge

```java
@GetMapping("/book/detail")
public String bookEdit(@RequestParam(name = "bookId") String bookId, Model model){
```

この場合はビュー側でパラメータを設定します

```html
<td><a th:href="@{/book/detail/(bookId=${book.bookId})}">[修正]</a></td>
```

* book/detail/?bookId=hogehoge


# メッセージ

messages.propertiesを作成する



# メモ

<details>
<summary>メモ</summary>
<div>

* RegisterFormはinputData
  * リクエストの内容をユースケースに便利な形に変換するが、ここはRegisterFormがそれに該当する(該当させる)
* バリデーション
  * フォームのバリデーションはコントローラー側にて実装する
    * 本来は別のレイヤーだが、Validatorを使った方が良い
* コントローラーからユースケースに渡すFormオブジェクト
  * 単純なDTOなので境界を超えて渡すデータとして成立しています
* フレームワークは詳細？
  * 本来ならばフレームワークは詳細ですが、内側レイヤーにも関与しています
    * 実際トランザクションはAOPで動いていますが、アプリケーション層からアノテーションを利用するということで、フレームワークに依存している
      * 厳密にはxmlでbean定義すればコード上は依存が消えると思います

</div>
</details>