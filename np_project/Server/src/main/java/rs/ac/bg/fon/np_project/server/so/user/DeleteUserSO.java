/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.user;

import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import repository.impl.RepositoryUser;
import repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 *
 * @author Simona
 */
public class DeleteUserSO extends AbstractSO {

    RepositoryUser repositoryUser;
    RepositoryUserCard repositoryUserCard;

    public DeleteUserSO() {
        repositoryUser = new RepositoryUser();
        repositoryUserCard = new RepositoryUserCard();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            User user = (User) param;
            checkValueConstraints(user);
            checkStructuralConstraints(user);
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        User user = (User) param;
        try {
            repositoryUser.delete(user);
            repositoryUserCard.delete(user.getUsercard());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom brisanja korisnika." + e.getMessage(), e);
        }
        
    }

    private void checkStructuralConstraints(User user) throws Exception {
        checkRentsExist(user);
    }

    private void checkValueConstraints(User user) throws Exception {
        if (user.getUserId().equals(null)) {
            throw new Exception("Id korisnika nije poslat!");
        }
    }

    private void checkRentsExist(User user) throws Exception {
        if (repositoryUser.checkIfRentsExist(user)) {
            throw new Exception("Korisnik je zaduzio igru. Nije moguce dovrsiti operaciju brisanja.");
        }
    }
}
