package com.smallwonders.controller;

import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.page.PageType;
import com.smallwonders.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.smallwonders.controller.Util.responseEntity;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/content")
@Api(value = "Content API", description = "Operations pertaining to Content")
@Transactional
public class ContentController {


    @Autowired
    private ContentService service;


    @ApiOperation(value = "View a Content by section type", response = Content.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Content"),
            @ApiResponse(code = 401, message = "You are not authorized to view the Content"),
            @ApiResponse(code = 403, message = "Accessing the content you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The content you were trying to reach is not found")
    }
    )


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping
    public ResponseEntity<List<Content>> getAll() {
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/section/{type}")
    public ResponseEntity<List<Content>> getContents(@PathVariable PageType type) {
        return new ResponseEntity(service.getContents(type), HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        return new ResponseEntity(service.createContent(content), HttpStatus.OK);
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContent(@PathVariable Long id) {
        return responseEntity(service.getContent(id));
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{id}/section/add/{sectionIds}")
    public ResponseEntity<Content> addSectionsForContent(@PathVariable Long id, @PathVariable Long[] sectionIds) {
        return responseEntity(service.addSectionsForContent(id, sectionIds));
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{id}/section/remove/{sectionIds}")
    public ResponseEntity<Content> removeSectionsForContent(@PathVariable Long id, @PathVariable Long[] sectionIds) {
        return responseEntity(service.removeSectionsForContent(id, sectionIds));
    }


}
