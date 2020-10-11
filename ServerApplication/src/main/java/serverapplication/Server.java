/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Francesco
 */
public class Server {
    ServerSocket socket_server = null;
    Socket socket_client = null;
    String operazione_client = null;
    String risultato_server = null;
    BufferedReader dati_dal_client;
    DataOutputStream dati_al_client;
    float num1, num2;
    char operazione;
    Operazioni operazioni;
    public Socket attendi(){
        try {
            System.out.println("Server in esecuzione...");
            socket_server = new ServerSocket(8888);
            System.out.println("Server in attesa del client...");
            socket_client = socket_server.accept();
            System.out.println("Client connesso...");
            dati_dal_client = new BufferedReader(new InputStreamReader(socket_client.getInputStream()));
            dati_al_client = new DataOutputStream(socket_client.getOutputStream());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore nell'istanziamento del server.");
            System.exit(1);
        }
        return(socket_client);
    }
    public void comunica(){
        try {
            System.out.println("In attesa dell'operazione da svolgere da parte del client...");
            String[] nums = dati_dal_client.readLine().split(" ");
            num1 = Float.parseFloat(nums[0]);
            num2 = Float.parseFloat(nums[2]);
            operazione = nums[1].charAt(0);
            operazioni = new Operazioni(num1, num2);
            System.out.println("Operazione ricevuta...");
            switch(operazione) {
		case '+' : {
                    System.out.println("Svolgendo l'operazione richiesta...");
                    System.out.println("Invio del risultato dell'operazione al client...");
                    dati_al_client.writeBytes(String.valueOf(operazioni.addizione()) + "\n");
                    break;
                }
		case '-' : {
                    System.out.println("Svolgendo l'operazione richiesta...");
                    System.out.println("Invio del risultato dell'operazione al client...");
                    dati_al_client.writeBytes(String.valueOf(operazioni.sottrazione()) + "\n");
                    break;
		}
		case '/' : {
                    System.out.println("Svolgendo l'operazione richiesta...");
                    System.out.println("Invio del risultato dell'operazione al client...");
                    dati_al_client.writeBytes(String.valueOf(operazioni.divisione()) + "\n");
                    break;
		}
		case '*' : {
                    System.out.println("Svolgendo l'operazione richiesta...");
                    System.out.println("Invio del risultato dell'operazione al client...");
                    dati_al_client.writeBytes(String.valueOf(operazioni.moltiplicazione()) + "\n");
                    break;
		}
		default : {
                    System.out.println("Svolgendo l'operazione richiesta...");
                    dati_al_client.writeBytes("Errore" + "\n");
                    break;
		}
            }
            System.out.println("Elaborazione completata...");
            socket_client.close();
        }
        catch (Exception e) {
            System.out.println("Errore durante la comunicazione...");
        }
    }
}
