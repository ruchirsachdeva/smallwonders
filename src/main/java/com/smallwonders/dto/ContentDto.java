package com.smallwonders.dto;

import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.content.ContentType;
import com.smallwonders.model.core.section.Section;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class ContentDto {
    private String title, description;
    private ContentType type;
    private MultipartFile data;
    private List<Long> sectionId;
    private List<Section> sections;

    private Date created;
    private Date updated;
    private Date deleted;

    private boolean visibleToPublic;

    public Content toContent(Set<Section> sections) throws IOException {
        byte[] bytes = data != null ? data.getBytes() : null;
        return new Content(title, description, type, bytes, sections, true);
    }
}
