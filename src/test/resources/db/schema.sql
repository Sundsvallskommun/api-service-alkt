create table dbo.Klartext
(
    Kodtyp    varchar(2) not null,
    Kod       varchar(5) not null,
    Klartext  varchar(50),
    primary key (Kod, Kodtyp)
);

create table dbo.Objekt
(
    ObjektID             int not null primary key,
    AgarID               int,
    ServeringsNamn       varchar(40),
    UpplagdDatum         datetime,
    AndradDatum          datetime
);

create index IX_AgarID
    on dbo.Objekt (AgarID);

create table dbo.Ägare
(
    AgarID            int not null primary key,
    OrganisationsNr   varchar(12),
    Bolagsnamn        varchar(40),
    UpplagdDatum      datetime,
    AndradDatum       datetime
);

create table dbo.Ärende
(
    ArendeID                int not null primary key,
    ObjektID                int,
    ArendeTyp               varchar(6),
    HandlaggarID            varchar(5),
    DiarieNr                varchar(30),
    OppnandeDatum           datetime,
    AvslutsDatum            datetime,
    UpplagdDatum            datetime,
    AndradDatum             datetime
);

create index IX_ObjektID
    on dbo.Ärende (ObjektID);

create table dbo.Ärende_Beslut
(
    BeslutsID               int not null primary key,
    ArendeID                int,
    BeslutsTyp              varchar(5),
    BeslutsDatumTid         datetime
);

create index IX_ÄrendeID
    on dbo.Ärende_Beslut (ArendeID);

create table dbo.Ärende_Händelser
(
    HandelseID       int not null primary key,
    ArendeID         int,
    HandelseTyp      varchar(5),
    HandelseDatumTid datetime,
    UpplagdDatum     datetime,
    AndradDatum      datetime,
    ObjektID         int,
    DiarieNr         varchar(30)
);

create index IX_ArendeID
    on dbo.Ärende_Händelser (ArendeID);
