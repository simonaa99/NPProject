/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.rent;

import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class GetAllUserRentsSO extends AbstractSO {

    RepositoryRent repositoryRent;

    public GetAllUserRentsSO() {
        repositoryRent = new RepositoryRent();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Poslat je parametar neodgovarajuceg tipa!");
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<Rent> rents = repositoryRent.getAllUserRents((User) param);
        return rents;
    }

}
