# AlgoSuite – File Integrity Checker

Ein JavaFX-basiertes Desktop-Tool zur Analyse, Verifikation und Verarbeitung kryptografischer Verfahren.

Das Projekt entstand im Rahmen meiner Weiterbildung zum Fachinformatiker für Anwendungsentwicklung und dient dazu, kryptografische Grundlagen praktisch umzusetzen und zu verstehen.

## Inhaltsverzeichnis

  * [Projektidee](#projektidee)
  * [Technische Herausforderungen und Lösungsansätze](#technische-herausforderungen-und-lösungsansätze)
  * [Screenshots](#screenshots)
  * [Installation](#installation)
  * [Autor](#autor)



## Projektidee

Der File Integrity Checker entstand ursprünglich als Werkzeug zur Überprüfung der Integrität von Dateien durch den Vergleich kryptografischer Hashwerte.

Im Verlauf der Entwicklung wurde das Projekt schrittweise erweitert und entwickelte sich zu einer modularen Crypto- und Security-Suite. Ziel ist es, kryptografische Verfahren nicht nur anzuwenden, sondern deren Funktionsweise und Einsatzgebiete praktisch erfahrbar zu machen.

Im Gegensatz zu vielen ähnlichen Anwendungen werden Algorithmen nicht fest im Quellcode hinterlegt. Stattdessen werden sie dynamisch über die Java Security API geladen. Dadurch erkennt die Anwendung automatisch die von der jeweiligen Java-Laufzeitumgebung unterstützten Algorithmen und stellt diese dem Benutzer zur Verfügung.

Die Anwendung verfolgt dabei drei Hauptziele:

- Vermittlung kryptografischer Grundlagen durch praktische Anwendung
- Überprüfung der Integrität von Dateien und Daten
- Entwicklung einer modularen und erweiterbaren JavaFX-Anwendung nach objektorientierten Prinzipien

Langfristig soll das Projekt eine kompakte Alternative für häufig genutzte kryptografische Operationen bieten, ähnlich dem Konzept von CyberChef, jedoch mit bewusst reduziertem Funktionsumfang und Fokus auf die wichtigsten Sicherheitsfunktionen.

## Technische Herausforderungen und Lösungsansätze
### Dynamische Algorithmus-Erkennung

Eine zentrale Herausforderung bestand darin, kryptografische Algorithmen nicht statisch zu hinterlegen.

Die Lösung basiert auf der Java Security API:

- MessageDigest
- Cipher
- Signature
- Mac
- KeyFactory
- KeyGenerator
- SecureRandom

Über `Security.getAlgorithms(...)` werden alle verfügbaren Algorithmen zur Laufzeit ermittelt und automatisch in der Benutzeroberfläche angezeigt.

Vorteile:

- Keine Pflege statischer Listen
- Unterstützung unterschiedlicher Security Provider
- Automatische Erweiterbarkeit

### Generische ComboBox-Navigation

Für die Navigation zwischen Hauptkategorien und Algorithmen wurde eine generische Lösung entwickelt.

Anstatt separate Implementierungen für Encryption- und Decryption-Ansichten zu erstellen, wird ein gemeinsamer `ComboBoxSelectionHandler<T extends Enum<T>>` verwendet.

Dadurch können unterschiedliche Enum-Klassen verarbeitet werden, beispielsweise:

- `EncryptionCategory`
- `DecryptionCategory`

Die gleiche Logik kann somit für verschiedene Bereiche der Anwendung wiederverwendet werden.

### Trennung von GUI und Geschäftslogik

Die Anwendung folgt einer klaren Trennung der Verantwortlichkeiten:

- Controller verwalten die Benutzerinteraktion
- Services enthalten die kryptografische Logik
- Models stellen Daten bereit
- Utility-Klassen kapseln wiederverwendbare Funktionen

Dadurch bleibt der Quellcode übersichtlich, testbar und leichter erweiterbar.

### Objektorientierte Konzepte

Während der Entwicklung wurden verschiedene Java-Konzepte praktisch eingesetzt:

- Generics
- Enums
- Collections Framework
- Event Handling
- MVC-orientierte Architektur
- Kapselung
- Wiederverwendbare Komponenten

Das Projekt dient somit nicht nur als Werkzeug, sondern gleichzeitig als Lern- und Demonstrationsprojekt für moderne Java-Entwicklung.

## Screenshots

![Dateiintegritätsprüfung - Hauptansicht der Anwendung](/Docs/pictures/FileIntegrityChecker_checksum.png)
![Dateiintegritätsprüfung - Ansicht nach Verarbeitung](/Docs/pictures/FileIntegrityChecker_checkFile.png)
![HashPlayground - Verschlüsselung von Klartext](/Docs/pictures/FileIntegrityChecker_hashplayground.png)

## Installation

### Repository klonen

```bash
git clone https://github.com/bitRaybytes/FileIntegrityChecker
```

### Projekt öffnen

```text
IntelliJ IDEA oder Eclipse
```

Anschließend Maven-Abhängigkeiten laden und die Main-Klasse starten.

## Autor

Ray

Umschulung zum Fachinformatiker für Anwendungsentwicklung    
Schwerpunkt: Java-Entwicklung, Cybersecurity / Ethical-Hacking