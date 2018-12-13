package fi.thl.termed;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

import java.util.Objects;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class AbstractAngularPage {

  WebDriver driver;

  private String urlRegexPattern;

  AbstractAngularPage(WebDriver driver, String urlRegexPattern) {
    this.driver = Objects.requireNonNull(driver);
    this.urlRegexPattern = Objects.requireNonNull(urlRegexPattern);
    initElements(driver, this);
    waitForUrlChange();
    waitForPageReady();
    waitForAngularReady();
  }

  <V> void waitUntil(Function<? super WebDriver, V> isTrue) {
    new WebDriverWait(driver, 20).until(isTrue);
  }

  private void waitForUrlChange() {
    waitUntil(urlMatches(urlRegexPattern));
  }

  private void waitForPageReady() {
    waitUntil(d -> ((JavascriptExecutor) d)
        .executeScript("return document.readyState === 'complete'")
        .equals(true));
  }

  private void waitForAngularReady() {
    String angularReadyScript = "return "
        + "(window.angular !== undefined) && "
        + "(angular.element(document.body).injector() !== undefined) && "
        + "(angular.element(document.body).injector().get('$http').pendingRequests.length === 0)";

    waitUntil(d -> ((JavascriptExecutor) d)
        .executeScript(angularReadyScript)
        .equals(true));
  }

}
