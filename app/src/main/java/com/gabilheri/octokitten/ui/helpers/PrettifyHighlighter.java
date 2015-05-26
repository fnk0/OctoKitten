package com.gabilheri.octokitten.ui.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prettify.PrettifyParser;
import syntaxhighlight.ParseResult;
import syntaxhighlight.Parser;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/15/15.
 */
public class PrettifyHighlighter {

    Map<String, String> COLORS = buildColorsMap();
    String FONT_PATTERN = "<font color=\"#%s\">%s</font>";
    Parser parser;

    public PrettifyHighlighter() {
        parser = new PrettifyParser();
    }

    public String highlight(String fileExtension, String sourceCode) {
        StringBuilder highlighted = new StringBuilder();
        List<ParseResult> results = parser.parse(fileExtension, sourceCode);
        for(ParseResult result : results){
            String type = result.getStyleKeys().get(0);
            String content = sourceCode.substring(result.getOffset(), result.getOffset() + result.getLength());
            highlighted.append(String.format(FONT_PATTERN, getColor(type), content));
        }
        return highlighted.toString();
    }

    private String getColor(String type){
        return COLORS.containsKey(type) ? COLORS.get(type) : COLORS.get("pln");
    }

    private static Map<String, String> buildColorsMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("typ", "4A148C");
        map.put("kwd", "388E3C");
        map.put("lit", "0288D1");
        map.put("com", "F44336");
        map.put("str", "00897B");
        map.put("pun", "E64A19");
        map.put("pln", "37474F");
        return map;
    }

}
