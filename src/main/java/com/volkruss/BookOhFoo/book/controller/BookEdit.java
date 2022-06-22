package com.volkruss.BookOhFoo.book.controller;

import com.volkruss.BookOhFoo.book.application.usecase.book.BookEditService;
import com.volkruss.BookOhFoo.book.application.usecase.book.BookOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookEdit {

    @Autowired
    private BookEditService bookEditService;

    // 書籍一覧画面
    @GetMapping("/book/edit")
    public String bookEditAll(@ModelAttribute BookRegisterForm bookRegisterForm, Model model){
        // 本の一覧を取得する
        List<BookOut> list = this.bookEditService.getAllBooks();
        model.addAttribute("list",list);
        return "book/edit";
    }

    // 書籍修正画面
    @GetMapping("/book/detail")
    public String bookEdit(@RequestParam(name = "bookId") String bookId, @ModelAttribute BookRegisterForm bookRegisterForm, Model model){
        BookOut bookOut = this.bookEditService.getBookByBookId(bookId);
        model.addAttribute("book",bookOut);
        return "book/editDetail";
    }

    @PostMapping("/book/edit/execute")
    public String update(@ModelAttribute BookRegisterForm bookRegisterForm){
        this.bookEditService.update(bookRegisterForm);
        return "book/editDone";
    }

    // 書籍削除
    @PostMapping("/book/delete")
    public String bookDelete(@RequestParam(name = "bookId") String bookId){
        this.bookEditService.delete(bookId);
        return "book/deleteDone";
    }

}
