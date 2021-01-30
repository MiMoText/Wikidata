# Wikidata

WikidataSearcher sucht die Begriffe der 1. Spalte des [Themenvokabulars.tsv](https://github.com/MiMoText/vocabularies/blob/main/Themenvokabular.tsv) auf Wikidata, extrahiert die Wikidata-IDs der ersten *fünf* Suchergebnisse und speichert diese mit der URL, dem englischen und französischen Label in der Datei [WikidataVokabular.tsv](https://github.com/MiMoText/Wikidata/blob/main/WikidataVokabular.tsv).

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
*id1 - label@fr1* | *Wikidata-Infos für 2. Suchergebnis [äquivalent zu 1.]*
*id2 - label@fr2* | *Wikidata-Infos für 3. Suchergebnis [äquivalent zu 1.]*
*id3 - label@fr3* | *Wikidata-Infos für 4. Suchergebnis [äquivalent zu 1.]*
*id4 - label@fr4* | *Wikidata-Infos für 5. Suchergebnis [äquivalent zu 1.]*
