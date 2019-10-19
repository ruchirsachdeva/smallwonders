package com.smallwonders.model.core.section;

import lombok.Getter;

public enum SectionType {
    CURRICULUM(Values.CURRICULUM),
    EVENT(Values.EVENT),
    FAQ(Values.FAQ),
    FOOTER(Values.FOOTER),
    HEADER(Values.HEADER),
    NEWS(Values.NEWS),
    ADMISSION(Values.ADMISSION),
    FRANCHISEE(Values.FRANCHISEE);


    @Getter
    private final String value;

    SectionType(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String CURRICULUM = "CURRICULUM";
        public static final String EVENT = "EVENT";
        public static final String FAQ = "FAQ";
        public static final String FOOTER = "FOOTER";
        public static final String HEADER = "HEADER";
        public static final String NEWS = "NEWS";
        public static final String ADMISSION = "ADMISSION";
        public static final String FRANCHISEE = "FRANCHISEE";
    }


}
