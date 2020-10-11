/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapplication;

import java.io.*;
import java.net.*;

/**
 *
 * @author Francesco
 */

public class Client {
    String nome_server = "Calcolatrice";
    int porta_server = 8888;
    Socket socket;
    BufferedReader input_tastiera;
    String operazione;
    String risultato;
    DataOutputStream dati_al_server;
    BufferedReader dati_dal_server;
    public Socket connetti(){
        System.out.println("Client in esecuzione...");
        try {
            input_tastiera = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(nome_server,porta_server);
            dati_al_server = new DataOutputStream(socket.getOutputStream());
            dati_dal_server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(UnknownHostException e){
            System.err.println("Host non riconosciuto.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione.");
            System.exit(1);
        }
        return(socket);
    }
    public void comunica(){
        try {
            System.out.println("Inserisci operazione usando gli spazi tra numero/operatore (Esempio: 1 + 1): ");
            operazione = input_tastiera.readLine();
            System.out.println("Invio dell'operazione al server...");
            dati_al_server.writeBytes(operazione + '\n');
            risultato = dati_dal_server.readLine();
            System.out.println("Risultato del server: " + risultato);
            System.out.println("Chiusura dell'esecuzione...");
            socket.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server.");
            System.exit(1);
        }
    }
}
