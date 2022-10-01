package com.example.schoolsite

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {
    @Override
    protected fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(SchoolSiteApplication::class.java)
    }
}