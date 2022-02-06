package com.ebay.challenge.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class ParserService {

    public Set<Map<String, String>> parse(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource != null) {
            try (Reader reader = new FileReader(new File(resource.toURI()))) {

                CSVParser parse = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

                return parse.getRecords().stream()
                        .map(record -> parseLine(parse.getHeaderNames(), record))
                        .collect(Collectors.toSet());

            } catch (IOException | URISyntaxException e) {
                throw new IllegalStateException("File not found");
            }
        }
        throw new IllegalStateException("File not found");
    }

    private Map<String, String> parseLine(List<String> headers, CSVRecord record) {
        return headers.stream()
                .collect(toMap(String::toLowerCase, record::get));
    }

}
