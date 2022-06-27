package stage.pointage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stage.pointage.domain.Employee;
import stage.pointage.domain.Mois_state;
import stage.pointage.repository.EmployeeRepository;
import stage.pointage.repository.MoisRepository;


@Service
public class MoisService {
	@Autowired
	private  MoisRepository rep;
	@Autowired
	private EmployeeRepository emprepo;
	
	public List<Mois_state> getAll(){
		return rep.find();
	}
	public List<Mois_state> getThisMonthList(String month){
		return rep.getMonthList(month);
	}
	public void updateMonth(float presence,String cin, String mois) {
		rep.updateMonth(presence, cin, mois);
		
	}
	public void insertAll(String month) {
		List<Employee> listemployee = emprepo.findAll();
		for(int i=0;i<listemployee.size();i++) {
			
		rep.insertMonth(rep.count()+1,listemployee.get(i).getCin(),
				listemployee.get(i).getNom(),month,0.0f);
		
		}
	}
	public void insertOneMonth(String cin,String nom,String mois) {
		rep.insertMonth(rep.count()+1, cin, nom, mois, 0.0f);
	}
	
	public Mois_state verifMonth(String cin,String month) {
		return rep.verif(cin,month);
	}
	
}
