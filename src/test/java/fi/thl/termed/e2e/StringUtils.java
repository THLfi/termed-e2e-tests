package fi.thl.termed.e2e;

public final class StringUtils {

  private StringUtils() {
  }

  public static String ensureLeadingSlash(String str) {
    return (str.startsWith("/") ? "" : "/") + str;
  }

}
