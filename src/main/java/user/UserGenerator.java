package user;

import com.github.javafaker.Faker;

import java.util.Locale;

public class UserGenerator {
    static Faker faker = new Faker();
    static Faker fakerRu = new Faker(new Locale("ru"));

    public UserRegistration randomUser() {
        return new UserRegistration(faker.name().username() + "@yandex.ru", faker.internet().password(6, 8,
                true, false, true),fakerRu.name().name());
    }
}
