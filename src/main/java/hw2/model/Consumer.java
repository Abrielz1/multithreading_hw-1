package hw2.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Consumer implements Runnable {

    private final static int PORT = 8080;

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            this.runner(PORT + i);
        }
    }


    public void runner(int port) {

        ServerSocket serverSocket;

          try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        try (Socket socket = serverSocket.accept();
             InputStreamReader isr = new InputStreamReader(socket.getInputStream());
             BufferedReader in = new BufferedReader(isr);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("Connection accepted");

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Server received: " + line + ". Sending to client");
                out.println(line);

                if (line.equals("Bye")) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
