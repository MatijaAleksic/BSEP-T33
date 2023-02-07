package bsep.admin.repository;

import bsep.admin.model.CertificateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificateRequestRepository extends JpaRepository<CertificateRequest, Long> {
    CertificateRequest findByCommonName(String name);
}
