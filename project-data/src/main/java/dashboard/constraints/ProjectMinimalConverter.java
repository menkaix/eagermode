package dashboard.constraints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dashboard.data.dto.ProjectMinimumDTO;
import dashboard.data.entities.Company;
import dashboard.data.entities.Project;
import dashboard.data.entities.ProjectGenre;
import dashboard.data.entities.ProjectStatus;
import dashboard.data.repositories.CompanyRepository;
import dashboard.data.repositories.ProjectGenreRepository;
import dashboard.data.repositories.ProjectRepository;
import dashboard.data.repositories.ProjectStatusRepository;
import platform.constraints.AbstractConverter;


@Component
public class ProjectMinimalConverter extends AbstractConverter<Project, ProjectMinimumDTO> {
	
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectStatusRepository projectStatusRepository;

	@Autowired
	private ProjectGenreRepository genreRepository;
	
	public ProjectMinimalConverter() {
		
	}

	@Override
	public ProjectMinimumDTO convertToDTO(Project input) {
		ProjectMinimumDTO ans = new ProjectMinimumDTO();
		ans.setId(input.getId());
		ans.setClient(input.getClient() != null ? input.getClient().getCompanyName() : "");
		ans.setProjectName(input.getProjectName());
		ans.setCreateDate(input.getDateCreated() != null ? (new SimpleDateFormat("dd/MM/yyyy")).format(input.getDateCreated()): "");
		ans.setStatus(input.getStatus() != null ? input.getStatus().getStatusName() : "");
		ans.setGenre(input.getGenre() != null ? input.getGenre().getGenreName() : "") ;
		ans.setProjectCode(input.getProjectCode());
		ans.setLastActivity(input.getLastActivity() != null ? (new SimpleDateFormat("dd/MM/yyyy")).format(input.getLastActivity()): "");
		
		
		return ans;
	}

	@Override
	public Project convertFormDTO(ProjectMinimumDTO req) {
		
		Project project;

		if (req.getId() != null) {
			try {
				Project projectCandidate = (projectRepository.findById(req.getId())).get();
				project = projectCandidate;
				//ans.getMessage().add("an existing project will be updated");
			} catch (NoSuchElementException e) {
				project = projectRepository.save(new Project());
				project.setDateCreated(new Date());
				//ans.getMessage().add("a new project will be created with new ID");
			}
		} else {
			project = projectRepository.save(new Project());
			project.setDateCreated(new Date());
			//ans.getMessage().add("a new project will be created");
		}

		project.setProjectName(req.getProjectName());
		project.setProjectCode(req.getProjectCode());
		project.setDescription(req.getDescription());

		try {
			if (req.getCreateDate() != null)
				project.setDateCreated(new SimpleDateFormat("dd/MM/yyyy").parse(req.getCreateDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (req.getLastActivity() != null)
				project.setLastActivity(new SimpleDateFormat("dd/MM/yyyy").parse(req.getCreateDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (req.getClient() != null) {
			List<Company> companyCandidates = companyRepository.findByCompanyName(req.getClient().toLowerCase().trim());

			if (companyCandidates.size() <= 0) {
				if (req.isNewDataNeeded()) {

					Company comp = new Company();
					comp.setCompanyName(req.getClient().trim().toLowerCase());
					companyRepository.save(comp);

					//ans.getMessage().add("a new company " + comp.getCompanyName() + " has been created");

					project.setClient(comp);
				} else {
					//ans.getMessage().add("no company named " + req.getClient() + "has been found, nor created");
				}
			} else if (companyCandidates.size() == 1) {
				project.setClient(companyCandidates.get(0));
			} else {
				project.setClient(companyCandidates.get(0));
				//ans.getMessage().add("warning : " + companyCandidates.size() + " candidates were found for " + req.getClient());
			}

		}

		if (req.getStatus() != null) {
			List<ProjectStatus> statusCandidates = projectStatusRepository
					.findByStatusName(req.getStatus().toUpperCase().trim());

			if (statusCandidates.size() <= 0) {
				if (req.isNewDataNeeded()) {

					ProjectStatus status = new ProjectStatus();
					status.setStatusName(req.getStatus().toUpperCase().trim());
					projectStatusRepository.save(status);

					//ans.getMessage().add("a new status " + status.getStatusName() + " has been created");

					project.setStatus(status);
				} else {
					//ans.getMessage().add("no status " + req.getStatus() + "has been found, nor created");
				}
			} else if (statusCandidates.size() == 1) {
				project.setStatus(statusCandidates.get(0));
			} else {
				project.setStatus(statusCandidates.get(0));
				//ans.getMessage().add("warning : " + statusCandidates.size() + " candidates were found for " + req.getClient());
			}

		}

		if (req.getGenre() != null) {

			List<ProjectGenre> genreCandidates = genreRepository.findByGenreName(req.getGenre().toUpperCase().trim());

			if (genreCandidates.size() <= 0) {
				if (req.isNewDataNeeded()) {

					ProjectGenre status = new ProjectGenre();
					status.setGenreName(req.getGenre().toUpperCase().trim());
					genreRepository.save(status);

					//ans.getMessage().add("a new genre " + status.getGenreName() + " has been created");

					project.setGenre(status);
				} else {
					//ans.getMessage().add("no genre " + req.getStatus() + "has been found, nor created");
				}
			} else if (genreCandidates.size() == 1) {
				project.setGenre(genreCandidates.get(0));
			} else {
				project.setGenre(genreCandidates.get(0));
				//ans.getMessage().add("warning : " + genreCandidates.size() + " candidates were found for " + req.getClient());
			}

		}

		
		
		return project;
	}

	
	

}
