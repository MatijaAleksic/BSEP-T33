package backend.admin.repository;

import java.util.List;

import backend.admin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);
}
