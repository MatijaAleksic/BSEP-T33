package bsep.admin.DTO;

import bsep.admin.model.SubjectData;

import java.security.PrivateKey;

public class SubjectDataDTO {

    private PrivateKey pk;
    private SubjectData sd;

    public SubjectDataDTO(PrivateKey pk, SubjectData sd) {
        this.pk = pk;
        this.sd = sd;
    }

    public PrivateKey getPk() {
        return pk;
    }

    public void setPk(PrivateKey pk) {
        this.pk = pk;
    }

    public SubjectData getSd() {
        return sd;
    }

    public void setSd(SubjectData sd) {
        this.sd = sd;
    }
}
