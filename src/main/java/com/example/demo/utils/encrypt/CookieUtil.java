package com.example.demo.utils.encrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String val, int time) {
        Cookie cookieName=new Cookie(key, val);
        cookieName.setPath(request.getContextPath());
        cookieName.setMaxAge(time);
        response.addCookie(cookieName);
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if (key.equals(cookie.getName())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
