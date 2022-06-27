package stage.pointage.domain;

import javax.persistence.*;

@Entity(name = "presence")
@Table(name = "mois_state")
public class Mois_state {
	@Id
	@SequenceGenerator(
			name ="mois_sequence",
			sequenceName = "mois_sequence",
			allocationSize=1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator ="mois_sequence"
			)
	
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "cin" ,length=16)
	private String cin;
	@Column(name = "nom", length = 50)
	private String nom;
	@Column(name = "mois", length = 50)
	private String mois;
	@Column(name = "presence")
	private float presence;
	
	
	


	public Mois_state() {
		super();
	}


	public Mois_state(int id,String cin, String nom,String mois, float presence) {
		super();
		this.id=id;
		this.cin = cin;
		this.mois = mois;
		this.presence = presence;
		this.nom = nom;
	}


	public String getCin() {
		return cin;
	}


	public void setCin(String cin) {
		this.cin = cin;
	}


	public String getMois() {
		return mois;
	}


	public void setMois(String mois) {
		this.mois = mois;
	}


	public float getPresence() {
		return presence;
	}


	public void setPresence(int presence) {
		this.presence = presence;
	}


	@Override
	public String toString() {
		return "Mois_sta [cin=" + cin + ", nom=" + nom + ", mois=" + mois + ", presence=" + presence + "]";
	}


	public String getNom() {
		return nom;
	}


	public void setAbsence(String nom) {
		this.nom = nom;
	}



	
}
