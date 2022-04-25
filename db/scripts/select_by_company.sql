CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);


CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) 
	VALUES  (22, 'Google'), 
		(4, 'Yandex'),
		(77, 'Mail Ru Group'),
		(1, 'Rambler'), 
		(5, 'Nikola Tesla');


INSERT INTO person (id, name, company_id) 
	VALUES  (1, 'Ivanov', 4), 
			(2, 'Petrov', 77),
			(3, 'Sidorov', 77),
			(4, 'Vasechkin', 4), 
			(5, 'Pushkin', 1), 
			(6, 'Lomonosov', 1),
			(7, 'Dostoevskiy', 4),
			(8, 'Gogol', 5), 
			(9, 'Gagarin', 77);

SELECT p.name, c.name
FROM person p, company c
WHERE p.company_id = c.id
AND p.company_id != 5;

SELECT c.name, COUNT(p.company_id)
FROM person p, company c
WHERE p.company_id = c.id
GROUP BY c.name
HAVING COUNT(p.company_id) = (
	SELECT COUNT(p.company_id)
	FROM person p, company c
	WHERE p.company_id = c.id
	GROUP BY c.name
	ORDER BY COUNT DESC
	LIMIT 1)
ORDER BY count DESC;