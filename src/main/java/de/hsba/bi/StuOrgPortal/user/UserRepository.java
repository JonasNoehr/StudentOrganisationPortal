package de.hsba.bi.StuOrgPortal.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String email);

    List<User> findByRole(String role);


}
