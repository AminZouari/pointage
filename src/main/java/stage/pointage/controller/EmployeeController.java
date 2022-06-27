package stage.pointage.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import stage.pointage.domain.Employee;
import stage.pointage.domain.Mois_state;
import stage.pointage.domain.PresenceList;
import stage.pointage.service.EmployeeService;
import stage.pointage.service.MoisService;
import stage.pointage.service.PresenceListService;



@Controller
@RequestMapping("/")
public class EmployeeController {
	
	 @Autowired
	   private EmployeeService service;
	 @Autowired
	 private PresenceListService presenceListservice;
	 @Autowired
	 MoisService monthService;
	
	
	   @GetMapping("/")
	    public String viewHomePage(Model model) {
		   
		   String date = service.getCurrentDate();	
		 	String month = service.getCurrentMonth();
		 	
	        List<Employee> listemployee = service.listAll();
	        model.addAttribute("listemployee", listemployee);
	       
	        
	        
	      
	   
	    	
	    	
	    	 List<PresenceList> presenceList = presenceListservice.getList(date);
	    	 List<Mois_state> monthList = monthService.getThisMonthList(month); 
	    	
	    	 if( presenceList.isEmpty()) {
	    		
	    	 	 presenceListservice.insertAllAbsence(date);
	    	 }
	    	 if(monthList.isEmpty()) {
	    		 monthService.insertAll(month);
	    	 }
	    	
	    	 
	    	 
	        return "index";
	    }

	    @GetMapping("/new")
	    public String add(Model model) {
	        model.addAttribute("employee", new Employee());
	        return "new";
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveEmployee(@ModelAttribute("employee") Employee emp) {
	    	 String date = service.getCurrentDate();	
			 	String month = service.getCurrentMonth();
	        service.save(emp);
	        PresenceList p = presenceListservice.verifPresence(emp.getCin(), date);
	    	

	        if(p==null) 
	        presenceListservice.insertOneAbsence(emp.getCin(),emp.getNom(),date);
	        
	        Mois_state m = monthService.verifMonth(emp.getCin(), month);
	        
	        if(m==null)
	        	monthService.insertOneMonth(emp.getCin(),emp.getNom(), month);
	        
	        return "redirect:/";
	    }
	   
	  
	    @RequestMapping(value="/updateAbsence",method=RequestMethod.GET)
	    public @ResponseBody List<PresenceList> updateTables(@RequestParam String name ) throws Exception {
	    	
	    	 String date = service.getCurrentDate();	
			 	String month = service.getCurrentMonth();
	    
	    	name = name.substring(1,name.length()-1);
	    	String [] table = name.split(",");
	    
	    	for(String v : table ) {
	    		
	    		
	    		int c = v.indexOf(":");
	    	
	    		Long id = Long.valueOf(v.substring(1,c-1));
	    		float presence = Float.valueOf(v.substring(c+2,v.length()-1));
	    		PresenceList pr = presenceListservice.getPresenceList(id);
	    		float oldPresence = pr.getPresence();
	    		String cin = pr.getCin();
	    		presenceListservice.edit(presence,id);
	    		
	    	
	    			monthService.updateMonth(presence-oldPresence, cin, month);
	    		
	    	}
	    	List<PresenceList> absenceList = presenceListservice.getAbsenceList(date);
	    	
	    
	    	return absenceList;
	    }
	    	@RequestMapping(value="/updatePresence",method=RequestMethod.GET)
		    public @ResponseBody List<PresenceList> updatePresence( ) throws Exception {
	    		 String date = service.getCurrentDate();	
	 		 
	    		List<PresenceList> presenceList = presenceListservice.getPresenceList(date);
	    		
	    		if(!presenceList.isEmpty()) {
	    			
	    		}
	    		return presenceList;
	    	}
	    	
	    
	    @RequestMapping("/edit/{cin}")
	    public ModelAndView showEditEmployeePage(@PathVariable(name = "cin")String  cin) {
	        ModelAndView mav = new ModelAndView("new");
	        Employee emp = service.getEmployee(cin);
	        mav.addObject("employee", emp);
	        return mav;
	        
	    }
	    @RequestMapping("/delete/{cin}")
	    public String deleteEmployeePage(@PathVariable(name = "cin") String cin) {
	        service.delete(cin);
	        return "redirect:/";
	    }
	    @GetMapping("/prJournalière")
	    public String showPresence(Model model) {
	    	 String date = service.getCurrentDate();	
			 
	    	
	    	 List<PresenceList> absenceList = presenceListservice.getAbsenceList(date);
	    	 model.addAttribute("absenceList", absenceList );
	    	 List<PresenceList> presenceList = presenceListservice.getPresenceList(date);
	    	 model.addAttribute("presenceList",presenceList);
	    	return "prJournalière";
	    	
	    }
	    @GetMapping("/prMensuelle")
	    public String showMonth(Model model) {
	    	List<Mois_state> monthList = monthService.getAll();
	    	model.addAttribute("monthList", monthList);
	    	return "prMensuelle";
	    	
	    
	    }

}