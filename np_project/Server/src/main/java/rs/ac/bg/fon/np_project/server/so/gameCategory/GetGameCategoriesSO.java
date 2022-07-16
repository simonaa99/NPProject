/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.gameCategory;

import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;

/**
 * Predstavlja klasu u kojoj se izvrsavaju metode za vracanje svih kategorija drustvenih
 * igara iz baze. Sadrzi implementaciju metoda precondition i executeOperation iz
 * nadklase AbstractSO.
 * 
 * @author Simona
 * @version 1.0.0
 */
public class GetGameCategoriesSO extends AbstractSO{

	/**
	 * Metoda ne proverava nikakve uslove.
	 */
    @Override
    protected void precondition(Object param) throws Exception {
        //no precondition to check
            }

    /**
     * Metoda vraca sve kategorije drustvenih igara iz baze. Ako dodje do 
     * greske baca Exception.
     * 
     * @return sve kategorije igri iz baze
     */
    @Override
    protected Object executeOperation(Object param) throws Exception {
          return GameCategory.values(); }
    
}
