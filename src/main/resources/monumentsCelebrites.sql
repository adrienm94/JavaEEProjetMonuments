drop table associea cascade;
drop table celebrite cascade;
drop table monument cascade;

alter table lieu drop constraint fk_dep;
alter table departement drop constraint fk_chef_lieu;

drop table lieu cascade;
drop table departement cascade;

create table lieu (
code_insee varchar(5) primary key,
nom_com varchar(46),
longitude float,
latitude float,
dep varchar(4)
);

insert into lieu values ('34172','MONTPELLIER',3.876716,43.610769,'34');
insert into lieu values ('34198','PEROLS',3.954211,43.563782,'34');
insert into lieu values ('34199','PEZENAS',3.423193,43.461531,'34');
insert into lieu values ('34142','LODEVE',3.313975,43.73366,'34');
insert into lieu values ('34032','BEZIERS',3.215795,43.344233,'34');
insert into lieu values ('34129','LATTES',3.896473,43.567296,'34');			   
insert into lieu values ('30334','UZES',4.419946,44.01211,'30');		     						 
insert into lieu values ('30189','NIMES',4.360054,43.836699,'30');	

create table departement(
dep varchar(4) primary key,
nom_dep varchar(30),
chef_lieu varchar(46),
reg varchar(40)
);

insert into departement values ('30','GARD','30189','OCCITANIE');
insert into departement values ('34','HERAULT','34172','OCCITANIE');

alter table departement add constraint fk_chef_lieu foreign key (chef_lieu) references lieu(code_insee) on delete cascade;
alter table lieu add constraint fk_dep foreign key (dep) references departement(dep) on delete cascade;

create table monument (
geohash varchar(12) primary key,
nom varchar(80),
proprietaire varchar(10), 
type_m varchar(20), 
longitude float, 
latitude float, 
code_lieu varchar(6), 
constraint fk_code_lieu foreign key (code_lieu) references Lieu(code_insee) on delete cascade
);


insert into monument values ('spfb05ty554b','HOTEL DUFFAU','PRIVE','HOTEL_PARTICULIER',3.87521667,43.6140222,'34172');                                          
insert into monument values ('spfb070h0d5t','HOTEL DE MONTFERRIER','PRIVE','HOTEL_PARTICULIER',3.8782,43.6109278,'34172');                                                            
insert into monument values ('spfb070hzm8g','HOTEL DE GRIFFY','PRIVE','HOTEL_PARTICULIER',3.87848611,43.611075,'34172');                                                            
insert into monument values ('spfb070q3mv0','HOTEL ESTORC','PRIVE','HOTEL_PARTICULIER',3.87857778,43.6113333,'34172');                                                       
insert into monument values ('spfb070qgp2u','HOTEL DE LA SOCIETE ROYALE DES SCIENCES','PRIVE','HOTEL_PARTICULIER',3.87864722,43.6114278,'34172');                                                            
insert into monument values ('spfb04ybz0hw','HOTEL LECOURT','PRIVE','HOTEL_PARTICULIER',3.87676389,43.6089861,'34172');                                                            
insert into monument values ('spfb04xr32w4','HOTEL DE SAINT-FELIX','PRIVE','HOTEL_PARTICULIER',3.87720556,43.6087306,'34172');                                                            
insert into monument values ('spfb05rczjtg','HOTEL DE ROQUEMORE','PRIVE','HOTEL_PARTICULIER',3.87813889,43.6119333,'34172');                                                            
insert into monument values ('spfb07248bmb','HOTEL D''AVEZE','PRIVE','HOTEL_PARTICULIER',3.87821389,43.6120333,'34172');                                                            
insert into monument values ('spfb0725nhcx','HOTEL DE BEAULAC','PRIVE','HOTEL_PARTICULIER',3.87843333,43.6121444,'34172');                                                            
insert into monument values ('spfb072k0td7','HOTEL DEYDE','PRIVE','HOTEL_PARTICULIER',3.87854167,43.6123194,'34172');                                                                                                                 
insert into monument values ('spfb07210dtw','HOTEL DE BAUDON DE MAUNY','PRIVE','HOTEL_PARTICULIER',3.87820278,43.6117889,'34172');                                                            
insert into monument values ('spfb05nwqmvu','HOTEL DE GANGES','PRIVE','HOTEL_PARTICULIER',3.87639,43.611334,'34172');  
insert into monument values ('spfb070wrnu3','HOTEL DE JOUBERT','PRIVE','HOTEL_PARTICULIER',3.87916667,43.6113389,'34172');                                                            
insert into monument values ('spfb06bq3hfv','HOTEL DE MAGNY','PRIVE','HOTEL_PARTICULIER',3.87856389,43.6099556,'34172');                                                            
insert into monument values ('spfb0703fxke','HOTEL BASCHY-DU-CAYLA','PRIVE','HOTEL_PARTICULIER',3.87863056,43.6105694,'34172');                                                            
insert into monument values ('spfb0702ubt1','HOTEL DE MANSE','PRIVE','HOTEL_PARTICULIER',3.87872778,43.6103611,'34172');                                                            
insert into monument values ('spfb05n1zkf3','HOTEL VERCHANT','PRIVE','HOTEL_PARTICULIER',3.87574167,43.6105556,'34172');                                                            
insert into monument values ('spfb05r6jkf7','HOTEL DE SOLAS','PRIVE','HOTEL_PARTICULIER',3.87737222,43.6119722,'34172');                                                            
insert into monument values ('spfb05r1yesc','HOTEL D''USTON','PRIVE','HOTEL_PARTICULIER',3.87708611,43.6119222,'34172');                                                            
insert into monument values ('spfb04yhhc7d','HOTEL DE MONTCALM','PRIVE','HOTEL_PARTICULIER',3.87563611,43.60955,'34172');                                                            
insert into monument values ('spfb068j1fdv','HOTEL LAMOUROUX','PRIVE','HOTEL_PARTICULIER',3.87825278,43.6083556,'34172');                                                                                                               
insert into monument values ('spfb04xgf629','HOTEL REY','PRIVE','HOTEL_PARTICULIER',3.87792778,43.6081389,'34172');                                                            
insert into monument values ('spfb04xehcqt','HOTEL DE FOURQUES','PRIVE','HOTEL_PARTICULIER',3.8777,43.6080056,'34172');                                                            
insert into monument values ('spfb06b5cb0b','HOTEL DE BENEZET','PRIVE','HOTEL_PARTICULIER',3.87825,43.6095,'34172');                                                            
insert into monument values ('spfb06b4s7pz','HOTEL DE FLAUGERGUES','PRIVE','HOTEL_PARTICULIER',3.87836667,43.6093028,'34172');                                                            
insert into monument values ('spfb05ptyz6b','HOTEL DE MIRMAN','PRIVE','HOTEL_PARTICULIER',3.87778056,43.6112556,'34172');                                                            
insert into monument values ('spfb0704x32z','HOTEL DE VARENNES','PRIVE','HOTEL_PARTICULIER',3.87848611,43.6106667,'34172');                                                            
insert into monument values ('spfb05jcert7','HOTEL PAS DE BEAULIEU','PRIVE','HOTEL_PARTICULIER',3.87523056,43.6105278,'34172');                                                            
insert into monument values ('spfb04v1ntsm','HOTEL DE CASTRIES','PRIVE','HOTEL_PARTICULIER',3.87433889,43.6090583,'34172');                                                            
insert into monument values ('spfb04ubqkr3','HOTEL DE RICARD','PRIVE','HOTEL_PARTICULIER',3.87398889,43.6089222,'34172');                                                            
insert into monument values ('spfb05m4pe36','HOTEL DE CAMBACERES-MURLES','PRIVE','HOTEL_PARTICULIER',3.87437778,43.6119639,'34172');                                                            
insert into monument values ('spfb073h9t9u','HOTEL DE GRAVE','PRIVE','HOTEL_PARTICULIER',3.87961389,43.6124056,'34172');                                                                                                            
insert into monument values ('spfb06bkjnx2','HOTEL DES TRESORIERS DE FRANCE','PRIVE','HOTEL_PARTICULIER',3.87874167,43.6095778,'34172');                                                            
insert into monument values ('spfb05h0fw4z','HOTEL DE LUNAS','PRIVE','HOTEL_PARTICULIER',3.87279167,43.6103917,'34172');                                                            
insert into monument values ('spfb04zhzzfh','HOTEL DE GAYON','PRIVE','HOTEL_PARTICULIER',3.87713611,43.6097139,'34172');                                                            
insert into monument values ('spfb05z3rwep','PORTE DE LA BLANQUERIE','PUBLIC','EDIFICE',3.87747,43.6146,'34172');                                                            
insert into monument values ('spfb05kxu4d5','FACULTE DE MEDECINE DE MONTPELLIER','PUBLIC','UNIVERSITE',3.87354167,43.6129472,'34172');                                                            
insert into monument values ('spf8pgvv6wse','EGLISE SAINTE-THERESE-DE-LISIEUX DE MONTPELLIER','PUBLIC','EGLISE',3.86421111,43.6152861,'34172');                                                            
insert into monument values ('spfb04fe0zkn','EGLISE SAINTE-EULALIE DE MONTPELLIER','PUBLIC','EGLISE',3.87065833,43.6094111,'34172');                                                            
insert into monument values ('spf8p5eby8du','EGLISE SAINTE-CROIX DE CELLENEUVE','PUBLIC','EGLISE',3.828675,43.6131083,'34172');                                                            
insert into monument values ('spdzbn81hv6q','EGLISE SAINT-MICHEL DE MONTELS','PUBLIC','EGLISE',3.86739444,43.5857111,'34172');                                                            
insert into monument values ('spfb15q3018t','PRIEURE SAINT-PIERRE DE MONTAUBEROU','PUBLIC','PRIEURE',3.91971667,43.6117833,'34172');                                                            
insert into monument values ('spfb05k426n3','ANCIENNE PRISON DE MONTPELLIER','PUBLIC','EDIFICE',3.8727,43.612,'34172');                                                                                                         
insert into monument values ('spfb04yzxkr1','HALLE CASTELLANE','PUBLIC','EDIFICE',3.87677778,43.6101667,'34172');                                                            
insert into monument values ('spfb055v1xfz','PORTE DU PEYROU','PUBLIC','EDIFICE',3.87240556,43.6111306,'34172');                                                            
insert into monument values ('spfb054nhrd9','PROMENADE DU PEYROU','PUBLIC','EDIFICE',3.87012,43.6113,'34172');                                                            
insert into monument values ('spfb05nw3q73','FONTAINE DE LA PREFECTURE','PUBLIC','EDIFICE',3.87617222,43.6113361,'34172');                                                            
insert into monument values ('spfb04x7htxd','HOTEL SAINT-COME','PRIVE','HOTEL_PARTICULIER',3.87734722,43.6080278,'34172');                                                            
insert into monument values ('spfb04jd7ux5','EGLISE SAINT-DENIS DE MONTPELLIER','PUBLIC','EGLISE',3.87491111,43.6051472,'34172');    
insert into monument values ('spdrjzvhx3eu','SQUARE MOLIERE','PUBLIC','SQUARE',3.4239307, 43.4613221,'34199'); 


create table celebrite(
num_celebrite integer primary key, 
nom varchar(16), 
prenom varchar(16), 
nationalite varchar(10),
epoque varchar(6)
);


insert into celebrite values (1,'Moulin','Jean','Francaise','XX');
insert into celebrite values (2,'Injalbert','Jean-Antoine','Francaise','XIX');
insert into celebrite values (3,'Poquelin','Jean-Baptiste','Francaise','XVII');
insert into celebrite values (4,'Chaptal','Jean-Antoine','Francaise','XVII');
insert into celebrite values (5,'Pitot','Henri','Francaise','XVIII');
insert into celebrite values (6,'Giral','Jean-Antoine','Francaise','XVIII');
insert into celebrite values (7,'Donnat','Jacques','Francaise','XVIII');
insert into celebrite values (8,'Rabelais','Francois','Francaise','XVI');
insert into celebrite values (9,'De Villeneuve','Arnaud','Francaise','XIII');
insert into celebrite values (10,'De Nostredame','Michel','Francaise','XVI');
 
 
create table associea(code_m varchar(12),
num_celebrite integer,
constraint pk_associea primary key (code_m,num_celebrite),
constraint fk_code_m foreign key (code_m) references Monument(geohash) on delete cascade,
constraint fk_num_celebrite foreign key (num_celebrite) references Celebrite(num_celebrite) on delete cascade
);

insert into associea values ('spdrjzvhx3eu',2);
insert into associea values ('spdrjzvhx3eu',3);
insert into associea values ('spfb054nhrd9',2);
insert into associea values ('spfb054nhrd9',1);
insert into associea values ('spfb054nhrd9',5);
insert into associea values ('spfb054nhrd9',6);
insert into associea values ('spfb054nhrd9',7);
insert into associea values ('spfb05kxu4d5',8);
insert into associea values ('spfb05kxu4d5',9);
insert into associea values ('spfb05kxu4d5',10);

select A.code_m, A.num_celebrite, C.nom, C.prenom, M.nom from associea A, Celebrite C, Monument M where
A.num_celebrite = C.num_celebrite and M.geohash = A.code_m ;
                                                      
select * from associea;
