package com.example.services;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class AsyncService {

    public CompletionStage<String> getFutureHello() {
        return CompletableFuture.supplyAsync(() -> "Hello");
    }

}
