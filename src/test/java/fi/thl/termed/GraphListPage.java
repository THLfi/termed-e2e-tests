package fi.thl.termed;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

import java.util.Set;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class GraphListPage extends AbstractAngularPage {

  @FindBy(tagName = "h1")
  private WebElement title;

  @FindBy(css = "button[ng-click='newGraph()']")
  private WebElement newGraph;

  GraphListPage(WebDriver driver) {
    super(driver);
  }

  public String getTitleText() {
    return title.getText();
  }

  public Set<String> getGraphNames() {
    return driver.findElements(
        By.cssSelector(
            "tr[ng-repeat='graph in graphs | filter:graphQuery | orderBy:localizedPrefLabel'] a"))
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toSet());
  }

  public GraphEditPage clickNewGraph() {
    newGraph.click();
    waitUntil(urlMatches(".*/graphs/" + RegularExpressions.UUID + "/edit$"));
    return new GraphEditPage(driver);
  }

  public GraphEditPage clickEditGraphWithLabel(String graphLabel) {
    driver
        .findElement(By.linkText(graphLabel))
        .findElement(By.xpath("../.."))
        .findElement(By.cssSelector("span.glyphicon.glyphicon-edit"))
        .findElement(By.xpath(".."))
        .click();

    waitUntil(urlMatches(".*/graphs/" + RegularExpressions.UUID + "/edit$"));

    return new GraphEditPage(driver);
  }

}
