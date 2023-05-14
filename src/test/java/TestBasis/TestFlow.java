package TestBasis;
import org.apache.commons.lang3.RandomStringUtils;

public class TestFlow {
    public static String randomEmail = RandomStringUtils.randomAlphanumeric(8) + "@bravo.com";
    public static String randomCorrectPassword = RandomStringUtils.randomAlphanumeric(6, 12);
    public static String randomIncorrectPassword = RandomStringUtils.randomAlphanumeric(0, 6);
    public static String randomName = RandomStringUtils.randomAlphabetic(4, 11);
}
