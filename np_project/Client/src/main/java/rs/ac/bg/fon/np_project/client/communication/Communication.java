/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.communication;

import java.net.Socket;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Operations;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Receiver;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Request;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Response;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Sender;

/**
 *
 * @author Simona
 */
public class Communication {

    private static Communication instance;
    private Socket socket;
    private Receiver receiver;
    private Sender sender;

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    private Communication() {
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;

    }

    public Response login(Request request) throws Exception {
        //posalji zahtev
        sender.send(request);
       // System.out.println("Zahtev za prijavom na sistem je poslat.");
        return (Response) receiver.receive();

    }

    public Response sendRequest(Request request) throws Exception {
        sender.send(request);
        return (Response) receiver.receive();

    }

    public Response receiveResponse() throws Exception {
        return (Response) receiver.receive();

    }



    public void logout(Worker user) throws Exception {
        try {
            Request request = new Request(Operations.LOGOUT, user);
            sender.send(request);

        } catch (Exception ex) {

            throw new Exception("Error in getting logging out: " + ex.getMessage());
        }

    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

}
