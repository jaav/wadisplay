# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table attitude (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_attitude primary key (id))
;

create table computers (
  id                        bigint not null,
  name                      varchar(255),
  introduced                timestamp,
  discontinued              timestamp,
  constraint pk_computers primary key (id))
;

create table conversation_type (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_conversation_type primary key (id))
;

create table linked_account (
  id                        bigint not null,
  user_id                   bigint,
  provider_user_id          varchar(255),
  provider_key              varchar(255),
  constraint pk_linked_account primary key (id))
;

create table origin (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_origin primary key (id))
;

create table presentation (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_presentation primary key (id))
;

create table product (
  id                        bigint not null,
  description               varchar(255),
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_product primary key (id))
;

create table product_question (
  id                        bigint not null,
  question                  varchar(255),
  last_modified             timestamp not null,
  constraint pk_product_question primary key (id))
;

create table question_type (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_question_type primary key (id))
;

create table refer (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_refer primary key (id))
;

create table registrations (
  id                        bigint not null,
  date                      timestamp,
  timer                     varchar(255),
  duration                  integer,
  conversation_type         bigint,
  question                  varchar(255),
  regUser                   bigint,
  gender                    varchar(1),
  age                       integer,
  zipcode                   varchar(255),
  location                  varchar(255),
  presentation              bigint,
  attitude                  bigint,
  question_type             bigint,
  interrupted               boolean,
  has_violence              boolean,
  tp_gender                 varchar(1),
  tp_age                    integer,
  tp_usage_type             bigint,
  origin                    bigint,
  constraint pk_registrations primary key (id))
;

create table relation (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_relation primary key (id))
;

create table security_role (
  id                        bigint not null,
  role_name                 varchar(255),
  constraint pk_security_role primary key (id))
;

create table social_context (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_social_context primary key (id))
;

create table task (
  id                        bigint not null,
  name                      varchar(255),
  creator                   bigint,
  previous_assignee         bigint,
  assignee                  bigint,
  description               varchar(255),
  creation_date             timestamp,
  due_date                  timestamp,
  importance                integer,
  status                    integer,
  task_type                 integer,
  registration              bigint,
  last_modified             timestamp not null,
  constraint ck_task_status check (status in (0,1,2)),
  constraint ck_task_task_type check (task_type in (0,1,2)),
  constraint pk_task primary key (id))
;

create table task_type (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_task_type primary key (id))
;

create table token_action (
  id                        bigint not null,
  token                     varchar(255),
  target_user_id            bigint,
  type                      varchar(2),
  created                   timestamp,
  expires                   timestamp,
  constraint ck_token_action_type check (type in ('EV','PR')),
  constraint uq_token_action_token unique (token),
  constraint pk_token_action primary key (id))
;

create table usage_type (
  id                        bigint not null,
  name                      varchar(255),
  last_modified             timestamp not null,
  constraint pk_usage_type primary key (id))
;

create table users (
  id                        bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  picture                   varchar(255),
  last_login                timestamp,
  active                    boolean,
  email_validated           boolean,
  constraint pk_users primary key (id))
;

create table user_permission (
  id                        bigint not null,
  value                     varchar(255),
  constraint pk_user_permission primary key (id))
;


create table product_map (
  registration                   bigint not null,
  product                        bigint not null,
  constraint pk_product_map primary key (registration, product))
;

create table product_question_map (
  registration                   bigint not null,
  product_question               bigint not null,
  constraint pk_product_question_map primary key (registration, product_question))
;

create table relation_map (
  registration                   bigint not null,
  relation                       bigint not null,
  constraint pk_relation_map primary key (registration, relation))
;

create table social_context_map (
  registration                   bigint not null,
  social_context                 bigint not null,
  constraint pk_social_context_map primary key (registration, social_context))
;

create table referrer_map (
  registration                   bigint not null,
  referrer                       bigint not null,
  constraint pk_referrer_map primary key (registration, referrer))
;

create table task_map (
  registration                   bigint not null,
  task                           bigint not null,
  constraint pk_task_map primary key (registration, task))
;

create table users_security_role (
  users_id                       bigint not null,
  security_role_id               bigint not null,
  constraint pk_users_security_role primary key (users_id, security_role_id))
;

create table users_user_permission (
  users_id                       bigint not null,
  user_permission_id             bigint not null,
  constraint pk_users_user_permission primary key (users_id, user_permission_id))
;
create sequence attitude_seq;

create sequence computers_seq;

create sequence conversation_type_seq;

create sequence linked_account_seq;

create sequence origin_seq;

create sequence presentation_seq;

create sequence product_seq;

create sequence product_question_seq;

create sequence question_type_seq;

create sequence refer_seq;

create sequence registrations_seq;

create sequence relation_seq;

create sequence security_role_seq;

create sequence social_context_seq;

create sequence task_seq;

create sequence task_type_seq;

create sequence token_action_seq;

create sequence usage_type_seq;

create sequence users_seq;

create sequence user_permission_seq;

alter table linked_account add constraint fk_linked_account_user_1 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_linked_account_user_1 on linked_account (user_id);
alter table registrations add constraint fk_registrations_conversationT_2 foreign key (conversation_type) references conversation_type (id) on delete restrict on update restrict;
create index ix_registrations_conversationT_2 on registrations (conversation_type);
alter table registrations add constraint fk_registrations_regUser_3 foreign key (regUser) references users (id) on delete restrict on update restrict;
create index ix_registrations_regUser_3 on registrations (regUser);
alter table registrations add constraint fk_registrations_presentation_4 foreign key (presentation) references presentation (id) on delete restrict on update restrict;
create index ix_registrations_presentation_4 on registrations (presentation);
alter table registrations add constraint fk_registrations_attitude_5 foreign key (attitude) references attitude (id) on delete restrict on update restrict;
create index ix_registrations_attitude_5 on registrations (attitude);
alter table registrations add constraint fk_registrations_questionType_6 foreign key (question_type) references question_type (id) on delete restrict on update restrict;
create index ix_registrations_questionType_6 on registrations (question_type);
alter table registrations add constraint fk_registrations_tpUsageType_7 foreign key (tp_usage_type) references usage_type (id) on delete restrict on update restrict;
create index ix_registrations_tpUsageType_7 on registrations (tp_usage_type);
alter table registrations add constraint fk_registrations_origin_8 foreign key (origin) references origin (id) on delete restrict on update restrict;
create index ix_registrations_origin_8 on registrations (origin);
alter table task add constraint fk_task_creator_9 foreign key (creator) references users (id) on delete restrict on update restrict;
create index ix_task_creator_9 on task (creator);
alter table task add constraint fk_task_previousAssignee_10 foreign key (previous_assignee) references users (id) on delete restrict on update restrict;
create index ix_task_previousAssignee_10 on task (previous_assignee);
alter table task add constraint fk_task_assignee_11 foreign key (assignee) references users (id) on delete restrict on update restrict;
create index ix_task_assignee_11 on task (assignee);
alter table task add constraint fk_task_registration_12 foreign key (registration) references registrations (id) on delete restrict on update restrict;
create index ix_task_registration_12 on task (registration);
alter table token_action add constraint fk_token_action_targetUser_13 foreign key (target_user_id) references users (id) on delete restrict on update restrict;
create index ix_token_action_targetUser_13 on token_action (target_user_id);



alter table product_map add constraint fk_product_map_registrations_01 foreign key (registration) references registrations (id) on delete restrict on update restrict;

alter table product_map add constraint fk_product_map_product_02 foreign key (product) references product (id) on delete restrict on update restrict;

alter table product_question_map add constraint fk_product_question_map_regis_01 foreign key (registration) references registrations (id) on delete restrict on update restrict;

alter table product_question_map add constraint fk_product_question_map_produ_02 foreign key (product_question) references product_question (id) on delete restrict on update restrict;

alter table relation_map add constraint fk_relation_map_registrations_01 foreign key (registration) references registrations (id) on delete restrict on update restrict;

alter table relation_map add constraint fk_relation_map_relation_02 foreign key (relation) references relation (id) on delete restrict on update restrict;

alter table social_context_map add constraint fk_social_context_map_registr_01 foreign key (registration) references registrations (id) on delete restrict on update restrict;

alter table social_context_map add constraint fk_social_context_map_social__02 foreign key (social_context) references social_context (id) on delete restrict on update restrict;

alter table referrer_map add constraint fk_referrer_map_registrations_01 foreign key (registration) references registrations (id) on delete restrict on update restrict;

alter table referrer_map add constraint fk_referrer_map_refer_02 foreign key (referrer) references refer (id) on delete restrict on update restrict;

alter table task_map add constraint fk_task_map_registrations_01 foreign key (registration) references registrations (id) on delete restrict on update restrict;

alter table task_map add constraint fk_task_map_task_02 foreign key (task) references task (id) on delete restrict on update restrict;

alter table users_security_role add constraint fk_users_security_role_users_01 foreign key (users_id) references users (id) on delete restrict on update restrict;

alter table users_security_role add constraint fk_users_security_role_securi_02 foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;

alter table users_user_permission add constraint fk_users_user_permission_user_01 foreign key (users_id) references users (id) on delete restrict on update restrict;

alter table users_user_permission add constraint fk_users_user_permission_user_02 foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists attitude;

drop table if exists computers;

drop table if exists conversation_type;

drop table if exists linked_account;

drop table if exists origin;

drop table if exists presentation;

drop table if exists product;

drop table if exists product_question;

drop table if exists question_type;

drop table if exists refer;

drop table if exists registrations;

drop table if exists product_map;

drop table if exists product_question_map;

drop table if exists relation_map;

drop table if exists social_context_map;

drop table if exists referrer_map;

drop table if exists task_map;

drop table if exists relation;

drop table if exists security_role;

drop table if exists social_context;

drop table if exists task;

drop table if exists task_type;

drop table if exists token_action;

drop table if exists usage_type;

drop table if exists users;

drop table if exists users_security_role;

drop table if exists users_user_permission;

drop table if exists user_permission;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists attitude_seq;

drop sequence if exists computers_seq;

drop sequence if exists conversation_type_seq;

drop sequence if exists linked_account_seq;

drop sequence if exists origin_seq;

drop sequence if exists presentation_seq;

drop sequence if exists product_seq;

drop sequence if exists product_question_seq;

drop sequence if exists question_type_seq;

drop sequence if exists refer_seq;

drop sequence if exists registrations_seq;

drop sequence if exists relation_seq;

drop sequence if exists security_role_seq;

drop sequence if exists social_context_seq;

drop sequence if exists task_seq;

drop sequence if exists task_type_seq;

drop sequence if exists token_action_seq;

drop sequence if exists usage_type_seq;

drop sequence if exists users_seq;

drop sequence if exists user_permission_seq;

