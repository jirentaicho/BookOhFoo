package com.volkruss.BookOhFoo.system.controller;

import org.springframework.web.servlet.view.AbstractView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

@Deprecated
public class PdfView extends AbstractView {

    protected String filename;

    protected Collection<?> data;

    public PdfView(){
        super();
        this.setContentType("application/pdf");

    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
