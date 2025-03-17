CREATE TABLE currency (
                          code VARCHAR(10) PRIMARY KEY,
                          chinese_name VARCHAR(50)
);

INSERT INTO currency (code, chinese_name) VALUES ('USD', '美元');
INSERT INTO currency (code, chinese_name) VALUES ('GBP', '英鎊');
INSERT INTO currency (code, chinese_name) VALUES ('EUR', '歐元');