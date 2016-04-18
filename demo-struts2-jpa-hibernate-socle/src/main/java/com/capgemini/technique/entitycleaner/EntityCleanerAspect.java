package com.capgemini.technique.entitycleaner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aspect permettant d'injecter le nettoyage d'entité en sortie d'appel de service métier.
 * 
 * @author Capgemini
 */
@Aspect
public class EntityCleanerAspect {
	
	Logger logger = LoggerFactory.getLogger(EntityCleanerAspect.class);

	/**
	 * Aspect positionné autour d'une méthode de SM
	 * 
	 * @param pjp le point de jonction
	 * @return la valeur de retour de la méthode de SM, nettoyée si besoin
	 * @throws Throwable en cas d'erreur
	 */
	@Around("execution(* com.capgemini.demo.sm.impl..* (..))")
	public Object execMethodeSM(ProceedingJoinPoint pjp) throws Throwable {
		Object value = null;
		
		try {
			
			logger.debug("EntityCleanerAspect.execMethodeSM()");
			
			value = pjp.proceed();
			value = EntityCleaner.clean(value);
			
		} catch (Throwable t) {
			throw t;
		}
		
		return value;
	}
}
