package org.productdemo;

import com.couchbase.client.java.json.JsonObject;
import com.github.javafaker.Faker;

public class UsersGenerator {

    public static JsonObject generateUser(Faker faker) {
        String username = faker.internet().emailAddress();
        String address = faker.address().fullAddress();
        int age = faker.number().numberBetween(18, 80); // Age between 18 and 77
        String gender = faker.random().nextBoolean() ? "Male" : "Female";
        return JsonObject.create()
                .put("username", username)
                .put("address", address)
                .put("age", age)
                .put("gender", gender);
    }
}
