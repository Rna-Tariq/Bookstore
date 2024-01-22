/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.springframework.context.annotation.Configuration;
/**
 *
 * @author osx
 */

@Configuration
public class LanguageConfig {

    /**
     *
     */
    public static final List<Locale> Locales = Arrays.asList(
            new Locale("en"),
            new Locale("fr"),
            new Locale("gr")    
    );
    
}
