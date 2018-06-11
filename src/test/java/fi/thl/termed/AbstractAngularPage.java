package fi.thl.termed;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class AbstractAngularPage {

  WebDriver driver;

  private String urlRegexPattern;

  AbstractAngularPage(WebDriver driver, String urlRegexPattern) {
    this.driver = driver;
    this.urlRegexPattern = urlRegexPattern;
    initElements(driver, this);
    waitForUrlChanged();
    waitForPageReady();
    waitForAngularReady();
  }

  <V> void waitUntil(Function<? super WebDriver, V> isTrue) {
    new WebDriverWait(driver, 30).until(isTrue);
  }

  private void waitForUrlChanged() {
    waitUntil(urlMatches(urlRegexPattern));
  }

  private void waitForPageReady() {
    waitUntil(d -> ((JavascriptExecutor) d)
        .executeScript("return document.readyState === 'complete'")
        .equals(true));
  }

  void waitForAngularReady() {
    String angularReadyScript = "return "
        + "(window.angular !== undefined) && "
        + "(angular.element(document.body).injector() !== undefined) && "
        + "(angular.element(document.body).injector().get('$http').pendingRequests.length === 0)";

    waitUntil(d -> ((JavascriptExecutor) d)
        .executeScript(angularReadyScript)
        .equals(true));
  }

}
