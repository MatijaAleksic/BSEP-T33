package bsep.devices.services;

import bsep.devices.dto.DeviceMessageDTO;
import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;


public class DeviceService {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceService.class);


    public static void startDevices() throws URISyntaxException {

        String url = "http://localhost:8081/device_messsage";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(url);
        RestTemplate restTemplate = new RestTemplate();

        DeviceMessageDTO device1 = new DeviceMessageDTO(1L, "DOOR");
        DeviceMessageDTO device2 = new DeviceMessageDTO(2L, "WINDOW");
        DeviceMessageDTO device3 = new DeviceMessageDTO(3L, "CAMERA1");
        DeviceMessageDTO device4 = new DeviceMessageDTO(4L, "CAMERA2");

        ArrayList<DeviceMessageDTO> allDevices = new ArrayList<>();
        allDevices.add(device1);
        allDevices.add(device2);
        allDevices.add(device3);
        allDevices.add(device4);


        Random rn = new Random();

        Timer timer = new Timer();
        int begin = 1000; //timer starts after 1 second.
        int timeinterval = 10 * 1000; //timer executes every 10 seconds.
        timer.scheduleAtFixedRate(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {

                for(DeviceMessageDTO device : allDevices){
                    int answer = rn.nextInt(100) + 1;

                    if(answer <= 70){
                        device.setMessage("ALL QUIET!");
                    }
                    else if(answer <= 90){
                        device.setMessage("SOME EVENT HAPPENED! (DOOR OPENED, CAMERA SPOTED SOMEONE,...)");
                    }
                    else {
                        device.setMessage("ALARM!!! SOMETHING BAD HAPPENED!");
                    }

                    HttpEntity<DeviceMessageDTO> httpEntity = new HttpEntity<>(device, headers);
                    restTemplate.postForObject(uri, httpEntity, DeviceMessageDTO.class);

                }
                LOG.info("LOOP RADI!");
            }
        },begin, timeinterval);
    }


}
