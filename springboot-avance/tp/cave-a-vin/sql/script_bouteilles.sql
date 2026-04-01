USE CAVE_A_VIN
GO

/*Couleur*/
INSERT INTO cav_color (name)  VALUES ('Blanc');
INSERT INTO cav_color (name)   VALUES ('Rouge');
INSERT INTO cav_color (name)   VALUES ('Rosé');


/*Region*/
INSERT INTO cav_region (name)  VALUES ('Auvergne-Rhône-Alpes');
INSERT INTO cav_region (name)   VALUES ('Bourgogne-Franche-Comté');
INSERT INTO cav_region (name)   VALUES ('Bretagne');
INSERT INTO cav_region (name)   VALUES ('Centre-Val de Loire');
INSERT INTO cav_region (name)   VALUES ('Corse');
INSERT INTO cav_region (name)   VALUES ('Grand Est');
INSERT INTO cav_region (name)   VALUES ('Hauts-de-France');
INSERT INTO cav_region (name)   VALUES ('Ile-de-France');
INSERT INTO cav_region (name)   VALUES ('Normandie');
INSERT INTO cav_region (name)   VALUES ('Nouvelle-Aquitaine');
INSERT INTO cav_region (name)   VALUES ('Occitanie');
INSERT INTO cav_region (name)   VALUES ('Pays de la Loire');
INSERT INTO cav_region (name)   VALUES ('Provence-Alpes-Côte d''Azur');


/*Bouteille*/
INSERT INTO cav_bottle (name, vintage, sparkling, quantity, price, region_id, color_id)  VALUES ('DOMAINE WW, Muscat Vendanges Tardives','2017',0,1872,11,6,1);
INSERT INTO cav_bottle (name, vintage, sparkling, quantity, price, region_id, color_id)  VALUES ('Domaine FF, BRUT pinot gris','2020',1,972,21.5,6,1);
INSERT INTO cav_bottle (name, vintage, sparkling, quantity, price, region_id, color_id)  VALUES ('Gilbert FF, Crémant d''Alsace rosé','2022',1,1872,19,6,3);
INSERT INTO cav_bottle (name, vintage, sparkling, quantity, price, region_id, color_id)  VALUES ('Domaine LG, Pisse Vieille','2022',0,999,7.25,2,2);
INSERT INTO cav_bottle (name, vintage, sparkling, quantity, price, region_id, color_id)  VALUES ('Domaine LG, Vieille Vigne','2021',0,1172,12,2,2);
INSERT INTO cav_bottle (name, vintage, sparkling, quantity, price, region_id, color_id)  VALUES ('Domaine du B','2021',0,187,17.5,2,2);
INSERT INTO cav_bottle (name, vintage, sparkling, quantity, price, region_id, color_id)  VALUES ('Château Caché Cuvée L','2018',0,1112,9.9,10,2);
INSERT INTO cav_bottle (name, vintage, sparkling, quantity, price, region_id, color_id)  VALUES ('Bordeaux AOP, Rosé','2021',0,2472,7.5,10,3);
INSERT INTO cav_bottle (name, vintage, sparkling, quantity, price, region_id, color_id)  VALUES ('Château du roi','2013',0,272,21,10,2);