package fi.thl.termed;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWebDriverTest {

  Logger log = LoggerFactory.getLogger(getClass());

  WebDriver driver;

  @BeforeClass
  public static void initWebDriverManager() {
    WebDriverManager.chromedriver().setup();
  }

  @Before
  public void initWebDriver() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--disable-gpu");

    String protocol = System.getProperty("termed.protocol", "http");
    String username = System.getProperty("termed.username", "admin");
    String password = System.getProperty("termed.password", "admin");
    String host = System.getProperty("termed.host", "localhost");
    String port = System.getProperty("termed.port", "8000");
    String path = ensureLeadingSlash(System.getProperty("termed.path", ""));

    String url = protocol + "://" + username + ":" + password + "@" + host + ":" + port + path;

    log.info("Testing {}://{}:{}{}", protocol, host, port, path);

    driver = new ChromeDriver(options);
    driver.get(url);
  }

  private String ensureLeadingSlash(String str) {
    return (str.startsWith("/") ? "" : "/") + str;
  }

  @After
  public void quitWebDriver() {
    driver.quit();
  }

}
