package org.productdemo;

import com.couchbase.client.java.json.JsonObject;
import com.github.javafaker.Faker;

import static org.productdemo.Main.numProducts;
import static org.productdemo.Main.numUsers;


public class RatingsGenerator {

    public static JsonObject generateRating(Faker faker) {
        long userId = faker.random().nextLong(numUsers);
        long productId = faker.random().nextLong(numProducts);
        int rating = faker.random().nextInt(5) + 1; // Rating between 1 and 5
        String ratingDate = faker.date().past(365, java.util.concurrent.TimeUnit.DAYS).toString();

        return JsonObject.create()
                .put("productId", productId)
                .put("userId", userId)
                .put("rating", rating)
                .put("ratingDate", ratingDate);
    }
}
