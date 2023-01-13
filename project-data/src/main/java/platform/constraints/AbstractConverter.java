package platform.constraints;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<I, O> {

	// TODO generalise this class with mapping

	public abstract O convertToDTO(I entity);

	public abstract I convertFormDTO(O dto);

	public List<O> covertAllToDTO(List<I> ins) {
		ArrayList<O> list = new ArrayList<O>();

		for (I prj : ins) {
			list.add(convertToDTO(prj));
		}

		return list;
	}

	public List<I> covertAllFromDTO(List<O> ins) {
		ArrayList<I> list = new ArrayList<I>();

		for (O prj : ins) {
			list.add(convertFormDTO(prj));
		}

		return list;
	}

	protected void fieldsFromEntity(I entity, O newDTO) {

		for (Method entityField : entity.getClass().getMethods()) {

			if(!entityField.getName().startsWith("get")) {
				continue ;
			}
			
			String fieldNakedName = entityField.getName().substring(3);

			Method dtoField;
			try {
				dtoField = newDTO.getClass().getMethod("set" + fieldNakedName,entityField.getReturnType());

				dtoField.invoke(newDTO, entityField.invoke(entity));
				
			} catch (SecurityException e) {
				System.err.println(e.getClass().getSimpleName() +" : " + e.getLocalizedMessage());
			} catch (IllegalArgumentException e) {
				System.err.println(e.getClass().getSimpleName() +" : " + e.getLocalizedMessage());
			} catch (IllegalAccessException e) {
				System.err.println(e.getClass().getSimpleName() +" : " + e.getLocalizedMessage());
			} catch (InvocationTargetException e) {
				System.err.println(e.getClass().getSimpleName() +" : " + e.getLocalizedMessage());
			} catch (NoSuchMethodException e) {
				//System.err.println(e.getClass().getSimpleName() +" : " + e.getLocalizedMessage());
			}
			

		}

	}
	
	protected void fieldsFromDTO(O dto, I newEntity) {

		for (Method dtoField : dto.getClass().getMethods()) {
			
			if(!dtoField.getName().startsWith("get")) {
				continue ;
			}

			String fieldNakedName = dtoField.getName().substring(3);

			//System.out.println(fieldNakedName);
			
			Method newEntityField;
			try {
				newEntityField = newEntity.getClass().getMethod("set" + fieldNakedName,dtoField.getReturnType());

				newEntityField.invoke(newEntity, dtoField.invoke(dto)) ;
				
			} catch (SecurityException e) {
				System.err.println(e.getClass().getSimpleName() +" : " + e.getLocalizedMessage());
			} catch (IllegalArgumentException e) {
				System.err.println(e.getClass().getSimpleName() +" : " + e.getLocalizedMessage());
			} catch (IllegalAccessException e) {
				System.err.println(e.getClass().getSimpleName() +" : " + e.getLocalizedMessage());
			} catch (InvocationTargetException e) {
				System.err.println(e.getClass().getSimpleName() +" : " + e.getLocalizedMessage());
			} catch (NoSuchMethodException e) {
				
			}

		}

	}
}
