DROP TABLE IF EXISTS genres;

create table genres (
    id int primary key,
    titre nvarchar(50) not null unique
);

insert into genres(id, titre) values(1, 'Animation');
insert into genres(id, titre) values(2, 'Science-fiction');
insert into genres(id, titre) values(3, 'Documentaire');
insert into genres(id, titre) values(4, 'Action');
insert into genres(id, titre) values(5, N'Comédie');
insert into genres(id, titre) values(6, 'Drame');

select * from genres;