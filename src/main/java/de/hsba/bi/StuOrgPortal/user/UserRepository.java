package de.hsba.bi.StuOrgPortal.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//TODO: SINN ÜBERPRÜFEN

@Repository

interface UserRepository extends JpaRepository<User, Long> {


}