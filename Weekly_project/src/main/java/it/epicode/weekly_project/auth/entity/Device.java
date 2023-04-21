package it.epicode.weekly_project.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devices")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Device {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_device;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Device_status device_status;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Device_type device_type;
	private Long serialNumber;
	
	
}
