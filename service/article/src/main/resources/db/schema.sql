CREATE TABLE article (
    article_id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(3000) NOT NULL,
    board_id BIGINT NOT NULL,
    writer_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    state VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',

    INDEX idx_board_id_article_id (board_id asc, article_id desc)
);

CREATE TABLE board_article_count (
    board_id BIGINT NOT NULL primary key,
    article_count BIGINT NOT NULL
);
