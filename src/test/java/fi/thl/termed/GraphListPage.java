package fi.thl.termed;

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
    super(driver, ".*/graphs/?(\\?.*)?$");
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
    return new GraphEditPage(driver);
  }

  public GraphEditPage clickEditGraphWithLabel(String graphLabel) {
    driver
        .findElement(By.linkText(graphLabel))
        .findElement(By.xpath("../.."))
        .findElement(By.cssSelector("span.glyphicon.glyphicon-edit"))
        .findElement(By.xpath(".."))
        .click();
    return new GraphEditPage(driver);
  }

}
