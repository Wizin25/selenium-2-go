/*
         * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
         * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.wizino.guru99.test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author tanph
 */
public class Guru99Test {

    static WebDriver myBrowser;

    /* class TestData {

                String username;
                String password;
                String expectedWelcomeMessage;

                public TestData(String username, String password, String expectedWelcomeMessage) {
                    this.username = username;
                    this.password = password;
                    this.expectedWelcomeMessage = expectedWelcomeMessage;
                }
            }*/
    public Guru99Test() {
    }

    @BeforeAll //hàm này sẽ chạy trước tất cả các @Test nếu có
    //đi đầu tiền, dùng để thiết lập môi trường kiểm thử
    //khởi động các biến gì đó sẽ dùng ở @Test
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    /*@Test
            public void CheckLoginWithDifferentAccounts() throws InterruptedException {
                // Ví dụ danh sách bộ dữ liệu đã được tạo ở phương thức setUpClass
                List<TestData> testDataList = Arrays.asList(
                        new TestData("mngr533997", "vAhAsep", "Expected Welcome Message 1"),
                        new TestData("mngr534133", "AhurUde", "Expected Welcome Message 2")
                );
                // Thêm các bộ dữ liệu khác nếu cần

                for (TestData testData : testDataList) {
                    // Code để mở trình duyệt, nhập dữ liệu và kiểm tra
                    // ...
                    WebElement lblMessage = myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
                    String welcomeMessage = lblMessage.getText();
                    // Đoạn code kiểm tra được thay thế với giá trị từ bộ dữ liệu hiện tại
                    assertEquals(testData.expectedWelcomeMessage, lblMessage.getText());

                    // Đóng trình duyệt
                    // ...
                }
            }*/
    @Test //code để test cái gì đó thì nằm ở đây
    //code để tự động hóa các test case nằm ở đây
    //assertEquals() nằm ở đây

    //Test case #1: Check the login process of Demo Guru 99 in successful case
    //Step 1: Open a certain browser, e.g. Chrome
    //Step 2: Navigate to Demo Guru99 login page via the url: https://demo.guru99.com/v4
    //Step 3: Input a valid account into the login page e.g. mngr533997/vAhAsep   
    //Step 4: Hit [login] button to process the login from
    //EXPECTED RUSULT:
    //         A new dashborad page is showed
    //         with a welcome message is under the format of
    //            Manger id: <user-name>
    public void CheckLoginGivenValidAccountLoginsSuccessfully() throws InterruptedException {
        myBrowser = new ChromeDriver();
        myBrowser.get("https://demo.guru99.com/v4");
        myBrowser.manage().window().maximize();
        //đưa valid account vào ta phải đi tìm các tag của màn hình login
        WebElement txtUsername = myBrowser.findElement(By.xpath("//input[@name='uid']"));
        txtUsername.sendKeys("mngr533997");
        WebElement txtPassword = myBrowser.findElement(By.xpath("//input[@name='password']"));
        txtPassword.sendKeys("vAhAsep");
        WebElement btnLogin = myBrowser.findElement(By.cssSelector("input[value='LOGIN']"));
        btnLogin.click();
        //đảm bảo bắt được tag ở trang mới, ta cần wait 1 chút
        //code cứ nhắm mắt chạy, trang thì chưa kịp tải về do mạng lag
        //ko wait code ko ổn định: mạng nhanh oke, mạng chậm failed tìm tag

        //chuyển trang bắt tag welcome
        Thread.sleep(1000);
        WebElement btnLogout = myBrowser.findElement(By.cssSelector("a[href='Logout.php']"));

        btnLogout.click();

        btnLogout.submit();
        Thread.sleep(3000);

        WebElement txtUsername2 = myBrowser.findElement(By.xpath("//input[@name='uid']"));
        txtUsername2.sendKeys("mngr534133");
        WebElement txtPassword2 = myBrowser.findElement(By.xpath("//input[@name='password']"));
        txtPassword2.sendKeys("AhurUde");
        WebElement btnLogin2 = myBrowser.findElement(By.cssSelector("input[value='LOGIN']"));
        btnLogin2.click();
        // sử dụng thử DDT vào expected
        WebElement lblMessage = myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
        String welcomeMessage = lblMessage.getText();
        System.out.println("Message: " + welcomeMessage);
        assertEquals("Message Id : mngr534133", welcomeMessage);
    }

    @AfterAll //chạy sau tất cảm sau kho các @Test đã xong thì thằng
    //này chạy. Nó dùng để odn dẹp những thứ mà @Test bày ra
    //ví dụ xóa data trong table do việc chạy Test
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

}
