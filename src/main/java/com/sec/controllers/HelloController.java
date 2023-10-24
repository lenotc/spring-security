package com.sec.controllers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {


    @GetMapping("/bey")
    @Async
    public void goodbye() {
        SecurityContext context = SecurityContextHolder.getContext();
        String useranme = context.getAuthentication().getName();
        // do something with the username
    }

    @GetMapping("")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        try (ExecutorService e = Executors.newCachedThreadPool()) {
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return "Ciao, " + e.submit(contextTask).get() + "!";
        }
    }

    @GetMapping("/hello")
    public String hello(Authentication a) {
        return "Hello, " + a.getName() + "!";
    }

    @GetMapping("/hi")
    public String hi() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        try (ExecutorService e = Executors.newCachedThreadPool();
             var a = new DelegatingSecurityContextExecutorService(e)) {
            return "Hello, " + e.submit(task).get() + "!";
        }
    }
}






