package bsep.admin.repository;

import bsep.admin.model.Device;
import bsep.admin.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
    Home findByName(String name);
}
