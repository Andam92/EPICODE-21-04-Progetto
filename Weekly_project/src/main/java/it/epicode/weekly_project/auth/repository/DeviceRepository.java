package it.epicode.weekly_project.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.weekly_project.auth.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

	public boolean existsBySerialNumber(Long number);
	
	public Device findBySerialNumber(int serial);
	
    @Query (value = "SELECT u.deviceList FROM User u WHERE u.id = :id")
    List<Device> findUserDevices(Long id);
	
}
