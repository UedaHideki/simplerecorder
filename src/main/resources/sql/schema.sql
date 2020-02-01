CREATE TABLE Note (
 date DATE,
 name VARCHAR(255),
 description VARCHAR(255)
);


CREATE TABLE Project (
 id INT NOT NULL AUTO_INCREMENT,
 name VARCHAR(255),
 no VARCHAR(255),
 description VARCHAR(255)
);

ALTER TABLE Project ADD CONSTRAINT PK_Project PRIMARY KEY (id);


CREATE TABLE Record (
 id INT NOT NULL,
 projectid INT NOT NULL,
 phase CHAR(10),
 target VARCHAR(255),
 start_date DATE,
 end_date DATE,
 status CHAR(10)
);

ALTER TABLE Record ADD CONSTRAINT PK_Record PRIMARY KEY (id,projectid);


CREATE TABLE Item (
 recordid INT NOT NULL,
 projectid INT NOT NULL,
 id INT NOT NULL,
 description VARCHAR(255),
 reviewer_person VARCHAR(255),
 isapplied CHAR(10),
 reply VARCHAR(255),
 reply_date DATE,
 confirm CHAR(10),
 confirm_person VARCHAR(255),
 confirm_date DATE,
 status CHAR(10)
);

ALTER TABLE Item ADD CONSTRAINT PK_Item PRIMARY KEY (recordid,projectid,id);


ALTER TABLE Record ADD CONSTRAINT FK_Record_0 FOREIGN KEY (projectid) REFERENCES Project (id);


ALTER TABLE Item ADD CONSTRAINT FK_Item_0 FOREIGN KEY (recordid,projectid) REFERENCES Record (id,projectid);


INSERT INTO Project
VALUES
(  '1',
   'プロジェクトX',
   'aaa',
   'bbb2002/3/31'
),
(  '2',
   'レビュー記録表管理ツールプロジェクト',
   'bbb',
   'bbb2002/3/31'
),
;

INSERT INTO Record
VALUES
(  '1',
   '1',
   '要件定義',
   'プロジェクト計画書',
   '2020-3-31',
   '2020-4-30',
   '完了'
);

INSERT INTO Item
VALUES
(  '1',
   '1',   
   '1',
   '○○が××である。',
   'Taro',
   '採用',
   '××は△である。',
   '2020-4-30',
   '△はAである。',
   'Taro',
   '2020-4-30',
   '済'
);

