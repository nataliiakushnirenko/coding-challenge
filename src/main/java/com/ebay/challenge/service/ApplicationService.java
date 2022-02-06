package com.ebay.challenge.service;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class ApplicationService {
    private static String USER_FILE = "users.csv";
    private static String PURCHASE_FILE = "purchases.csv";

    private DatabaseService databaseService;
    private ParserService parserService;

    public ApplicationService() {
        this.databaseService = new DatabaseService();
        this.parserService = new ParserService();
    }

    public void populateDatabase() {
        Set<Map<String, String>> users = parserService.parse(USER_FILE);
        Set<Map<String, String>> purchases = parserService.parse(PURCHASE_FILE);
        databaseService.add("users", users);
        databaseService.add("purchases", purchases);
    }

    public SortedSet<Map<String, String>> getOrderedTable(String tableName, String columnName) {
        return databaseService.orderBy(tableName, columnName);
    }

}


