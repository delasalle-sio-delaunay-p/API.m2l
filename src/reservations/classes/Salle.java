/**
 * Application de suivi des r�servations de la Maison des Ligues de Lorraine
 * Th�me : d�veloppement et test des classes Salle, Reservation, Utilisateur et Passerelle
 * Auteur : Leilla
 * Derni�re mise � jour : 20/03/2018
 */

package reservations.classes;


/**
 * Cette classe repr�sente une salle
 */
public class Salle {
	
	/** Membres priv�s */
	
	private int _id;				 // identifiant de l'utilisateur
	private String _room_name;      //nom de la salle
	private int _capacity;       //capacit� de la salle
	private String _area_name;      //nom de la zone

	/** Constructeurs  */
	public Salle() {
        this._id = 0;
        this._room_name = "";
        this._capacity = 0;
        this._area_name = "";
    }
	public Salle(int unId, String unRoomName, int unCapacity, String unAreaName) {
		this._id = unId;
		this._room_name = unRoomName;
		this._capacity = unCapacity;
		this._area_name = unAreaName;
	}	
	
	/** Accesseurs */	
	public int getId() {
		return _id;
	}
	public void setId(int unId) {
		this._id = unId;
	}
	
	public String getRoomName() {
		return _room_name;
	}
	public void setRoomName(String unRoomName) {
		this._room_name = unRoomName;
	}
	
	public int getCapacity() {
		return _capacity;
	}
	public void setCapacity(int unCapacity) {
		this._capacity = unCapacity;
	}
	
	public String getArea_Name() {
		return _area_name;
	}
	public void setArea_name(String unAreaName) {
		this._area_name = unAreaName;
	}
	
	
		/** M�thodes publiques */	
	public String toString() {
		String msg = "";
		msg += "id :\t\t" + this._id + "\n";
		msg += "room_name :\t" + this._room_name + "\n";
		msg += "capacity :\t\t" + this._capacity + "\n";
		msg += "area_Name :\t" + this._area_name + "\n";
		return msg;
	}
	
	
}
