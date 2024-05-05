package org.example;

import autoitx4java.AutoItX;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Bot_2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Установить путь к драйверу для Google Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\fadem\\Downloads\\chromedriver-win64/chromedriver.exe");

        // Настройки Веб драйвера
        ChromeOptions options = new ChromeOptions();

        // Устанавливаем флаг для открытия браузера в полноэкранном режиме
        options.addArguments("--disable-extensions"); // Отключить расширения браузера
        options.addArguments("--disable-blink-features=AutomationControlled"); // Отключить функцию автоматизации
        options.addArguments("--start-maximized");
        //options.addArguments("C:\\Users\\fadem\\AppData\\Local\\Google\\Chrome\\User Data\\");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://ru.imgbb.com/");
        WebElement element = driver.findElement(By.xpath("//a[@data-trigger='anywhere-upload-input']"));
        element.click();

        Thread.sleep(5000);

        String scriptFileExe = "C:\\ProjectBot\\Bot\\src\\main\\resources\\loaderScript.exe";

        ProcessBuilder processBuilder = new ProcessBuilder(scriptFileExe);
        Process process = processBuilder.start();
        process.waitFor();

        WebElement clickLoadBtn = driver.findElement(By.xpath("//button[@data-action='upload']"));
        Thread.sleep(3000);
        clickLoadBtn.click();
    }
}
