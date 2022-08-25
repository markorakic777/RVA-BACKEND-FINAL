insert into "nacionalnost"
values (-100, 'Test', 'T');

insert into "liga"
values (-100, 'Test', 'T');

insert into "tim"
values (-100, 'Test', to_date('01.01.2022', 'dd.mm.yyyy.'), 'Test, Test', -100);

insert into "igrac"
values (-100, 'Test', 'Test', '000001', to_date('03.03.1999.', 'dd.mm.yyyy.'), -100, -100);

insert into "nacionalnost"
values (nextval('nacionalnost_seq'), 'Srbija', 'srb'),
	(nextval('nacionalnost_seq'), 'Spanija', 'spa'),
	(nextval('nacionalnost_seq'), 'hrvatska', 'hrv'),
	(nextval('nacionalnost_seq'), 'Italija', 'ita'),
	(nextval('nacionalnost_seq'), 'Madjarska', 'hun'),
	(nextval('nacionalnost_seq'), 'Estonija', 'est'),
	(nextval('nacionalnost_seq'), 'Letonia', 'let');

insert into "liga"
values (nextval('liga_seq'), 'B', 'b'),
	(nextval('liga_seq'), 'Super Liga Srbije', 'sls'),
	(nextval('liga_seq'), 'Premier Liga', 'premier'),
	(nextval('liga_seq'), 'pioniri', 'pio'),
	(nextval('liga_seq'), 'A', 'a');

insert into "tim"
values (nextval('tim_seq'), 'Petlici', to_date('03.12.1955.', 'dd.mm.yyyy.'), 'Srbija', 5),
	(nextval('tim_seq'), 'Sindjelic', to_date('13.12.1934.', 'dd.mm.yyyy.'), 'Srbija', 1),
	(nextval('tim_seq'), 'Crvena zvezda', to_date('10.12.1963.', 'dd.mm.yyyy.'), 'Srbija', 2),
	(nextval('tim_seq'), 'Juventus', to_date('03.12.1952.', 'dd.mm.yyyy.'), 'Torino', 4),
	(nextval('tim_seq'), 'Manchester United', to_date('11.12.1902.', 'dd.mm.yyyy.'), 'UK', 3),
	(nextval('tim_seq'), 'Bayern', to_date('11.01.1922.', 'dd.mm.yyyy.'), 'Nemacka', 5);

insert into "igrac"
values (nextval('igrac_seq'), 'Marko', 'Markovic', '101', to_date('10.03.1989.', 'dd.mm.yyyy.'), 1, 3),
	(nextval('igrac_seq'), 'Kristijan', 'Savic', '102', to_date('24.11.1994.', 'dd.mm.yyyy.'), 1, 3),
	(nextval('igrac_seq'), 'David', 'Davidovic', '103', to_date('13.04.1986.', 'dd.mm.yyyy.'), 3, 4),
	(nextval('igrac_seq'), 'Hanc', 'Majn', '104', to_date('14.02.1982.', 'dd.mm.yyyy.'), 7, 1),
	(nextval('igrac_seq'), 'Federico', 'Rote', '105', to_date('04.05.1997.', 'dd.mm.yyyy.'), 6, 2)
	;