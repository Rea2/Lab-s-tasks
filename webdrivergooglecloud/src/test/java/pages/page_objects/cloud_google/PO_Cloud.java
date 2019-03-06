package pages.page_objects.cloud_google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.page_objects.PO;

public class PO_Cloud extends PO {

    @FindBy(xpath = "//div[@class = 'cloud-section-header__link']/a[@href = 'https://cloud.google.com/products/']")
    private WebElement buttonExploreAllProducts;

    public PO_Cloud(WebDriver driver) {
        super(driver);

        // Провекрка того факта, что мы на верной странице.
        checkTitlePage("Google Cloud including GCP & G Suite — Try Free  |  Google Cloud");
    }

    public PO_CloudProducts clickExploreAllProducts(){
        clickButtonWhenClickable(buttonExploreAllProducts);
        return PageFactory.initElements(driver, PO_CloudProducts.class);
    }
}



























