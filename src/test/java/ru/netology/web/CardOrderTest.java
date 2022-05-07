package ru.netology.web;


import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardOrderTest {

    @Test
    public void testHappyPath() {

        open("http://localhost:9999");
        $(".heading heading_size_l heading_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Анастасия Антонова");
        $("[data-test-id=phone] input").setValue("+79146562545");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void testHappyPath2() {

        open("http://localhost:9999");
        $(".heading heading_size_l heading_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Анастасия-Виктория Петровна");
        $("[data-test-id=phone] input").setValue("+70000000002");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

}
