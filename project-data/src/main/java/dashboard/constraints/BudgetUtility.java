package dashboard.constraints;

import org.springframework.stereotype.Component;

@Component
public class BudgetUtility {
	
	public String convertSecondstoJoho(Float float1) {
		
		String ans = "" ;
		
		int joho = (int)(float1/(3600*8)); //3600s * 8h
		
		
		ans += joho + " Joho, " ;
		
		int restSecs = float1.intValue() - (joho*3600*8) ;
		int hours = (int)(restSecs /3600) ;

		ans += hours + " h, " ;
		
		restSecs = restSecs - (hours * 3600) ;
		int minutes = (int)(restSecs/60) ;
		
		ans += minutes +" min, " ;
				
		restSecs = restSecs - 60*minutes ;
		
		ans += restSecs + " s " ;
		
		
		return ans ;
	}
	
	

}
