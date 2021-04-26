class: inverse, center, middle

# Újdonságok Java 9-től

---

## Tematika 1.

* Nyelvi módosítások
    * Try-with-resources effectively final változókon
    * Diamond operátor anonymous inner class esetén
    * Privát metódusok interfészekben
* API-k
    * `Set.of`, `List.of`, `Map.of`
	* Nem csak varargs, hanem sok paraméteres - gyorsabb
	* Null értéket nem lehet megadni
	* Unmodifiable kollekciók
        * A módosító metódusok `UnsupportedOperationException` kivételt dobnak
        * Nem feltétlenül immutable (ha az elemei módosíthatóak)
	* Minden iterálásnál más sorrendben adja vissza a `Set` és `Map` az elemeket, hogy hamarabb
	    kiderüljön, ha valami a sorrendre támaszkodik
    * `Map.ofEntries()`, `Map.ofEntries(entry(k1, v1), entry(k2, v2))`
    * Stream `dropWhile`, `takeWhile`, `ofNullable`, `iterate` overloadolt változata
    * `Optional` `stream()`, `ifPresentOrElse()`, `or()` metódusok
    * CompletableFuture API Improvements
    * Deprecated interface metódusai: `forRemoval` és `since` 
    * Multi-Resolution Image API
    * Method handle
* Kereshetőség a JavaDoc-ban
* Java Module System, jlink, Multi-release JARs
* Új HTTP kliens
* Process API, `ProcessHandle`
* Publish-Subscribe Framework - Reactive streams
* JShell - Java REPL
* Applet API deprecated