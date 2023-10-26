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
        FileInputStream fis = new FileInputStream("C:\\Users\\tanph\\OneDrive\\Máy tính\\messy stub\\netbean 19\\selenium-2-go\\data\\data.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String username = row.getCell(1).getStringCellValue();
            String password = row.getCell(2).getStringCellValue();
            
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

    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        // Đóng trình duyệt
        myBrowser.quit();
    }
}
