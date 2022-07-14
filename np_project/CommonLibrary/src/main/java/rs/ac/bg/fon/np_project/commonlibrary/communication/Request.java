/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.communication;

import java.io.Serializable;

/**
 *
 * @author Simona
 */
public class Request implements Serializable{
    
    private int operation; //tip operacije
    private Object argument;

    public Request() {
    }

    public Request(int operacija, Object argument) {
        this.operation = operacija;
        this.argument = argument;
    }

    public Object getArgument() {
        return argument;
    }

    public int getOperation() {
        return operation;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
    
}
