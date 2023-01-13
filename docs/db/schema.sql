create table user
(
    id           bigint(20)   not null auto_increment,
    user_id      varchar(256) not null comment '로그인 아이디(이메일 or 휴대폰번호)',
    password     varchar(100) not null comment '비밀번호',
    full_name    varchar(50)  not null comment '검색 사용자 이름',
    user_name    varchar(50)  not null comment '화면에 보여질 사용자 이름',
    birth_date   varchar(10)  not null comment '생년월일',
    email        varchar(256) null comment '이메일',
    phone_number varchar(13)  null comment '휴대폰 번호',
    primary key (id)
) comment '회원정보';

create table email_auth
(
    id           bigint(20)   not null auto_increment,
    email        varchar(256) not null comment '인증 발송 이메일',
    auth_token   varchar(250) not null comment '인증 토큰',
    expired_date timestamp    not null comment '만료일자',
    expired      bit(1)       not null comment '만료여부',
    primary key (id)
) comment '이메일 인증코드';

create table follow
(
    id        bigint(20) not null auto_increment,
    follower  bigint(20) not null,
    following bigint(20) not null,
    primary key (id)
) comment '팔로우 관계';

create table post
(
    id         bigint(20)    not null auto_increment,
    content    varchar(2000) not null,
    user_id    bigint(20)    not null,
    created_at timestamp     not null,
    updated_at timestamp,
    deleted_at timestamp,
    primary key (id)
) comment '게시글';

create table image
(
    id      bigint(20)   not null auto_increment,
    url     varchar(255) not null,
    post_id bigint(20)   not null,
    primary key (id)
) comment '게시글 첨부 사진';

create table comment
(
    id         bigint(20)    not null auto_increment,
    post_id    bigint(20)    not null,
    content    varchar(2000) not null,
    user_id    bigint(20)    not null,
    create_at  timestamp     not null,
    updated_at timestamp,
    deleted_at timestamp,
    primary key (id)
) comment '게시글 댓글';

create table likes
(
    id      bigint(20) not null auto_increment,
    post_id bigint(20) not null,
    user_id bigint(20) not null
) comment '게시글 좋아요';

create table hashtag
(
    id   bigint(20)  not null auto_increment,
    name varchar(20) not null,
    primary key (id)
) comment '해시태그';

create table post_hashtag
(
    id         bigint(20) not null auto_increment,
    post_id    bigint(20) not null,
    hashtag_id bigint(20) not null,
    primary key (id)
) comment '게시글 해시태그';

-- Unique Key
alter table follow
    add constraint uk_follow_follower_following(follower, following);

alter table hashtag
    add constraint uk_hashtag_name(name);

-- Foreign Key
alter table follow
    add constraint fk_follow_follower foreign key (follower) references user (id);

alter table follow
    add constraint fk_follow_following foreign key (following) references user (id);

alter table post
    add constraint fk_post_user_id foreign key (user_id) references user (id);

alter table image
    add constraint fk_image_post_id foreign key (post_id) references post (id);

alter table comment
    add constraint fk_post_post_id foreign key (post_id) references post (id);

alter table comment
    add constraint fk_post_user_id foreign key (post_id) references user (id);

alter table likes
    add constraint fk_likes_post_id foreign key (post_id) references post (id);

alter table likes
    add constraint fk_likes_user_id foreign key (user_id) references user (id);

alter table post_hashtag
    add constraint fk_post_hash_tag_post_id foreign key (post_id) references post (id);

alter table post_hashtag
    add constraint fk_post_hash_tag_hashtag_id foreign key (hashtag_id) references hashtag (id);