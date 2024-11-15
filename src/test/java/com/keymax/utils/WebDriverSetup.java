package com.keymax.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSetup {

    public static WebDriver getDriver() {
        // Configurer automatiquement le ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Ajouter des options pour désactiver les notifications
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");



        // Initialiser le WebDriver avec les options
        return new ChromeDriver(options);
    }
}
