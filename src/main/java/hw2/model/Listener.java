package hw2.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Listener implements Runnable  {

    private final static int PORT = 8080;

    private final static String HOSTNAME = "localhost";

    @Override
    public void run() {


        for (int i = 0; i < 10; i++) {
            this.runner(PORT + i);
        }
    }

    public void runner(int port) {

        try (Socket clientSocket = new Socket(HOSTNAME, port);
             InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
             BufferedReader in = new BufferedReader(isr);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            System.out.println("Connected to " + HOSTNAME + " on port " + PORT);

            String data = "Hello\nBye";

            System.out.println("Sending to server:\n" + data);
            out.println(data);

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Client received: " + line);

            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}