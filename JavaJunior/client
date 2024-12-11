package com.example.socketchat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1"; // Локальный сервер
    private static final int SERVER_PORT = 12345; // Порт сервера

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Подключение к серверу...");
            System.out.println("Ответ от сервера: " + in.readLine());

            // Поток для чтения сообщений от сервера
            new Thread(() -> {
                String messageFromServer;
                try {
                    while ((messageFromServer = in.readLine()) != null) {
                        System.out.println("\n[Чат]: " + messageFromServer);
                    }
                } catch (IOException e) {
                    System.err.println("Соединение с сервером потеряно.");
                }
            }).start();

            // Отправка сообщений на сервер
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Вы: ");
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("/exit")) {
                    break;
                }
                out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Ошибка подключения к серверу: " + e.getMessage());
        }
    }
}
