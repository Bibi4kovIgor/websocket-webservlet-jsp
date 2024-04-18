package org.example;

import org.example.client.GreetClient;

import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    final int port = 7777;
    final String ip = "127.0.0.1";
    try(GreetClient client = new GreetClient(ip, port)) {
      System.out.println(client.sendMessageAndGetResponse("Hello, server"));
      System.out.println(client.sendMessageAndGetResponse("Hello"));
    } catch (IOException exception) {
      System.err.println(exception.getMessage());
    }
  }
}