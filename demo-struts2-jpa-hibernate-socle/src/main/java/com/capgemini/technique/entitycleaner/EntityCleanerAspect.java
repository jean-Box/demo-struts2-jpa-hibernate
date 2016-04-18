package com.capgemini.technique.entitycleaner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aspect permettant d'injecter le nettoyage d'entit� en sortie d'appel de service m�tier.
 * 
 * @author Capgemini
 */
@Aspect
public class EntityCleanerAspect {
	
	Logger logger = LoggerFactory.getLogger(EntityCleanerAspect.class);

	/**
	 * Aspect positionn� autour d'une m�thode de SM
	 * 
	 * @param pjp le point de jonction
	 * @return la valeur de retour de la m�thode de SM, nettoy�e si besoin
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
