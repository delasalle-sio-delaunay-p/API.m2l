/***
 * Application de suivi des réservations de la Maison des Ligues de Lorraine
 * Thème : développement et test des classes Salle, Reservation, Utilisateur et Passerelle
 * @author Pierre
 * @version 1.0
 */
package reservations.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import reservations.classes.Salle;

public class SalleTest {

	private Salle uneSalle;
	
	@Before
	public void setUp() {
		int unId = 5;
		String unRoomName = "Multimédia";
		int unCapacity = 25;
		String unAreaName = "Informatique - multimédia";
		uneSalle = new Salle(unId, unRoomName, unCapacity, unAreaName);
	}

	@Test
	public void testGetId() {
		assertEquals("Test getId", 5, uneSalle.getId());
		uneSalle.setId(20);
		assertEquals("Test setId", 20, uneSalle.getId());
	}

	@Test
	public void testGetRoomName() {
		assertEquals("Test getRoomName", "Multimédia", uneSalle.getRoomName());
		uneSalle.setRoomName("Salle informatique");
		assertEquals("Test setRoomName", "Salle informatique", uneSalle.getRoomName());
	}

	@Test
	public void testGetCapacity() {
		assertEquals("Test getCapacity", 25, uneSalle.getCapacity());
		uneSalle.setCapacity(20);
		assertEquals("Test setCapacity", 20, uneSalle.getCapacity());
	}

	@Test
	public void testGetAreaName() {
		assertEquals("Test getAreaName", "Informatique - multimédia", uneSalle.getArea_Name());
		uneSalle.setArea_name("Salles de réception");
		assertEquals("Test setAreaName", "Salles de réception", uneSalle.getArea_Name());
	}

}