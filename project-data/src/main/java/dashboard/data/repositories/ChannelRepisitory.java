package dashboard.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.Channel;

public interface ChannelRepisitory extends PagingAndSortingRepository<Channel, Integer> {

}
