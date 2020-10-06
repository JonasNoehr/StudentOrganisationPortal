package de.hsba.bi.StuOrgPortal.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findUserById(Long id);

}
