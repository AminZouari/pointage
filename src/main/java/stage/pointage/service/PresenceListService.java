package stage.pointage.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stage.pointage.domain.Employee;
import stage.pointage.domain.PresenceList;
import stage.pointage.repository.EmployeeRepository;
import stage.pointage.repository.PresenceListRepository;

@Service
public class PresenceListService {
	
	
	
	
	@Autowired
	private PresenceListRepository repo;
	@Autowired
	private EmployeeRepository  emprepo;
	
	public List<PresenceList> listAll() {

		return repo.findAll();

	}
	public void save(PresenceList e){
		repo.save(e);
	}
	public PresenceList getPresenceList(Long id ){
		return repo.findById(id).get();
	}

	public void delete(Long id){
		repo.deleteById(id);
	}
	
	public void insertAllAbsence(String jour){
		List<Employee> listemployee = emprepo.findAll();
		for(int i=0;i<listemployee.size();i++) {
			
		repo.insertIntoPresenceList(repo.count()+1,listemployee.get(i).getCin(),
				listemployee.get(i).getNom(),jour,0.0f);
		
		}
	}
	public void insertOneAbsence(String cin,String nom,String jour)	{
		repo.insertIntoPresenceList(repo.count()+1, cin, nom, jour,0.0f);
	
	}
	public void initialize() {
		/*List<Employee> listemployee = emprepo.findAll();
		for(int i=0;i<listemployee.size();i++) {
			save(listemployee.get(i));
		}*/
	}
	
	public List<PresenceList> getAbsenceList(String jour){
		
		return repo.getAbsenceList(jour);
	}
public List<PresenceList> getPresenceList(String jour){
		
		return repo.getPresenceList(jour);
	}
	
	public void edit(float presence, Long id) {
		
		repo.edit(presence,id);
	}
	
	public PresenceList verifPresence(String cin,String jour) {
		return repo.verif(cin, jour);
	}
	
	public  List<PresenceList>  getList(String jour ) {
		return repo.getList(jour);
	}
	
	
	


}
