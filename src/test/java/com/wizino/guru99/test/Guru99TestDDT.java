/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.wizino.guru99.test;

import static com.wizino.guru99.test.Guru99Test.myBrowser;
import java.io.FileInputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *
 * @author tanph
 */
public class Guru99TestDDT {

    public WebDriver myBrowser;

    public Guru99TestDDT() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        System.setProperty("webdriver.chrome.logfile", "chromedriver.log"); // Lưu log vào tệp chromedriver.log
        System.setProperty("webdriver.chrome.verboseLogging", "true"); // Bật chế độ verbose logging

    }

    @Test
    @SuppressWarnings({"UnusedAssignment", "SleepWhileInLoop"})
    public void CheckLoginGivenValidAccountLoginsSuccessfully() throws InterruptedException, FileNotFoundException, IOException {
        myBrowser = new ChromeDriver();
        myBrowser.get("https://demo.guru99.com/v4");
        FileInputStream fis = new FileInputStream("C:\\Users\\tanph\\OneDrive\\Máy tính\\data.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                

                String username = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();

                // Your test logic here using username, password, and expectedMessage
                // ...
                // Example: login, perform actions, and assert
                WebElement txtUsername = myBrowser.findElement(By.xpath("//input[@name='uid']"));
                txtUsername.sendKeys(username);
                WebElement txtPassword = myBrowser.findElement(By.xpath("//input[@name='password']"));
                txtPassword.sendKeys(password);
                WebElement btnLogin = myBrowser.findElement(By.cssSelector("input[value='LOGIN']"));
                btnLogin.click();

                WebElement lblMessage = myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
                String welcomeMessage = lblMessage.getText();
                System.out.println("Message: " + welcomeMessage);
                assertEquals("Manger Id : " + username, welcomeMessage);
                Thread.sleep(3000);
                try {
                    WebElement btnLogout = myBrowser.findElement(By.cssSelector("a[href='Logout.php']"));
                    btnLogout.click();
                    btnLogout.submit();
                } catch (Exception e) {
                  // System.out.println(e.getMessage());
                }
                
            }

            /* // Wait for logout to complete
            Thread.sleep(3000);
            username = row.getCell(2).getStringCellValue();
            password = row.getCell(3).getStringCellValue();
            WebElement txtUsername2 = myBrowser.findElement(By.xpath("//input[@name='uid']"));
            txtUsername2.sendKeys(username);
            WebElement txtPassword2 = myBrowser.findElement(By.xpath("//input[@name='password']"));
            txtPassword2.sendKeys(password);
            WebElement btnLogin2 = myBrowser.findElement(By.cssSelector("input[value='LOGIN']"));
            btnLogin2.click();
            WebElement lblMessage2 = myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
            String welcomeMessage2 = lblMessage2.getText();
            System.out.println("Message: " + welcomeMessage2);
            assertEquals("Manger Id : " + username, welcomeMessage2);
            Thread.sleep(3000);
            try {
                WebElement btnLogout = myBrowser.findElement(By.cssSelector("a[href='Logout.php']"));
                btnLogout.click();
                btnLogout.submit();
            } catch (Exception e) {
            }
            
             Thread.sleep(3000);
            username = row.getCell(4).getStringCellValue();
            password = row.getCell(5).getStringCellValue();
            WebElement txtUsername3 = myBrowser.findElement(By.xpath("//input[@name='uid']"));
            txtUsername3.sendKeys(username );
            WebElement txtPassword3 = myBrowser.findElement(By.xpath("//input[@name='password']"));
            txtPassword3.sendKeys(password);
            WebElement btnLogin3 = myBrowser.findElement(By.cssSelector("input[value='LOGIN']"));
            btnLogin3.click();
            WebElement lblMessage3 = myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
            String welcomeMessage3 = lblMessage3.getText();
            System.out.println("Message: " + welcomeMessage3);
            assertEquals("Manger Id : " + username, welcomeMessage3);
            Thread.sleep(3000);
            try {
                WebElement btnLogout = myBrowser.findElement(By.cssSelector("a[href='Logout.php']"));
                btnLogout.click();
                btnLogout.submit();
            } catch (Exception e) {
            }

        }*/
        
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        // Thực hiện đăng xuất ở đây
        /* WebElement btnLogout = myBrowser.findElement(By.cssSelector("a[href='Logout.php']"));
        btnLogout.click();
        btnLogout.submit();*/

        // Đợi một khoảng thời gian để chờ quá trình đăng xuất hoàn thành
        Thread.sleep(3000);

        // Đóng trình duyệt
        myBrowser.quit();
    }
}

/* @AfterAll
    public static void tearDownClass() throws InterruptedException {

        Thread.sleep(5000);
        // Thực hiện đăng xuất ở đây
        WebElement btnLogout = myBrowser.findElement(By.cssSelector("a[href='Logout.php']"));
        btnLogout.click();
        btnLogout.submit();
        // Đợi một khoảng thời gian để chờ quá trình đăng xuất hoàn thành
        Thread.sleep(5000);
        // Đóng trình duyệt
        myBrowser.quit();
    }
    
}*/
