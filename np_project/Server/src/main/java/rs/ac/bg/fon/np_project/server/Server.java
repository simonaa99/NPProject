/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server;

import rs.ac.bg.fon.np_project.server.form.ServerForm;


/**
 *
 * @author Simona
 */
public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer();

        } catch (Exception ex) {
            System.out.println("Greska pri pokretanju servera: " + ex.getMessage());
        }
    }

    private void startServer() throws Exception {
        new ServerForm().setVisible(true);
    }

}
