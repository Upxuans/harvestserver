package service.app.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import service.app.domain.TeachAchInfo;

public interface TeachAchRepository extends JpaRepository<TeachAchInfo, Integer> {
	
	public List<TeachAchInfo> findByTachteach(Integer tachteach);
	
	public List<TeachAchInfo> findByTachteachAndTachtype(
			
			Integer tachteach, Integer tachtype);
	
}
