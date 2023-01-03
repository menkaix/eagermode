package dashboard.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.ClientType;

public interface ClientTypeRepository  extends PagingAndSortingRepository<ClientType, Integer>{

}
