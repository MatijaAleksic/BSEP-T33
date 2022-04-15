package bsep.admin.controller;

import bsep.admin.DTO.CertificateRequestDTO;
import bsep.admin.Exceptions.CertificateNotFoundException;
import bsep.admin.mapper.CertificateRequestMapper;
import bsep.admin.service.CertificateRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/certificate_request", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateRequestController {

    @Autowired
    CertificateRequestService certificateRequestService;

    CertificateRequestMapper certificateRequestMapper;

    public CertificateRequestController() {
        certificateRequestMapper = new CertificateRequestMapper();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CertificateRequestDTO>> findAll() {

        List<CertificateRequestDTO> reqs = certificateRequestMapper.toDtoList(certificateRequestService.findAll());
        return new ResponseEntity<>(reqs, HttpStatus.OK);
    }

    @RequestMapping(value = "/send_request", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody CertificateRequestDTO certificateRequestDTO) {
        certificateRequestService.save(certificateRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = "text/plain")
    public ResponseEntity<?> delete(@RequestBody String id) throws CertificateNotFoundException {

        Long realId;
        try{
            realId = Long.parseLong(id);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            return new ResponseEntity<>(certificateRequestService.delete(realId), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
