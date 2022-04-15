package bsep.admin.repository;

import bsep.admin.model.CertificateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRequestRepository extends JpaRepository<CertificateRequest, Long> {

    CertificateRequest findByCommonName(String name);
    CertificateRequest findByUid(Long uid);
    CertificateRequest findByEmail(String email);

}
