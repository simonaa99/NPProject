/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.worker;

import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class LogOutSO extends AbstractSO{
    private RepositoryWorker RepositoryWorker;

    public LogOutSO() {
        RepositoryWorker=new RepositoryWorker();
    }
    
    
    @Override
    protected void precondition(Object param) throws Exception {
    if (param == null || !(param instanceof Worker)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } 
        if(((Worker)param).getUsername().equals(null) || ((Worker)param).getPassword().equals(null))
            throw new Exception("Nisu poslati kredencijali!");
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        Worker w=(Worker) param;
        RepositoryWorker.setUserIsLoggedOut(w);
        return null;
        
            }
    
}
