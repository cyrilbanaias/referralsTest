package com.niji.steps.globalSteps;

import com.niji.factory.DriverManager;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import java.util.List;

public class ScrollSteps {

    public static MobileElement findBestScrollView(String direction) {
        if (DriverManager.isAndroidPlatform()){
            List<MobileElement> scrollableViews = DriverManager.getDriver().driverMobile.findElementsByXPath("//*[@scrollable=\"true\" and not(@class=\"android.widget.Spinner\")]");
            return scrollableViews
                    .stream()
                    .max((v1, v2) -> {
                        switch (direction) {
                            case "TOP":
                            case "DOWN":
                                return v2.getSize().height - v1.getSize().height;
                            default:
                                return v2.getSize().width - v1.getSize().width;
                        }
                    })
                    .orElseThrow(() -> new IllegalStateException("No scrollable view found in this screen"));
        } else if (DriverManager.isiOSPlatform()){
            List<MobileElement> scrollableViews = DriverManager.getDriver().driverMobile.findElements(By.className("UIAScrollView"));
            if (scrollableViews == null || scrollableViews.isEmpty()) {
                scrollableViews = DriverManager.getDriver().driverMobile.findElements(By.className("UITableView"));
            }
            if (scrollableViews == null || scrollableViews.isEmpty()) {
                scrollableViews = DriverManager.getDriver().driverMobile.findElements(By.className("UICollectionView"));
            }
            if (scrollableViews == null || scrollableViews.isEmpty()) {
                scrollableViews = DriverManager.getDriver().driverMobile.findElements(MobileBy.AccessibilityId("form.tableView"));
            }
            if (scrollableViews == null || scrollableViews.isEmpty()) {
                scrollableViews = DriverManager.getDriver().driverMobile.findElements(MobileBy.xpath("//XCUIElementTypeApplication[@name=\"d-Intermarché\"]"));
            }
            if (scrollableViews == null || scrollableViews.isEmpty()) {
                throw new IllegalStateException("No scrollable view was found");
            }
            return scrollableViews.get(0);
        } else {
            return null;
        }
    }

    /**
     * Scroll simple dans une direction
     */
    public static void scroll(String direction) {
        MobileElement scrollview = findBestScrollView(direction);
        int x = scrollview.getSize().getWidth();
        int y = scrollview.getSize().getHeight();
        if (direction.equals("DOWN")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(x/2, 3*y/4)))
                            .moveTo(PointOption.point(new Point(x/2, y/4)))
                                    .release().perform();
        } else if (direction.equals("TOP")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(x/2, y/4)))
                    .moveTo(PointOption.point(new Point(x/2, 3*y/4)))
                    .release().perform();
        } else if (direction.equals("RIGHT")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(3*x/4, y/2)))
                    .moveTo(PointOption.point(new Point(x/4, y/2)))
                    .release().perform();
        } else if (direction.equals("LEFT")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(x/4, y/2)))
                    .moveTo(PointOption.point(new Point(3*x/4, y/2)))
                    .release().perform();
        }
    }

    /**
     * Scroll dans une direction sur un element
     */
    public static void scrollElement(String direction, MobileElement element) {
        int y_elem_min = element.getLocation().y;
        int y_elem_max = y_elem_min+element.getSize().height;
        int x_elem_min = element.getLocation().x;
        int x_elem_max = x_elem_min+element.getSize().width;
        int y_center = (y_elem_max + y_elem_min)/2;
        int x_center = (x_elem_max + x_elem_min)/2;
        if (direction.equals("DOWN")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(x_center, 3*y_center/4)))
                    .moveTo(PointOption.point(new Point(x_center, y_center/4)))
                    .release().perform();
        } else if (direction.equals("TOP")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(x_center, y_center/4)))
                    .moveTo(PointOption.point(new Point(x_center, 3*y_center/4)))
                    .release().perform();
        } else if (direction.equals("RIGHT")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(3*x_center/4, y_center)))
                    .moveTo(PointOption.point(new Point(x_center/4, y_center)))
                    .release().perform();
        } else if (direction.equals("LEFT")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(x_center/4, y_center)))
                    .moveTo(PointOption.point(new Point(3*x_center/4, y_center)))
                    .release().perform();
        }
    }

    /**
     * Scroll dans une direction jusqu'à ce que l'élément soit visible
     */
    public static void scrollToElement(String direction, MobileElement element){
        if (DriverManager.isiOSPlatform()) {
            int tries = 0;
            while (!element.getAttribute("visible").equals("true") && tries++ < 10) {
                ScrollSteps.scroll(direction);
            }
        } else {
            int tries = 0;
            while (!element.getAttribute("visible").equals("true") && tries++ < 10) {
                ScrollSteps.scroll(direction);
            }
        }
    }

    /**
     * Scroll simple dans une direction
     */
    public static void swipe(String direction) {
        int x;
        int y;
        if (DriverManager.isAndroidPlatform()){
            String size = StringUtils.substringBetween((String) DriverManager.getDriver().driverMobile.executeScript("seetest:client.getDeviceProperty(\"device.screensize\")"), "text\":\"", "\",\"scenarioIndex");
            x = Integer.parseInt(StringUtils.substringBefore(size, "x"));
            y = Integer.parseInt(StringUtils.substringAfter(size, "x"));
        } else {
            MobileElement elem = DriverManager.getDriver().driverMobile.findElements(By.xpath("/*[@class='android.widget.FrameLayout']")).get(0);
            x = elem.getSize().getWidth();
            y = elem.getSize().getHeight();
        }
        if (direction.equals("TOP")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(x/2, 3*y/4)))
                    .moveTo(PointOption.point(new Point(x/2, y/4)))
                    .release().perform();
        } else if (direction.equals("DOWN")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(x/2, y/4)))
                    .moveTo(PointOption.point(new Point(x/2, 3*y/4)))
                    .release().perform();
        } else if (direction.equals("LEFT")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(3*x/4, y/2)))
                    .moveTo(PointOption.point(new Point(x/4, y/2)))
                    .release().perform();
        } else if (direction.equals("RIGHT")) {
            new TouchAction(DriverManager.getDriver().driverMobile)
                    .press(PointOption.point(new Point(x/4, y/2)))
                    .moveTo(PointOption.point(new Point(3*x/4, y/2)))
                    .release().perform();
        }
    }
}
