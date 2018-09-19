/**
 * 
 */
/**
 * @author Bran
 *
 */
package service.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.app.domain.ManagerModel;
import service.app.domain.repository.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public ManagerModel getLogInData(String username){
    	ManagerModel info = logRepository.findByUsername(username);
		return info;
	}
    
}