package backend.admin.service;

import backend.admin.repository.CertificateRequestRepository;
import backend.admin.DTO.CertificateRequestDTO;
import backend.admin.Exceptions.CertificateNotFoundException;
import backend.admin.model.CertificateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateRequestService {

    @Autowired
    private CertificateRequestRepository certificateRequestRepository;


    public List<CertificateRequest> findAll()
    {
        return certificateRequestRepository.findAll();
    }

    public CertificateRequest findOne(Long id) throws CertificateNotFoundException {
        return certificateRequestRepository
                .findById(id)
                .orElseThrow(() -> new CertificateNotFoundException("Cannot find certificate by id: " + id.toString()));

    }

//    public CertificateRequest findByEmail(String email)
//    {
//        return certificateRequestRepository.findByEmail(email);
//    }

    public CertificateRequest findByCommonName(String email)
    {
        return certificateRequestRepository.findByCommonName(email);
    }


    public void save(CertificateRequestDTO certificateRequestDTO)
    {
        CertificateRequest cr = new CertificateRequest(certificateRequestDTO.getCommonName(),
                certificateRequestDTO.getSurname(),certificateRequestDTO.getGivenName(),
                certificateRequestDTO.getOrganization(),certificateRequestDTO.getOrganizationUnit(),
                certificateRequestDTO.getCountry(),certificateRequestDTO.getEmail(), certificateRequestDTO.getCertExtension());

        certificateRequestRepository.save(cr);
    }



    public boolean delete(String commonName) throws CertificateNotFoundException {
        CertificateRequest cr = certificateRequestRepository.findByCommonName(commonName);
        if (cr != null) {
            certificateRequestRepository.delete(cr);
            return true;
        }
        else{
            throw new CertificateNotFoundException("Cannot find certificate by Common Name: " + commonName);
        }

    }

    public boolean deleteByCommonName(String commonName) throws CertificateNotFoundException {
        CertificateRequest cr = findByCommonName(commonName);
        if (cr != null) {
            certificateRequestRepository.delete(cr);
            return true;
        }
        else{
            throw new CertificateNotFoundException("Cannot find certificate by Common Name: " + commonName);
        }

    }
}
