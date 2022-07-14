/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client;

import rs.ac.bg.fon.np_project.client.communication.Communication;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Operations;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Receiver;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Request;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Response;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Sender;
import rs.ac.bg.fon.np_project.client.controller.ControllerUI;
import java.awt.Window;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.Socket;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.np_project.client.view.FrmLogin;
import rs.ac.bg.fon.np_project.client.view.FrmMain;


/**
 *
 * @author Simona
 */
public class Client {

    private FrmLogin frm;
    private FrmMain frmMain;

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client klijent = new Client();
        try {
            klijent.connect();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server nije pokrenut.");
        }

    }

    public void connect() throws IOException {
        Socket soket = new Socket("localhost", 9000);
        System.out.println("Klijent se povezao..");
        Receiver receiver = new Receiver(soket);
        Sender sender = new Sender(soket);
        Communication.getInstance().setReceiver(receiver);
        Communication.getInstance().setSender(sender);
        ThreadListener listener = new ThreadListener(receiver, sender);

        frm = new FrmLogin();
        frm.setVisible(true);

    }

    class ThreadListener extends Thread {

        private Receiver receiver;
        private Sender sender;

        public ThreadListener(Receiver receiver, Sender sender) {
            this.receiver = receiver;
            this.sender = sender;
            start();
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    sleep(3000);

                    Request request = new Request(Operations.STOP, null);
                    sender.send(request);

                    Response response = (Response) receiver.receive();
                    handleResponse(response);
                }

            } catch (Exception ex) {
                System.out.println("Prekinuta konekcija.");
                System.exit(0);
                
                

            }
        }

        private void handleResponse(Response response) {
            try {
                if (response != null && response.getOperation() == Operations.SERVER_STOPPED) {
                    Request request = new Request(Operations.STOP, null);
                    sender.send(request);

                    ControllerUI.getInstance().finish();
                    this.interrupt();
                } else {

                }
            } catch (Exception ex) {
                System.out.println("Konekcija prekinuta>> " + ex.getMessage());
            }
        }

    }
}
