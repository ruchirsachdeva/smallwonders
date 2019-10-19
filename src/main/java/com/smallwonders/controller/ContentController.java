package com.smallwonders.controller;

import com.smallwonders.dto.ContentDto;
import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.page.Page;
import com.smallwonders.model.core.section.Section;
import com.smallwonders.model.core.section.SectionType;
import com.smallwonders.repository.ContentRepository;
import com.smallwonders.repository.SectionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/content")
@Api(value = "Content API", description = "Operations pertaining to Content")
@Transactional
public class ContentController {


    @Autowired
    private ContentRepository repository;
    @Autowired
    private SectionRepository sectionRepository;


    @ApiOperation(value = "View a Content by section type", response = Content.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Page"),
            @ApiResponse(code = 401, message = "You are not authorized to view the Page"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{section}")
    public List<Content> getPages(@PathVariable SectionType section) {
        return repository.findBySections_Type(section);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping
    public ResponseEntity<Content> setContent(@RequestBody Content content) {
        return new ResponseEntity(repository.save(content), HttpStatus.OK);
    }


}
