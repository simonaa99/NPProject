/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;

/**
 * Predstavlja enum koji sadrzi sve kategorije drustvenih igara.
 * Kategorije su: Porodicne_igre, Karticne_igre, Igre_na_srpskom,
 * Party_games, Igre_za_dvoje i Igre_za_decu.
 * 
 * @author Simona
 * @version 1.0.0
 */
public enum GameCategory implements Serializable{
    /**
     * Sve kategorije kojim neka drustvena igra moze pripadati.
     */
    Porodicne_igre,
    Karticne_igre,
    Igre_na_srpskom,
    Party_games,
    Igre_za_dvoje,
    Igre_za_decu
    
}
