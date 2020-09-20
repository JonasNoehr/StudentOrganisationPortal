package de.hsba.bi.StuOrgPortal.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByEmail(String email);

    List<User> findByRole(String role);



}
