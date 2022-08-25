drop table if exists nacionalnost cascade;
drop table if exists liga cascade;
drop table if exists tim cascade;
drop table if exists igrac cascade;

drop sequence if exists nacionalnost_seq cascade;
drop sequence if exists liga_seq cascade;
drop sequence if exists tim_seq cascade;
drop sequence if exists igrac_seq cascade;


create table nacionalnost (
id integer not null primary key,
naziv varchar(200),
skracenica varchar(50)
);



create table liga (
id integer not null primary key,
naziv varchar(200),
oznaka varchar(50)
);

create table tim (
id integer not null primary key, 
naziv varchar(200),
osnovan date,
sediste varchar(200),
liga integer not null
);

create table igrac(
id integer not null primary key,
ime varchar(200),
prezime varchar(50),
broj_reg varchar(50),
datum_rodjenja date,
nacionalnost integer not null,
tim integer not null
);




alter table tim add constraint fk_tim_liga foreign key(liga) references liga(id);
alter table igrac add constraint fk_igrac_nacionalnost foreign key(nacionalnost) references nacionalnost(id);
alter table igrac add constraint fk_igrac_tim foreign key(tim) references tim(id);

create index idxpk_nacionalnost on nacionalnost(id);
create index idxpk_igrac on igrac(id);
create index idxpk_tim on tim(id);
create index idxpk_liga on liga(id);

create index idxfk_tim_liga on tim(liga);
create index idxfk_igrac_nacionalnost on igrac(nacionalnost);
create index idxfk_igrac_tim on igrac(tim);


create sequence if not exists nacionalnost_seq increment 1 start with 1;
create sequence if not exists liga_seq increment 1 start with 1;
create sequence if not exists igrac_seq increment 1 start with 1;
create sequence if not exists tim_seq increment 1 start with 1;


alter table nacionalnost alter column id
set default nextval('nacionalnost_seq');

alter table igrac alter column id
set default nextval('igrac_seq');

alter table tim alter column id
set default nextval('tim_seq');

alter table liga alter column id
set default nextval('liga_seq');