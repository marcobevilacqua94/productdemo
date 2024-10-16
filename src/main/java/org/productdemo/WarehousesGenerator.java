package org.productdemo;

import com.couchbase.client.java.json.JsonObject;
import com.github.javafaker.Faker;


public class WarehousesGenerator {

    public static JsonObject generateWarehouse(Faker faker) {
        String address = faker.address().fullAddress();
        String owner = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
        String email = faker.internet().safeEmailAddress();
        return JsonObject.create()
                .put("address", address)
                .put("owner", owner)
                .put("phone", phone)
                .put("email", email);

    }

}

