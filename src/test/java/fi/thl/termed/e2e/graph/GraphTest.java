package fi.thl.termed.e2e.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fi.thl.termed.e2e.AbstractWebDriverTest;
import java.util.UUID;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class GraphTest extends AbstractWebDriverTest {

  private String testGraphLabel = "Test Graph " + UUID.randomUUID();

  @Test
  @Order(1)
  void shouldCreateGraph() {
    GraphListPage graphListPage = new GraphListPage(driver);

    GraphEditPage graphEditPage = graphListPage.clickNewGraph();
    assertNotNull(graphEditPage);
  }

  @Test
  @Order(2)
  void shouldUpdateGraph() {
    GraphEditPage graphEditPage = new GraphEditPage(driver);

    graphEditPage.setGraphLabel(testGraphLabel);
    GraphHomePage graphHomePage = graphEditPage.clickSave();
    assertEquals(testGraphLabel, graphHomePage.getHeadingText());
  }

  @Test
  @Order(3)
  void shouldDeleteGraph() {
    GraphHomePage graphHomePage = new GraphHomePage(driver);

    GraphListPage graphListPage = graphHomePage.clickFirstLinkInBreadcrumb();
    assertTrue(graphListPage.getGraphNames().contains(testGraphLabel));

    GraphEditPage graphEditPage = graphListPage.clickEditGraphWithLabel(testGraphLabel);

    graphListPage = graphEditPage.clickRemove();
    assertFalse(graphListPage.getGraphNames().contains(testGraphLabel));
  }

}
