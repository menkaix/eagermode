package dashboard.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.MileStone;

public interface MileStoneRepository extends PagingAndSortingRepository<MileStone, Integer> {

}
