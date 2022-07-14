/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.worker;

import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class LoginSO extends AbstractSO {

    private RepositoryWorker repositoryWorker;

    public LoginSO() {
        repositoryWorker = new RepositoryWorker();
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
        List<Worker> workers;
        Worker currentUser;
        Worker user = (Worker) param;
        try {
            workers = (List<Worker>) repositoryWorker.getAll();
            for (Worker r : workers) {
                if (r.getUsername().equals(user.getUsername()) && r.getPassword().equals(user.getPassword())) {
                    currentUser = r;
                    if(currentUser.isLoggedIn()==true){
                    throw  new Exception("Korisnik je vec prijavljen.");
                    }
                    repositoryWorker.setUserIsLoggedIn(currentUser);
                    return currentUser;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
       
        }
        throw new Exception("Nepoznat korisnik!");

    }

}
