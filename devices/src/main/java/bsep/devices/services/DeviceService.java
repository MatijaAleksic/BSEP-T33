package bsep.devices.services;

import bsep.devices.dto.DeviceMessageDTO;
import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;


public class DeviceService {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceService.class);


    public static void startDevices(){
        Timer timer = new Timer();
        int begin = 1000; //timer starts after 1 second.
        int timeinterval = 10 * 1000; //timer executes every 10 seconds.
        timer.scheduleAtFixedRate(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                String url = "http://localhost:8081/device/message";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                URI uri = new URI(url);

                DeviceMessageDTO dto = new DeviceMessageDTO(1L, "Hello from 8082!");
                HttpEntity<DeviceMessageDTO> httpEntity = new HttpEntity<>(dto, headers);

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.postForObject(uri, httpEntity, DeviceMessageDTO.class);


                LOG.info("LOOP RADI!");
            }
        },begin, timeinterval);
    }


}
