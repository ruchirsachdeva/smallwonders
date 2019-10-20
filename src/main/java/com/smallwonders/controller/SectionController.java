package com.smallwonders.controller;

import com.smallwonders.model.core.section.Section;
import com.smallwonders.model.core.section.SectionType;
import com.smallwonders.service.SectionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.smallwonders.controller.Util.responseEntity;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/section")
@Api(value = "Section API", description = "Operations pertaining to Section")
public class SectionController {


    @Autowired
    private SectionService service;


    @ApiOperation(value = "Save and View a Section", response = Section.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Section"),
            @ApiResponse(code = 401, message = "You are not authorized to view the Section"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{type}")
    public ResponseEntity<Section> getSection(@ApiParam @PathVariable SectionType type) {
        Optional<Section> section = service.getSection(type);
        return responseEntity(section);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping
    public Section setSection(@RequestBody Section section) {
        return service.createSection(section);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{id}/contents/add/{contentIds}")
    public ResponseEntity<Section> addContentsToSection(@PathVariable Long id, @PathVariable Long[] contentIds) {
        return responseEntity(service.addContentsToSection(id, contentIds));
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{id}/contents/remove/{contentIds}")
    public ResponseEntity<Section> removeContentsFromSection(@PathVariable Long id, @PathVariable Long[] contentIds) {
        return responseEntity(service.removeContentsFromSection(id, contentIds));
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{id}/categories/add/{categories}")
    public ResponseEntity<Section> addCategoriesToSection(@PathVariable Long id, @PathVariable Section.Category[] categories) {
        return responseEntity(service.addCategoriesToSection(id, categories));
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{id}/categories/remove/{categories}")
    public ResponseEntity<Section> removeCategoriesFromSection(@PathVariable Long id, @PathVariable Section.Category[] categories) {
        return responseEntity(service.removeCategoriesFromSection(id, categories));
    }




}

