package it.epicode.weekly_project.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.weekly_project.auth.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

	public boolean existsBySerialNumber(Long number);
	
}
