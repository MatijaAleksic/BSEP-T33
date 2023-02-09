package backend.admin.mapper;

import backend.admin.DTO.CertificateRequestDTO;
import backend.admin.model.CertificateRequest;

import java.util.ArrayList;
import java.util.List;

public class CertificateRequestMapper implements MapperInterface<CertificateRequest, CertificateRequestDTO>{

    @Override
    public CertificateRequest toEntity(CertificateRequestDTO dto) {
        return new CertificateRequest(dto.getCommonName(),dto.getSurname(),dto.getGivenName(),dto.getOrganization(),dto.getOrganizationUnit(),dto.getCountry(),dto.getEmail(), dto.getCertExtension());
    }

    @Override
    public CertificateRequestDTO toDto(CertificateRequest entity) {
        return new CertificateRequestDTO(entity.getCommonName(),entity.getSurname(),entity.getGivenName(),entity.getOrganization(),entity.getOrganizationUnit(),entity.getCountry(),entity.getEmail(), entity.getExtension());
    }

    public List<CertificateRequestDTO> toDtoList(List<CertificateRequest> requests)
    {
        ArrayList<CertificateRequestDTO> lista = new ArrayList<>();
        for(CertificateRequest r : requests)
        {
            lista.add(this.toDto(r));
        }
        return lista;
    }

}
