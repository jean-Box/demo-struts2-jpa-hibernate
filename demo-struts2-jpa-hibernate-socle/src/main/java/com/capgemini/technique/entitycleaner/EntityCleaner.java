package com.capgemini.technique.entitycleaner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.hibernate.collection.PersistentSet;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

/**
 * Classe permettant de nettoyer les objets persistants Hibernate.
 * 
 * Adaptation de l'EntityPruner de Steven C. Saliman (http://code.google.com/p/entity-pruner/).
 * 
 * @author Steven C. Saliman
 * @author Erwan Fouret
 */
public class EntityCleaner {
	/**
	 * Nettoie l'entit� pas�e en param�tre
	 * 
	 * @param entity l'entit� � nettoyer
	 * @throws IllegalStateException s'il y a un probl�me
	 */
	public static <T> T clean(T entity) {
		
		if (entity == null) {
			return null;
		}
		
		if (isEntity(entity.getClass())) {
			return cleanEntity(entity, new HashSet<Object>());
		
		} else {
			return cleanContainer(entity, new HashSet<Object>());
		}
	}

	/**
	 * Nettoie un conteneur (Bean, Collection, ...)
	 * 
	 * @param container le conteneur
	 * @param cleaned set des objets d�j� nettoy�s
	 * @return le conteneur nettoy�
	 */
	@SuppressWarnings("unchecked")
	private static <T> T cleanContainer(T container, Set<Object> cleaned) {
		T retour = container;
		Class<?> containerClass = container.getClass();
		
		String msg = "Erreur en nettoyant le conteneur " + container + ": ";
		try {
			if (!cleaned.contains(container)) {
	
				cleaned.add(container);
				
				if (container instanceof String || container instanceof Integer) {
					// on ne fait rien
				
				// Collection
				} else if (container instanceof Collection<?>) {
					Collection<?> collection = (Collection<?>) container;
					Class<?> classeContenue = getContainedClass(containerClass);
	
					Collection newCollection = (Collection<?>) containerClass.newInstance();
	
					for (Object object : collection) {
						Object obj = clean(classeContenue.cast(object));
						newCollection.add(obj);
					}
					
					retour = (T) newCollection;
					
				// Bean
				} else {
					List<Field> fields = loadFields(containerClass);
					for (Field field : fields) {
						field.setAccessible(true);
						Object value = getValue(field, container);
						Object newValue = value;
						if (value != null) {
							if (isEntity(value.getClass())) {
								newValue = cleanEntity(value, cleaned);
								
							} else {
								newValue = cleanContainer(value, cleaned);
							}
						}
						field.set(container, newValue);
					}
				}
			}
			
		} catch (InstantiationException e) {
			msg = msg + e.getMessage();
			throw new IllegalStateException(msg, e);
		} catch (IllegalAccessException e) {
			msg = msg + e.getMessage();
			throw new IllegalStateException(msg, e);
		} catch (InvocationTargetException e) {
			msg = msg + e.getMessage();
			throw new IllegalStateException(msg, e);
		}

		return retour;
	}
	
	/**
	 * Retourne la classe g�r�e par une collection typ�e
	 * 
	 * @param containerClass la classe de la collection
	 * @return la classe param�tr�e dans la collection
	 */
	private static Class<?> getContainedClass(Class<?> containerClass) {
		return (Class<?>) containerClass.getTypeParameters()[0].getBounds()[0];
	}

	/**
	 * Nettoyage d'une entit� persistante.
	 * 
	 * L'entit� est nettoy�e en profondeur et d�proxifi�e.
	 * 
	 * @param entity l'entit� � nettoyer
	 * @param cleaned les objets d�j� nettoy�s
	 * @return l'objet nettoy�
	 */
	private static <T> T cleanEntity(T entity, Set<Object> cleaned) {
		
		Hibernate.initialize(entity);
		T entityDeproxied = deproxy(entity);
		
		if (entityDeproxied != null && !cleaned.contains(entityDeproxied)) {
			cleanEntityRec(entityDeproxied, cleaned);
		}

		cleaned.add(entity);
		cleaned.add(entityDeproxied);
		
		return entityDeproxied;
	}
	
	/**
	 * Nettoyage d'une entit�, m�thode r�cursive.
	 * 
	 * @param entity l'entit� � nettoyer
	 * @param cleaned l'ensemble des entit�s d�j� nettoy�es
	 */
	private static void cleanEntityRec(Object entity, Set<Object> cleaned) {
		if (entity == null || cleaned.contains(entity)) {
			return;
		}
		cleaned.add(entity);

		String msg = "Erreur en nettoyant " + entity + ": ";
		try {
			List<Field> fields = loadFields(entity.getClass());
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = getValue(field, entity);
				if (value != null) {
					if (isEntity(value.getClass())) {
						value = deproxy(value);
						field.set(entity, value);
						cleanEntityRec(value, cleaned);

					} else if (Collection.class.isAssignableFrom(field.getType())) {
						cleanCollection(entity, (Collection<?>) value, field, cleaned);
					}
				}
			}
		} catch (IllegalAccessException e) {
			msg = msg + e.getMessage();
			throw new IllegalStateException(msg, e);
		} catch (InvocationTargetException e) {
			msg = msg + e.getMessage();
			throw new IllegalStateException(msg, e);
		} catch (SecurityException e) {
			msg = msg + e.getMessage();
			throw new IllegalStateException(msg, e);
		} catch (IllegalArgumentException e) {
			msg = msg + e.getMessage();
			throw new IllegalStateException(msg, e);
		}
	}

	/**
	 * Retourne tous les champs de la classe et de ses classes m�res.
	 * 
	 * @param clazz La classe � analyser
	 * @return la liste des champs d�clar�s par la classe et ses classes m�res
	 */
	private static List<Field> loadFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		
		if (clazz.getSuperclass() != null) {
			fields.addAll(loadFields(clazz.getSuperclass()));
		}
		
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

		return fields;
	}

	/**
	 * M�thode qui nettoie une collection d'une entit�
	 * 
	 * @param entity l'entit� contenant la collection
	 * @param collection la collection
	 * @param field le champ correspondant � la collection
	 * @param cleaned l'ensemble des objets d�j� nettoy�s
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static <T> void cleanCollection(Object entity, Collection<T> collection, Field field, Set<Object> cleaned) throws IllegalAccessException, InvocationTargetException {
		
		Collection<T> newValue = null;

		if (PersistentSet.class.isAssignableFrom(collection.getClass())) {
			if (((PersistentSet) collection).wasInitialized()) {
				newValue = new HashSet<T>();
				newValue.addAll(collection);
			}
			
		} else {
			newValue = collection;
		}
		
		if (newValue != null) {
			for (Object child : collection) {
				if (isEntity(child.getClass())) {					
					cleanEntityRec(child, cleaned);
				}
			}
		}
		
		setValue(field, entity, newValue);
	}


	/**
	 * Retourne la valeur d'un champ, par getter si possible, par la valeur du champ sinon
	 * 
	 * @param field le champ
	 * @param entity l'entit�
	 * @return la valeur du champ
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object getValue(Field field, Object entity) throws IllegalAccessException, InvocationTargetException {
		Object value = null;
		
		try {
			value = PropertyUtils.getProperty(entity, field.getName());
			
		} catch (NoSuchMethodException e) {
			value = field.get(entity);
		}
		
		return value;
	}

	/**
	 * Positionne la valeur d'un champ, par setter si possible, par le champ directement sinon
	 * 
	 * @param field le champ
	 * @param entity l'entit�
	 * @param value la valeur
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void setValue(Field field, Object entity, Object value) throws IllegalAccessException, InvocationTargetException {
		try {
			PropertyUtils.setProperty(entity, field.getName(), value);
			
		} catch (NoSuchMethodException e) {
			field.set(entity, value);
		}
	}

	/**
	 * Transforme un proxy Hibernate en un objet non proxy.
	 * 
	 * @param maybeProxy le proxy potentiel
	 * @return le proxy "d�proxifi�"
	 * @throws ClassCastException
	 */
	@SuppressWarnings("unchecked")
	private static <T> T deproxy(T maybeProxy) throws ClassCastException {
		if (maybeProxy instanceof HibernateProxy) {
			HibernateProxy proxy = (HibernateProxy) maybeProxy;
			LazyInitializer li = proxy.getHibernateLazyInitializer();
			if (li.isUninitialized()) {
				return null;
			} else {
				return (T) li.getImplementation();
			}
		}
		return maybeProxy;
	}

	/**
	 * Indique si une classe correspond � une entit� persistante
	 * 
	 * @param classe la classe � tester
	 * @return true si la classe est une entit� persistante
	 */
	private static boolean isEntity(Class<?> classe) {

		boolean isEntity = false;

		List<Annotation> annotations = loadAnnotations(classe);
		for (Annotation annotation : annotations) {
			if (Entity.class.isAssignableFrom(annotation.annotationType())) {
				isEntity = true;
				break;
			}
		}

		return isEntity;
	}
	
	/**
	 * Chargement des annotations d'une classe et de sa hi�rarchie
	 * 
	 * @param classe la classe
	 * @return la liste des annotations de classe
	 */
	private static List<Annotation> loadAnnotations(Class<?> classe) {
		List<Annotation> annotations = new ArrayList<Annotation>();
		if (classe.getSuperclass() != null) {
			annotations.addAll(loadAnnotations(classe.getSuperclass()));
		}
		Annotation[] annotationsClasse = classe.getDeclaredAnnotations();
		annotations.addAll(Arrays.asList(annotationsClasse));
		return annotations;
	}
}
