package com.capgemini.presentation.struts.interceptor;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.capgemini.presentation.struts.action.ParentAction;
import com.capgemini.technique.erreur.MetierException;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Intercepteur pour les erreurs technique et métier.
 */
public class ExceptionInterceptor extends AbstractInterceptor {

	/**
	 * le loggueur.
	 */
	protected static final Log log = LogFactory.getLog(ExceptionInterceptor.class);
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Interception.
	 * 
	 * @see AbstractInterceptor#intercept(ActionInvocation)
	 * 
	 * @param invocation l'invocation
	 * @throws Exception en cas d'échec de l'action
	 * @return résultat de l'invocation
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		String result;

		try {
			result = invocation.invoke();

		} catch (final MetierException exceptionMetier) {
			result = traiterErreurMetier(invocation, exceptionMetier);

		} catch (final Throwable t) {
			result = traiterErreurTechnique(invocation, t);
		}

		return result;
	}

	/**
	 * Méthode de traitement de les erreurs métier.
	 * 
	 * @param invocation
	 *            ActionInvocation
	 * @param exceptionMetier
	 *            l'exeption métier
	 * @return retour struts
	 */
	private String traiterErreurMetier(final ActionInvocation invocation, final MetierException exceptionMetier) {
		
		log.debug(exceptionMetier);

		String result = ParentAction.AFFICHER;
		
		final Object action = invocation.getAction();
		if (action instanceof ParentAction) {
			final ParentAction parenAction = (ParentAction) action;
			result = parenAction.traiterErreursMetier(exceptionMetier.getErreursMetier());
		}

		return result;
	}

	/**
	 * Méthode de traitement de l'erreur technique.
	 * 
	 * @param invocation
	 *            ActionInvocation
	 * @param t
	 *            Throwable
	 * @return retour struts
	 */
	private String traiterErreurTechnique(final ActionInvocation invocation, final Throwable t) {
		
		log.error("Erreur technique lors de l'appel a l'action " + invocation.getAction().getClass().getName(), t);

		invocation.getStack().push(new ErreurTechniqueObject(t));

		return ParentAction.ERREUR;
	}

	/**
	 * Classe locale qui représente l'objet de à l'erreur technique.
	 */
	public class ErreurTechniqueObject implements Serializable {

		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;
		
		Throwable throwable;
		
		
		public ErreurTechniqueObject(Throwable t) {
			throwable = t;
		}

		public Throwable getThrowable() {
			return throwable;
		}

		public void setThrowable(Throwable throwable) {
			this.throwable = throwable;
		}

		/**
		 * getteur pour l'erreur technique.
		 * 
		 * @return true.
		 */
		public boolean getErreurTechnique() {
			return true;
		}
	}

}
