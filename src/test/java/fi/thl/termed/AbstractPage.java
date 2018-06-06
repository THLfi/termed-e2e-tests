package fi.thl.termed;

import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.function.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class AbstractPage {

  WebDriver driver;

  AbstractPage(WebDriver driver) {
    this.driver = driver;
    initElements(driver, this);
  }

  <V> void waitUntil(Function<? super WebDriver, V> isTrue) {
    new WebDriverWait(driver, 10).until(isTrue);
  }

}
