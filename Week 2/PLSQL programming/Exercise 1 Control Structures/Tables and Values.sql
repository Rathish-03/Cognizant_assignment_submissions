CREATE TABLE PirateCrews (
    crew_id NUMBER PRIMARY KEY,
    crew_name VARCHAR2(100) NOT NULL,
    captain_name VARCHAR2(100) NOT NULL,
    bounty_total NUMBER(15,2) DEFAULT 0
);

CREATE TABLE PirateMembers (
    member_id NUMBER PRIMARY KEY,
    crew_id NUMBER,
    member_name VARCHAR2(100) NOT NULL,
    role VARCHAR2(50),
    individual_bounty NUMBER(12,2),
    status VARCHAR2(20) DEFAULT 'Active', -- e.g., 'Active', 'Deceased', 'Imprisoned', 'Retired'
    date_joined DATE,
    FOREIGN KEY (crew_id) REFERENCES PirateCrews(crew_id)
);

INSERT INTO PirateCrews (crew_id, crew_name, captain_name, bounty_total) VALUES
(1, 'Straw Hat Pirates', 'Monkey D. Luffy', 3000000000);
INSERT INTO PirateCrews (crew_id, crew_name, captain_name, bounty_total) VALUES
(2, 'Red Hair Pirates', 'Shanks', 4048900000);
INSERT INTO PirateCrews (crew_id, crew_name, captain_name, bounty_total) VALUES
(3, 'Whitebeard Pirates', 'Edward Newgate', 5046000000);
INSERT INTO PirateCrews (crew_id, crew_name, captain_name, bounty_total) VALUES
(4, 'Blackbeard Pirates', 'Marshall D. Teach', 3996000000);

INSERT INTO PirateMembers (member_id, crew_id, member_name, role, individual_bounty, status, date_joined) VALUES
(101, 1, 'Monkey D. Luffy', 'Captain', 3000000000, 'Active', TO_DATE('2000-01-01', 'YYYY-MM-DD'));
INSERT INTO PirateMembers (member_id, crew_id, member_name, role, individual_bounty, status, date_joined) VALUES
(102, 1, 'Roronoa Zoro', 'Swordsman', 1111000000, 'Active', TO_DATE('2000-02-15', 'YYYY-MM-DD'));
INSERT INTO PirateMembers (member_id, crew_id, member_name, role, individual_bounty, status, date_joined) VALUES
(103, 1, 'Nami', 'Navigator', 366000000, 'Active', TO_DATE('2000-03-20', 'YYYY-MM-DD'));
INSERT INTO PirateMembers (member_id, crew_id, member_name, role, individual_bounty, status, date_joined) VALUES
(104, 2, 'Shanks', 'Captain', 4048900000, 'Active', TO_DATE('1985-06-01', 'YYYY-MM-DD'));
INSERT INTO PirateMembers (member_id, crew_id, member_name, role, individual_bounty, status, date_joined) VALUES
(105, 3, 'Edward Newgate', 'Captain', 5046000000, 'Deceased', TO_DATE('1950-01-01', 'YYYY-MM-DD'));
INSERT INTO PirateMembers (member_id, crew_id, member_name, role, individual_bounty, status, date_joined) VALUES
(106, 3, 'Marco', 'First Commander', 1374000000, 'Active', TO_DATE('1980-10-10', 'YYYY-MM-DD'));
INSERT INTO PirateMembers (member_id, crew_id, member_name, role, individual_bounty, status, date_joined) VALUES
(107, 4, 'Marshall D. Teach', 'Captain', 3996000000, 'Active', TO_DATE('2000-04-22', 'YYYY-MM-DD'));
INSERT INTO PirateMembers (member_id, crew_id, member_name, role, individual_bounty, status, date_joined) VALUES
(108, 1, 'Usopp', 'Sniper', 500000000, 'Active', TO_DATE('2000-05-01', 'YYYY-MM-DD'));

COMMIT; 

SELECT * FROM PirateCrews;
SELECT * FROM PirateMembers;