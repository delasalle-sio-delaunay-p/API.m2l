/***
 * Application de suivi des r�servations de la Maison des Ligues de Lorraine
 * Th�me : d�veloppement et test des classes Salle, Reservation, Utilisateur et Passerelle
 * @author JM CARTRON
 * @version 1.0
 */
package reservations.classes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

//les import suivant n�cessitent d'ajouter au projet tous les composant du plugin (fourni) 
//httpcomponents-client-4.2.3 / lib  (ces plugins font partie �galement du SDK Android)
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

//les import suivant sont inclus dans le JDK
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Cette classe fait le lien entre les services web et l'application
 * Elle utilise le mod�le Jaxp pour parcourir le document XML
 * Ce mod�le fait partie du JDK (et �galement du SDK Android)
 */
public class Passerelle {

	/** Membres priv�s */
	
	// Adresse de l'h�bergeur Internet
	//private static String _adresseHebergeur = "http://xxxxxxxxxxxxxxxx/m.m2l/services/";
	// Adresse du localhost en cas d'ex�cution sur le poste de d�veloppement (projet de tests des classes)
	private static String _adresseHebergeur = "http://localhost/ws-php-iradukunda/m.m2l/services/";
	
	// Noms des services web d�j� trait�s par la passerelle
	private static String _urlConnecter = "Connecter.php";
	private static String _urlConsulterReservations = "ConsulterReservations.php";
	private static String _urlCreerUtilisateur = "CreerUtilisateur.php";
	
	// noms des services web pas encore trait�s par la passerelle (� d�velopper)
	private static String _urlConsulterSalles = "ConsulterSalles.php";
	private static String _urlAnnulerReservation = "AnnulerReservation.php";
	private static String _urlChangerDeMdp = "ChangerDeMdp.php";	
	private static String _urlConfirmerReservation = "ConfirmerReservation.php";	
	private static String _urlDemanderMdp = "DemanderMdp.php";	
	private static String _urlSupprimerUtilisateur = "SupprimerUtilisateur.php";
	private static String _urlTesterDigicodeBatiment = "TesterDigicodeBatiment.php";
	private static String _urlTesterDigicodeSalle = "TesterDigicodeSalle.php";
	
	/***
	 * Fonction priv�e statique pour obtenir un document XML � partir de l'URL d'un service web
	 * @author JM CARTRON
	 * @param urlDuServiceWeb
	 * @param parametresPostes
	 * @return document XML
	 */
	private static Document getDocumentXML(String urlDuServiceWeb, ArrayList<NameValuePair> parametresPostes)
	{
    	try
    	{	// cr�ation d'un DefaultHttpClient
			HttpClient unClientHttp = new DefaultHttpClient();
			
			// cr�ation d'une requ�te HTTP de type POST
			HttpPost uneRequeteHttp = new HttpPost(urlDuServiceWeb);
			uneRequeteHttp.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			// passage des param�tres � poster
			uneRequeteHttp.setEntity(new UrlEncodedFormEntity(parametresPostes, "UTF-8"));
			
			// ex�cution de la requ�te HTTP
			HttpResponse uneReponseHttp = unClientHttp.execute(uneRequeteHttp);
			
			// r�cup�ration de la r�ponse dans un flux en lecture (InputStream)
			InputStream unFluxEnEntree = uneReponseHttp.getEntity().getContent();
	
			// cr�ation d'une instance de DocumentBuilderFactory et DocumentBuilder
			DocumentBuilderFactory leDBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder leDB = leDBF.newDocumentBuilder();
	
			// cr�ation d'un nouveau document XML avec en argument le flux XML
			Document leDocument = leDB.parse(unFluxEnEntree);
			return leDocument;
    	}
    	catch (Exception ex)
    	{	return null;
		}	
	}
	
	/***
	 * M�thode de classe pour se connecter (service Connecter.php)
	 * @author Leilla
	 * @param nomUtilisateur
	 * @param mdpUtilisateur
	 * @return string
	 */
    public static String connecter(String nomUtilisateur, String mdpUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));    		
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConnecter;	
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }

    /***
     * M�thode de classe pour cr�er un utilisateur (service CreerUtilisateur.php)
     * @author Pierre
     * @param nomAdmin
     * @param mdpAdmin
     * @param unUtilisateur
     * @return string
     */
    public static String creerUtilisateur(String nomAdmin, String mdpAdmin, Utilisateur unUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nomAdmin", nomAdmin));
    		parametresPostes.add(new BasicNameValuePair("mdpAdmin", mdpAdmin));
    		parametresPostes.add(new BasicNameValuePair("name", unUtilisateur.getName()));
    		parametresPostes.add(new BasicNameValuePair("level", String.valueOf(unUtilisateur.getLevel())));
    		parametresPostes.add(new BasicNameValuePair("email", unUtilisateur.getEmail()));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlCreerUtilisateur;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }

    /***
     * M�thode de classe pour r�cup�rer les r�servations d'un utilisateur (service ConsulterReservations.php)
     * @author Pierre
     * @param unUtilisateur
     * @return string
     */
    public static String consulterReservations(Utilisateur unUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", unUtilisateur.getName()));
    		parametresPostes.add(new BasicNameValuePair("mdp", unUtilisateur.getPassword()));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConsulterReservations;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsMembres = leDocument.getElementsByTagName("reservation");
			/* Exemple de donn�es obtenues pour un utilisateur :
			    <reservation>
			      <id>1</id>
			      <timestamp>2014-09-11 22:20:54</timestamp>
			      <start_time>2014-10-01 09:00:00</start_time>
			      <end_time>2014-10-01 12:00:00</end_time>
			      <room_name>Multim�dia</room_name>
			      <status>4</status>
			      <digicode></digicode>
			    </reservation>
			 */
			String formatUS = "yyyy-MM-dd HH:mm:ss";
			
			// parcours de la liste des noeuds <reservation> et ajout dans l'objet unUtilisateur
			for (int i = 0 ; i <= listeNoeudsMembres.getLength()-1 ; i++)
			{	// cr�ation de l'�lement courant � chaque tour de boucle
				Element courant = (Element) listeNoeudsMembres.item(i);
				
				// lecture des balises int�rieures
				int id = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				Date timestamp = Outils.ConvertirEnDate(courant.getElementsByTagName("timestamp").item(0).getTextContent(), formatUS);
				Date start_time = Outils.ConvertirEnDate(courant.getElementsByTagName("start_time").item(0).getTextContent(), formatUS);
				Date end_time = Outils.ConvertirEnDate(courant.getElementsByTagName("end_time").item(0).getTextContent(), formatUS);
				String room_name = courant.getElementsByTagName("room_name").item(0).getTextContent();
				int status = Integer.parseInt(courant.getElementsByTagName("status").item(0).getTextContent());
				String digicode = courant.getElementsByTagName("digicode").item(0).getTextContent();
				
				// cr�e un objet Reservation
				Reservation uneReservation = new Reservation(id, timestamp, start_time, end_time, room_name, status, digicode);
				
				// ajoute la r�servation � l'objet unUtilisateur
				unUtilisateur.ajouteReservation(uneReservation);
			}

    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    /***
     * M�thode de classe pour cr�er annuler la r�servation (service AnnulerReservation.php)
     * @author Leilla
     * @param nomUtilisateur
     * @param mdpUtilisateur
     * @param numReservation
     * @return
     */
    public static String annulerReservation(String nomUtilisateur, String mdpUtilisateur, String numReservation)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur));
    		parametresPostes.add(new BasicNameValuePair("numreservation", numReservation));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlAnnulerReservation;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);
    		
    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);
    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }

    /***
     * M�thode de classe pour r�cup�rer les salles (service ConsulterSalles.php)
     * @author Leilla
     * @param unUtilisateur
     * @return string
     */
    public static String consulterSalles(Utilisateur unUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", unUtilisateur.getName()));
    		parametresPostes.add(new BasicNameValuePair("mdp", unUtilisateur.getPassword()));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConsulterSalles;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
			NodeList listeNoeudsMembres = leDocument.getElementsByTagName("salle");
			/* Exemple de donn�es obtenues pour une salle :
			    <salle>
			      <id>5</id>
			      <room_name>Multim�dia</room_name >
			      <capacity>25</capacity >
			      <area_name>Informatique - multim�dia</area_name >
			      <area_admin_email>chemin.lorette@lorraine-sport.net</area_admin_email >
			    </salle>

			 */
			String formatUS = "yyyy-MM-dd HH:mm:ss";
			
			// parcours de la liste des noeuds <salle>
			for (int i = 0 ; i <= listeNoeudsMembres.getLength()-1 ; i++)
			{	// cr�ation de l'�lement courant � chaque tour de boucle
				Element courant = (Element) listeNoeudsMembres.item(i);
				
				// lecture des balises int�rieures
				int id = Integer.parseInt(courant.getElementsByTagName("id").item(0).getTextContent());
				String room_name = courant.getElementsByTagName("room_name").item(0).getTextContent();
				int capacity = Integer.parseInt(courant.getElementsByTagName("capacity").item(0).getTextContent());
				String area_name = courant.getElementsByTagName("area_name").item(0).getTextContent();
				
				// cr�e un objet Salle
				Salle uneSalle = new Salle(id, room_name, capacity, area_name);
				
			}

    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}
    }
    
    /***
     * M�thode de classe pour confirmer une r�servation (service ConfimerReservation.php)
     * @author Pierre
     * @param nomUtilisateur
     * @param mdpUtilisateur
     * @param numReservation
     * @return string
     */
    public static String confirmerReservation(String nomUtilisateur, String mdpUtilisateur, String numReservation)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur ));
    		parametresPostes.add(new BasicNameValuePair("mdp", mdpUtilisateur ));
    		parametresPostes.add(new BasicNameValuePair("numreservation", numReservation ));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlConfirmerReservation;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		

    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}    	
    }
    
    /***
     * M�thode de classe pour changer de mot de passe (service ChangerDeMdp.php)
     * @author Pierre
     * @param nomUtilisateur
     * @param ancienMdp
     * @param nouveauMdp
     * @param confirmationMdp
     * @return string
     */
    public static String changerDeMdp(String nomUtilisateur, String ancienMdp, String nouveauMdp, String confirmationMdp)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur ));
    		parametresPostes.add(new BasicNameValuePair("ancienMdp", ancienMdp ));
    		parametresPostes.add(new BasicNameValuePair("nouveauMdp", nouveauMdp ));
    		parametresPostes.add(new BasicNameValuePair("confirmationMdp", confirmationMdp ));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlChangerDeMdp;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}      	
    	
    }
    
    /***
     * M�thode de classe pour demander un nouveau mot de passe (service DemanderMdp.php)
     * @author Pierre
     * @param nomUtilisateur
     * @return string
     */
    public static String demanderMdp(String nomUtilisateur)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nom", nomUtilisateur ));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlDemanderMdp;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}       	
    }
    
    /***
     * M�thode de classe pour supprimer un utilisateur (service SupprimerUtilisateur.php)
     * @author Pierre
     * @param nomAdmin
     * @param mdpAdmin
     * @param name
     * @return string
     */
    public static String supprimerUtilisateur(String nomAdmin, String mdpAdmin, String name)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("nomAdmin", nomAdmin ));
    		parametresPostes.add(new BasicNameValuePair("mdpAdmin", mdpAdmin ));
    		parametresPostes.add(new BasicNameValuePair("name", name ));
    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlSupprimerUtilisateur;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}      	
    	
    }
    
    /***
     * M�thode de classe pour tester un digicode (service TesterDigicodeBatiment.php)
     * @author Pierre
     * @param digicode
     * @return string
     */
    public static String testerDigicodeBatiment(String digicode)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("digicode", digicode ));

    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlTesterDigicodeBatiment;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}      	
    	
    }
    
    /***
     * M�thode de classe pour tester un digicode correspondant � une salle (service TesterDigicodeSalle.php)
     * @author Pierre
     * @param numSalle
     * @param digicode
     * @return string
     */
    public static String testerDigicodeSalle(String numSalle, String digicode)
    {
    	String reponse = "";
    	try
    	{	// pr�paration des param�tres � poster vers le service web
    		ArrayList<NameValuePair> parametresPostes = new ArrayList<NameValuePair>();
    		parametresPostes.add(new BasicNameValuePair("numSalle", numSalle ));
    		parametresPostes.add(new BasicNameValuePair("digicode", digicode ));

    		
    		// cr�ation d'un nouveau document XML � partir de l'URL du service web et des param�tres
    		String urlDuServiceWeb = _adresseHebergeur + _urlTesterDigicodeSalle;
    		Document leDocument = getDocumentXML(urlDuServiceWeb, parametresPostes);

    		// parsing du flux XML
    		Element racine = (Element) leDocument.getElementsByTagName("data").item(0);

    		reponse = racine.getElementsByTagName("reponse").item(0).getTextContent();
    		
    		// retour de la r�ponse du service web
    		return reponse;
    	}
    	catch (Exception ex)
    	{	String msg = "Erreur : " + ex.getMessage();
			return msg;
		}      	
    	
    }
}
