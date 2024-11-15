package com.keymax.tests;

import com.keymax.utils.WebDriverSetup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteClientTest {

    @Test
    public void testDeleteClient() {
        WebDriver driver = WebDriverSetup.getDriver();
        driver.get("https://login.salesforce.com");

        // Connexion à Salesforce
        driver.findElement(By.id("username")).sendKeys("ton_email@exemple.com");
        driver.findElement(By.id("password")).sendKeys("ton_mot_de_passe");
        driver.findElement(By.id("Login")).click();

        // Attendre quelques secondes pour le chargement
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Rechercher le compte créé
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='search']"));
        searchBox.sendKeys("Client Test Selenium");
        searchBox.submit();

        // Cliquer sur le menu d'options et sélectionner "Supprimer"
        WebElement optionsButton = driver.findElement(By.xpath("//button[@title='Actions']"));
        optionsButton.click();

        WebElement deleteOption = driver.findElement(By.xpath("//a[@title='Supprimer']"));
        deleteOption.click();

        // Confirmer la suppression
        WebElement confirmDelete = driver.findElement(By.xpath("//button[@title='Supprimer']"));
        confirmDelete.click();

        // Fermer le navigateur
        driver.quit();
    }
}
