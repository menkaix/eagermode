package dashboard.data.repositories;

import java.util.List;

import dashboard.data.entities.Actor;

public interface ActorRepositoryCustom {

	public List<Actor> getByProjectCode(String p) ;
	
}
