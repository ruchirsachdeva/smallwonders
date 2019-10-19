package com.smallwonders.repository;

import com.smallwonders.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by rucsac on 10/10/2018.
 */
@RepositoryRestResource
//@CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
public interface RoleRepository extends JpaRepository<Role, Long> {
    @RestResource
    Role findByName(@Param("role") String role);

}
