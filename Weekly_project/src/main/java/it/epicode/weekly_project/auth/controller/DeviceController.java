package it.epicode.weekly_project.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.weekly_project.auth.entity.Device;
import it.epicode.weekly_project.auth.exception.GlobalExceptionHandler;
import it.epicode.weekly_project.auth.repository.DeviceRepository;
import it.epicode.weekly_project.auth.service.DeviceService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

	@Autowired DeviceRepository deviceRepo;
	@Autowired DeviceService deviceServ;
	
	// ottieni tutti i device
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllDevices() {
		return new ResponseEntity<>(deviceRepo.findAll(), HttpStatus.OK);
	}
	
	
	// ottieni device per ID
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDeviceById(@PathVariable Long id){
		Device d = deviceRepo.findById(id).get();
		if(d == null) {
			throw new EntityNotFoundException();
		}
		
		return new ResponseEntity<>(deviceRepo.findById(id), HttpStatus.OK);		
	}
	
	
	// ottieni device per seriale
	@GetMapping("/serial/{serial}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDeviceBySerial(@PathVariable int serial) {
		Device d = deviceRepo.findBySerialNumber(serial);
		if(d.getSerialNumber() == null) {
			throw new EntityNotFoundException();
		}
		return new ResponseEntity<>(d, HttpStatus.OK);
	}
	
	
	// crea device
	@PostMapping
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> addNewDevice(@RequestBody Device device) {
		
		return new ResponseEntity<>(deviceRepo.save(device), HttpStatus.CREATED);
	}
	
	
	// cancella device
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> deleteDevice(@PathVariable Long id){
		return new ResponseEntity<>(deviceServ.deleteDeviceById(id), HttpStatus.OK);
	}
	
	
	// modifica un device
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> editDevice(@RequestBody Device device, @PathVariable Long id)  {
		
		device.setId_device(id);	
		 return new ResponseEntity<>(deviceRepo.save(device), HttpStatus.OK);
	}
		
	
}
