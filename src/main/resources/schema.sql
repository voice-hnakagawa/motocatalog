DROP TABLE IF EXISTS m_motorcycle;
CREATE TABLE m_motorcycle (
    moto_no INT NOT NULL PRIMARY KEY COMMENT 'バイク番号',
    moto_name VARCHAR(255) COMMENT 'バイク名',
    seat_height INT comment 'シート高',
    cylinder INT comment 'シリンダー',
    cooling VARCHAR(255) comment '冷却',
    price INT comment '価格',
    comment VARCHAR(255) COMMENT 'コメント',
    brand_id INT comment 'ブランドID',
    version INT COMMENT 'バージョン',
    ins_dt DATETIME COMMENT '登録日時',
    upd_dt DATETIME COMMENT '更新日時'
) COMMENT 'モーターサイクルマスタ';

DROP TABLE IF EXISTS m_brand;
CREATE TABLE m_brand (
    brand_id INT NOT NULL PRIMARY KEY COMMENT 'ブランドID',
    brand_name VARCHAR(255) COMMENT 'ブランド名'
) COMMENT 'ブランドマスタ';

DROP TABLE IF EXISTS m_user;
CREATE TABLE m_user (
    -- user_id INT NOT NULL PRIMARY KEY COMMENT 'ユーザID',
    username VARCHAR(255) NOT NULL PRIMARY KEY COMMENT 'ユーザ名',
    password VARCHAR(255) COMMENT 'パスワード'
) COMMENT 'ユーザーマスタ';