package com.concordia.ece.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

public class DemoApplication {

    private final static File MP4_FILE = new File("C:/Users/Admin/Desktop/videos/bulb.mp4");

   

    @Controller
    final static class MyController {

        @Autowired
        private MyResourceHttpRequestHandler handler;

        // supports byte-range requests
        @GetMapping("/playvideo")
        public void home(
                HttpServletRequest request,
                HttpServletResponse response
        ) throws ServletException, IOException {

            request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, MP4_FILE);
            handler.handleRequest(request, response);
        }

        // does not support byte-range requests
        @GetMapping(path = "/plain", produces = "video/mp4")
        public FileSystemResource plain() {

            return new FileSystemResource(MP4_FILE);
        }
    }

    @Component
    final static class MyResourceHttpRequestHandler extends ResourceHttpRequestHandler {

        private final static String ATTR_FILE = MyResourceHttpRequestHandler.class.getName() + ".file";

        @Override
        protected org.springframework.core.io.Resource getResource(HttpServletRequest request) throws IOException {

            final File file = (File) request.getAttribute(ATTR_FILE);
            return (org.springframework.core.io.Resource) new FileSystemResource(file);
        }
    }
}