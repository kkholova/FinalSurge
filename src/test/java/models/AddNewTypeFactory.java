package models;

import com.github.javafaker.Faker;

public class AddNewTypeFactory {
    public static AddNewType get() {
        Faker faker = new Faker();
        return AddNewType.builder()
                .newTypeName(faker.name().firstName())
                .textColor("white")
                .build();
    }
}
