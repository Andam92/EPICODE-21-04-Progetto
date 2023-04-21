package it.epicode.weekly_project.auth.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import it.epicode.weekly_project.auth.configuration.DeviceConfig;
import it.epicode.weekly_project.auth.entity.Device;
import it.epicode.weekly_project.auth.entity.Device_type;
import it.epicode.weekly_project.auth.repository.DeviceRepository;
import jakarta.persistence.EntityExistsException;

@Service 
public class DeviceService {
	
	@Autowired DeviceRepository deviceRepo;
	
	
	public Device createDevice(Device_type type) {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(DeviceConfig.class);
		Device d = (Device) appContext.getBean("createDevice", type);
		return addDevice(d);
	}
	
	public Device addDevice(Device d) {
		if(!deviceRepo.existsBySerialNumber(d.getSerialNumber())) {
			deviceRepo.save(d);
			System.out.println("Device added to DataBase");
			return d;			
		} else {
			throw new EntityExistsException("This device already exists!");
		}
	}
	

}
