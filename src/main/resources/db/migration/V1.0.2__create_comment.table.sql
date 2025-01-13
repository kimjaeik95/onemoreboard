CREATE TABLE board.comment(
id BIGSERIAL PRIMARY KEY,
article_id BIGINT,
nickname VARCHAR(255),
content VARCHAR(255),
create_at TIMESTAMP,

FOREIGN KEY (article_id) REFERENCES board.articles(id)
)