/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapplication;

/**
 *
 * @author Francesco
 */
public class ClientApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.connetti();
        client.comunica();
    }
    
}
