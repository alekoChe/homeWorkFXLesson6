package ru.gbquoter2.homeworkfxlesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SomeServerBis {
    private static String outMessage;

    private static Socket socket = null;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Ожидаем подключения клиента...");
            socket = serverSocket.accept(); // <-- ожидаем подключения клиента
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                final String message = in.readUTF();
                System.out.println("Сообщение от клиента: " + message);
                if (message.startsWith("/end")) {
                    out.writeUTF("/end");
                    break;
                }
                out.writeUTF("Эхо: " + message);
                serverMessageConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void serverMessageConnection() { // домашнее задание
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Scanner scanner = new Scanner(System.in);
                        final String serverMessage = scanner.nextLine();
                        if (!serverMessage.isEmpty()) {
                            //return;
                            if ("/end".equals(serverMessage)) {
                                break;
                            }
                            out.writeUTF("Сообщение от сервера: " + serverMessage);
                        }
                        if ("/end".equals(serverMessage)) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }
        }).start();
    }
    private static void closeConnection() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
