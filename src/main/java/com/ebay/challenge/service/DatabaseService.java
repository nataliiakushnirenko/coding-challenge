package com.ebay.challenge.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class DatabaseService {

    private final Map<String, Set<Map<String, String>>> tables;

    public DatabaseService() {
        this.tables = new HashMap<>();
    }

    public void add(String tableName, Set<Map<String, String>> records) {
        tables.compute(tableName, (k, v) -> addRecord(emptyIfNull(v), records));
    }

    private Set<Map<String, String>> addRecord(Collection<Map<String, String>> allRecords,
                                               Set<Map<String, String>> records) {
        return Stream.concat(allRecords.stream(), records.stream())
                .collect(toSet());
    }

    public SortedSet<Map<String, String>> orderBy(String tableName, String columnName) {
        Comparator<Map<String, String>> comparing = Comparator.comparing(value -> value.get(columnName),
                Comparator.reverseOrder());
        return tables.getOrDefault(tableName, emptySet()).stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparing)));
    }


}