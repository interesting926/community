CREATE table question
(
  id int AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50),
  description TEXT,
  gmt_create bigint,
  gmt_modified bigint,
  creator int,
  comment_count INT DEFAULT 0,
  view_count int DEFAULT 0,
  like_count int DEFAULT 0,
  tag VARCHAR(256)
);