package com.smallwonders.repository;

import com.smallwonders.model.core.page.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
//@CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
public interface PageRepository extends JpaRepository<Page, Long> {

    @RestResource
    List<Page> findByPageType(@Param("page") Page.PageType pageType);

}
