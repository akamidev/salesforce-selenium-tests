package com.keymax.tests;

import com.keymax.utils.WebDriverSetup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTest {

    @Test
    public void testLogin() {
        WebDriver driver = WebDriverSetup.getDriver();
        driver.get("https://login.salesforce.com");

        // Saisir l'email et le mot de passe
        WebElement emailField = driver.findElement(By.id("username"));
        emailField.sendKeys("ton_email@exemple.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("ton_mot_de_passe");

        WebElement loginButton = driver.findElement(By.id("Login"));
        loginButton.click();

        // Attendre quelques secondes pour vérifier la connexion
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Fermer le navigateur
        driver.quit();
    }
}
