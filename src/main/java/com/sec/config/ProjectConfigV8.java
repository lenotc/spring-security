package com.sec.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.context.SecurityContextHolder;


//@EnableAsync
public class ProjectConfigV8 {
    public InitializingBean initializingBean() {
        return () ->
                SecurityContextHolder.setStrategyName(
                        SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
                );
    }
}
