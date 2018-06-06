package fi.thl.termed;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class GraphEditPage extends AbstractPage {

  @FindBy(tagName = "h1")
  private WebElement title;

  @FindBy(css = "button[ng-click='save()']")
  private WebElement save;

  @FindBy(css = "button[ng-click='remove()']")
  private WebElement remove;

  // assume that graph label is the first property in graph.properties table
  @FindBy(css = "thl-graph-properties-edit[property-map='graph.properties'] textarea[ng-model='langValue.value']")
  private WebElement graphLabel;

  GraphEditPage(WebDriver driver) {
    super(driver);
  }

  public String getTitleText() {
    return title.getText();
  }

  public void setGraphLabel(String label) {
    graphLabel.clear();
    graphLabel.sendKeys(label);
    waitUntil(textToBe(By.tagName("h1"), label));
  }

  public GraphHomePage clickSave() {
    save.click();
    waitUntil(urlMatches(".*/graphs/" + RegularExpressions.UUID + "/nodes(\\?.*)?$"));
    return new GraphHomePage(driver);
  }

  public GraphListPage clickRemove() {
    remove.click();
    waitUntil(urlMatches(".*/graphs/$"));
    return new GraphListPage(driver);
  }

}
