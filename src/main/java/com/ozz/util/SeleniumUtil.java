package com.ozz.util;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ozz.ConfirmAttendance;

public class SeleniumUtil {
  protected static final Logger log = LoggerFactory.getLogger(SeleniumUtil.class);
  
  public static WebDriver getWebDriver() {
//    System.setProperty("webdriver.firefox.bin","D:/Develop/tools/Mozilla Firefox/firefox.exe");
//    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir").replaceAll("\\\\", "/") + "/driver/geckodriver.exe");
//       return new FirefoxDriver();

    String driverPath = ConfirmAttendance.USER_DIR + "/driver/chromedriver.exe";
    String bin = ConfirmAttendance.USER_DIR + "/driver/ChromePortable/ChromePortable.exe";
    log.info("webdriver.chrome.bin: " + bin);
    log.info("webdriver.chrome.driver: " + driverPath);

    System.setProperty("webdriver.chrome.driver", driverPath);
    ChromeOptions options = new ChromeOptions(); 
    options.setBinary(bin); 
    ChromeDriver driver = new ChromeDriver(options);

    driver.manage().window().maximize();
    return driver;
  }

  public static void closeWindow(WebDriver driver) {
    String currentWindow = driver.getWindowHandle();// 得到当前窗口的句柄
    Set<String> handles = driver.getWindowHandles();// 得到所有窗口的句柄
    Iterator<String> it = handles.iterator();
    String newWindow = null;
    while (it.hasNext()) {
      if (currentWindow == it.next())
        continue;
      newWindow = it.next();
    }
    driver.close();
    if (newWindow != null) {
      driver.switchTo().window(newWindow);
    }
  }

  public static WebElement findElement(WebDriver driver, By by) {
    return findElement(driver, by, 5);
  }
  
  public static WebElement findElement(WebDriver driver, By by, long timeOutInSeconds) {
    WebElement ele = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
    return ele;
  }

  public static void moveToElement(WebDriver driver, WebElement ele) {
    Actions action = new Actions(driver);
    action.moveToElement(ele).build().perform();
  }
  
  // public static boolean isElementPresent(WebDriver driver, By by) {
  // try {
  // driver.findElement(by);
  // return true;
  // } catch (NoSuchElementException e) {
  // return false;
  // }
  // }
  //
  // private boolean isAlertPresent() {
  // try {
  // driver.switchTo().alert();
  // return true;
  // } catch (NoAlertPresentException e) {
  // return false;
  // }
  // }
  //
  // private String closeAlertAndGetItsText() {
  // try {
  // Alert alert = driver.switchTo().alert();
  // String alertText = alert.getText();
  // if (acceptNextAlert) {
  // alert.accept();
  // } else {
  // alert.dismiss();
  // }
  // return alertText;
  // } finally {
  // acceptNextAlert = true;
  // }
  // }
}