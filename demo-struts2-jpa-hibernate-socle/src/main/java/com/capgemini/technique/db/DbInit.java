package com.capgemini.technique.db;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.technique.entitycleaner.EntityCleanerAspect;
import com.capgemini.technique.util.FileUtil;

/**
 * Classe d'initialisation de la base de donnee invoquee au lancement du serveur d'application.
 * 
 * @author Capgemini
 */
public class DbInit {
	
	Logger logger = LoggerFactory.getLogger(DbInit.class);
	
	/**
	 * Methode appelee au lancement du serveur d'application pour initialiser la base de donnees.<br/>
	 * Copie, si necessaire, les fichiers de la base de donnees dans le repertoire de l'utilisateur.
	 * 
	 * @throws IOException En cas d'erreur d'entree/sortie.
	 */
	public void init() throws IOException {
		
		logger.info("Initialisation de la base de donnees");

		// construction du repertoire destine à heberger la base de donnee
		String homeDirPath = System.getProperty("user.home");
		File dbDir = new File(homeDirPath + "/.demodb");
		
		// verification de la presence des fichiers de la base
		if (!checkDbDir(dbDir)) {
			// si les fichier ne sont pas present, initialisation de la base
			initDb(dbDir);
		} else {
			// si les fichiers sont dejà present, suppression des verrous le cas echeant
			checkLock(dbDir);
		}
	}

	/**
	 * Verifie la presence des fichiers necessaires à la base de donnees.
	 * 
	 * @param dbDir Le repertoire hebergeant la base de donnees.
	 * 
	 * @return Un booleen indiquant si les fichiers necessaires sont presents.
	 */
	private boolean checkDbDir(File dbDir) {
		boolean dbOK = false;
		
		// verification de la presence du repertoire
		if (dbDir.exists() && dbDir.isDirectory()) {
			// verification de la presence des fichiers
			File file1 = new File(dbDir.getPath() + "/demodb.properties");
			File file2 = new File(dbDir.getPath() + "/demodb.script");
			dbOK = file1.exists() && file2.exists();
		}

		return dbOK;
	}

	/**
	 * Initialisation de la base de donnee par copie des fichiers necessaires.
	 * 
	 * @param dbDir Le repertoire hebergeant la base de donnees.
	 * 
	 * @throws IOException En cas d'erreur d'entree/sortie.
	 */
	private void initDb(File dbDir) throws IOException {
		
		logger.info("Copie des fichiers de la base de donnees");

		// supression du repertoire s'il existe
		if (dbDir.exists()) {
			dbDir.delete();
		}

		// creation du repertoire
		dbDir.mkdir();

		// copie des fichiers à partir de ceux contenus dans le classpath
		FileUtil.copyResource("com/capgemini/demo/db/demodb.properties", 	dbDir.getPath() + "/demodb.properties");
		FileUtil.copyResource("com/capgemini/demo/db/demodb.script", 		dbDir.getPath() + "/demodb.script");
	}

	/**
	 * Supprime le cas echeant les verrous sur la base de donnees.
	 * 
	 * @param dbDir Le repertoire hebergeant la base de donnees.
	 */
	private void checkLock(File dbDir) {
		// fichier de verrou
		File lockFile = new File(dbDir.getPath() + "/demodb.lck");
		
		// supression du fichier verrou s'il existe
		if (lockFile.exists()) {
			lockFile.delete();
		}
	}
}
