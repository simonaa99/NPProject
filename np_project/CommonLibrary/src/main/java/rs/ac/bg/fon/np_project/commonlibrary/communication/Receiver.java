/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simona
 */
public class Receiver {
    
    private Socket soket;

    public Receiver(Socket soket) {
        this.soket = soket;
    }

    public synchronized Object receive() throws IOException, ClassNotFoundException {
        ObjectInputStream in;
        try {

            in = new ObjectInputStream(soket.getInputStream());
            return in.readObject();

        } catch (IOException ex) {

            throw new IOException("Error receiving object." + ex.getMessage());

        } catch (ClassNotFoundException ex) {

            throw new ClassNotFoundException("Error receiving object." + ex.getMessage());
        }

    }
    
}
