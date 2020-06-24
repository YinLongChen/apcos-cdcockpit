package com.jinxin.platform.cdcockpit.utils;

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

}
