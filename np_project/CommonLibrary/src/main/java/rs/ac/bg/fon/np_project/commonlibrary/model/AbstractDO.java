/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

/**
 *Predstavlja apstraktnu klasu cije metode treba da implementiraju sve ostale domenske klase
 *Sadrzi metode koje su neophodne za rad sa bazom 
 *
 * @author Simona
 * @version 1.0.0
 */
public abstract class AbstractDO {
    /**
     * Apstraktna metoda koja vraca string svih atributa klase koja implementira ovu metodu
     * 
     * @return nazive atributa klase kao String
     * */
    public abstract String getAttributeList();
    
    /**
     * Apstraktna metoda koja vraca naziv klase koja implementira ovu metodu
     * 
     * @return naziv klase kao String
     * */
    public abstract String getClassName();
    
    /**
     * Apstraktna metoda koja vraca vrednosti svih atributa klase koja implementira ovu metodu
     * 
     * @return vrednosti atributa klase kao String
     * */
    public abstract String getAttributeValues();
    
    /**
     * Apstraktna metoda koja vraca uslov koji se koristi pri kreiranju
     * upita u bazi za klasu koja implementira ovu metodu
     * 
     * @return uslov za upit kao String
     * */
    public abstract String getQueryCondition();
    
}
