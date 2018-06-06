package fi.thl.termed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;
import org.junit.Test;

public class GraphCrudTest extends AbstractWebDriverTest {

  @Test
  public void shouldCreateUpdateAndDeleteMinimalGraph() {
    String testGraphLabel = "Test Graph " + UUID.randomUUID();

    GraphListPage graphListPage = new GraphListPage(driver);

    GraphEditPage graphEditPage = graphListPage.clickNewGraph();
    graphEditPage.setGraphLabel(testGraphLabel);

    GraphHomePage graphHomePage = graphEditPage.clickSave();
    assertEquals(testGraphLabel, graphHomePage.getTitleText());

    graphListPage = graphHomePage.clickFirstLinkInBreadcrumb();
    assertTrue(graphListPage.getGraphNames().contains(testGraphLabel));

    graphEditPage = graphListPage.clickEditGraphWithLabel(testGraphLabel);

    graphListPage = graphEditPage.clickRemove();
    assertFalse(graphListPage.getGraphNames().contains(testGraphLabel));
  }

}
