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
        List<JsonObject> warehouses = generateWarehouses(faker);
        return JsonObject.create()
                .put("productName", productName)
                .put("price", price)
                .put("supplier", supplier)
                .put("warehouses", warehouses);
    }

    public static List<JsonObject> generateWarehouses(Faker faker){
        List<JsonObject> warehouses = new ArrayList<>();
        for(int i = 0; i < faker.random().nextInt(8) + 1; i++){
            warehouses.add(JsonObject.create()
                    .put("warehouseId", faker.random().nextLong(numWarehouses))
                    .put("stockQuantity", faker.number().numberBetween(0, 1000)));
        }
        return warehouses;
    }

}

