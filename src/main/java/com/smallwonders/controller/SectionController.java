package com.smallwonders.controller;

import com.smallwonders.model.core.section.Event;
import com.smallwonders.model.core.section.Section;
import com.smallwonders.model.core.section.SectionType;
import com.smallwonders.repository.SectionRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/section")
@Api(value="Section API", description="Operations pertaining to Section")
public class SectionController {


    @Autowired
    private SectionRepository repository;


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
    public List<Section> getSection(@ApiParam @PathVariable SectionType type) {
        return repository.findByType(type);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping
    public Section setSection(@RequestBody Section section) {
        return repository.save(section);
    }


}

