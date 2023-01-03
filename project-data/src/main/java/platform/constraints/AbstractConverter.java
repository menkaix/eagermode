package platform.constraints;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<I,O> {
	
	//TODO generalise this class with mapping
	
	public abstract O convertToDTO(I entity) ;
	public abstract I convertFormDTO(O dto) ;	
	
	public List<O> covertAllToDTO(List<I> ins) {
		ArrayList<O> list = new ArrayList<O>();
		
		for(I prj : ins) {
			list.add(convertToDTO(prj));
		}
		
		
		return list;
	}

	
	public List<I> covertAllFromDTO(List<O> ins) {
		ArrayList<I> list = new ArrayList<I>();
		
		for(O prj : ins) {
			list.add(convertFormDTO(prj));
		}
		
		return list;
	}
}
