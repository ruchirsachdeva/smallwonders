package com.smallwonders.model.core.page;

import lombok.Getter;

public enum PageType {
    HOME(Values.HOME),
    ABOUT_US(Values.ABOUT_US),
    CURRICULUM(Values.CURRICULUM),
    ADMISSION(Values.ADMISSION),
    FRANCHISEE(Values.FRANCHISEE),
    BRANCHES(Values.BRANCHES),
    BOOKS_AND_BEYOND(Values.BOOKS_AND_BEYOND),
    EVENT(Values.EVENT),
    FAQ(Values.FAQ),
    FOOTER(Values.FOOTER),
    HEADER(Values.HEADER),
    NEWS(Values.NEWS);


    @Getter
    private final String value;

    PageType(String value) {
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
        public static final String ABOUT_US = "ABOUT US";
        public static final String BRANCHES = "BRANCHES";
        public static final String BOOKS_AND_BEYOND = "BOOKS AND BEYOND";
        public static final String HOME = "HOME";
    }


}
