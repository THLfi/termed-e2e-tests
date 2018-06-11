package fi.thl.termed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class GraphHomePage extends AbstractAngularPage {

  @FindBy(tagName = "h1")
  private WebElement title;

  @FindBy(css = "ol.breadcrumb li:first-child a")
  private WebElement breadcrumbFirstLink;

  GraphHomePage(WebDriver driver) {
    super(driver, ".*/graphs/" + RegularExpressions.UUID + "/nodes(\\?.*)?$");
  }

  public String getTitleText() {
    return title.getText();
  }

  public GraphListPage clickFirstLinkInBreadcrumb() {
    breadcrumbFirstLink.click();
    return new GraphListPage(driver);
  }
}
