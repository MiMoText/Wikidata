# Wikidata

WikidataSearcher ermöglicht die Abfrage von Wikidata, wie IDs, Label und Alias, und beinhaltet zwei Main-Skripte: **DelMain** und **VocabularyMain**.

## DelMain

DelMain erweitert die Datei [DEL_reduziert.tsv](https://github.com/MiMoText/vocabularies/blob/main/DEL_reduziert.tsv) um eine Spalte für die deutschen Labels *Wikidata Label (de)*, indem diese mithilfe der Wikidata-ID *Wikidata Ids (zu DEL-Begriffen)* von Wikidata abgefragt werden.

Die erweiterte Tabelle ist die [DEL_reduziert_de.tsv](https://github.com/MiMoText/vocabularies/blob/main/DEL_reduziert_de.tsv).

## VocabularyMain

VocabularyMain sucht die Begriffe der 1. Spalte des [Themenvokabulars.tsv](https://github.com/MiMoText/vocabularies/blob/main/Themenvokabular.tsv) auf deren Existenz in Wikidata als Label oder Alias.

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
