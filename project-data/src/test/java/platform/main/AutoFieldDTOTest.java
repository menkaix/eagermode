package platform.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import dashboard.constraints.MileStoneDTOConverter;
import dashboard.data.dto.MileStoneDTO;
import dashboard.data.entities.MileStone;

import org.junit.jupiter.api.Assertions;


@ActiveProfiles("test")
public class AutoFieldDTOTest {
	
	
	private MileStoneDTOConverter converter ;
	
	@Test
	public void  shouldConvertFromDTO() {
		
		MileStoneDTOConverter converter = new MileStoneDTOConverter();
		
		MileStoneDTO dto = new MileStoneDTO() ;
		
		dto.setId(150);
		
		MileStone mileStone = converter.convertFormDTO(dto);
		
		Assertions.assertEquals(150, mileStone.getId());
		
	}

}
