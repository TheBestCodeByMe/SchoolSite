package com.example.schoolsite

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
object SchoolSiteApplication {
    fun main(args: Array<String?>?) {
        SpringApplication.run(SchoolSiteApplication::class.java, args)
    }
}