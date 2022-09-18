package bsep.devices;

import bsep.devices.services.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;

@SpringBootApplication
public class DevicesApplication {

	private static final Logger LOG = LoggerFactory.getLogger(DevicesApplication.class);

	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(DevicesApplication.class, args);

		LOG.info("Devices pocinje da radi");

		DeviceService.startDevices();

	}

}
