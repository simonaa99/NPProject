/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.worker;

import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class RemoveWorkerSO extends AbstractSO{
    private RepositoryWorker repositoryWorker;

    public RemoveWorkerSO() {
        repositoryWorker=new RepositoryWorker();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
         if (param == null || !(param instanceof Worker)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
             Worker work=(Worker) param;
            checkValueConstraints(work);
        }
         }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryWorker.delete((Worker)param);
        return null;
          }

    private void checkValueConstraints(Worker work) throws Exception {
         if (work.getId().equals(null)) {
            throw new Exception("Id radnika nije poslat!");
        }
            }
    
}
