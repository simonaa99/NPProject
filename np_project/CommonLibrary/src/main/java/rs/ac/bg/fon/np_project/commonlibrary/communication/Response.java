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
public class Response implements Serializable{
    
    private Object result;
    private ResponseType responseType;
    private Exception exception;
    private int operation;
  

    public Response() {
    }

    public Response(Object result, ResponseType responseType, Exception exception) {
        this.result = result;
        this.responseType = responseType;
        this.exception = exception;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public Object getResult() {
        return result;
    }

    ////////////////////
    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
    
}
