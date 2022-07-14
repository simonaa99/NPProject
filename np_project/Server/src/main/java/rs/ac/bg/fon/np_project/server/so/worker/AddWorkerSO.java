/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.worker;

import rs.ac.bg.fon.np_project.server.broker.DatabaseBroker;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class AddWorkerSO extends AbstractSO{
    private RepositoryWorker repositoryWorker;
    private DatabaseBroker dbBroker;

    public AddWorkerSO() {
        repositoryWorker=new RepositoryWorker();
        dbBroker=new DatabaseBroker();
    }
    
            

    @Override
    protected void precondition(Object param) throws Exception {
         if (param == null || !(param instanceof Worker)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            Worker worker=(Worker) param;
            //   checkValueConstraints(user);
            checkStructuralConstraints(worker);
        }
        }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryWorker.add((Worker)param);
        return null;
       }

    private void checkStructuralConstraints(Worker worker) throws Exception {
         boolean exists = checkIfExists(worker);
        if (exists) {
            throw new Exception("Korisnicko ime je u upotrebi. Pokusajte ponovo.");
        }
            }

    private boolean checkIfExists(Worker worker) throws Exception {
         try {
            return repositoryWorker.checkIfExists(worker);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere postojanja radnika u bazi.", e);
        }
           }
    
}
