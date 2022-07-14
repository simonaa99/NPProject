/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.communication;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simona
 */
public class Sender {
    
    private Socket soket;

    public Sender(Socket soket) {
        this.soket = soket;
    }
    
    public synchronized void send(Object object) throws Exception{
        ObjectOutputStream out=null;
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(soket.getOutputStream()));
            out.writeObject(object);
            out.flush();
            
        } catch (IOException ex) {
           throw new Exception("Error sending object."+ex.getMessage());
        } 
    }
    
}
