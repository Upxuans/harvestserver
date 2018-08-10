package service.app.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import service.app.domain.PaperInfo;

public interface PaperRepository extends JpaRepository<PaperInfo, Integer>{
	
	
}
