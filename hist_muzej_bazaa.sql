CREATE DATABASE hist_muzej CHARACTER SET = 'utf8' COLLATE = 'utf8_croatian_ci';
USE hist_muzej;
CREATE TABLE EKSPONAT(
sifEksp CHAR(10) NOT NULL,
nazEksp NCHAR(50) NOT NULL,
materijalEksp NCHAR(15) NOT NULL,
velEksp NCHAR(30) NOT NULL,
sifPeriod CHAR(10) NOT NULL,
sifDvor CHAR(9) NOT NULL,
sifTipEksp NCHAR(10) NOT NULL,
PRIMARY KEY (sifEksp),
FOREIGN KEY (sifPeriod) REFERENCES PERIOD(sifPeriod),
FOREIGN KEY(sifDvor) REFERENCES DVORANA(sifDvor),
FOREIGN KEY(sifTipEksp) REFERENCES TIP_EKSPONAT(sifTipEksp)
);
-- CREATE UNIQUE INDEX  EKSPONAT_Unique ON EKSPONAT(sifEksp);
CREATE TABLE TIP_EKSPONAT(
sifTipEksp NCHAR(10) NOT NULL,
nazTipEksp NCHAR(50) NOT NULL,
PRIMARY KEY(sifTipEksp)
);
-- CREATE UNIQUE INDEX  TIP_EKSPONAT_Unique ON TIP_EKSPONAT(sifTipEksp);
CREATE TABLE PERIOD(
sifPeriod CHAR(10) NOT NULL,
nazPeriod NCHAR(30) NOT NULL,
PRIMARY KEY(sifPeriod)
);
-- CREATE UNIQUE INDEX  PERIOD_Unique ON PERIOD(sifPeriod);
CREATE TABLE DOGAĐAJ(
sifDog CHAR(15) NOT NULL,
datumDog DATE NOT NULL,
temaDog NCHAR(60) NOT NULL,
opisDog TEXT(500) NOT NULL,
sifTipDog NCHAR(50) NOT NULL,
PRIMARY KEY(sifDog),
FOREIGN KEY(sifTipDog) REFERENCES TIP_DOGAĐAJ(sifTipDog)
);
--  CREATE UNIQUE INDEX  DOGAĐAJ_Unique ON DOGAĐAJ(sifDog);
CREATE TABLE TIP_DOGAĐAJ(
sifTipDog NCHAR(50) NOT NULL,
nazTipDog TEXT NOT NULL,
PRIMARY KEY(sifTipDog)
);
-- CREATE UNIQUE INDEX  TIPDOGAĐAJ_Unique ON TIP_DOGAĐAJ(sifTipDog);
CREATE TABLE DVORANA(
sifDvor CHAR(9) NOT NULL,
nazDvor NCHAR(20) NOT NULL,
opisDvor NCHAR(250) NOT NULL,
kapacLjudi INTEGER NOT NULL,
kapacEksp INTEGER NOT NULL,
sifDog CHAR(15) NOT NULL,
PRIMARY KEY(sifDvor),
FOREIGN KEY(sifDog) REFERENCES DOGAĐAJ(sifDog)
);
-- CREATE UNIQUE INDEX  DVORANA_Unique ON DVORANA(sifDvor);
CREATE TABLE ZAPOSLENIK(
sifZap SMALLINT NOT NULL,
imeZap NCHAR(25) NOT NULL,
prezZap NCHAR(25) NOT NULL,
sifRadMjesto CHAR(20) NOT NULL,
PRIMARY KEY(sifZap),
FOREIGN KEY(sifRadMjesto) REFERENCES RADNO_MJESTO(sifRadMjesto)
);
-- CREATE UNIQUE INDEX  ZAPOSLENIK_Unique ON ZAPOSLENIK(sifZap);
CREATE TABLE RADNO_MJESTO(
sifRadMjesto CHAR(20) NOT NULL,
nazRadMjesto NCHAR(25) NOT NULL,
PRIMARY KEY(sifRadMjesto)
);
-- CREATE UNIQUE INDEX  RADNO_MJESTO_Unique ON RADNO_MJESTO(sifRadMjesto);
CREATE TABLE učestvuje(
sifZap SMALLINT NOT NULL,
sifDog CHAR(15) NOT NULL
-- PRIMARY KEY(sifZap)
);
CREATE  INDEX  ucestvujeUnique ON učestvuje(sifZap);
CREATE TABLE izložen(
sifEksp CHAR(15) NOT NULL,
sifDog CHAR(15) NOT NULL
-- PRIMARY KEY(sifEksp)
);

 CREATE INDEX  izloženUnique ON izložen(sifEksp);
LOAD DATA  INFILE 'C:/fajl/radno_mjesto.unl' INTO TABLE RADNO_MJESTO FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';
LOAD DATA  INFILE 'C:/fajl/zaposlenik.unl' INTO TABLE ZAPOSLENIK FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';
LOAD DATA INFILE 'C:/fajl/tip_dogadaj.unl' INTO TABLE TIP_DOGAĐAJ FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';
LOAD DATA INFILE 'C:/fajl/dogadaj.unl' INTO TABLE DOGAĐAJ FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';

LOAD DATA INFILE 'C:/fajl/dvorana.unl' INTO TABLE DVORANA FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';
LOAD DATA INFILE 'C:/fajl/period.unl' INTO TABLE PERIOD FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';
LOAD DATA INFILE 'C:/fajl/tip_eksponat.unl' INTO TABLE TIP_EKSPONAT FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';
LOAD DATA INFILE 'C:/fajl/eksponat.unl' INTO TABLE EKSPONAT FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';
LOAD DATA INFILE 'C:/fajl/ucestvuje.unl' INTO TABLE UČESTVUJE FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';
LOAD DATA INFILE 'C:/fajl/izlozen.unl' INTO TABLE IZLOŽEN FIELDS TERMINATED BY
'#' LINES STARTING BY '\n' TERMINATED BY '#\r';
-- ubacivanje ručno u tabelu radno mjesto
INSERT INTO RADNO_MJESTO VALUES('D700','dokumentarist');
INSERT INTO RADNO_MJESTO VALUES('D800','direktor');
INSERT INTO RADNO_MJESTO VALUES('R900','restaurator');
INSERT INTO RADNO_MJESTO VALUES('S101','sekretar');
INSERT INTO RADNO_MJESTO VALUES('K201','knjižničar');
INSERT INTO RADNO_MJESTO VALUES('K301','konzervator');
INSERT INTO ZAPOSLENIK VALUES('21209','Smajl','Isaković', 'D800');
INSERT INTO ZAPOSLENIK VALUES('27215','Amir','Smajlović', 'R900');
INSERT INTO ZAPOSLENIK VALUES('18898','Sulejman','Hadžimehmedović', 'K201');
INSERT INTO ZAPOSLENIK VALUES('31339','Aljo','Aljić', 'K301');
INSERT INTO ZAPOSLENIK VALUES('25678','Smajl','Isaković', 'S101');
INSERT INTO ZAPOSLENIK VALUES('26751','Ramo','Arnaut', 'D700');
INSERT INTO TIP_DOGAĐAJ VALUES('Rs','Radni_sastanak');
INSERT INTO TIP_DOGAĐAJ VALUES('Sn','Snimanje');
INSERT INTO TIP_DOGAĐAJ VALUES('Sp','Specijalne_posjeta');
INSERT INTO TIP_DOGAĐAJ VALUES('St','Specijalne_tribine');
INSERT INTO TIP_DOGAĐAJ VALUES('Op','Ostale posjete');
INSERT INTO DOGAĐAJ VALUES('6af6','2022.05.26','Učeni u muzeju', 'Održavanje skupa naučnika i stručnjaka o suštini historijskog blaga','NS');
INSERT INTO DOGAĐAJ VALUES('7ag7','2022.06.02','Snimanje priloga', 'Dolazak uredničke ekipe televizije radi uzimanja izjava i snimanja priloga za kulturno-obrazovnu emisiju','Sn');
INSERT INTO DOGAĐAJ VALUES('8ah8','2022.06.05','Službena posjeta ministra za kulturu','Obilazak historijskog muzeja od strane ministra za kulturu i njegovih saradnika te razgovor o planovima za dodatno unapređenje muzeja','Sp');
INSERT INTO DOGAĐAJ VALUES('9ai9','2022.06.10','Studenti u posjeti', 'Radna posjeta studenata PMF-a, odsjek historija, pojedinim odjelima historijskog muzeja','Op');
INSERT INTO DOGAĐAJ VALUES('10aj10','2022.09.09','Izložba - BiH u prahistorijsko doba', 'Izložba artifakata koji prikazuju život čovjeka u dalekim vremenima','Iz');

-- INSERT INTO TIP_DOGAĐAJ VALUES('Pt','Prezentacije');
INSERT INTO PERIOD VALUES('skdb_60', 'srednje_kameno_doba');
INSERT INTO PERIOD VALUES('skdb_70', 'starije_kameno_doba');
INSERT INTO PERIOD VALUES('bdb_80', 'bakarno_doba');
INSERT INTO PERIOD VALUES('brdb_90', 'brončano_doba_I');
INSERT INTO PERIOD VALUES('brdb_100', 'brončano_doba_II');
INSERT INTO PERIOD VALUES('stv_110', 'stari vijek');

INSERT INTO TIP_EKSPONAT VALUES('dk01','dokument');
INSERT INTO TIP_EKSPONAT VALUES('to02','oruđe');
INSERT INTO TIP_EKSPONAT VALUES('cl03','odora');
INSERT INTO TIP_EKSPONAT VALUES('me04','medalja');
INSERT INTO TIP_EKSPONAT VALUES('gr10','grb');
INSERT INTO TIP_EKSPONAT VALUES('gr11','grafika');



 INSERT INTO DVORANA VALUES('1000','Medalje i plakete','Skup odlikovanja za različite doprinose u društvu','140', '210','2ab2');
 INSERT INTO DVORANA VALUES('1001','Arheološka zbirka','Skup arheoloških eksponata','95', '100','10aj10');
 INSERT INTO DVORANA VALUES('1010','Zbirka oružja','Prikaz načina izrade različitih oružja u različitim periodima historije','115', '210','9ai9');
 INSERT INTO DVORANA VALUES('1011','Heraldika','Zbirka grbova kroz historiju','160', '210','9ai9');
 INSERT INTO DVORANA VALUES('1100','Zbirka zastava i pejzaža gradova','Skup različitih zastava kao simboli zemalja ','80', '40','7ag7');
INSERT INTO DVORANA VALUES('1101','Odore','Različite odore i odjeća koje su nosili ljudi u prošlosti','80', '70','6af6');

INSERT INTO EKSPONAT VALUES('Eks06', 'Grb BiH', 'željezo', 'prečnik 6cm','sv_30','1011','gr10');
INSERT INTO EKSPONAT VALUES('Eks07', 'Grb BiH- Austro Ugarska', 'srebro', 'prečnik 8cm','nv_40','1011','gr10');
INSERT INTO EKSPONAT VALUES('Eks08', 'Zastava Autonomne BiH', 'željezo', '200x140 cm','nv_f0','1100','fl05');
INSERT INTO EKSPONAT VALUES('Eks09', 'Kaput', 'koža', 'dužina 80cm, širina 40cm ','nv_40','1101','cl03');
INSERT INTO EKSPONAT VALUES('Eks10', 'Naučni tekst', 'papir', '30x20 cm','sv_30','110','dk01');
INSERT INTO EKSPONAT VALUES('Eks11', 'Pejzaž', 'drvorez', '25x39 cm','sv_30','1100','gr01');
INSERT INTO UČESTVUJE VALUES('21209', '10aj10');
INSERT INTO UČESTVUJE VALUES('31339', '10aj10');
INSERT INTO UČESTVUJE VALUES('19201', '9ai9');
INSERT INTO UČESTVUJE VALUES('21209', '8ah8');
INSERT INTO UČESTVUJE VALUES('26751', '6af6');
INSERT INTO IZLOŽEN VALUES('Eks08', '7ag7');
INSERT INTO IZLOŽEN VALUES('Eks11', '7ag7');
INSERT INTO IZLOŽEN VALUES('Eks09', '6af6');
INSERT INTO IZLOŽEN VALUES('Eks06', '9ai9');
INSERT INTO IZLOŽEN VALUES('Eks08', '3ac3');
DROP DATABASE hist_muzej;
DROP DATABASE histo_muzej;
select user();
show variables;
SELECT * FROM mysql.user;
SHOW VARIABLES WHERE Variable_name = 'port';
SELECT User, Host, password_expired FROM mysql.user;
SELECT * FROM ZAPOSLENIK;
SELECT * FROM Period;
SELECT *FROM izložen;
SELECT *FROM TIP_EKSPONAT;
/*
DELETE  FROM ZAPOSLENIK;
DELETE FROM EKSPONAT;
DELETE FROM TIP_DOGAĐAJ;
DELETE FROM DOGAĐAJ;
DELETE FROM DVORANA;
DELETE FROM PERIOD;
DELETE FROM TIP_EKSPONAT;
DELETE FROM TIP_DOGAĐAJ WHERE nazTipDog='Radni sastanak';
DROP DATABASE hist_muzej;
DROP TABLE UČESTVUJE;
DROP TABLE IZLOŽEN;
SELECT * FROM ZAPOSLENIK;
SELECT * FROM RADNO_MJESTO;
-- SELECT * FROM DVORANA;
SELECT * FROM TIP_DOGAĐAJ;	
SELECT * FROM PERIOD;
SELECT *FROM TIP_EKSPONAT;
SELECT * FROM DOGAĐAJ;



UPDATE DVORANA
SET kapacLjudi = '140'
WHERE sifDvor = 1000;
-- DELETE FROM PERIOD WHERE sifPeriodSET nazPeriod = 'željezno doba' WHERE sifPeriod = 'mdb_20';
*/



