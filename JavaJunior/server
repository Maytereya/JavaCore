package com.example.socketchat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 12345; // Любой рандомный порт сервера
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новое подключение: " + clientSocket.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Ошибка запуска сервера: " + e.getMessage());
        }
    }

    /**
     * Метод для рассылки сообщений всем подключённым клиентам.
     * @param message сообщение для отправки
     */
    public static void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    /**
     * Удаляет клиента из списка клиентов при отключении.
     * @param client клиент, который отключился
     */
    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    /**
     * Обработчик для каждого клиента.
     */
    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Добро пожаловать в чат!");

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Получено сообщение: " + message);
                    Server.broadcastMessage(message); // Отправляем сообщение всем клиентам
                }
            } catch (IOException e) {
                System.err.println("Ошибка соединения с клиентом: " + e.getMessage());
            } finally {
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии соединения с клиентом: " + e.getMessage());
                }
                Server.removeClient(this);
                System.out.println("Клиент отключился");
            }
        }

        /**
         * Отправляет сообщение клиенту.
         * @param message сообщение
         */
        public void sendMessage(String message) {
            out.println(message);
        }
    }
}
