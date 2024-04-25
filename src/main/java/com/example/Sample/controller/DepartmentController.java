package com.example.Sample.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.Sample.model.DepartmentModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DepartmentController {
	
	private static EntityManagerFactory entityManagerFactory =
	          Persistence.createEntityManagerFactory("automation");
	

	  @RequestMapping("/depart")
	    public ModelAndView depart () {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("department");
	        return modelAndView;
	    }
	  
	  @RequestMapping("/")
	    public ModelAndView index () {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("index");
	        return modelAndView;
	    }
	
@GetMapping(path = "/GetDepartmentListData", produces = "application/json")
public String getdepartmetString()
{
	List<String> data = new ArrayList<String>();
	JSONArray ja1 = new JSONArray();
	ObjectMapper mapper = new ObjectMapper();
	try {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	      StoredProcedureQuery procedureQuery =
	              entityManager.createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentListStoreProcedure);		      
	      procedureQuery.execute(); 
	      @SuppressWarnings("unchecked")
	      List<Object[]> resultList = procedureQuery.getResultList();
	      for (Object[] r : resultList) {
	    	  //System.out.print(r[0]);
	    	  //System.out.print(r[1]);
	    	  JSONObject obj1 = new JSONObject();		 
	    	  obj1.put("DepartmentId",r[0]);
			  obj1.put("DepartmentName",r[1]);
			  obj1.put("CreatedDate",r[2]);			
			  obj1.put("CreatedBy",r[3]);
			  obj1.put("UpdatedDate",r[4]);
			  obj1.put("UpdatedBy",r[5]);
			  obj1.put("IsActive",r[6]);
			  ja1.add(obj1); 
	      
	      }
	     
		
	     return ja1.toString();
	     // return "Developer";
	
	}
	catch (Exception e) {
		e.printStackTrace();
		
	}
	
	return null;	
}

}