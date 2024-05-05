package org.example;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class Screenshot {
    public void doScreen() {
        try {
            // Создаем объект класса Robot для захвата экрана
            Robot robot = new Robot();

            // Задаем размеры и положение зоны скриншота
            Rectangle captureRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            // Создаем скриншот с помощью объекта Robot
            BufferedImage screenshot = robot.createScreenCapture(captureRect);

            // Создаем путь к папке, в которую будет сохранен скриншот
            String outputFilePath = "c:\\users\\fadem\\onedrive\\desktop\\screenshot\\screenshot.png";

            // Создаем объект класса File для сохранения скриншота
            File outputFile = new File(outputFilePath);

            // Сохраняем скриншот в указанную папку
            ImageIO.write(screenshot, "png", outputFile);

            // Выводим сообщение об успешном сохранении скриншота
            System.out.println("Скриншот успешно сохранен по пути: " + outputFilePath);
        } catch (Exception e) {
            // В случае ошибки выводим сообщение об ошибке
            System.out.println("Ошибка при сохранении скриншота: " + e.getMessage());
        }
    }
}
