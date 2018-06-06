package fi.thl.termed;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class AbstractWebDriverTest {

  WebDriver driver;

  @BeforeClass
  public static void initWebDriverManager() {
    WebDriverManager.chromedriver().setup();
  }

  @Before
  public void initWebDriver() throws IOException {
    Properties properties = new Properties();

    URL testPropertiesUrl = getClass().getResource("test.properties");
    if (testPropertiesUrl != null) {
      properties.load(testPropertiesUrl.openStream());
    }

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--disable-gpu");

    String protocol = properties.getProperty("termed.protocol", "http");
    String username = properties.getProperty("termed.username", "admin");
    String password = properties.getProperty("termed.password", "admin");
    String host = properties.getProperty("termed.host", "localhost");
    String port = properties.getProperty("termed.port", "8000");
    String path = properties.getProperty("termed.path", "/");

    String url = protocol + "://" + username + ":" + password + "@" + host + ":" + port + (
        path.startsWith("/") ? "" : "/") + path;

    driver = new ChromeDriver(options);
    driver.get(url);
  }

  @After
  public void quitWebDriver() {
    driver.quit();
  }

}
