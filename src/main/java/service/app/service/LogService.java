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

import service.app.domain.ManagerInfo;
import service.app.domain.repository.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public ManagerInfo getLogInData(String username){
    	ManagerInfo info = logRepository.findByUsername(username);
		return info;
	}
    
}