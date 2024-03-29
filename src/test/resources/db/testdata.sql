insert into dbo.Ägare (AgarID, OrganisationsNr, Bolagsnamn, AndradDatum, UpplagdDatum)
values  (52, N'559162-8136', N'Kalle Ankas Aktiebolag', N'2020-09-11 08:45:19.923', '2020-09-11 08:45:19.923');

insert into dbo.Objekt (ObjektID, AgarID, ServeringsNamn, AndradDatum, UpplagdDatum)
values  (202, 52, N'Kalles Uteservering', N'2020-08-28 10:54:11.813', N'2003-04-02 09:18:37.000');

insert into dbo.Ärende (ArendeID, HandlaggarID, ArendeTyp, DiarieNr, ObjektID, OppnandeDatum, AvslutsDatum, AndradDatum, UpplagdDatum)
values  (2, N'JOS', N'5', N'1234567', 202, N'1996-01-18 00:00:00.000', N'1996-01-18 00:00:00.000', null, null),
        (9, N'JOS', N'2', N'2345678', 202, N'1996-01-18 00:00:00.000', N'1996-02-23 23:12:01.000', N'2003-03-29 23:12:02.000', null),
        (12, N'JOS', N'5', N'3456789', 202, N'1996-01-16 00:00:00.000', N'1996-01-17 23:08:50.000', N'2003-03-29 23:08:50.000', null);

insert into dbo.Ärende_Beslut (BeslutsID, ArendeID, BeslutsTyp, BeslutsDatumTid)
values  (3259, 2, N'Deleg', N'1996-01-18 00:00:00.000'),
        (3260, 9, N'Deleg', N'1996-01-22 00:00:00.000'),
        (3262, 12, N'Deleg', N'1996-01-22 00:00:00.000');

insert into dbo.Ärende_Händelser (HandelseID, ObjektID, ArendeID, DiarieNr, HandelseTyp, AndradDatum, HandelseDatumTid, UpplagdDatum)
values  (2, null, 2, N'1234567', N'REMP', null, N'1996-02-13 00:00:00.000', null),
        (3, null, 9, N'2345678', N'REMP', null, N'1996-02-13 00:00:00.000', null),
        (4, null, 12, N'3456789', N'REMP', null, N'1996-03-01 00:00:00.000', null);

insert into dbo.Klartext (Kod, Kodtyp, Klartext)
values  (N'00', N'C', N'-'),
        (N'01', N'C', N'Planskiss över lokal'),
        (N'01', N'F', N'Adm.område 1'),
        (N'01', N'K', N'Casio QT 7300 nr 78'),
        (N'01', N'M', N'Intern bevakning'),
        (N'01', N'V', N'Bristande tillgång till lagad mat'),
        (N'0100', N'B', N'Tillstånd allmänhet'),
        (N'0100', N'O', N'Krav på bordsservering'),
        (N'0110', N'B', N'Tillstånd allmänheten, delvis bifall'),
        (N'0120', N'B', N'Tillstånd allmänhet, avslag'),
        (N'02', N'C', N'Ritning över elsystemet'),
        (N'02', N'F', N'Adm.område 2'),
        (N'02', N'K', N'Siemens RT 565 - R5'),
        (N'02', N'L', N'Uppföljningstillsyn'),
        (N'02', N'M', N'Felaktiga uppgifter'),
        (N'02', N'V', N'Bristande tillgång till lättdrycker'),
        (N'0200', N'B', N'Utökat tillstånd, bifall'),
        (N'0200', N'O', N'Krav från Räddningstjänst'),
        (N'0210', N'B', N'Utökat tillstånd, delvis bifall'),
        (N'0220', N'B', N'Utökat tillstånd, avslag'),
        (N'0221', N'B', N'Utökad serveringsyta, avslag'),
        (N'0222', N'B', N'Utökad serveringsyta, delvis bifall'),
        (N'0223', N'B', N'Utökad serveringsyta'),
        (N'0230', N'B', N'Tillfälligt utökat tillstånd, bifall'),
        (N'0231', N'B', N'Tillfälligt tillstånd i avvaktan på beslut'),
        (N'0232', N'B', N'Tillfälligt tillstånd; allmänhet'),
        (N'0233', N'B', N'Tillfälligt tillstånd allmänhet; delvis bifall'),
        (N'0234', N'B', N'Tillfälligt utökat tillstånd, delvis bifall'),
        (N'0235', N'B', N'Tillfälligt tillstånd slutet sällskap, bifall'),
        (N'0236', N'B', N'Tillfälligt tillstånd slutet sällskap, avslag'),
        (N'03', N'C', N'Ritning över ventilation'),
        (N'03', N'K', N'Casio CE-4700'),
        (N'03', N'L', N'Påkallad tillsyn'),
        (N'03', N'V', N'Bristande återhållsamhet vid servering'),
        (N'0300', N'B', N'Ändrat tillstånd, bifall'),
        (N'0300', N'O', N'Krav på föorordnade ordningsvakter'),
        (N'0310', N'B', N'Ändrat tillstånd, delvis bifall'),
        (N'0320', N'B ', N'Ändrat tillstånd, avslag'),
        (N'04', N'A', N'Tillstånd, trafikservering'),
        (N'04', N'C', N'Ritning över VVS-system'),
        (N'04', N'K', N'Casio TK-T200'),
        (N'04', N'L', N'Rutintillsyn'),
        (N'04', N'V', N'Servering till märkbart påverkad person'),
        (N'0400', N'B', N'Tillstånd slutet sällskap, bifall'),
        (N'0410', N'B', N'Tillstånd slutet sällskap, delvis bifall'),
        (N'0420', N'B', N'Tillstånd slutet sällskap, avslag'),
        (N'05', N'K', N'Sharp XE-A113'),
        (N'05', N'L', N'Mysam'),
        (N'05', N'V', N'Servering till minderårig '),
        (N'0500', N'B', N'Tillsyn, ej åtgärd'),
        (N'0510', N'B', N'Tillsyn, skärpta villkor'),
        (N'0520', N'B', N'Tillsyn, varning'),
        (N'0530', N'B', N'Tillsyn, varning+villkor'),
        (N'0540', N'B', N'Tillsyn, återkallat tillstånd'),
        (N'0550', N'B', N'Tillsyn, erinran'),
        (N'06', N'K', N'Casio QT-2100'),
        (N'06', N'V', N'Servering utom tillåten serveringstid'),
        (N'0600', N'B', N'Begäran om information'),
        (N'0610', N'B', N'Extern information'),
        (N'0620', N'B', N'Avskrivvning av ärendet '),
        (N'0630', N'B', N'Att avge yttrande'),
        (N'0640', N'B', N'Avsluta utredning utan åtgärd'),
        (N'07', N'A', N'Tillsyn, ifrågasatt åtgärd'),
        (N'07', N'K', N'Orient RT565-R4'),
        (N'07', N'V', N'Servering utan/utöver tillstånd'),
        (N'0700', N'B', N'Återkallat tillstånd, konkurs'),
        (N'0710', N'B', N'Återkallat tillstånd'),
        (N'0720', N'B', N'Upphävande av beslutad varning'),
        (N'08', N'A', N'Tillsyn, restaurangrapport'),
        (N'08', N'K', N'Atronic Uniwell TX-850'),
        (N'08', N'V', N'Olägenheter i fråga om ordning'),
        (N'09', N'A', N'Tillsyn, information m m'),
        (N'09', N'K', N'Casio FIR-C650'),
        (N'09', N'V', N'Olägenheter i fråga om nykterhet'),
        (N'1', N'A', N'Utökat tillstånd, allmänheten'),
        (N'1', N'E', N'Stat1'),
        (N'1', N'G', N'Centrala Sundsvall'),
        (N'1', N'Q', N'Discotek'),
        (N'10', N'A', N'Övriga ärenden'),
        (N'10', N'G', N'Skönsmon'),
        (N'10', N'K', N'Samsung ER 550'),
        (N'10', N'Q', N'Trafikrestaurang'),
        (N'10', N'V', N'Olämplig prissättning'),
        (N'11', N'A', N'Utökat tillstånd, slutet sällskap'),
        (N'11', N'G', N'Granlo/Granloholm/Bergsåker'),
        (N'11', N'K', N'Trivec EP'),
        (N'11', N'Q', N'Utpräglad matrestaurang'),
        (N'11', N'V', N'Ingen serveringsansvarig närvarande'),
        (N'12', N'A', N'Ändrat tillstånd, slutet sällskap'),
        (N'12', N'G', N'Catering'),
        (N'12', N'K', N'Casio TE-2000'),
        (N'12', N'Q', N'Kvartersrestaurang'),
        (N'12', N'V', N'Ej anställd person'),
        (N'13', N'A', N'Upphörande tillstånd - egen begäran'),
        (N'13', N'G', N'Haga'),
        (N'13', N'K', N'Orient RP 3115 RICHPOS 3000'),
        (N'13', N'Q', N'Värdshus'),
        (N'13', N'V', N'Medförande av alkohol från serv ställ'),
        (N'14', N'A', N'Upphörande tillstånd - konkurs'),
        (N'14', N'G', N'Kovland'),
        (N'14', N'K', N'Casio RT-565-R5'),
        (N'14', N'Q', N'Pausservering'),
        (N'14', N'V', N'Otillåten förvaring/förtäring alk dryck'),
        (N'15', N'A', N'Yttrande Spelinspektionen'),
        (N'15', N'G', N'Timrå'),
        (N'15', N'K', N'TouchPoint T 70 J2 Itaz'),
        (N'15', N'Q', N'Kursgård'),
        (N'15', N'V', N'Otillräckligt antal sittplatser'),
        (N'16', N'A', N'Ändring i bolag/PBI'),
        (N'16', N'K', N'Casio TK 1300'),
        (N'16', N'Q', N'Vägkrog'),
        (N'16', N'V', N'Ombyggnad utan medgivande '),
        (N'17', N'A', N'Anmälan ändring serveringsansvariga'),
        (N'17', N'K', N'Omron 2810'),
        (N'17', N'Q', N'Sportanläggning'),
        (N'17', N'V', N'Bristande bokföringsrutiner '),
        (N'18', N'K', N'Touch Point RT 565 - R4'),
        (N'18', N'Q', N'Säsongsrestaurang'),
        (N'18', N'V', N'Ifrågasatt efterlevn annan lagstiftning'),
        (N'19', N'K', N'TouchPoint RP-3115 FEC Richpos'),
        (N'19', N'Q', N'Folkets Hus/föreningslokal'),
        (N'19', N'V', N'Servering utanför insynad serveringsyta'),
        (N'2', N'A', N'Nytt tillstånd, allmänheten'),
        (N'2', N'E', N'Stat2'),
        (N'2', N'G', N'Njurunda'),
        (N'20', N'K', N'Touch Point 370'),
        (N'20', N'Q', N'Restaurangtält'),
        (N'20', N'V', N'Ej anmält ändring i bolaget'),
        (N'21', N'A', N'Nytt tillstånd provsmakning, allmänheten'),
        (N'21', N'K', N'Uniwell UX-45F'),
        (N'21', N'Q', N'Teater'),
        (N'21', N'V', N'Brister gällande matkravet'),
        (N'22', N'A', N'Nytt tillstånd, catering'),
        (N'22', N'K', N'Nitsuka PB 4500-0700'),
        (N'22', N'Q', N'Bryggeri'),
        (N'22', N'V', N'Avsaknad av alkohofria alternativ i meny'),
        (N'23', N'A', N'Tillfälligt tillstånd pausservering'),
        (N'23', N'K', N'Sharp ER-A440/450'),
        (N'24', N'K', N'Torex XT+'),
        (N'25', N'K', N'Touch  Point RP 3515'),
        (N'26', N'K', N'Trivec RP-3615'),
        (N'27', N'K', N'Pixel point ver 9.0'),
        (N'28', N'K', N'Sam 4 S 260'),
        (N'29', N'K', N'Casio CE-T300'),
        (N'3', N'A', N'Ändrat tillstånd, allmänheten'),
        (N'3', N'E', N'Stat3'),
        (N'3', N'G', N'Matfors'),
        (N'3', N'Q', N'Dansrestaurang'),
        (N'30', N'K', N'Squirrel 6i'),
        (N'31', N'K', N'Casio CE-T300'),
        (N'32', N'K', N'FEC Gladius'),
        (N'33', N'K', N'Sharp UP 3500'),
        (N'34', N'K', N'Pixelpoint 10,1'),
        (N'4', N'A', N'Tillfälligt tillstånd, allmänhet'),
        (N'4', N'E', N'Stat4'),
        (N'4', N'G', N'Birsta/Sundsbruk'),
        (N'4', N'Q', N'Föreningslokaler'),
        (N'40', N'A', N'Tillfälligt utökat tillstånd, allmänhet'),
        (N'41', N'A', N'Tillfälligt utökat tillstånd, slutet sällskap'),
        (N'5', N'A', N'Tillstånd, slutet sällskap'),
        (N'5', N'E', N'Stat5'),
        (N'5', N'G', N'Alnö'),
        (N'6', N'A', N'Ändrade ägarförhållanden'),
        (N'6', N'G', N'Indal/Liden'),
        (N'7', N'A', N'Tidsbegränsat tillstånd - omprövning'),
        (N'7', N'G', N'Stöde'),
        (N'7', N'Q', N'Båtrestaurang'),
        (N'8', N'A', N'Tidsbegränsat tillstånd'),
        (N'8', N'G', N'Skönsberg'),
        (N'8', N'Q', N'Hotellrestaurang'),
        (N'9', N'A', N'Tillfälligt tillstånd, slutet sällskap'),
        (N'9', N'G', N'Nacksta'),
        (N'9', N'Q', N'Personal/lunchrestaurang'),
        (N'99', N'A', N'Tillståndsärende, Lst före 1996'),
        (N'9900', N'B', N'Tillstånd beviljade före 1996'),
        (N'AB', N'S', N'Aktiebolag'),
        (N'Ägare', N'P', N'Ägare'),
        (N'ANMC', N'D', N'Anmälan Catering'),
        (N'ANMP', N'D', N'Anmälan Provsmakning'),
        (N'ANSA', N'D', N'Anmälan serveringsansvariga'),
        (N'ANSK', N'D', N'Ansökan komplett'),
        (N'ANST', N'D', N'Ansökan om tillstånd'),
        (N'ANSU', N'D', N'Utsända ansökningshandlingar m m'),
        (N'ANT', N'D', N'Anteckning'),
        (N'ANTB', N'D', N'Besök'),
        (N'ANTT', N'D', N'Telefonsamtal'),
        (N'AVG', N'D', N'Avgift fakturerad'),
        (N'AVGB', N'D', N'Inkommen betalning av avgift'),
        (N'AVGU', N'D', N'Utsänd begäran om betalning av avgift'),
        (N'BESD', N'D', N'Beslut av delegat'),
        (N'BESN', N'D', N'Beslut av nämnd'),
        (N'BESX', N'D', N'Beslutsexpediering'),
        (N'BILAG', N'D', N'Bilaga till ansökan'),
        (N'Bman', N'P', N'Bolagsman'),
        (N'BOK', N'D', N'Begäran komplettering'),
        (N'D 02', N'B', N'Ändring i bolag'),
        (N'D 03', N'B', N'Ändring i bolag, ej godkänd'),
        (N'Deleg', N'B', N'Delegationsbeslut SKÅL'),
        (N'DOMI', N'D', N'Inkommen handling från domstol'),
        (N'DOMU', N'D', N'Översändande av handlingar till domstol'),
        (N'EF', N'S', N'Enskild firma'),
        (N'FE', N'S', N'Förening'),
        (N'Ft', N'P', N'Firmatecknare'),
        (N'GTILL', N'D', N'Gällande tillstånd dokument'),
        (N'HAND', N'D', N'Annan inkommen handling'),
        (N'HANS', N'D', N'Upprättad handling'),
        (N'HANU', N'D', N'Annan utsänd handling'),
        (N'HB', N'S', N'Handelsbolag'),
        (N'IKOMP', N'D', N'Inkommen komplettering'),
        (N'INKAN', N'D', N'Inkommen anmälan'),
        (N'KB', N'S', N'Kommanditbolag'),
        (N'KO', N'S', N'Kommun'),
        (N'KOMS', N'D', N'Svar på kommunicering'),
        (N'KOMU', N'D', N'Kommunicering'),
        (N'KPAN', N'D', N'Kunskapsprov  anteckning'),
        (N'Länss', N'B', N'Länsstyrelsebeslut SKÅL'),
        (N'Led', N'P', N'Ledamot'),
        (N'MOTT', N'D', N'Mottagningsbrev'),
        (N'MYND', N'D', N'Handling från annan myndighet'),
        (N'MYNU', N'D', N'Översändande av handlingar till myndighet'),
        (N'NAMF', N'D', N'Förslag till nämnd'),
        (N'Nämnd', N'B', N'Nämdsbeslut SKÅL'),
        (N'Ordf', N'P', N'Ordförande'),
        (N'REMA', N'D', N'Remiss, annan myndighet'),
        (N'REMFA', N'D', N'Remiss till fältassistenterna'),
        (N'REMK', N'D', N'Remiss till kronofogdemyndigheten'),
        (N'REMM', N'D', N'Remiss till miljö- och hälsoskydd'),
        (N'REMP', N'D', N'Remiss till polisen'),
        (N'REMR', N'D', N'Remiss till räddningstjänsten'),
        (N'REMS', N'D', N'Remissvar'),
        (N'REMSK', N'D', N'Remiss till skattemyndigheten'),
        (N'Rest', N'P', N'Restaurangchef'),
        (N'Rev', N'P', N'Revisor'),
        (N'STAI', N'D', N'Infordran av statistik'),
        (N'STAT', N'D', N'Inkommen statistik'),
        (N'Suppl', N'P', N'Suppleant'),
        (N'TILL', N'D', N'Förtydligande, tillägg e dyl'),
        (N'TILUT', N'D', N'Begäran om förtydligande m m'),
        (N'UNG', N'Q', N'Undomlig målgrupp'),
        (N'UTOK', N'Q', N'Utökad tillsyn'),
        (N'UTSKR', N'D', N'Dokument skapat'),
        (N'VD', N'P', N'Verkställande direktör');
