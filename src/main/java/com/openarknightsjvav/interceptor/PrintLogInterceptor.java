package com.openarknightsjvav.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * ClassName: PrintLogInterceptor
 * Package: com.openarknightsjvav.interceptor
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/7 13:04
 * @Version 1.0
 */
@Component
public class PrintLogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
//        StringBuffer data = new StringBuffer();
//        String line = null;
//        BufferedReader reader = null;
//        reader = request.getReader();
//        while (null != (line = reader.readLine())) {
//            data.append(line);
//        }
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String payload = data.toString();
//        System.out.println( time.format(formatter) + "  method:" + method + "  requestURI:  " + requestURI + "  payload: " + payload);
        System.out.println( time.format(formatter) + "  method:" + method + "  requestURL:" + requestURI);
        return true;
    }

}
