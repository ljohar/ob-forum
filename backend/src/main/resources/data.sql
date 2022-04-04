INSERT INTO role (description, name) VALUES ('Admin role', 'ADMIN');
INSERT INTO role (description, name) VALUES ('User role', 'USER');
--TOPICS
INSERT INTO topics (fixed,synopsis,name) VALUES (true, 'Topic 1 description', 'Topic 1');
INSERT INTO topics (fixed,synopsis,name) VALUES (false, 'Topic 2 description', 'Topic 2');
INSERT INTO topics (fixed,synopsis,name) VALUES (true, 'Topic 3 description', 'Topic 3');

--COURSES
INSERT INTO courses (description,name) VALUES('Angular description', 'Angular');
INSERT INTO courses (description,name) VALUES('React description', 'React' );

--MODULES

INSERT INTO modules (description, name) VALUES('Description Module 1', 'Module 1');
INSERT INTO modules (description, name) VALUES('Description Module 2', 'Module 2');

--USER
INSERT INTO ob_user(email,name,password,username) VALUES ('user3@admin.com', 'user3','$2a$10$hqws6ko/.Q81YHR.V5.ZA.cFMkOelp/uRtzdK7KE80tRPROWUUdx.', 'user3@admin.com');
INSERT INTO user_roles(user_id, role_id) VALUES (1, 1);

INSERT INTO ob_user(email,name,password,username) values ('user5@user.com', 'user5','$2a$10$axAgOjrgIezhyEkMWovEzudRV4E08fPA2FDdfUv2aNWBo2f6GIwvK', 'user5@user.com');
INSERT INTO user_roles(user_id, role_id) values (2, 2);
INSERT INTO user_topics(user_id, topic_id) values(2,3);

--THREADS

INSERT INTO threads(content,fixed, posts_count, title, topic_id, user_id) VALUES('Content thread 1',false,1,'title thread 1', 1, 2);

--POSTS

INSERT INTO posts(content, fixed, thread_id, user_id, dislikes_count, likes_count) VALUES('content', false, 1, 2,0,0);

--TOPICS-COURSES
INSERT INTO topic_courses(topic_id,course_id) VALUES(1,1);
INSERT INTO topic_courses(topic_id,course_id) VALUES(1,2);
INSERT INTO topic_courses(topic_id,course_id) VALUES(2,1);

--TOPICS-MODULES
INSERT INTO topic_modules(topic_id,module_id) VALUES(1,1);
INSERT INTO topic_modules(topic_id,module_id) VALUES(2,1);
