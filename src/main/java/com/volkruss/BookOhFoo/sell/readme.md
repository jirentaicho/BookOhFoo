# バリデーション

単項目チェックと相関項目チェックで異なる

アノテーションを使う場合に依存

```gradle
implementation 'org.springframework.boot:spring-boot-starter-validation'
```


◆アノテーションで済ませる

1. Formクラスにアノテーションをつける
2. コントローラーに@Validatedアノテーションを付ける
3. BindingResultで受け取る

例
```java
@NotBlank
String name;
```

エラーは存在チェックをしないと処理が流れていきます

```java
@PostMapping("/sell/execute")
public String handle(@Validated @ModelAttribute RegisterForm registerForm, BindingResult bindingResult, @ModelAttribute SubmitForm submitForm, Model model) {
    if(bindingResult.hasErrors()){
        System.out.println("aaaaaaa");
    }
```


◆相関チェックや独自のロジックを含める

1. Validatorを実装したクラスを作成する
2. validateメソッドを実装する
3. @InitBinderでWebDataBinderと作成したクラスを紐づける
4. コントローラーに@Validatedアノテーションを付ける
5. BindingResultで受け取る



