package com.keymax.tests;

import com.keymax.utils.WebDriverSetup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CreateClientTest {

    @Test
    public void testCreateUser() {
        // Configuration du WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = WebDriverSetup.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com");

        // Connexion à Salesforce
        driver.findElement(By.id("username")).sendKeys("akamimehdi.dev");
        driver.findElement(By.id("password")).sendKeys("your-mot-de-password");
        driver.findElement(By.id("Login")).click();

        try {
            // Attendre que la page soit complètement chargée
            Thread.sleep(5000);

            // Étape 2 : Lister les iframes présentes sur la page
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("Nombre d'iframes détectées : " + iframes.size());

            // Afficher les attributs des iframes pour identification
            for (int i = 0; i < iframes.size(); i++) {
                System.out.println("Iframe " + i + " : " + iframes.get(i).getAttribute("id"));
            }

            // Basculer vers l'iframe si nécessaire (utilisez l'index correct)
            if (iframes.size() > 0) {
                driver.switchTo().frame(0); // Changez "0" par l'index de l'iframe contenant le formulaire
            }

            // Changer vers le panneau de contenu
            WebElement tabContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tabContent.active")));
            driver.switchTo().activeElement();

            // Remplir les champs du formulaire
            js.executeScript("document.getElementById('name_firstName').value='Test';");
            js.executeScript("document.getElementById('name_lastName').value='User';");
            js.executeScript("document.getElementById('Alias').value='testuser';");
            js.executeScript("document.getElementById('Email').value='test.user@example.com';");
            js.executeScript("document.getElementById('Username').value='testuser@example.com';");
            js.executeScript("document.getElementById('CommunityNickname').value='testuser';");

            // Sélectionner les options des listes déroulantes
            WebElement roleDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("role")));
            js.executeScript("arguments[0].value='CEO';", roleDropdown);

            WebElement licenseDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_license_id")));
            js.executeScript("arguments[0].value='Salesforce';", licenseDropdown);

            WebElement profileDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Profile")));
            js.executeScript("arguments[0].value='Work.com Only User';", profileDropdown);

            // Clic sur le bouton "Save"
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("save")));
            js.executeScript("arguments[0].click();", saveButton);

            System.out.println("Utilisateur créé avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de l'utilisateur.");
            e.printStackTrace();
        } finally {
            // Revenir au contexte principal et fermer le navigateur
            driver.switchTo().defaultContent();
            driver.quit();
        }
    }
}
