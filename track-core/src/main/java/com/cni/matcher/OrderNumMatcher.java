package com.cni.matcher;

import java.util.regex.Pattern;

/**
 * 因为使用正则进行匹配，正则再properties文件中配置，实现一致
 */
public class OrderNumMatcher {

    private final String parttern;

    public OrderNumMatcher(String parttern) {
        this.parttern = parttern;
    }

    public Pattern getRegex() {
        return Pattern.compile(parttern);
    }

    public boolean match(String orderNum) {
        return orderNum != null && orderNum.matches(parttern);
    }
}