package it.epicode.weekly_project.auth.configuration;




import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

import it.epicode.weekly_project.auth.entity.Device;
import it.epicode.weekly_project.auth.entity.Device_status;
import it.epicode.weekly_project.auth.entity.Device_type;

@Configuration
public class DeviceConfig {

	@Bean
	@Scope("prototype")
	public Device createDevice(Device_type type) {
		Faker fakeDevice = Faker.instance(new Locale("it-IT"));
		return Device.builder()
				.device_status(Device_status.AVAILABLE)
				.device_type(type)
				.serialNumber((long) fakeDevice.number().numberBetween(1000, 10000))
				.build();
	}
	
}
