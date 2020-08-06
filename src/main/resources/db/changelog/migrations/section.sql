
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

 create table Content (
       content_id bigint not null auto_increment,
        created datetime,
        data longblob,
        deleted datetime,
        description varchar(255),
        title varchar(255),
        type integer,
        updated datetime,
        visible_to_public bit not null,
        primary key (content_id)
    );

     create table Feedback (
       feedback_id bigint not null auto_increment,
        content_content_id bigint,
        user_id bigint,
        primary key (feedback_id)
    );

     create table Organization (
       organization_id bigint not null auto_increment,
        coordinates_id bigint,
        latitude double precision not null,
        longitude double precision not null,
        content_content_id bigint,
        primary key (organization_id)
    );

     create table Role (
       role_id bigint not null auto_increment,
        name varchar(255),
        primary key (role_id)
    );

     create table role_type (
       role_id bigint not null,
        type varchar(255)
    );

    create table Section (
       section_id bigint not null auto_increment,
        description varchar(255),
        rendering varchar(255),
        title varchar(255),
        TYPE varchar(255) default 'EVENT' not null,
        primary key (section_id)
    );

 create table section_category (
       section_id bigint not null,
        categories varchar(255)
    );

    create table section_content (
       section_id bigint not null,
        content_id bigint not null
    );

     create table User (
       user_id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        image longblob,
        is_verified bit not null,
        last_name varchar(255),
        password varchar(255),
        password_confirm varchar(255),
        username varchar(255),
        manager_id bigint,
        parent_id bigint,
        role_id bigint,
        primary key (user_id)
    );


     create table user_grade (
       grade_id bigint not null,
        grades varchar(255)
    );


     create table user_organization (
       user_id bigint not null,
        organization_id bigint not null,
        primary key (user_id, organization_id)
    );

--     alter table Section
--        drop index UK_hq8y00c1uc1oq1ieusy25c6ui;

--     alter table Section
--        add constraint UK_section_type unique (TYPE);

    alter table Feedback
       add constraint FK_feedback_content
       foreign key (content_content_id)
       references Content (content_id);

    alter table Feedback
       add constraint FK_feedback_user
       foreign key (user_id)
       references User (user_id);

    alter table Organization
       add constraint FK_org_content
       foreign key (content_content_id)
       references Content (content_id);


    alter table role_type
       add constraint FK_roletype_role
       foreign key (role_id)
       references Role (role_id);

    alter table section_category
       add constraint FK_sec_cat_sec
       foreign key (section_id)
       references Section (section_id);


    alter table section_content
       add constraint FK_sec_con_con
       foreign key (content_id)
       references Content (content_id);


    alter table section_content
       add constraint FKsec_con_sec
       foreign key (section_id)
       references Section (section_id);

    alter table User
       add constraint FK_user_mgr
       foreign key (manager_id)
       references User (user_id);

    alter table User
       add constraint FK_user_parent
       foreign key (parent_id)
       references User (user_id);

    alter table User
       add constraint FK_user_role
       foreign key (role_id)
       references Role (role_id);

    alter table user_grade
       add constraint FK_user_grade_grade
       foreign key (grade_id)
       references User (user_id);

    alter table user_organization
       add constraint FK_user_org_org
       foreign key (organization_id)
       references Organization (organization_id);

    alter table user_organization
       add constraint FK_user_org_user
       foreign key (user_id)
       references User (user_id);


-- Dumping data for table `section`
--

-- INSERT INTO `note` (`note_id`, `request_id`, `note`, `provider_id`) VALUES
--   (1, 1, 'Well this is interesting.', 2),
--   (2, 1, 'This seams a bit weird.', 1);

INSERT into `section` (`section_id`, `title`, `description`, `rendering`, `type`  ) values
(1,"Event", "Selectively chosen images from Small Wonders", "CAROUSAL", "EVENT"),
(2,"Curriculum", "Curriculum of Small Wonders", "GRID", "CURRICULUM"),
(3,"News", "News of Small Wonders", "STACK", "NEWS"),
(4,"Header", "Header of Small Wonders", "CAROUSAL", "HEADER"),
(5,"Event", "Selectively chosen images from Small Wonders", "CAROUSAL", "HOME"),
(6,"Home 1", "Home carousal image 1", "CAROUSAL", "HOME"),
(7,"Home 2", "Home car 2", "CAROUSAL", "HOME"),
(8,"Home st 1", "Home stack 1", "STACK", "HOME"),
(9,"Home st 2", "Home stack 2", "STACK", "HOME");