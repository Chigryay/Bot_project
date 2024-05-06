package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChromeScreenshot {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Установить путь к драйверу для Google Chrome
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        // Настройки Веб драйвера
        ChromeOptions options = new ChromeOptions();

        // Устанавливаем флаг для открытия браузера в полноэкранном режиме
        options.addArguments("--disable-extensions"); // Отключить расширения браузера
        options.addArguments("--disable-blink-features=AutomationControlled"); // Отключить функцию автоматизации
        options.addArguments("--start-maximized");
        //options.addArguments("C:\\Users\\fadem\\AppData\\Local\\Google\\Chrome\\User Data\\");
        options.addArguments("--disable-dev-shm-usage");

        /*options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));*/


        // Список логинов и паролей для авторизации
        Map<String, String> users = new HashMap<>();
//        users.put("9001036891", "Noyser123");
        users.put("9954432260", "0123456");
        users.put("9134188955", "valeriy");
        users.put("9331939406", "123456");
//        users.put("9960394762", "Noyser123");
//        users.put("9001036891", "Noyser123");

        for (Map.Entry<String, String> user : users.entrySet()) {
            // станавливаем драйвер
            WebDriver driver = new ChromeDriver(options);

            driver.get("https://www.bbdowork07.com/#/login");

            /*--------------- Авторизация -----------------*/

            try {
                // Ждать, пока страница полностью загрузится
                Thread.sleep(1500);

                // Поиск элемента для ввода значения "9001036891"
                String login = user.getKey();
                WebElement inputField = driver.findElement
                        (By.xpath("//input[@placeholder='Имя пользователя/телефон']"));

                // Очистить поле ввода (на всякий случай)
                inputField.clear();

                // Ввести значение "9001036891" по одному символу с клавиатуры
                for (char c : login.toCharArray()) {
                    inputField.sendKeys(String.valueOf(c));
                    Thread.sleep(1); // Пауза между вводом символов
                }
                inputField.sendKeys(Keys.TAB);
                WebElement passwordBox = driver.switchTo().activeElement();

                String password = user.getValue();
                passwordBox.sendKeys(password);

                WebElement inLogin = driver.findElement(By.tagName("button"));
                inLogin.click();
                Thread.sleep(3000);


            } catch (Exception ex) {
                System.out.println("err");
            }
            try {
                WebElement clickYoutube = driver.findElement(
                        By.xpath("//img[@src='https://admin.bbdo999.com/upload/resource/202112041914546795754878.png']"));
                clickYoutube.click();
            } catch (Exception ignored) {

            }

            // Алгоритм прокликивания ссылок ютуб
            Thread.sleep(3000);
            WebElement failClick = driver.findElement(By.className("van-button--mini"));
            failClick.click();

            String setCompleted = driver.findElement(By.className("van-toast__text")).getText();
            System.out.println(setCompleted);
// Прогоняем по ссылкам пока не закончит добавление всех задач в Список задач
            while (!setCompleted.equals("Сегодняшнее время истекло '")) {
                // Проверить работает или нет
//                if (setCompleted.equals("Неудача")) break;
                if (driver.getCurrentUrl().equals("https://www.bbdowork07.com/#/MyTask")) {
                    driver.navigate().back();
                }
                List<WebElement> elements = driver.findElements(By.className("van-button--mini"));
                for (WebElement element : elements) {
                    if (driver.getCurrentUrl().equals("https://www.bbdowork07.com/#/MyTask")) {
                        driver.navigate().back();
                    }
                    try {
                        element.click();
                        Thread.sleep(400);
                        setCompleted = driver.findElement(By.className("van-toast__text")).getText();
                        System.out.println(setCompleted);
                        if (setCompleted.equals("Сегодняшнее время истекло '")) {
                            break;
                        }

                    } catch (Exception ignored) {

                    }
                }

            }

            // Переходим в список задач
            driver.get("https://www.bbdowork07.com/#/mytask");
            Thread.sleep(2000);

            List<WebElement> elements_1 = driver.findElements(By.className("van-button--small"));
            while (!elements_1.isEmpty()) {
                try {
                    elements_1 = driver.findElements(By.className("van-button--small"));
                } catch (Exception ex) {
                    elements_1 = driver.findElements(By.className("van-button--small"));
                }

                // Проходим по каждой задаче
                for (WebElement element : elements_1) {
                    Thread.sleep(1500);
                    try {
                        element.click();
                    } catch (StaleElementReferenceException ex) {
                        element = driver.findElement(By.className("van-button--small"));
                        try {
                            element.click();
                        } catch (Exception ignored) {

                        }
                    }

                    try {
                        WebElement youtubeReference = driver.findElement(By.className("van-button--mini"));
                        youtubeReference.click();

                    } catch (StaleElementReferenceException e) {
                        WebElement youtubeReference = driver.findElement(By.className("van-button--mini"));
                        youtubeReference.click();
                    }

                    Thread.sleep(3000);

                    Set<String> handles = driver.getWindowHandles();
                    for (String handle : handles) {
                        driver.switchTo().window(handle);
                    }

                    try {
                        // Авторизация в гугл
                        Thread.sleep(2000);
                        WebElement loginButton = driver.findElement(By.xpath("//a[@aria-label='Войти']"));
                        loginButton.click();
                        Map<String, String> gmailUsers = new HashMap<>();
                        gmailUsers.put("Fademon2019@gmail.com", "Noyser123");

                        for (Map.Entry<String, String> gmailUser : gmailUsers.entrySet()) {
                            WebElement loginGmail = driver.findElement(By.xpath("//input[@autocomplete='username']"));
                            for (char c : gmailUser.getKey().toCharArray()) {
                                loginGmail.sendKeys(String.valueOf(c));
                                Thread.sleep(10); // Пауза между вводом символов
                            }
                            loginGmail.click();
                            loginGmail.sendKeys(Keys.TAB);
                            WebElement temp = driver.switchTo().activeElement();
                            temp.sendKeys(Keys.TAB);
                            WebElement temp2 = driver.switchTo().activeElement();
                            temp2.sendKeys(Keys.TAB);
                            WebElement nextBTN = driver.switchTo().activeElement();
                            nextBTN.click();

                            Thread.sleep(3000);
                            //  Ввод пароля
                            WebElement passwordGmail = driver.findElement(By.xpath("//input[@autocomplete='current-password']"));

                            for (char c : gmailUser.getValue().toCharArray()) {
                                passwordGmail.sendKeys(String.valueOf(c));
                                Thread.sleep(10); // Пауза между вводом символов
                            }
                            // Кликаем на кнопки авторизации всякие...
                            passwordGmail.sendKeys(Keys.TAB);
                            WebElement passTemp = driver.switchTo().activeElement();
                            passTemp.sendKeys(Keys.TAB);
                            WebElement nextPassBTN = driver.switchTo().activeElement();
                            nextPassBTN.click();
                        }
                    } catch (Exception ignored) {
                    }


                    Thread.sleep(3000);
                    String currentUrl = driver.getCurrentUrl();
                    System.out.println("Текущая страница: " + currentUrl);
                    Thread.sleep(3000);
                    // лайкаем видео
                    WebElement likeBTN = driver.findElement(By.xpath("//button[@aria-pressed='false']"));
                    Thread.sleep(2000);
                    try {
                        likeBTN.click();
                    } catch (Exception ignored) {

                    }


                    System.out.println("Good");
                    // Делаем скрин с сохранением в папку
                    Thread.sleep(3000);
                    TakesScreenshot screenshot = (TakesScreenshot) driver;

                    // Создаем объект файла для сохранения скриншота
                    File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

                    // Указываем путь для сохранения скриншота
                    String filePath = "src\\main\\resources\\Screenshots\\screenshot.png";

                    try {
                        // Копируем содержимое файла скриншота в указанное расположение
                        FileUtils.copyFile(srcFile, new File(filePath));
                        Files.copy(srcFile.toPath(), Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Скриншот успешно сохранен по пути: " + filePath);
                        likeBTN.click();
                    } catch (IOException e) {
                        System.out.println("Произошла ошибка при сохранении скриншота: " + e.getMessage());
                    }
                    ((JavascriptExecutor) driver).executeScript("window.close();");
                    handles = driver.getWindowHandles();
                    for (String handle : handles) {
                        driver.switchTo().window(handle);
                    }
                    Thread.sleep(1000);

                    currentUrl = driver.getCurrentUrl();
                    System.out.println("Текущая страница: " + currentUrl);

                    WebElement upload = driver.findElement(By.className("van-uploader__upload"));
                    upload.click();
                    //--------------------- тут загрузка файла на проверку -----------------------//
                    Thread.sleep(2000);

                    String scriptFileExe = "src/main/resources/loaderScript.exe";

                    ProcessBuilder processBuilder = new ProcessBuilder(scriptFileExe);
                    Process process = processBuilder.start();
                    process.waitFor();
                    Thread.sleep(1000);

                    WebElement uploader = driver.findElement(By.className("cpButton1"));
                    Thread.sleep(1000);
                    uploader.click();
                    Thread.sleep(1000);

                    driver.navigate().refresh();
                }
            }
            System.out.println("ERR");
            driver.quit();
        }
        JOptionPane.showMessageDialog(null, "Бот закончил работу!!!!");
    }
}