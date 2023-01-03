package platform.services.structure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;

import platform.data.annotations.Display;

@Service
public class DescriptionService implements ApplicationContextAware {

	private ApplicationContext context;

	private Repositories repositories = null;

	public String Enum2Options(@SuppressWarnings("rawtypes") Class clazz) {
		String select = "Enum :[";

		Object[] enums = clazz.getEnumConstants();

		for (Object object : enums) {
			select += "\"" + object.toString() + "\",";
		}

		select = select.substring(0, select.length() - 1);

		select += "]";

		return select;
	}
	
	
	@SuppressWarnings("rawtypes")
	public CrudRepository getRepo(Class entityClass) {
		
		
		repositories = new Repositories(context);

		CrudRepository repo = (CrudRepository) repositories.
				getRepositoryFor(entityClass).get();
		
		return repo ;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String Reference2Options(Class entityClass) {

		String ans = "[ ";

		CrudRepository repo = getRepo(entityClass) ;

		for (Object o : repo.findAll()) {
			try {

				String idValue = "" + entityClass.getDeclaredMethod("getId").invoke(entityClass.cast(o));

				String dispValue = "";

				for (Method m : entityClass.getMethods()) {
					if (m.isAnnotationPresent(Display.class)) {

						dispValue = "" + entityClass.getDeclaredMethod(m.getName()).invoke(entityClass.cast(o));

						break;
					}

				}

				ans += "{\"id\":\"" + idValue + "\", \"display\" : \"" + dispValue + "\"},";

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		ans = ans.substring(0, ans.length() - 1);
		
		ans += "]";

		return ans;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		context = applicationContext;

	}

}
