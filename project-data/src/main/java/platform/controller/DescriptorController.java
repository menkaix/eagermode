package platform.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import platform.data.annotations.TextArea;
import platform.services.structure.DescriptionService;

@RestController
@CrossOrigin
@RequestMapping("/entities")
public class DescriptorController {

	@Autowired
	private DescriptionService descService ;
	
	@RequestMapping(value = "/structure/{entity}", method = RequestMethod.GET)
	public ResponseEntity<WeakHashMap<String, String>> EntityStrucure(@PathVariable("entity") String entityname) {

		WeakHashMap<String, String> ans = new WeakHashMap<String, String>();

		try {
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName("dashboard.data.entities." + entityname);

			for (Field f : clazz.getDeclaredFields()) {

				if (f.getType().isEnum()) {
					
					String select = descService.Enum2Options(f.getType());

					ans.put(f.getName(), select);

				} else if (f.isAnnotationPresent(ManyToOne.class)) {
					String reference = "Reference>";

					
					
					reference += descService.Reference2Options(f.getType()) ;

					ans.put(f.getName(), reference);

				
				} else if (f.isAnnotationPresent(TextArea.class)) {
					String reference = "TextArea";

					ans.put(f.getName(), reference);

				} else if (f.getType() == String.class) {
					String reference = "text";

					ans.put(f.getName(), reference);

				} else {
					ans.put(f.getName(), f.getType().getSimpleName());
				}

			}

		} catch (ClassNotFoundException e) {

			return new ResponseEntity<WeakHashMap<String, String>>(HttpStatus.NOT_FOUND);

		}

		return ResponseEntity.ok(ans);
	}

	@RequestMapping(value = "/list")
	public ResponseEntity<List<String>> listEntities() {
		ArrayList<String> lst = new ArrayList<String>();

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);

		scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

		for (BeanDefinition bd : scanner.findCandidateComponents("dashboard.data.entities")) {

			try {
				@SuppressWarnings("rawtypes")
				Class clazz = Class.forName(bd.getBeanClassName());

				@SuppressWarnings("unchecked")
				Entity entity = (Entity) clazz.getAnnotation(Entity.class);

				lst.add(clazz.getSimpleName());

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return ResponseEntity.ok(lst);

	}

	/*
	@RequestMapping(value = "/url/{entity}", method = RequestMethod.GET)
	public ResponseEntity<String> EntityUrl(@PathVariable("entity") String entityname) {
		String ans = "";

		try {
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName("dashboard.data.entities." + entityname);
			
			CrudEntity crd = (CrudEntity) clazz.getAnnotation(CrudEntity.class) ;
			ans = crd.value() ;
			
			
		} catch (ClassNotFoundException e) {

			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

		}

		return ResponseEntity.ok(ans);
	}
	
	@RequestMapping(value = "/foroptions/{entity}", method = RequestMethod.GET)
	public ResponseEntity<String> EntityForPotions(@PathVariable("entity") String entityname) {
		String ans = "id";

		try {
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName("dashboard.data.entities." + entityname);
			
			for(Field f : clazz.getDeclaredFields()) {
				
				if(f.isAnnotationPresent(Represents.class)) {
					ans = f.getName() ;
					return ResponseEntity.ok(ans);
				}
				
			}

			
		} catch (ClassNotFoundException e) {

			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

		}

		return ResponseEntity.ok(ans);
	}
	*/
}
