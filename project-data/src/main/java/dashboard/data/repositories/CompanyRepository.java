package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {
	
	public List<Company> findByCompanyName(String companyName);

}
