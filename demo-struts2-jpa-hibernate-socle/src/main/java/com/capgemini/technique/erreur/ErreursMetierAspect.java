package com.capgemini.technique.erreur;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.metier.SM;

@Aspect
public class ErreursMetierAspect {
	
	Logger logger = LoggerFactory.getLogger(ErreursMetierAspect.class);
	
	@Around("execution(* com.capgemini.demo.sm.impl..* (..))")
	public Object execMethodeSM(ProceedingJoinPoint pjp) throws Throwable {
		Object value = null;
		
		logger.debug("ErreursMetierAspect.execMethodeSM()");
		
		final SM sm = (SM) pjp.getTarget();
		
		try {
			sm.nettoyerErreursMetier();
			value = pjp.proceed();
			sm.remonterErreursMetier();
			
		} catch (final Throwable t) {
			throw t;
		}

		return value;
	}
}
