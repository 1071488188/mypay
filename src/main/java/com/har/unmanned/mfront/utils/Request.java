package com.har.unmanned.mfront.utils;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;

public class Request extends HttpServletRequestWrapper {

    private Map<String, String> headers;

    public Request(HttpServletRequest request) {
        super(request);

        this.headers = Maps.newHashMap();

        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            headers.put(name, request.getHeader(name));
        }
    }

    @Override
    public String getHeader(String name) {
        return this.headers.get(name);
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    @Override
    public String toString() {
        if (this.headers != null) {
            return this.headers.toString();
        }

        return "";
    }
}
