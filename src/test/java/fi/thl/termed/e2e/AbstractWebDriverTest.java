package fi.thl.termed.e2e;

import static fi.thl.termed.e2e.StringUtils.ensureLeadingSlash;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract superclass for web driver based Termed UI tests.
 *
 * Reads Termed connection properties form system properties (environment variables). Supported
 * properties are:
 *
 * <ul>
 * <li>termed.protocol</li>
 * <li>termed.username</li>
 * <li>termed.password</li>
 * <li>termed.host</li>
 * <li>termed.port</li>
 * <li>termed.path</li>
 * </ul>
 */
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public abstract class AbstractWebDriverTest {

  protected WebDriver driver;

  private Logger log = LoggerFactory.getLogger(getClass());

  @BeforeAll
  void initWebDriver() {
    WebDriverManager.chromedriver().setup();

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

  @AfterAll
  void quitWebDriver() {
    driver.quit();
  }

}
