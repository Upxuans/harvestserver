/**
 * 
 */
/**
 * @author Bran
 *
 */
package service.app.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import service.app.domain.ManagerInfo;

//Jpa方法
public interface LogRepository extends JpaRepository<ManagerInfo, Integer> {

	public ManagerInfo findByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT * from manager")
	public List<Object> selectAllTagView(); 

}
