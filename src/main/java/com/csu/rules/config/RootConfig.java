package com.csu.rules.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ltaoj on 17-6-2.
 */
@Configuration
@ComponentScan(basePackages = {"com.csu.rules"},
        excludeFilters = {
          @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
public class RootConfig {
}
