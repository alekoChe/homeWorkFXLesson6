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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
