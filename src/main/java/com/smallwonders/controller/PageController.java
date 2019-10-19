package com.smallwonders.controller;

import com.smallwonders.model.core.page.Page;
import com.smallwonders.repository.PageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/page")
@Api(value="Page API", description="Operations pertaining to Page")
@Transactional
public class PageController {


    @Autowired
    private PageRepository pageRepository;


    @ApiOperation(value = "View a Page by type", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Page"),
            @ApiResponse(code = 401, message = "You are not authorized to view the Page"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/{page}")
    public List<Page> getPages(@PathVariable Page.PageType page) {
        return pageRepository.findByPageType(page);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("/{type}")
    public Page setPage(@PathVariable Page.PageType type) {
        Page page = Page.dummyPage();
        page.setPageType(type);
        return pageRepository.save(page);
    }


}
