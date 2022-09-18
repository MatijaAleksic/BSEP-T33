package bsep.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bsep.admin.DTO.CertificateRequestDTO;
import bsep.admin.Exceptions.CertificateNotFoundException;
import bsep.admin.mapper.CertificateRequestMapper;
import bsep.admin.service.CertificateRequestService;

@Controller
@RequestMapping(value = "/certificate_request", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateRequestController {

	@Autowired
	CertificateRequestService certificateRequestService;

	CertificateRequestMapper certificateRequestMapper;

	public CertificateRequestController() {
		certificateRequestMapper = new CertificateRequestMapper();
	}

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('READ_CERTIFICATE_REQUESTS')")
	public ResponseEntity<?> findAll(Model model) {
		List<CertificateRequestDTO> reqs = certificateRequestMapper.toDtoList(certificateRequestService.findAll());
		return new ResponseEntity<>(reqs, HttpStatus.OK);
	}

	@RequestMapping(value = "/send_request", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('CREATE_CERTIFICATE_REQUESTS')")
	public ResponseEntity<?> create(@RequestBody CertificateRequestDTO certificateRequestDTO) {
		certificateRequestService.save(certificateRequestDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('DELETE_CERTIFICATE_REQUESTS')")
	public ResponseEntity<?> delete(@RequestBody String id) throws CertificateNotFoundException {
		Long realId = null;
		try {
			realId = Long.parseLong(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		try {
			return new ResponseEntity<>(certificateRequestService.deleteByUid(realId), HttpStatus.OK);
//				return new ResponseEntity<>(certificateRequestService.delete(realId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
