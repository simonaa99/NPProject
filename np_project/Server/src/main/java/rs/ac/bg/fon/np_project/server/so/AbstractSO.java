/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so;

import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;

/**
 * Predstavlja apstraktnu klasu cije metode nasledjuju ostale klase za sistemske operacije.
 * Sadrzi metode: precondition, executeOperation, startTransaction, execute, commitTransaction i
 * rollbackTransaction. Poslednje tri metode su u ovoj klasi i implementirane.
 * 
 * @author Simona
 * @version 1.0.0
 */
public abstract class AbstractSO {

	/**
     * Metoda koja obradjuje ulazni objekat tako sto izvrsava metode precondition, startTransaction,
     * executeOperation i commitTransaction i vraca taj objekat (u slucaju da ne dodje do izuzetka). 
     * Metoda baca exception ako dodje do greske i izvrsava metodu rollbackTransaction.
     * 
     * @param param tipa Object koji treba da se obradi u metodi
     * @return obradjen objekat kao Object
     * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja
     * */
    public Object execute(Object param) throws Exception {
        try {
            precondition(param);
            startTransaction();
            Object returnParam = executeOperation(param);
            comitTransaction();
            return returnParam;
        } catch (Exception exception) {
            rollbackTransaction();
            throw exception;
        }

    }

    /**
     * Apstraktna metoda koja proverava da li objekat ispunjava odredjene uslove
     * kako bi mogao biti obradjen.
     * 
     * @param param tipa Object za koji treba proveriti da li ispunjava uslove
     * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja i vraca
     * poruku "Poslati objekat je neodgovarajuceg tipa!"
     * */
    protected abstract void precondition(Object param) throws Exception;

    
    /**
     * Apstraktna metoda koja izvrsava odredjenu operaciju nad ulaznim objektom.
     * 
     * @param param tipa Object nad kojim treba da se izvrsi odredjena operacija
     * @return  Object objekat nad kojim se izvrsila odredjena operacija
     * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja i vraca
     * odgovarajucu poruku
     * */
    protected abstract Object executeOperation(Object param) throws Exception;

    /**
     * Metoda koja pokrece transakciju.
     * 
     * */
    private void startTransaction() {
    }

    /**
     * Metoda koja izvrsava transakciju u bazi.
     * 
     * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja 
     * */
    protected void comitTransaction() throws Exception {
        DbConnectionFactory.getInstance().getConnection().commit();

    }
    
    /**
     * Metoda koja ponistava transakciju u bazi.
     * 
     * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja 
     * */
    protected void rollbackTransaction() throws Exception {
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
