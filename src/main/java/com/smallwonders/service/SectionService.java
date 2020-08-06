package com.smallwonders.service;

import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.section.Section;
import com.smallwonders.model.core.page.PageType;
import com.smallwonders.repository.ContentRepository;
import com.smallwonders.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionService {


    @Autowired
    private SectionRepository repository;


    @Autowired
    private ContentRepository contentRepository;


    public List<Section> getSection(PageType type) {
        return repository.findByType(type);
    }

    public Section createSection(Section section) {
        return repository.save(section);
    }


    public Optional<Section> addContentsToSection(Long id, Long[] contentIds) {
        return repository.findById(id)
                .map(section -> {
                    List<Content> contents = contentRepository.findAllById(Arrays.asList(contentIds));
                    section.addContents(contents);
                    return section;
                });
    }

    public Optional<Section> removeContentsFromSection(Long id, Long[] contentIds) {
        return repository.findById(id)
                .map(section -> {
                    List<Content> contents = contentRepository.findAllById(Arrays.asList(contentIds));
                    section.removeContents(contents);
                    return section;
                });
    }


    public Optional<Section> addCategoriesToSection(@PathVariable Long id, @PathVariable Section.Category[] categories) {
        return repository.findById(id)
                .map(section -> {
                    section.addCategories(Arrays.asList(categories));
                    return section;
                });
    }

    public Optional<Section> removeCategoriesFromSection(@PathVariable Long id, @PathVariable Section.Category[] categories) {
        return repository.findById(id)
                .map(section -> {
                    section.removeCategories(Arrays.asList(categories));
                    return section;
                });
    }
}
