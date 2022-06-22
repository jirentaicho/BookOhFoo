package com.volkruss.BookOhFoo.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volkruss.BookOhFoo.system.application.usecase.csv.CsvService;
import com.volkruss.BookOhFoo.system.application.usecase.csv.SellCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SystemController {

    @Autowired
    private CsvService csvService;

    @GetMapping("/system/outcsv")
    public String outcsv(Model model) throws JsonProcessingException {

        List<SellCsv> sells = this.csvService.getSellCsv();

        CsvOutput csvOutput = new CsvOutput(SellCsv.class);
        String csv = csvOutput.getCsv(sells);

        model.addAttribute("csv",csv);

        return "system/showcsv";
    }

    // download.csvでファイルダウンロード
    @GetMapping(value = "/system/download.csv",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=Shift_JIS; Content-Disposition: attachment")
    @ResponseBody
    public Object download() throws JsonProcessingException {
        List<SellCsv> sells = this.csvService.getSellCsv();
        CsvOutput csvOutput = new CsvOutput(SellCsv.class);
        return csvOutput.getCsv(sells);
    }

    @GetMapping("/system/pdf")
    public ModelAndView getPdf(){
        return null;
    }

    @GetMapping("/system/csv/download")
    public ModelAndView downloadView(){
        List<SellCsv> sells = this.csvService.getSellCsv();
        CsvDownLoadView csvDownLoadView = new CsvDownLoadView(SellCsv.class,sells);
        return new ModelAndView(csvDownLoadView);
    }

}
