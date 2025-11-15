package chat;

import java.net.Socket;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.classfile.CodeBuilder.CatchBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientThread extends Thread {
  private Socket client;
  private String name;
  private BufferedReader bs;
  private DataOutputStream outchan;
  private String channel;

  public ClientThread(Socket s) {
    this.client = s;
    this.name = "";
    this.channel = "";
  }

  public void run() {
    System.out.println("New user connected, waiting for name attribution");
    try {
      InputStream is = this.client.getInputStream();
      this.bs = new BufferedReader(new InputStreamReader(is));
      this.outchan = new DataOutputStream(this.client.getOutputStream());
      this.usernameChoice();
      this.channelChoice();
      String line;
      do {
        System.out.println("into run");
        line = this.bs.readLine();
        this.sendMessage(line);
        System.out.println(line);
      } while (line != "exit");
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void usernameChoice() {
    try {
      String line;
      this.outchan.writeChars("Bienvenue, entrez votre pseudo:\n");
      System.out.println("into username choice");
      line = this.bs.readLine();
      this.name = line;
      this.outchan.writeChars("Bonjour " + line + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void channelChoice() {
    try {
      String line;
      if (Main.channels.size() == 0) {
        this.outchan.writeChars("Il n'y a actuellement aucun channel ouvert.\nNom de votre channel:\n");
        line = this.bs.readLine();
        this.channel = line;
        this.outchan.writeChars("Vous rentrez dans le channel " + this.channel + "\n");
        Main.channels.add(this.channel);
      } else {
        this.outchan.writeChars("veuillez choisir une room (entrez un autre nom pour créer une room): \n");
        this.outchan.writeChars(Main.channels.toString() + "\n");
        line = this.bs.readLine();
        if (Main.channels.contains(line)) {
          this.channel = line;
          this.outchan.writeChars("Vous entrez dans le channel " + this.channel + "\n");
        } else {
          this.channel = line;
          this.outchan.writeChars("Vous rentrez dans le channel " + this.channel + "\n");
          Main.channels.add(this.channel);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void display(String message) {
    try {
      this.outchan.writeChars(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void sendMessage(String line) {
    String message = this.name + ": " + line + "\n";
    Main.clients.stream()
        .filter(client -> client.getChannel().equals(this.channel))
        .forEach(client -> client.display(message));
    System.out.println(message);
  }

  public String getChannel() {
    return this.channel;
  }
}
