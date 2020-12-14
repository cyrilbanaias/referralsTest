package com.niji.utils;

import com.niji.factory.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ScreenShot {

    public static void addScreenShot(WebDriver driver){
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            DriverManager.getDriver().scenario.embed(screenshot, "image/png");
        } catch (WebDriverException platformWithoutScreenshotSupport) {

        }
    }

    public static void addScreenShot(WebDriver driver, WebElement element){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            DriverManager.getDriver().scenario.embed(screenshot, "image/png");
            js.executeScript("arguments[0].setAttribute('style', ''", element);
        } catch (WebDriverException platformWithoutScreenshotSupport) {

        }
    }

    public static void addScreenShot(AppiumDriver<MobileElement> driverMobile) throws Exception {
        try {
            byte[] screenshot = ((TakesScreenshot) driverMobile).getScreenshotAs(OutputType.BYTES);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(screenshot);
            BufferedImage img = ImageIO.read(inputStream);
            Image tmp = img.getScaledInstance(img.getWidth()/3, img.getHeight()/3, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(img.getWidth()/3, img.getHeight()/3, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(dimg, "png", outputStream);
            screenshot = outputStream.toByteArray();

            DriverManager.getDriver().scenario.embed(screenshot, "image/png");
        } catch (WebDriverException platformWithoutScreenshotSupport) {

        }
    }
}
