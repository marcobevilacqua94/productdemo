package org.productdemo;

import com.couchbase.client.java.*;
import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

public class Main {

    static public long numProducts = 5000000L;
    static public long numRatings = 200000000L;
    static public long numTransactions = 200000000L;
    static public long numUsers = 2000000L;
    static public long numWarehouses = 10000L;

    public static void main(String[] args) {


        /* parameters to use if no command line is found */
        String username = "app";
        String password = "Couchbase123!";
        String ip = "couchbases://cb.awxbztn9j7ujeeq9.cloud.couchbase.com";
        String bucketName = "productDemo";
        String scopeName = "productDemo";
        int buffer = 1000;


        try (
                Cluster cluster = Cluster.connect(
                        ip,
                        ClusterOptions.clusterOptions(username, password))
        ) {

            ReactiveBucket buck = cluster.bucket(bucketName).reactive();
            ReactiveScope scope = buck.scope(scopeName);


            Faker faker = new Faker();

            //products
            ReactiveCollection productsCollection = scope.collection("products");
            Flux.generate(() -> 0L, (i, sink) ->
                    {
                        sink.next(i);
                        if (i > numProducts) {
                            sink.complete();
                        }
                        return i + 1;
                    })
                    .buffer(buffer)
                    .map(countList -> Flux.fromIterable(countList)
                            .parallel()
                            .flatMap(count -> productsCollection.upsert(
                                    count.toString(),
                                    ProductsGenerator.generateProduct(faker))
                            )
                            .sequential()
                            .retry()
                            .collectList()
                            .block()
                    )
                    .retry()
                    .collectList()
                    .block();


            //ratings
            ReactiveCollection ratingsCollection = scope.collection("ratings");
            Flux.generate(() -> 0L, (i, sink) ->
                    {
                        sink.next(i);
                        if (i > numRatings) {
                            sink.complete();
                        }
                        return i + 1;
                    })
                    .buffer(buffer)
                    .map(countList -> Flux.fromIterable(countList)
                            .parallel()
                            .flatMap(count -> ratingsCollection.upsert(
                                    count.toString(),
                                    RatingsGenerator.generateRating(faker))
                            )
                            .sequential()
                            .retry()
                            .collectList()
                            .block()
                    )
                    .retry()
                    .collectList()
                    .block();

            //transactions
            ReactiveCollection transactionsCollection = scope.collection("transactions");
            Flux.generate(() -> 0L, (i, sink) ->
                    {
                        sink.next(i);
                        if (i > numTransactions) {
                            sink.complete();
                        }
                        return i + 1;
                    })
                    .buffer(buffer)
                    .map(countList -> Flux.fromIterable(countList)
                            .parallel()
                            .flatMap(count -> transactionsCollection.upsert(
                                    count.toString(),
                                    TransactionsGenerator.generateTransaction(faker))
                            )
                            .sequential()
                            .retry()
                            .collectList()
                            .block()
                    )
                    .retry()
                    .collectList()
                    .block();

//            //users
//            ReactiveCollection usersCollection = scope.collection("users");
//            Flux.generate(() -> 0L, (i, sink) ->
//                    {
//                        sink.next(i);
//                        if (i > numUsers) {
//                            sink.complete();
//                        }
//                        return i + 1;
//                    })
//                    .buffer(buffer)
//                    .map(countList -> Flux.fromIterable(countList)
//                            .parallel()
//                            .flatMap(count -> usersCollection.upsert(
//                                    count.toString(),
//                                    UsersGenerator.generateUser(faker))
//                            )
//                            .sequential()
//                            .retry()
//                            .collectList()
//                            .block()
//                    )
//                    .retry()
//                    .collectList()
//                    .block();
//
//            //warehouses
//            ReactiveCollection warehousesCollection = scope.collection("warehouses");
//            Flux.generate(() -> 0L, (i, sink) ->
//                    {
//                        sink.next(i);
//                        if (i > numWarehouses) {
//                            sink.complete();
//                        }
//                        return i + 1;
//                    })
//                    .buffer(buffer)
//                    .map(countList -> Flux.fromIterable(countList)
//                            .parallel()
//                            .flatMap(count -> warehousesCollection.upsert(
//                                    count.toString(),
//                                    WarehousesGenerator.generateWarehouse(faker))
//                            )
//                            .sequential()
//                            .retry()
//                            .collectList()
//                            .block()
//                    )
//                    .retry()
//                    .collectList()
//                    .block();

        }

    }


}


