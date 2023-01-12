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

	protected O fieldsFromEntity(I entity, O newDTO) {

		for (Method entityField : entity.getClass().getMethods()) {

			String fieldNakedName = entityField.getName().substring(3);

			Method dtoField;
			try {
				dtoField = newDTO.getClass().getMethod(entityField.getName());

				if (dtoField != null && entityField.getReturnType() == dtoField.getReturnType()) {

					Method setter = newDTO.getClass().getMethod("set" + fieldNakedName);
					//setter.set(newDTO, entityField.get(entity));
					setter.invoke(newDTO, dtoField.invoke(entity));
				}

			 

			} catch (SecurityException e) {

			} catch (IllegalArgumentException e) {

			} catch (IllegalAccessException e) {

			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

		return null;
	}
	
	protected O fieldsFromDTO(O dto, I newEntity) {

		for (Method entityField : dto.getClass().getMethods()) {

			String fieldNakedName = entityField.getName().substring(3);

			System.out.println(fieldNakedName);
			
			Method dtoField;
			try {
				dtoField = newEntity.getClass().getMethod(entityField.getName());

				if (dtoField != null && entityField.getReturnType() == dtoField.getReturnType()) {

					Method setter = newEntity.getClass().getMethod("set" + fieldNakedName);
					//setter.set(newEntity, entityField.get(dto));
					setter.invoke(newEntity, entityField.invoke(dto)) ;
				}

			
			} catch (SecurityException e) {
				
			} catch (IllegalArgumentException e) {
				
			} catch (IllegalAccessException e) {
				
			} catch (InvocationTargetException e) {
				
			} catch (NoSuchMethodException e) {
				
			}

		}

		return null;
	}
}
