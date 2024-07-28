package ru.hashtables.seleznev.a;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Websites Change State Tests.
 */
public class WebsitesChangeTests {

    /**
     * No changes test.
     */
    @Test
    public void testNoChanges() {
        Map<String, String> yesterdayMap = new HashMap<>();
        Map<String, String> todayMap = new HashMap<>();

        yesterdayMap.put("https://example.com/page1", "<html>Content 1</html>");
        todayMap.put("https://example.com/page1", "<html>Content 1</html>");

        WebsitesState yesterdayState = new WebsitesState(yesterdayMap);
        WebsitesState todayState = new WebsitesState(todayMap);

        WebsitesChangeDetector detector = new WebsitesChangeDetector(yesterdayState, todayState);
        String report = detector.generateReport();

        Assertions.assertTrue(report.contains("Исчезнувших страниц не обнаружено."));
        Assertions.assertTrue(report.contains("Новых страниц не обнаружено."));
        Assertions.assertTrue(report.contains("Изменившихся страниц не обнаружено."));
    }

    /**
     * Disappeared pages test.
     */
    @Test
    public void testDisappearedPages() {
        Map<String, String> yesterdayMap = new HashMap<>();
        Map<String, String> todayMap = new HashMap<>();

        yesterdayMap.put("https://example.com/page1", "<html>Content 1</html>");
        yesterdayMap.put("https://example.com/page2", "<html>Content 2</html>");

        todayMap.put("https://example.com/page2", "<html>Content 2</html>");

        WebsitesState yesterdayState = new WebsitesState(yesterdayMap);
        WebsitesState todayState = new WebsitesState(todayMap);

        WebsitesChangeDetector detector = new WebsitesChangeDetector(yesterdayState, todayState);
        String report = detector.generateReport();

        Assertions.assertTrue(report.contains("Исчезли следующие страницы:\nhttps://example.com/page1"));
        Assertions.assertTrue(report.contains("Новых страниц не обнаружено."));
        Assertions.assertTrue(report.contains("Изменившихся страниц не обнаружено."));
    }


    /**
     * New pages test.
     */
    @Test
    public void testNewPages() {
        Map<String, String> yesterdayMap = new HashMap<>();
        Map<String, String> todayMap = new HashMap<>();

        yesterdayMap.put("https://example.com/page1", "<html>Content 1</html>");

        todayMap.put("https://example.com/page1", "<html>Content 1</html>");
        todayMap.put("https://example.com/page2", "<html>Content 2</html>");

        WebsitesState yesterdayState = new WebsitesState(yesterdayMap);
        WebsitesState todayState = new WebsitesState(todayMap);

        WebsitesChangeDetector detector = new WebsitesChangeDetector(yesterdayState, todayState);
        String report = detector.generateReport();

        Assertions.assertTrue(report.contains("Исчезнувших страниц не обнаружено."));
        Assertions.assertTrue(report.contains("Появились следующие новые страницы:\nhttps://example.com/page2"));
        Assertions.assertTrue(report.contains("Изменившихся страниц не обнаружено."));
    }

    /**
     * Changed pages test.
     */
    @Test
    public void testChangedPages() {
        Map<String, String> yesterdayMap = new HashMap<>();
        Map<String, String> todayMap = new HashMap<>();

        yesterdayMap.put("https://example.com/page1", "<html>Content 1</html>");
        yesterdayMap.put("https://example.com/page2", "<html>Content 2</html>");

        todayMap.put("https://example.com/page1", "<html>Content 1</html>");
        todayMap.put("https://example.com/page2", "<html>Content 2 updated</html>");

        WebsitesState yesterdayState = new WebsitesState(yesterdayMap);
        WebsitesState todayState = new WebsitesState(todayMap);

        WebsitesChangeDetector detector = new WebsitesChangeDetector(yesterdayState, todayState);
        String report = detector.generateReport();

        Assertions.assertTrue(report.contains("Исчезнувших страниц не обнаружено."));
        Assertions.assertTrue(report.contains("Новых страниц не обнаружено."));
        Assertions.assertTrue(report.contains("Изменились следующие страницы:\nhttps://example.com/page2"));
    }
}