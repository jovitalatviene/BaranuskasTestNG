import org.example.Bing;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BingTest {
    @BeforeMethod
    public void setup() {
        Bing.setup();
    }

    @Test (priority = 1)
    public void searchByKeyword() {
        Bing.searchByKeyword("Baranauskas");
    }

    @Test (priority = 2)
    public void compareResultsPositiveTest(){
        Bing.searchByKeyword("Baranauskas");
        int resultInt = Bing.getResultInt();
        String resultActual = Bing.compareResult(resultInt);
        Assert.assertEquals("Jaunimas dar neužmiršo Anykščių šilelio.", resultActual);
    }

    @Test
    public void compareResultsNegativeTest(){
        Bing.searchByKeyword("Baranauskas");
        int resultInt = Bing.getResultInt();
        String resultActual = Bing.compareResult(resultInt);
        Assert.assertNotEquals("Rašytojas nelabai populiarus internetinėse platybėse.", resultActual);
    }

    @AfterMethod
    public void close() {
        Bing.close();
    }

}
