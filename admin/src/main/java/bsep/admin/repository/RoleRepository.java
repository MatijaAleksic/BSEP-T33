package bsep.admin.repository;

import java.util.List;

import bsep.admin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);
}
