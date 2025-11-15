package chat;

import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.*;

public class ClientController {
  private ArrayList<ClientThread> clients;

  public ClientController() {
    this.clients = new ArrayList<ClientThread>();
  }

  public void addThread(ClientThread t) {
    this.clients.add(t);
  }

  public void sendMessage(String message, ClientThread sender) {
    System.out.println(this.clients);
    this.clients.stream()
        .filter(client -> !client.equals(sender))
        .forEach(thread -> thread.display(message));
  }
}
