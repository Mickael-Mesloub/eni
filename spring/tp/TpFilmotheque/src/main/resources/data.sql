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

create table participants (
    id int not null primary key identity,
    prenom varchar(50) not null,
    nom varchar(50) not null
);

create table films(
    id int not null primary key identity,
    titre varchar(50) not null,
    annee int not null,
    duree int not null,
    synopsis varchar(500) not null,
    genreId int not null,
    realisateurId int not null
);

alter table films add constraint fk_films_genre_id foreign key(genreId)
    references genres(id);

alter table films add constraint fk_films_realisateur_id foreign key(realisateurId)
    references participants(id);


create table acteurs(
    filmId int not null,
    participantId int  not null
);

alter table acteurs add primary key (filmId, participantId);

alter table acteurs add constraint fk_acteurs_filmId foreign key(filmId) references films(id);
alter table acteurs add constraint fk_acteurs_participantId foreign key (participantId) references participants(id);

insert into participants(nom, prenom) values ('Spielberg', 'Steven');
insert into participants(nom, prenom) values ('Cronenberg', 'David');
insert into participants(nom, prenom) values ('Boon', 'Dany');


insert into participants(nom, prenom) values('Attenborough', 'Richard');
insert into participants(nom, prenom) values('Goldblum', 'Jeff');
insert into participants(nom, prenom) values('Davis', 'Geena');
insert into participants(nom, prenom) values('Rylance', 'Mark');
insert into participants(nom, prenom) values('Barnhill', 'Ruby');
insert into participants(nom, prenom) values('Merad', 'Kad');

select * from participants;

insert into films (titre, annee, duree, synopsis, genreId, realisateurId)
values ('Jurassic Park',
        1993,
        128,
        N'Le film raconte l''histoire d''un milliardaire et son équipe de généticiens parvenant à ramener à la vie des dinosaures grâce au clonage.',
        2,
        1
       );

insert into films (titre, annee, duree, synopsis, genreId, realisateurId)
values ('The Fly',
        1986,
        95,
        N'Il s''agit de l''adaptation cinématographique de la nouvelle éponyme de l''auteur George Langelaan.',
        2,
        2
       );

insert into films (titre, annee, duree, synopsis, genreId, realisateurId)
values ('The BFG',
        2016,
        117,
        N'Le Bon Gros Géant est un géant bien différent des autres habitants du Pays des Géants.',
        5,
        1
       );

insert into films (titre, annee, duree, synopsis, genreId, realisateurId)
values (  N'Bienvenue chez les Ch''tis',
          2008,
          106,
          N'Philippe Abrams est directeur de la poste de Salon-de-Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d''obtenir une mutation sur la Côte d''Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord.',
          5,
          3
       );

select * from films;

CREATE TABLE Membres(
    id INT IDENTITY CONSTRAINT PK_Membres PRIMARY KEY,
    prenom VARCHAR(50) NOT NULL,
    nom VARCHAR(50) NOT NULL,
    pseudo VARCHAR(50) NOT NULL UNIQUE,
    motDePasse VARCHAR(50) NOT NULL,
    admin BIT NOT NULL
);