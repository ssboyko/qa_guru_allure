import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureTests {
    private static final String REPOSITORY = "ssboyko/qa_guru_ssboiko";
    private static final int ISSUE = 2;

    @Test
    @Feature("Issue в репозитории")
    @Story("Issue")
    @Owner("ssboiko")
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Проверка Issue в репозитории с лямбдами для аллюра")
    public void testLambdaStep() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Кликаем по найденному репозиторию " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Issue")
    @Owner("ssboiko")
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Проверка Issue в репозитории через аннотации для аллюра")
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Issue")
    @Owner("ssboiko")
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Проверка Issue в репозитории с листенером")
    public void checkIssueTestWithListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText("#" + ISSUE)).should(Condition.exist);
    }

}

