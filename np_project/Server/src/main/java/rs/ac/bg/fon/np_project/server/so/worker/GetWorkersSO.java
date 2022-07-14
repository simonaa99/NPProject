/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.worker;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetWorkersSO extends AbstractSO{
   private RepositoryWorker repositoryWorker;

    public GetWorkersSO() {
        repositoryWorker=new RepositoryWorker();
    }
   

    @Override
    protected void precondition(Object param) throws Exception {
        //no recondition to check
           }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        return repositoryWorker.getAll();
           }
    
}
