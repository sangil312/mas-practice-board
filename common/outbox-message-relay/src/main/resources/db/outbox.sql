CREATE TABLE outbox (
    id BIGINT NOT NULL PRIMARY KEY,
    shard_key BIGINT NOT NULL,
    event_type VARCHAR(100) NOT NULL,
    payload VARCHAR(5000) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_shard_key_created_at (shard_key, created_at)
);
