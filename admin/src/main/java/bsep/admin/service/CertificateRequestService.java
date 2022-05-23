//package bsep.admin.service;
//
//import bsep.admin.DTO.CertificateRequestDTO;
//import bsep.admin.Exceptions.CertificateNotFoundException;
//import bsep.admin.Exceptions.UserNotFoundException;
//import bsep.admin.model.CertificateRequest;
//import bsep.admin.repository.CertificateRequestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CertificateRequestService {
//
//    @Autowired
//    private CertificateRequestRepository certificateRequestRepository;
//
//
//    public List<CertificateRequest> findAll()
//    {
//        return certificateRequestRepository.findAll();
//    }
//
//    public CertificateRequest findOne(Long id) throws CertificateNotFoundException {
//        return certificateRequestRepository
//                .findById(id)
//                .orElseThrow(() -> new CertificateNotFoundException("Cannot find certificate by id: " + id.toString()));
//
//    }
//
//    public CertificateRequest findByEmail(String email)
//    {
//        return certificateRequestRepository.findByEmail(email);
//    }
//
//    public CertificateRequest findByUid(long uid)
//    {
//        return certificateRequestRepository.findByUid(uid);
//    }
//
//    public void save(CertificateRequestDTO certificateRequestDTO)
//    {
//        CertificateRequest cr = new CertificateRequest(certificateRequestDTO.getCommonName(),
//                certificateRequestDTO.getSurname(),certificateRequestDTO.getGivenName(),
//                certificateRequestDTO.getOrganization(),certificateRequestDTO.getOrganizationUnit(),
//                certificateRequestDTO.getCountry(),certificateRequestDTO.getEmail(),certificateRequestDTO.getUid());
//
//        certificateRequestRepository.save(cr);
//    }
//
//
//
//    public boolean delete(Long id) throws CertificateNotFoundException {
//        CertificateRequest cr = findOne(id);
//        if (cr != null) {
//            certificateRequestRepository.delete(cr);
//            return true;
//        }
//        else{
//            throw new CertificateNotFoundException("Cannot find certificate by id: " + id);
//        }
//
//    }
//
//    public boolean deleteByUid(Long uid) throws CertificateNotFoundException {
//        CertificateRequest cr = findByUid(uid);
//        if (cr != null) {
//            certificateRequestRepository.delete(cr);
//            return true;
//        }
//        else{
//            throw new CertificateNotFoundException("Cannot find certificate by uid: " + uid);
//        }
//
//    }
//}
