package backend.admin.repository;

import backend.admin.model.CertificateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRequestRepository extends JpaRepository<CertificateRequest, Long> {
    CertificateRequest findByCommonName(String name);
}
