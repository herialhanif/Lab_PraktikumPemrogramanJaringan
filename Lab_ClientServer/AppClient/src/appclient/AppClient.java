/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appclient;

import com.sun.media.jfxmediaimpl.HostUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author skyha
 */
public class AppClient {

    private static InetAddress host;  
    public static int PORT = 9876;
    
    
    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("Host ID Not Found");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer() {
        Socket link = null;        
        try {
            link = new Socket(host , PORT);
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);
            Scanner userEntry = new Scanner(System.in);
            String message, response;
            do {                
                System.out.println("Enter message: ");
                message = userEntry.nextLine();
                output.println(message);
                response = input.nextLine();
                System.out.println("\nSERVER >"+response);
            } while (!message.equals("***CLOSE***"));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }finally{
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
