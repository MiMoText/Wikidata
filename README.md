# Wikidata

WikidataSearcher sucht die Begriffe der 1. Spalte des [Themenvokabulars.tsv](https://github.com/MiMoText/vocabularies/blob/main/Themenvokabular.tsv) auf Wikidata, extrahiert die Wikidata-IDs der ersten *fünf* Suchergebnisse und speichert diese mit der URL, dem englischen und französischen Label in der Datei [WikidataVokabular.tsv](https://github.com/MiMoText/Wikidata/blob/main/WikidataVokabular.tsv).

Die Ergebnisse wurden um die **deutschen Labels** erweitert. Die erweiterte Liste befindet sich in der Datei [WikidataVokabularDE.tsv](https://github.com/MiMoText/Wikidata/blob/main/WikidataVokabularDE.tsv).

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
