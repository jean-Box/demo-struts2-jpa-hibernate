package com.capgemini.technique.util;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe utilitaire pour travailler sur les objets.
 * 
 * @author efouret
 */
public class ObjectUtil {
	
	/**
	 * Methode indiquant si la valeur est renseignee : 
	 *      - la valeur n'est pas nulle
	 *      - si la valeur est une String, elle n'est pas vide (apres trim)
	 *  - si la valeur est un Integer, elle n'est pas à 0
	 *  - si la valeur est une List, elle ne doit pas avoir une size a 0
	 *  - si la valeur est une Map, elle ne doit pas avoir une size a 0
	 * @param obj l'object a tester
	 * @return true si la valeur est non nulle
	 */
	public static boolean estRenseigne(Object obj) {
	    boolean estRenseigne = false;
	                    
	    if (obj != null) {
            estRenseigne = true;

            if (obj instanceof String) {
                    String stringObject = (String) obj;

                    estRenseigne = !"".equals(stringObject.trim());

            } else if (obj instanceof Integer) {
                    Integer intObject = (Integer) obj;

                    estRenseigne = intObject.intValue() != 0;
                    
            } else if (obj instanceof Long) {
                    Long longObject = (Long) obj;

                    estRenseigne = longObject.longValue() != 0;
                    
            } else if (obj instanceof List<?>) {
                    List<?> listObject = (List<?>) obj;

                    estRenseigne = !listObject.isEmpty();
            } else if (obj instanceof String[]) {
                    String[] listObject = (String[]) obj;
                    if (listObject.length == 0) {
                     estRenseigne = false;
                     } else {
                             estRenseigne = true;
                     }
            } else if (obj instanceof Map<?, ?>) {
                    Map<?, ?> mapObject = (Map<?, ?>) obj;

                    estRenseigne = !mapObject.isEmpty();
                    
            } else if (obj instanceof Set<?>) {
                    Set<?> setObject = (Set<?>) obj;

                    estRenseigne = !setObject.isEmpty();
                    
            } else if (obj instanceof Date) {
                    Date dateObject = (Date) obj;

                    estRenseigne = !"".equals(dateObject.toString().trim());
            }
        }
                
        return estRenseigne;
    }
}
