package stage.pointage.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
@Entity
@Table(name="presence_list")
public class PresenceList {



//@GeneratedValue(strategy= GenerationType.IDENTITY)

	
		
		@Id
		@GeneratedValue
		/*@Column(name="id")
		@SequenceGenerator(
				name="presence_sequence",
				sequenceName="presence_sequence",
				allocationSize=1
				)
		@GeneratedValue(strategy= GenerationType.SEQUENCE,
				generator="presence_sequence"
		)*/
		private Long id ;
		@Column(name="cin",length=16)
	    private String cin;
		@Column(name="nom",length=50)
	    private String nom;
		@Column(name="jour")
	    private Date jour;
		@Column(name="presence")
	    private float presence;

	    public PresenceList() {
			
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCin() {
			return cin;
		}

		public void setCin(String cin) {
			this.cin = cin;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public Date getJour() {
			return jour;
		}

		public void setJour(Date jour) {
			this.jour = jour;
		}

		public float getPresence() {
			return presence;
		}

		public void setPresence(float presence) {
			this.presence = presence;
		}
	    

}