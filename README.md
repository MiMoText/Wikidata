# Wikidata

WikidataSearcher sucht die Begriffe der 1. Spalte des [Themenvokabulars.tsv](https://github.com/MiMoText/vocabularies/blob/main/Themenvokabular.tsv) auf deren Existenz in Wikidata als Label oder Alias.

### Ablauf

Je Thema (1. Spalte), Suche nach Wikidata-Entitäten, die das Thema als
1. französisches Label (exakt oder kleingeschrieben) [**LABELFRENCH**]
1. französisches Alias (exakt oder kleingeschrieben) [**ALIASFRENCH**]
1. englisches Label (exakt oder kleingeschrieben) [**LABELENGLISH**]
1. englisches Alias (exakt oder kleingeschrieben) [**ALIASENGLISH**]

besitzen. Diese vier Strategien werden in der genannten Reihenfolge angewandt. Sobald mindestens eine Entität existiert, wird nicht mehr mit den anderen Strategien nach weiteren Entitäten gesucht.

*Bsp.: Bei der Suche für das Thema "Banques" wird keine Entität gefunden, deren franzosisches Label "Banques" ist. Daher wird nach Entitäten gesucht, die den Alias "Banques" besitzen. Hier konnte eine gefunden werden, diese wird in der Ergebnis-Datei notiert und es wird nicht noch nach Entitäten gesucht, deren englisches Label oder Alias "Banques" ist.*

### Erstellte TSV

[WikidataVokabular2.tsv](https://github.com/MiMoText/Wikidata/blob/main/WikidataVokabular2.tsv)

Header | Beschreibung
--- | ---
DEL komplett |*1. Spalte Original-Datei*
DEL Schnittmenge |*2. Spalte Original-Datei*
Anzahl der Suchergebnisse|Anzahl der Entitäten, die mit einer Strategie gefunden wurden
Ergebnisse durch Suche nach|Strategie, mit welcher die Entitäten gefunden wurden. Wurden keine Entitäten gefunden, ist diese Zelle mit **NONE** beschriftet 
Ergebnisse (IDs)|Array von Wikidata-Entitäten-IDs 
Ergebnisse (URLs)|Array von Wikidata-Entitäten-URLs (passend zu den IDs)

---
## ältere Version
[Code](https://github.com/MiMoText/Wikidata/tree/b29e47dbba527053456db79142a53298f83f11f0)

WikidataSearcher sucht die Begriffe der 1. Spalte des [Themenvokabulars.tsv](https://github.com/MiMoText/vocabularies/blob/main/Themenvokabular.tsv) auf Wikidata, extrahiert die Wikidata-IDs der ersten *fünf* Suchergebnisse und speichert diese mit der URL, dem englischen und französischen Label in der Datei [WikidataVokabular.tsv](https://github.com/MiMoText/Wikidata/blob/main/WikidataVokabular.tsv).

Die Ergebnisse wurden um die **deutschen Labels** erweitert. Die erweiterte Liste befindet sich in der Datei [WikidataVokabularDE.tsv](https://github.com/MiMoText/Wikidata/blob/main/WikidataVokabularDE.tsv). Die Datei wurde durch neue Suchanfragen an Wikidata generiert, wodurch nicht zwingend, die gleichen Wikidata-Entitäten, wie in der Datei [WikidataVokabular.tsv](https://github.com/MiMoText/Wikidata/blob/main/WikidataVokabular.tsv), enthalten sind.

### Ablauf

Je Thema (1. Spalte)
1. HTTP-Get Anfrage an https://www.wikidata.org/w/index.php?search=
2. Aus dem Body der HTML werden die Wikidata-IDs der ersten *fünf* Suchergebnisse extrahiert
3. Per SPARQL Anfrage werden das englische Label und französische Label ermittelt

### Erstellte TSV
[WikidataVokabular.tsv](https://github.com/MiMoText/Wikidata/blob/main/WikidataVokabular.tsv)

Header | Beschreibung
--------- | ---------------
DEL komplett |*1. Spalte Original-Datei*
DEL Schnittmenge |*2. Spalte Original-Datei*
id0 | Wikidata-ID des 1. Suchergebnisses
url0 | Wikidata-URL des 1. Suchergebnisses
label0 | Wikidata-Label des 1. Suchergebnisses (englisch)
label@fr0 | Wikidata-Label des 1. Suchergebnisses (französisch)
label@de0 | Wikidata-Label des 1. Suchergebnisses (deutsch)
*id1 - label@de1* | *Wikidata-Infos für 2. Suchergebnis [äquivalent zu 1.]*
*id2 - label@de2* | *Wikidata-Infos für 3. Suchergebnis [äquivalent zu 1.]*
*id3 - label@de3* | *Wikidata-Infos für 4. Suchergebnis [äquivalent zu 1.]*
*id4 - label@de4* | *Wikidata-Infos für 5. Suchergebnis [äquivalent zu 1.]*
