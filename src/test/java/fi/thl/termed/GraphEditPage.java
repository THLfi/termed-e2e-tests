package fi.thl.termed;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class GraphEditPage extends AbstractAngularPage {

  @FindBy(tagName = "h1")
  private WebElement heading;

  @FindBy(css = "button[ng-click='save()']")
  private WebElement save;

  @FindBy(css = "button[ng-click='remove()']")
  private WebElement remove;

  // assume that graph label is the first property in graph.properties table
  @FindBy(css = "thl-graph-properties-edit[property-map='graph.properties'] textarea[ng-model='langValue.value']")
  private WebElement graphLabel;

  GraphEditPage(WebDriver driver) {
    super(driver, ".*/graphs/" + RegularExpressions.UUID + "/edit(\\?.*)?$");
  }

  public String getHeadingText() {
    return heading.getText();
  }

  public void setGraphLabel(String label) {
    graphLabel.clear();
    graphLabel.sendKeys(label);
    waitUntil(textToBe(By.tagName("h1"), label));
  }

  public GraphHomePage clickSave() {
    save.click();
    return new GraphHomePage(driver);
  }

  public GraphListPage clickRemove() {
    remove.click();
    return new GraphListPage(driver);
  }

}
