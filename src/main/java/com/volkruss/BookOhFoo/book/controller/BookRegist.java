package com.volkruss.BookOhFoo.book.controller;

import com.volkruss.BookOhFoo.book.application.usecase.book.BookRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookRegist {

    @Autowired
    private BookRegistService bookRegistService;

    @GetMapping("/book/register")
    public String bookRegister(@ModelAttribute BookRegisterForm bookRegisterForm){
        return "book/bookregiseter";
    }

    @PostMapping("/book/register")
    public String bookRegist(@ModelAttribute BookRegisterForm bookRegisterForm){
        this.bookRegistService.registerNewBook(bookRegisterForm);
        return "/book/done";
    }

}
