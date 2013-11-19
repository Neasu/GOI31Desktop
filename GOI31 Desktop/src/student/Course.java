package student;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse beschreibt einen Kurs, den ein Sch�ler belegen kann.
 * 
 * @author Valentin
 *
 */
public class Course {

	public Course() {
		
	}
	
	private String identifier;

	private String fullname;
	
	private List<Occurrence> occurrences = new ArrayList<Occurrence>();
	
	/**
	 * Gibt die ID des Kurses zur�ck
	 * @return
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Setzt die ID des Kurses
	 * @param identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Gibt einen lesbaren Namen des Kurses zur�ck
	 * @return
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * Setzt den lesbaren Namen f�r den Kurs
	 * @param fullname
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	/**
	 * F�gt ein Vorkommen (eine Schulstunde) hinzu
	 * @param oc
	 */
	public void addOccurrence(Occurrence oc) {
		occurrences.add(oc);
	}

	/**
	 * Gibt die Liste mit den Vorkommen zur�ck
	 * @return
	 */
	public List<Occurrence> getOccurrences() {
		return occurrences;
	}
	
	
}
