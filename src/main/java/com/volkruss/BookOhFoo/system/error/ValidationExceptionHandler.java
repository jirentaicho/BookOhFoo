package com.volkruss.BookOhFoo.system.error;

import com.volkruss.BookOhFoo.system.exception.InputValidationException;
import com.volkruss.BookOhFoo.utils.message.MessageUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.regex.Pattern;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InputValidationException.class)
    public String validationException(Exception e, WebRequest request, RedirectAttributes redirectAttributes){
        if(e instanceof InputValidationException) {
            InputValidationException exception = (InputValidationException) e;
            redirectAttributes.addFlashAttribute("errors", exception.getErrors().getAllErrors());
            String beforUrl = request.getHeader("REFERER");
            String redirectPath = this.getPath(beforUrl);
            return "redirect:" + redirectPath;
        }
        return "redirect:/system/error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String AccessDeniedException(Exception e, WebRequest request, RedirectAttributes redirectAttributes){
        if(e instanceof AccessDeniedException) {
            AccessDeniedException exception = (AccessDeniedException) e;
            MyError myErrror = new MyError("myError",MessageUtils.getSimpleMessage("NotRole"));

            // errorsを設定する
            redirectAttributes.addFlashAttribute("errors", List.of(myErrror));
            // 元画面のURLに戻す
            String beforUrl = request.getHeader("REFERER");
            String redirectPath = this.getPath(beforUrl);
            return "redirect:" + redirectPath;
        }
        return "redirect:/system/error";
    }

    private String getPath(String fullPath){
        // http(s)://~~~~/までを除去する
        Pattern p = Pattern.compile("http(s)?://(.*?)/");
        String result = fullPath.replaceAll(p.pattern(),"");
        return "/" + result;
    }
}
