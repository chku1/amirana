import com.codeborne.selenide.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {
    public SelenideTests(){
        Configuration.timeout=10000;
        Configuration.browser = "chrome";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserSize="1920x1080";
        baseUrl="http://the-internet.herokuapp.com";
        reportsFolder = "src/main/resources/Reports";
        downloadsFolder="src/main/resources/images";
    }

    @Test
    public void dropdownTest() throws InterruptedException {
        open("http://the-internet.herokuapp.com/dropdown");

        // Find the dropdown element by its locator
        $("#dropdown").selectOptionContainingText("tion 2");

        // Validate that 'Option 2' is selected
        $("#dropdown").getSelectedOption().shouldHave(text("Option 2"));

        Thread.sleep(4000);
    }

    @Test
    public void inputTest() throws InterruptedException {
        try {
            open("https://the-internet.herokuapp.com/inputs");

            // Find the input field by its CSS selector and fill it with text '100'
            $("[type='number']").setValue("100");

            // Validate that the input field is not empty (failed case for screenshot)
            $("[type='number']").shouldNotBe(empty);
        }catch (Exception e) {
            // Capture screenshot on failure
            screenshot("failed_test");

            // Rethrow the exception to mark the test as failed
            throw e;
        }

        Thread.sleep(4000);
    }




}
