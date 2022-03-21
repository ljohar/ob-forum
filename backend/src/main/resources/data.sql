INSERT INTO role (description, name) VALUES ('Admin role', 'ADMIN');
INSERT INTO role (description, name) VALUES ('User role', 'USER');

INSERT INTO topics (description,name) VALUES ('Angular description', 'Angular');
INSERT INTO topics (description,name) VALUES ('React description', 'React');
INSERT INTO topics (description,name) VALUES ('Python description', 'Python');

INSERT INTO ob_user(email,name,password,username) values ('user3@admin.com', 'user3','$2a$10$hqws6ko/.Q81YHR.V5.ZA.cFMkOelp/uRtzdK7KE80tRPROWUUdx.', 'user3@admin.com');
INSERT INTO user_roles(user_id, role_id) values (1, 1);

INSERT INTO ob_user(email,name,password,username) values ('user5@user.com', 'user5','$2a$10$axAgOjrgIezhyEkMWovEzudRV4E08fPA2FDdfUv2aNWBo2f6GIwvK', 'user5@user.com');
INSERT INTO user_roles(user_id, role_id) values (2, 2);
INSERT INTO user_topics(user_id, topic_id) values(2,3);

