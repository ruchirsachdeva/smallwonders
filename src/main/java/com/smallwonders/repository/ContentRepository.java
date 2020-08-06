package com.smallwonders.repository;

import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.page.PageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
//@CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
public interface ContentRepository extends JpaRepository<Content, Long> {

    @RestResource
    List<Content> findBySections_Type(@Param("type") PageType pageType);

}
