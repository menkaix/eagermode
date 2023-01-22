package dashboard.services;

import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.data.entities.People;
import dashboard.data.repositories.PeopleRepository;

@Service
public class PeopleService {
	
	private Logger logger = LoggerFactory.getLogger(PeopleService.class);

	@Autowired
	private PeopleRepository repo ;
	
	public People findFromFullName(String fullName) {
		
		String lastname = "" ;
		String firstName = "" ;
		
		StringTokenizer stk = new StringTokenizer(fullName.trim(), " ");
		
		while(stk.hasMoreTokens()) {
			
			String candidate = stk.nextToken() ;
			
			char [] chars = candidate.toCharArray() ;
			
			boolean isAllCaps = true;
			
			for (char c : chars) {
				isAllCaps = isAllCaps && Character.isUpperCase(c);
			}
			
			if(isAllCaps) {
				lastname = (lastname+" "+candidate).trim() ;
			}
			else {
				firstName = (firstName+" "+candidate).trim() ;
			}
			
		}
		
		List<People> peoples = repo.findByLastName(lastname) ;
		
		for (People people : peoples) {
			if(people.getFirstName().equalsIgnoreCase(firstName)) {
				return people ;
			}
			else if(people.getFirstName().toLowerCase().contains(firstName.toLowerCase()) || firstName.toLowerCase().contains(people.getFirstName().toLowerCase())) {
				return people ;
			}
		}
		
		return null ;
	}
	
	public People addFromFullName(String fullName) {
		
		String lastname = "" ;
		String firstName = "" ;
		
		StringTokenizer stk = new StringTokenizer(fullName.trim(), " ");
		
		while(stk.hasMoreTokens()) {
			
			String candidate = stk.nextToken() ;
			
			char [] chars = candidate.toCharArray() ;
			
			boolean isAllCaps = true;
			
			for (char c : chars) {
				isAllCaps = isAllCaps && Character.isUpperCase(c);
			}
			
			if(isAllCaps) {
				lastname = (lastname+" "+candidate).trim() ;
			}
			else {
				firstName = (firstName+" "+candidate).trim() ;
			}
			
		}
		
		People ans = new People() ;
		
		ans.setFirstName(firstName);
		ans.setLastName(lastname);
		
		return repo.save(ans) ;
		
	}
	
	public People findOrCreateWithFullName(String fullName) {
		
		People t = findFromFullName(fullName) ;
		
		if(t !=null) {
			return t ;
		}
		else {
			return addFromFullName(fullName) ;
		}
	}
	
}
