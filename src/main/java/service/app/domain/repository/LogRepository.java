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

import service.app.domain.ManagerModel;

//Jpa方法
public interface LogRepository extends JpaRepository<ManagerModel, Integer> {

	public ManagerModel findByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT * from manager")
	public List<Object> selectAllTagView(); 

}
