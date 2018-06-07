package fi.thl.termed;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class GraphHomePage extends AbstractAngularPage {

  @FindBy(tagName = "h1")
  private WebElement title;

  @FindBy(css = "ol.breadcrumb li:first-child a")
  private WebElement breadcrumbFirstLink;

  GraphHomePage(WebDriver driver) {
    super(driver);
  }

  public String getTitleText() {
    return title.getText();
  }

  public GraphListPage clickFirstLinkInBreadcrumb() {
    breadcrumbFirstLink.click();
    waitUntil(urlMatches(".*/graphs/$"));
    return new GraphListPage(driver);
  }
}
