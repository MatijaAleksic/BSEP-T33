package bsep.admin.service;

import bsep.admin.Exceptions.UserNotFoundException;
import bsep.admin.model.Admin;
import bsep.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository repository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    public List<Admin> findAll() {
        return repository.findAll();
    }

    public Admin findOne(Long id) throws UserNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Cannot find user by username: " + id.toString()));
    }

    public Admin save(Admin entity) throws Exception {
        return repository.save(entity);
    }

    public Admin update(Admin entity) throws Exception {
        Admin existingAdmin = findOne(entity.getId());

        if(existingAdmin!=null){
            existingAdmin.setEmail(entity.getEmail());
            existingAdmin.setPassword(entity.getPassword());
            existingAdmin.setUsername(entity.getUsername());

            return save(existingAdmin);
        }
        else{
            throw new UserNotFoundException("Cannot find user by username: " + existingAdmin.getId().toString());
        }
    }


    public void delete(Long id) throws UserNotFoundException {
        Admin existingAdmin = findOne(id);
        if(existingAdmin!=null){
            repository.delete(existingAdmin);
        }
        else{
            throw new UserNotFoundException("Cannot find user by username: " + existingAdmin.getId().toString());
        }
    }
}
