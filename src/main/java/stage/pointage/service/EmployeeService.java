package stage.pointage.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stage.pointage.domain.Employee;
import stage.pointage.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repo;

	 SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
	 SimpleDateFormat monthformat = new SimpleDateFormat("MM/yyyy");
	public List<Employee> listAll() {

		return repo.findAll();

	}
	public void save(Employee e){


		repo.save(e);
	}
	public Employee getEmployee(String cin ){
		return repo.findById(cin).get();
	}

	public void delete(String cin){
		repo.deleteById(cin);
	}
	public String  getCurrentDate() {
		
		return formatter1.format(new Date()); 	
	}
	
public String  getCurrentMonth() {
		
	return monthformat.format(new Date());
	}

}
