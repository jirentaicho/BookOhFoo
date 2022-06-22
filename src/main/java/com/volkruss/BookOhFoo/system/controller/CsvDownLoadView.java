package com.volkruss.BookOhFoo.system.controller;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class CsvDownLoadView extends AbstractView {

    private final CsvOutput csvOutput;

    private final Collection<?> data;

    public CsvDownLoadView(Class<?> clazz, List<?> data){
        setContentType("application/octet-stream; charset=Windows-31J;");
        this.csvOutput = new CsvOutput(clazz);
        this.data = data;
    }

    @Override
    protected boolean generatesDownloadContent(){
        return true;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String filename = "csv.csv";

        String encodedFilename = URLEncoder.encode(filename, "UTF-8");
        String contentDisposition = String.format("inline");

        response.setHeader(CONTENT_TYPE, getContentType());
        response.setHeader(CONTENT_DISPOSITION, contentDisposition);

        ObjectWriter w = this.csvOutput.getWriterWithSchema();
        ByteArrayOutputStream out = createTemporaryOutputStream();

        try(Writer writer = new OutputStreamWriter(out,"Windows-31J")){
            w.writeValue(writer,this.data);
        }
        writeToResponse(response,out);
    }
}
