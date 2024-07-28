package ru.hashtables.seleznev.a;

import java.util.Map;

/**
 * Websites State record.
 *
 * @param utlToHtmlMap map that contains url as a key and html code as value.
 */
public record WebsitesState(Map<String, String> utlToHtmlMap) {
}
