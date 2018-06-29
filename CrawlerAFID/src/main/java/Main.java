import Parse.CommentParse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phuongnl
 */
public class Main {
    private static String baseUrl = "https://www.tripadvisor.com.vn/Attraction_Review-g293925-d454974-Reviews-Saigon_Opera_House_Ho_Chi_Minh_Municipal_Theater-Ho_Chi_Minh_City.html";
    //private static String geckoPath = "/Users/phuongnl/UminoProjects/SpecializeSubject/geckodriver";
    private static  String geckoPath = "D:\\subject\\SpecializedProject\\Source\\geckodriver.exe";
    
    private static String email = "phuong29071996@gmal.com";
    private static String password = "p1234560";
    
    
    public static void main(String [ ] args) throws InterruptedException {
        setupEnviroment();
        WebDriver driver = new FirefoxDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
        TimeUnit.SECONDS.sleep(10);
        

        CommentParse commentParse = new CommentParse(driver);
        commentParse.parse();
        
        driver.quit();
    }
    
    private static void setupEnviroment() {
        System.setProperty("webdriver.gecko.driver", geckoPath);
    }
}
