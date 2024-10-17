package org.productdemo;

import com.couchbase.client.java.json.JsonObject;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

import static org.productdemo.Main.numWarehouses;

public class ProductsGenerator {

    public static JsonObject generateProduct(Faker faker) {
        String productName = faker.commerce().productName();
        double price = Double.parseDouble(faker.commerce().price());
        String supplier = faker.company().name();
        List<JsonObject> stocks = generateStocks(faker);
        return JsonObject.create()
                .put("productName", productName)
                .put("price", price)
                .put("supplier", supplier)
                .put("stocks", stocks);
    }

    public static List<JsonObject> generateStocks(Faker faker){
        List<JsonObject> stocks = new ArrayList<>();
        for(int i = 0; i < faker.random().nextInt(8) + 1; i++){
            stocks.add(JsonObject.create()
                    .put("warehouseId", faker.random().nextLong(numWarehouses))
                    .put("stockQuantity", faker.number().numberBetween(0, 1000)));
        }
        return stocks;
    }

}

