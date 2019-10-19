package com.smallwonders.controller;

import com.smallwonders.model.core.section.Event;
import com.smallwonders.model.core.section.Section;
import com.smallwonders.model.core.section.SectionType;
import com.smallwonders.repository.SectionRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public Section getSection(@ApiParam @PathVariable SectionType type) {
        return repository.findByType(type).orElse(null);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping
    public Section setSection(@RequestBody Section section) {
        return repository.save(section);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("/upload")
    public ResponseEntity<Section> uploadFile(@RequestParam("file") MultipartFile uploadfile) {


        if (uploadfile.isEmpty()) {
            return new ResponseEntity("You must select a file!", HttpStatus.OK);
        }

//        try {
//
//            saveUploadedFiles(Arrays.asList(uploadfile));
//
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity("Successfully uploaded - " + uploadfile.getOriginalFilename(), new HttpHeaders(),
                HttpStatus.OK);

    }

    // multiple upload
    @RequestMapping(value = "/rest/multipleupload", method = RequestMethod.POST)
    public ResponseEntity uploadFile(@RequestPart String metaData,
                                     @RequestPart(required = true) MultipartFile[] uploadfiles) {
        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

//        try {
//
//            saveUploadedFiles(Arrays.asList(uploadfiles));
//
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity("Successfully uploaded - " + uploadedFileName, HttpStatus.OK);
    }

//    // file download
//    @RequestMapping(path = "/rest/download", method = RequestMethod.GET)
//    public ResponseEntity<Resource> download(String param) throws IOException {
//
//        File file = new File(param);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        Path path = Paths.get(file.getAbsolutePath());
//        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//        return ResponseEntity.ok().headers(headers).contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
//    }
//
//    // save file
//    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
//
//        for (MultipartFile file : files) {
//
//            if (file.isEmpty()) {
//                continue; // next pls
//            }
//
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//        }



    }

