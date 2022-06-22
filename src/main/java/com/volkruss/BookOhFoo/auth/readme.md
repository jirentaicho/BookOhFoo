
# ユーザーについて

* ログイン対象者
  * 一般ユーザーは無し
    * 管理者のみ
  * ロールあり
    * 在庫管理者
    * システム管理者

# ログイン構成

DB認証を行うため、自作のプロバイダーを作成していません。  
loadUserByUsernameのオーバーライドのみを行う
  
* パスワードのチェックはDaoAuthenticationProvider
* ユーザーの取得はUserDetailsServiceの自作実装クラス
* ロールの付与はUserDetailsServiceの自作実装クラス
* ユーザクラスは既存のUserを拡張したクラスを作成(現状はそれだけ別にしなくてもいい)

[参考](https://zenn.dev/misaka/scraps/3ce785913f1bc3#comment-9a48a70686fb8a)

# ロール

* システム管理者
  * csvの出力
* 在庫管理者
  * Bookレコードの管理


# インターセプタ

特定のURLにアクセスした場合は、ログインしているユーザが特定の権限を持っているかチェックします。

実装手順

1. HandlerInterceptorを実装したクラスを作成する
2. preHandle(コントローラー処理前)に差し込む(他にもオーバーライドできるメソッドがあります)
3. WebMvcConfigurerの実装クラスを作成する
4. addInterceptorsメソッドをオーバーライドする
5. InterceptorRegistryに作成したクラスを登録する

