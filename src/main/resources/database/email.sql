CREATE SCHEMA IF NOT EXISTS NINTENDO;

-- Email Details
CREATE TABLE "NINTENDO"."EMAIL" (
    EMAIL_ID varchar(36) PRIMARY KEY NOT NULL, 
    NINTENDO_ID varchar(7) NOT NULL,
    EMAIL_PURPOSE_DC varchar(10) NOT NULL DEFAULT 'BOTH',
    EMAIL_ADDRESS varchar(255) NOT NULL,
    MODIFIED timestamp NOT NULL
);

CREATE UNIQUE INDEX XPK_EMAIL ON "NINTENDO"."EMAIL"(EMAIL_ID);
CREATE INDEX EMAIL_IDX1 ON "NINTENDO"."EMAIL"(NINTENDO_ID);

INSERT INTO NINTENDO.EMAIL 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-11a8-4d5b-beda-3ma1la120f71', 'nin0001', 'WORK', 'mario@nintendo.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-11a8-4d5b-beda-3ma1la120f72', 'nin0001', 'PERSONAL', 'mario@switch.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-12a8-4d5b-beda-3ma1la120f71', 'nin0002', 'WORK', 'luigi@nintendo.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-12a8-4d5b-beda-3ma1la120f72', 'nin0002', 'PERSONAL', 'luigi@switch.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-14a8-4d5b-beda-3ma1la120f71', 'nin0004', 'WORK', 'olivia@nintendo.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-14a8-4d5b-beda-3ma1la120f72', 'nin0004', 'PERSONAL', 'olivia@switch.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-15a8-4d5b-beda-3ma1la120f71', 'nin0005', 'WORK', 'michael@nintendo.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-15a8-4d5b-beda-3ma1la120f72', 'nin0005', 'PERSONAL', 'michael@switch.com', '2022-07-05 23:54:00');

-- Email History Details
CREATE TABLE "NINTENDO"."EMAIL_HST" (
    EMAIL_ID varchar(36) NOT NULL, 
    NINTENDO_ID varchar(7) NOT NULL,
    EMAIL_PURPOSE_DC varchar(10) NOT NULL,
    EMAIL_ADDRESS varchar(255) NOT NULL,
    MODIFIED timestamp NOT NULL, 
    CONSTRAINT XPK_EMAIL_HST PRIMARY KEY (EMAIL_ID, MODIFIED)
);

CREATE UNIQUE INDEX XPK_EMAIL_HST ON "NINTENDO"."EMAIL_HST"(EMAIL_ID, MODIFIED);
CREATE INDEX EMAIL_HST_IDX1 ON "NINTENDO"."EMAIL_HST"(NINTENDO_ID);

INSERT INTO NINTENDO.EMAIL_HST 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-11a8-4d5b-beda-3ma1la120f71', 'nin0001', 'WORK', 'mario@nintendo.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL_HST 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-11a8-4d5b-beda-3ma1la120f72', 'nin0001', 'PERSONAL', 'mario@switch.com', '2022-07-05 23:50:00');

INSERT INTO NINTENDO.EMAIL_HST 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-11a8-4d5b-beda-3ma1la120f71', 'nin0001', 'WORK', 'mario@nintendo.com', '2022-07-04 23:54:00');

INSERT INTO NINTENDO.EMAIL_HST 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-11a8-4d5b-beda-3ma1la120f71', 'nin0001', 'WORK', 'mario@nintendo.com', '2022-07-03 23:54:00');

INSERT INTO NINTENDO.EMAIL_HST 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-14a8-4d5b-beda-3ma1la120f71', 'nin0004', 'WORK', 'olivia@nintendo.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL_HST 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-14a8-4d5b-beda-3ma1la120f72', 'nin0004', 'PERSONAL', 'olivia@switch.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL_HST 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-15a8-4d5b-beda-3ma1la120f71', 'nin0005', 'WORK', 'michael@nintendo.com', '2022-07-05 23:54:00');

INSERT INTO NINTENDO.EMAIL_HST 
(EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
VALUES ('n1nt3nd0-15a8-4d5b-beda-3ma1la120f72', 'nin0005', 'PERSONAL', 'michael@switch.com', '2022-07-05 23:54:00');