package org.example;

import com.lambdatest.tunnel.Tunnel;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SampleWebTest {

    private RemoteWebDriver driver;
    private String Status = "failed";

  //  private Tunnel t;

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws Exception {
        String username = "mohammadk";
        String authkey = "rakcBoBYHiy8BW7osVi4N1LGYjgJhRfAwvL1pPUvwCA1wfNChd";


        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "docker-tunnel");
        caps.setCapability("name", "sample-test");
        caps.setCapability("tunnel", true);

        //create tunnel instance
      //  t = new Tunnel();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user", username);
        options.put("key", authkey);

        //start tunnel
        //t.start(options);
       // System.out.println("Tunnel has been started");

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }

    @Test
    public void basicTest() throws InterruptedException {
        String spanText;
        System.out.println("Loading Url");

        driver.get("http://192.168.0.106:8000/");

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("cypress.io"),
                "Page title missing");


        Status = "passed";
        Thread.sleep(150);

        System.out.println("TestFinished");

    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
       // t.stop();
    }

}