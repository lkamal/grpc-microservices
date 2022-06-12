package com.digizol.grpc.java.ms.review;

import com.digizol.grpc.java.ms.review.controller.ReviewController;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReviewServer {

    private Server server;
    private final int port;

    public ReviewServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new ReviewController())
                .addService(ProtoReflectionService.newInstance())
                .build();
    }

    public void start() throws IOException, InterruptedException {
        server.start();
        System.out.println("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
        server.awaitTermination();
    }
}
