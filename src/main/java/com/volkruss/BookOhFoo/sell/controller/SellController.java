package com.volkruss.BookOhFoo.sell.controller;

import com.volkruss.BookOhFoo.sell.usecase.SellRegisterService;
import com.volkruss.BookOhFoo.sell.usecase.SellSubmitService;
import com.volkruss.BookOhFoo.sell.usecase.ShowPurchaseView;
import com.volkruss.BookOhFoo.sell.usecase.output.PurchaseViewOutput;
import com.volkruss.BookOhFoo.sell.usecase.output.RegistViewOutput;
import com.volkruss.BookOhFoo.system.exception.InputValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class SellController {
    @Autowired
    private SellRegisterService purchaseRequestInputBoundary;

    @Autowired
    private ShowPurchaseView showPurchaseView;

    @Autowired
    private SellSubmitService sellSubmitService;

    @PostMapping("/sell/execute")
    public String handle(@Validated @ModelAttribute RegisterForm registerForm, BindingResult bindingResult, @ModelAttribute SubmitForm submitForm, Model model) {
        if(bindingResult.hasErrors()){
            throw new InputValidationException(bindingResult);
        }
        RegistViewOutput registViewOutput = this.purchaseRequestInputBoundary.action(registerForm);

        model.addAttribute("result",registViewOutput);

        return "purchase/confirmation";
    }

    @GetMapping("/sell/view")
    public String view(@ModelAttribute RegisterForm registerForm, Model model){

        List<PurchaseViewOutput> outs = this.showPurchaseView.showPurchaseView();

        List<BookViewModel> books = outs.stream()
                .map(this::toViewModel)
                .collect(Collectors.toList());
        model.addAttribute("cmbBoxs",books);

        ReceiveWay[] ways = ReceiveWay.values();
        model.addAttribute("ways",ways);

        // templateはフォルダを"/"で区切って指定します。
        return "purchase/register";
    }

    @PostMapping("/sell/submit")
    public String submit(@ModelAttribute SubmitForm submitForm){
        this.sellSubmitService.submit(submitForm.getSellId());
        return "purchase/done";
    }

    private BookViewModel toViewModel(PurchaseViewOutput out){
        return new BookViewModel(out.getBookId(),out.getTitle(),out.getPrice());
    }
}
