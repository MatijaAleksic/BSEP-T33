package bsep.mojakuca.repository;

import bsep.mojakuca.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
    Home findByName(String name);
}
