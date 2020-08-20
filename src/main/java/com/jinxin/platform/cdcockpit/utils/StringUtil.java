package com.jinxin.platform.cdcockpit.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huang LingSong
 * 2020-06-16 13:54
 */
public class StringUtil {

    /**
     * 转驼峰
     *
     * @param name
     * @return
     */
    public static String toFirstLetterUpper(String name) {
        StringBuffer output = new StringBuffer();
        if (org.springframework.util.StringUtils.isEmpty(name)) {
            return output.toString();
        }
        String[] words = name.toLowerCase().split("_");
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                output.append(words[i]);
            } else {
                output.append(words[i].substring(0, 1).toUpperCase() + words[i].substring(1));
            }
        }
        return output.toString();
    }

    public static void distinct(List<Map<String, Object>> list, String key) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).get(key).equals(list.get(i).get(key))) {
                    list.remove(j);
                }
            }
        }
    }

    public static void main(String[] args) {




    }
}
