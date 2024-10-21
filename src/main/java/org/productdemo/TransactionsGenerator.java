package org.productdemo;

import com.couchbase.client.java.json.JsonObject;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

import static org.productdemo.Main.numProducts;
import static org.productdemo.Main.numUsers;

public class TransactionsGenerator {

    public static JsonObject generateTransaction(Faker faker) {
        long userId = faker.random().nextLong(numUsers);
        String transactionDate = faker.date().past(995, 730,java.util.concurrent.TimeUnit.DAYS).toString();
        String paymentMethod = faker.random().nextBoolean() ? "Credit Card" : "PayPal";
        String shippingMethod = faker.random().nextBoolean() ? "Standard" : "Express";
        String[] devices = {"Desktop", "Mobile", "Tablet"};
        String device = devices[faker.random().nextInt(devices.length)];
        List<JsonObject> purchases = generatePurchases(faker);
        return JsonObject.create()
                .put("userId", userId)
                .put("transactionDate", transactionDate)
                .put("paymentMethod", paymentMethod)
                .put("purchases", purchases)
                .put("device", device)
                .put("shippingMethod", shippingMethod);

    }

    public static List<JsonObject> generatePurchases(Faker faker){
        List<JsonObject> purchases = new ArrayList<>();
        for(int i = 0; i < faker.random().nextInt(8) + 1; i++){
            purchases.add(JsonObject.create()
                    .put("productId", faker.random().nextLong(numProducts))
                    .put("quantityPurchased", faker.random().nextInt(5) + 1)
                    .put("discountApplied", faker.random().nextBoolean() ? faker.random().nextInt(50) : 0));
        }
        return purchases;
    }
}

