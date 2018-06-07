package fi.thl.termed;

import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class AbstractAngularPage {

  WebDriver driver;

  AbstractAngularPage(WebDriver driver) {
    this.driver = driver;
    initElements(driver, this);
    waitForPageReady();
    waitForAngularReady();
  }

  <V> void waitUntil(Function<? super WebDriver, V> isTrue) {
    new WebDriverWait(driver, 30).until(isTrue);
  }

  void waitForPageReady() {
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
