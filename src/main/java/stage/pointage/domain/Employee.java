package stage.pointage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "employee")
public class Employee {
	
	@Id
	
	@Column(name="cin",unique=true,  length=16 ,nullable=false)
    private String cin;
	@Column(name="nom",length=50)
    private String nom;
	@Column(name="adresse",length=255)
    private String adresse;
	@Column(name="tel",length=30)
    private String tel;

    public Employee() {
		
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
    
    
}

	