package com.cni.httptrack.support;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCookieJar implements CookieJar {
    private final Map<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

    /**
     * 通过主机名获取，保存cookie
     * @param url
     * @param cookies
     */
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.put(url.host(), cookies);
    }

    /**
     * 通过主机名，获取所有cookie
     * @param url
     * @return
     */
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url.host());
        return cookies != null ? cookies : Collections.emptyList();
    }
}