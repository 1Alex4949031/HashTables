package ru.hashtables.seleznev.a;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Websites change detector class.
 */
@AllArgsConstructor
public class WebsitesChangeDetector {
    private final WebsitesState yesterdayState;
    private final WebsitesState todayState;

    /**
     * Function that generates report with all information needed:
     * 1) Disappeared pages
     * 2) New pages
     * 3) Changed pages
     *
     * @return String report
     */
    public String generateReport() {
        Map<String, String> yesterdayMap = yesterdayState.utlToHtmlMap();
        Map<String, String> todayMap = todayState.utlToHtmlMap();

        Set<String> yesterdayKeys = yesterdayMap.keySet();
        Set<String> todayKeys = todayMap.keySet();

        Set<String> disappearedPages = new HashSet<>(yesterdayKeys);
        disappearedPages.removeAll(todayKeys);

        Set<String> newPages = new HashSet<>(todayKeys);
        newPages.removeAll(yesterdayKeys);

        Set<String> changedPages = new HashSet<>();
        for (String url : yesterdayKeys) {
            if (todayKeys.contains(url)) {
                if (!yesterdayMap.get(url).equals(todayMap.get(url))) {
                    changedPages.add(url);
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Здравствуйте, дорогая {имя} {отчество}.\n\n");
        report.append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n");

        if (disappearedPages.isEmpty()) {
            report.append("Исчезнувших страниц не обнаружено.\n\n");
        } else {
            report.append("Исчезли следующие страницы:\n");
            report.append(String.join("\n", disappearedPages));
            report.append("\n\n");
        }

        if (newPages.isEmpty()) {
            report.append("Новых страниц не обнаружено.\n");
        } else {
            report.append("Появились следующие новые страницы:\n");
            report.append(String.join("\n", newPages));
            report.append("\n\n");
        }

        if (changedPages.isEmpty()) {
            report.append("Изменившихся страниц не обнаружено.\n\n");
        } else {
            report.append("Изменились следующие страницы:\n");
            report.append(String.join("\n", changedPages));
            report.append("\n\n");
        }

        report.append("С уважением,\n");
        report.append("автоматизированная система\n");
        report.append("мониторинга.\n");

        return report.toString();
    }
}
