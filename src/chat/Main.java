package chat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class Main {
  // private static ArrayList<Socket> clients = new ArrayList<>();

  public static void main(String[] args) {
    int port = 5156;

    try {
      ServerSocket server = new ServerSocket(port);

      while (true) {
        try {
          ClientController clientController = new ClientController();
          Socket client = server.accept();
          ClientThread clientThread = new ClientThread(client, clientController);
          clientController.addThread(clientThread);
          clientThread.start();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    } catch (IOException e) {
      System.out.println(e);
      e.printStackTrace();
    }
  }

  // public ArrayList<Socket> getClients() {
  // return clients;
  // }
}
