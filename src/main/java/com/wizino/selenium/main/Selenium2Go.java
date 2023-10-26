/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.wizino.selenium.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author tanph
 */
public class Selenium2Go {

    static WebDriver myBrowser;

    public Selenium2Go() {
    }

    //biến trỏ đến tab trình duyệt
    //trong OOP, cái gì cững là object, phải new, tốn ram
    //1 tab trình duyệt là 1 object trong RAM, có UI bên ngoài
    
    //muốn có trình duyệt xuất hiện thì phải new()
    public static void main(String[] args)throws InterruptedException{
        //Nạp drive là file.exe vào máy ảo Java để nó lắng nghe
        //những hành động code của mình và nó chuyển lại cho trình duyệt
        //~~~ câu lệnh Class.forName(jdbc driver); môn Java Web
        //System.out.println();
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        
        //mở trình duyệt ẩn danh
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--incognito");
        opt.addArguments("lang=ar-AE");
        
        //gọi trình duyệt xuất hiện
        myBrowser = new ChromeDriver();
        
        //lúc này myBrowser trỏ thẳng vào tab vừa mở
        //chấm gọi hàm là bắt trình duyệt làm việc gì đó
        
        //bung full màn hình
        myBrowser.manage().window().maximize();
        
        //duyệt web, lấy trang về
        myBrowser.get("https://www.google.com");
        
        //ta đi tìm các tag -thẻ, để sau đó gọi các event trên tag
        //hoặc là đưa dât vào tag
        //mọi tag trong trang web đều là object: WebElement
        //tìm 1 tag trong trang web giống như select 1 dòng trong table
        //ta phải viết câu query để tìm tag như viết SQL tìm data trong CSDL
        //có nhiều cách để query 1 tag: query theo name của tag
        // id, tên tag, css, class
        // xPath -ngầu nhất
        WebElement searchBox = myBrowser.findElement(By.name("q"));
        searchBox.sendKeys("github");
        searchBox.submit();
        
        //mỗi khi ta tắt tab trình duyệt, driver vẫn nằm trong RAM
        //ko được xài đến, RAM giảm dung lượng dần dần - MEMORY LEAKING
        //ta phải tắt trình duyệt đúng chuẩn để free driver        
        Thread.sleep(3000); //dừng CPT, đừng chạy đóng trình duyệt
                                //sau 3s hãy làm
        myBrowser.quit(); //tắt trình duyệt và gở luôn cả driver
        
        
    }
}
