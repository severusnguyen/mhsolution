package com.mh.core.mhscommons.utils;

public class SeoUtils {
    public static String generateSeoId(String title, String id) {
        if (title != null) {
            String seoId = title.length() > 200 ? title.substring(0, 200) : title;
            seoId = StringUtil.textToUrl(seoId);
            return seoId + "-" + id;
        } else
            return "applicant - " + id;

    }

    public static String normalSeoId(String input) {
        String[] list = input.split("-");
        if (list.length <= 1) return input;
        String id = list[list.length - 1];
        StringBuffer title = new StringBuffer();
        for (int i = 0; i < list.length - 1; i++) {
            title.append(" " + list[i].trim());
        }
        return generateSeoId(title.toString(), id);
    }
}
