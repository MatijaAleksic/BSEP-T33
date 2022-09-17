package bsep.devices;

import bsep.devices.services.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevicesApplication {

	private static final Logger LOG = LoggerFactory.getLogger(DevicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DevicesApplication.class, args);

		LOG.info("Devices pocinje da radi");

		DeviceService.startDevices();

	}

}
