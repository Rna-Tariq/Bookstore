/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author osx
 */

@RestController
public class GreetingController {
    
    private final MessageSource messageSource;

    public GreetingController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/greeting")
    public String greeting() {
        return messageSource.getMessage("greeting", null, LocaleContextHolder.getLocale());
    }
    
}
