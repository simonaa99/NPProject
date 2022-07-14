/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.threads;

import rs.ac.bg.fon.np_project.server.constants.ServerConstants;
import rs.ac.bg.fon.np_project.server.controller.Controller;
//import form.ViewWorkersForm;
import rs.ac.bg.fon.np_project.server.form.ViewWorkersForm;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.View;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.tableModel.TableModelWorkers;


/**
 *
 * @author Simona
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private List<HandleClientThread> clients;
    private ViewWorkersForm workersForm;

    public ServerThread() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerConstants.SERVER_CONFIG_FILE_PATH));
        String port = properties.getProperty(ServerConstants.SERVER_CONFIG_PORT);
        serverSocket = new ServerSocket(Integer.parseInt(port));
        clients = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket s = serverSocket.accept();
                connectClient(s);
            }
        } catch (Exception e) {

        }
    }

    private void connectClient(Socket s) {
        HandleClientThread client = new HandleClientThread(this, s);
        clients.add(client);
        client.start();

    }

    public void stopCommunication() throws IOException {
        for (HandleClientThread client : clients) {
            client.stopCommunication();
        }
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        serverSocket.close();
    }

    void removeClient(HandleClientThread ct) {
        clients.remove(ct);
        System.out.println("Klijentska nit uklonjena.");
    }

    void logout(HandleClientThread ct) throws IOException {

        ct.getSocket().close();
        removeClient(ct);

    }

    void setUserLoggedIn(Worker dbUser, boolean loggedIn) {
        ((TableModelWorkers) workersForm.getTblRadnici().getModel()).updateWorker(dbUser, loggedIn);
    }

    public void setWorkersForm(ViewWorkersForm v) {
        workersForm = v;
    }

    public void logOutAllUsers() {
        for (HandleClientThread client : clients) {

            Worker w = client.getUser();

            if (w != null) {
                Controller.getInstance().logout(w);
                setUserLoggedIn(w, false);
                System.out.println("Korisnik izlogovan: " + w.getUsername());
            }

        }
    }

}
