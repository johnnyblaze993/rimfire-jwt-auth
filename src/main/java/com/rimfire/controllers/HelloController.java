package com.rimfire.controllers;

import io.micronaut.http.annotation.Controller;
//import get
import io.micronaut.http.annotation.Get;


import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;


@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/hello")
public class HelloController {
    
        @Get("/")
        public String index() {
            return "Hello World";
        }
    
}
