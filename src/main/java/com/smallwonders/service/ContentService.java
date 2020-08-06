package com.smallwonders.service;

import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.section.Section;
import com.smallwonders.model.core.page.PageType;
import com.smallwonders.repository.ContentRepository;
import com.smallwonders.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContentService {

    @Autowired
    private ContentRepository repository;
    @Autowired
    private SectionRepository sectionRepository;

    public List<Content> getAll() {
        return repository.findAll();
    }

    public List<Content> getContents(PageType type) {
        return repository.findBySections_Type(type);
    }

    public Content createContent(Content content) {
        return repository.save(content);
    }


    public Optional<Content> getContent(Long id) {
        return repository.findById(id);
    }

    public Optional<Content> addSectionsForContent(Long id, Long[] sectionIds) {
        return repository.findById(id)
                .map(content -> {
                    List<Section> sections = sectionRepository.findAllById(Arrays.asList(sectionIds));
                    content.addSections(sections);
                    return content;
                });
    }

    public Optional<Content> removeSectionsForContent(Long id, Long[] sectionIds) {
        return repository.findById(id)
                .map(content -> {
                    List<Section> sections = sectionRepository.findAllById(Arrays.asList(sectionIds));
                    content.removeSections(sections);
                    return content;
                });
    }
}
