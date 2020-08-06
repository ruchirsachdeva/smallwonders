package com.smallwonders.repository;

import com.smallwonders.model.core.section.Section;
import com.smallwonders.model.core.page.PageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
//@CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
public interface SectionRepository extends JpaRepository<Section, Long> {

    @RestResource
    List<Section> findByType(@Param("type") PageType pageType);

}
