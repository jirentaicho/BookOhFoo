package com.volkruss.BookOhFoo.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.util.List;

// TODO makeBase
public class CsvOutput {

    private final Class targetClass;

    private final CsvMapper csvMapper;

    public CsvOutput(Class clazz){
        this.targetClass = clazz;
        this.csvMapper = new CsvMapper();
        this.csvMapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS,true);
        this.csvMapper.findAndRegisterModules();
    }

    // TODO moveBase
    public String getCsv(List<?> data) throws JsonProcessingException {
        CsvSchema csvSchema = this.csvMapper.schemaFor(this.targetClass).withHeader();
        String result = csvMapper.writer(csvSchema).writeValueAsString(data);
        return result;
    }

    // TODO moveBase
    public ObjectWriter getWriterWithSchema(){
        CsvSchema csvSchema = this.csvMapper.schemaFor(this.targetClass).withHeader();
        return this.csvMapper.writer(csvSchema);
    }
}
