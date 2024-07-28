package ru.hashtables.seleznev.a;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class with test values.
 */
public class Main {
    public static void main(String[] args) {
        Map<String, String> yesterdayMap = new HashMap<>();
        Map<String, String> todayMap = new HashMap<>();

        // Test values
        yesterdayMap.put("https://example.com/page1", "<html>Content 1</html>");
        yesterdayMap.put("https://example.com/page2", "<html>Content 3</html>");
        todayMap.put("https://example.com/page2", "<html>Content 2</html>");
        todayMap.put("https://example.com/page3", "<html>Content 3</html>");

        WebsitesState yesterdayState = new WebsitesState(yesterdayMap);
        WebsitesState todayState = new WebsitesState(todayMap);

        WebsitesChangeDetector detector = new WebsitesChangeDetector(yesterdayState, todayState);
        String report = detector.generateReport();

        System.out.println(report);
    }
}