import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import entities.RegistrationInfo;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.DataGenerator;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryDateConfirmTest {
    private RegistrationInfo registrationInfo = DataGenerator.Registration.generateInfo("ru");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openURL() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue(registrationInfo.getCity());
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(formatter.format(registrationInfo.getFirstMeetingDate()));
        form.$("[data-test-id=name] input").setValue(
                registrationInfo.getFirstName() + " " + registrationInfo.getLastName());
        form.$("[data-test-id=phone] input").setValue("+7" + registrationInfo.getPhoneNumber());
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + formatter.format(registrationInfo.getFirstMeetingDate())), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(formatter.format(registrationInfo.getSecondMeetingDate()));
        form.$(".button").click();
        $$(".button").find(exactText("Перепланировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + formatter.format(registrationInfo.getSecondMeetingDate())), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
