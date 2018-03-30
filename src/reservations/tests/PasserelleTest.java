package reservations.tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import reservations.classes.Passerelle;
import reservations.classes.Reservation;
import reservations.classes.Utilisateur;

public class PasserelleTest {

//	@Test
//	public void testConnecter() {
//		String msg = Passerelle.connecter("admin", "adminnnnnnnn");
//		assertEquals("Test Passerelle.connecter", "Erreur : authentification incorrecte.", msg);
//		
//		msg = Passerelle.connecter("admin", "admin");
//		assertEquals("Test Passerelle.connecter", "Administrateur authentifi�.", msg);
//		
//		msg = Passerelle.connecter("giboired", "passe");
//		assertEquals("Test Passerelle.connecter", "Utilisateur authentifi�.", msg);	
//	}

//	@Test
//	public void testCreerUtilisateur() {
//		Utilisateur unUtilisateur = new Utilisateur(0, 4, "yvesz", "", "yves.zenels@gmail.com");
//		String msg = Passerelle.creerUtilisateur("admin", "adminnnnnnnn", unUtilisateur);
//		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : le niveau doit �tre 0, 1 ou 2.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
//		msg = Passerelle.creerUtilisateur("admin", "adminnnnnnnn", unUtilisateur);
//		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : authentification incorrecte.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
//		msg = Passerelle.creerUtilisateur("admin", "admin", unUtilisateur);
//		assertEquals("Test Passerelle.creerUtilisateur", "Enregistrement effectu�.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
//		msg = Passerelle.creerUtilisateur("admin", "admin", unUtilisateur);
//		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : nom d'utilisateur d�j� existant.", msg);	
//	}

//	private static String FormaterDateHeure(Date uneDate, String unFormat) {
//		SimpleDateFormat leFormat = new SimpleDateFormat(unFormat);
//		return leFormat.format(uneDate);
//	}
	
//	@Test
//	public void testConsulterReservations() {
//		Utilisateur unUtilisateur = new Utilisateur(0, 0, "giboired", "passeeeeeeeeeee", "");
//		String msg = Passerelle.consulterReservations(unUtilisateur);
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 0, "guilletm", "passe", "");
//		msg = Passerelle.consulterReservations(unUtilisateur);
//		assertEquals("Erreur : vous n'avez aucune r�servation.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 0, "giboired", "passe", "");
//		msg = Passerelle.consulterReservations(unUtilisateur);
//		assertEquals("Vous avez effectu� 2 r�servation(s).", msg);
//		assertEquals(2, unUtilisateur.getNbReservations());
//		
//		String formatUS = "yyyy-MM-dd HH:mm:ss";
//		Reservation laReservation = unUtilisateur.getLaReservation(0);
//		assertEquals("Amphith��tre", laReservation.getRoomName());		
//		assertEquals(0, laReservation.getStatus());	
//		assertEquals("2018-05-30 18:00:00", FormaterDateHeure(laReservation.getStartTime(), formatUS));
//		assertEquals("2018-05-30 23:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
//		
//		laReservation = unUtilisateur.getLaReservation(1);
//		assertEquals("Hall d'accueil", laReservation.getRoomName());		
//		assertEquals(4, laReservation.getStatus());	
//		assertEquals("2018-05-30 18:00:00", FormaterDateHeure(laReservation.getStartTime(), formatUS));
//		assertEquals("2018-05-30 23:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
//	}
	
//	@Test
//	public void testConsulterSalles()
//	{
//		Utilisateur unUtilisateur = new Utilisateur(0, 0, "giboired", "passeeeeeeeeeee", "");
//		String msg = Passerelle.consulterSalles(unUtilisateur);
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		unUtilisateur = new Utilisateur(0, 0, "guilletm", "passe", "");
//		msg = Passerelle.consulterSalles(unUtilisateur);
//		assertEquals("Il y a 14 salles(s) de disponible en r�servation.", msg);		
//	}
	
//	@Test
//	public void testConfirmerReservation()
//	{
//		// V�rification de l'auteur
//		String msg = Passerelle.confirmerReservation("admin", "admin", "33");
//		assertEquals("Erreur : vous n'�tes pas l'auteur de cette r�servation.", msg);		
//	
//		// V�rification : la confirmation s'est bien pass�e
//		msg = Passerelle.confirmerReservation("admin", "admin", "38");
//		assertEquals("Enregistrement effectu� : vous allez recevoir un mail de confirmation.", msg);		
//		
//		// V�rification : num�ro de r�sevration inexistant
//		msg = Passerelle.confirmerReservation("admin", "admin", "");
//		assertEquals("Erreur : num�ro de r�servation inexistant.", msg);
//		
//		// V�rification : r�servation d�j� confirm�e
//		msg = Passerelle.confirmerReservation("admin", "admin", "38");
//		assertEquals("Erreur : cette r�servation est d�j� confirm�e.", msg);			
//		
//		// V�rification : r�servation d�j� pass�e
//		msg = Passerelle.confirmerReservation("admin", "admin", "38");
//		assertEquals("Erreur : cette r�servation est d�j� confirm�e.", msg);		
//			
//	}
		
//	@Test
//	public void testChangerMdp()
//	{
//		
//		
//		
//	}
	
//	@Test
//	public void testDemanderMdp()
//	{
//		// V�rifications : nom incorrect
//		String msg = Passerelle.demanderMdp("adminzzz");
//		assertEquals("Erreur : nom d'utilisateur inexistant.", msg);
//		
//		// V�rification : nom valide
//		msg = Passerelle.demanderMdp("giboired");
//		assertEquals("Vous allez recevoir un mail avec votre nouveau mot de passe.", msg);		
//		
//	}
	
//	@Test
//	public void testSupprimerUtilisateur()
//	{
//		// V�rification : authentification incorrecte
//		String msg = Passerelle.supprimerUtilisateur("admin", "abcd", "giboired");
//		assertEquals("Erreur : authentification incorrecte.", msg);
//		
//		// V�rification : nom d'utilisateur inexistant
//		msg = Passerelle.supprimerUtilisateur("admin", "admin", "giboiredzzz");
//		assertEquals("Erreur : nom d'utilisateur inexistant.", msg);
//		
//		// V�rification : utilisateur avec des r�servation � venir
//		msg = Passerelle.supprimerUtilisateur("admin", "admin", "giboired");
//		assertEquals("Erreur : cet utilisateur a pass� des r�servations � venir.", msg);	
//		
//		// V�rification : suppression r�ussie avec envoi de mail
//		 msg = Passerelle.supprimerUtilisateur("admin", "admin", "testuser2");
//		assertEquals("Suppression  effectu�e ; un mail va �tre envoy� � l'utilisateur.", msg);		
//		
//	}
	
//	@Test
//	public void testTesterDigicodeBatiment()
//	{
//		// V�rification : digicode incorrect
//		String msg = Passerelle.testerDigicodeBatiment("123ABC");
//		assertEquals("0", msg);		
//		
//		// V�rification : digicode correct
//		msg = Passerelle.testerDigicodeBatiment("D36711");
//		assertEquals("1", msg);	
//	}
	
//	@Test
//	public void testTesterDigicodeSalle()
//	{
//		// V�rification : digicode incorrect
//		String msg = Passerelle.testerDigicodeSalle("35","123ABC");
//		assertEquals("0", msg);		
//		
//		// V�rification : digicode correct
//		msg = Passerelle.testerDigicodeSalle("5","18963A");
//		assertEquals("1", msg);		
//	}
		
}
