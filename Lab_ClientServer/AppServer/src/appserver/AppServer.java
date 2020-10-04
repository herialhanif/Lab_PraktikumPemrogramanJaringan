/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author skyha
 */
public class AppServer {

    private static int PORT = 9876;
    private static ServerSocket serverSocket;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Opening port.....\n");
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.err.println("Unable to attacch port");
            System.exit(1);
        }
        do {
            handleClient();
        } while (true);
    }

    private static void handleClient() {
        Socket link = null;
        try {
            //creating socket and waiting for client connection
            link = serverSocket.accept();
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);
            int numMassage = 0;
            String massage = input.nextLine();
            while (!massage.equals("***CLOSE***")) {
                System.out.println("Message Recive");
                numMassage++;
                output.println("Message " + numMassage + ": " + massage);
            }
            output.println(numMassage + " Mssage recieved");
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\n closing connection....");
                link.close();
            } catch (IOException ioE) {
                System.out.println("\n closing connection....");
                System.exit(1);
            }
        }

    }

}