package com.volkruss.BookOhFoo.system.controller;

import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class HogeView extends AbstractView {

    protected static final CsvMapper csvMapper = createCsvMapper();

    protected Class<?> clazz;

    protected Collection<?> data;

    private String filename;

    static CsvMapper createCsvMapper(){
        CsvMapper mapper = new CsvMapper();
        mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS,true);
        mapper.findAndRegisterModules();
        return mapper;
    }

    public HogeView(Class<?> clazz, Collection<?> data, String filename){
        setContentType("application/octet-stream; charset=Windows-31J;");
        this.clazz = clazz;
        this.data = data;
        this.filename = filename;
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String filename = "csv.csv";

        String encodedFilename = URLEncoder.encode(filename, "UTF-8");
        String contentDisposition = String.format("attachment; filename*=UTF-8''%s", encodedFilename);

        response.setHeader(CONTENT_TYPE, getContentType());
        response.setHeader(CONTENT_DISPOSITION, contentDisposition);


        CsvSchema schema = csvMapper.schemaFor(clazz).withHeader();
        ByteArrayOutputStream outputStream = createTemporaryOutputStream();
        try (Writer writer = new OutputStreamWriter(outputStream, "Windows-31J")) {
            csvMapper.writer(schema).writeValue(writer, data);
            writer.write("aaaaaaaaa");
        }

    }

}
