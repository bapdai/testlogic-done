package com.example.testlogic.repository;

import com.example.testlogic.entity.Role;
import com.example.testlogic.util.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /*
     * Find role by name
     * @param name
     * return role
     * */
    Optional<Role> findByName(ERole name);
}
