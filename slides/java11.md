class: inverse, center, middle

# Újdonságok Java 11-től

---

## Tematika 1.

* `var` használata lambda kifejezésekben
* `Predicate` `not(Predicate)`
* `Optional*` `isEmpty()`
* `Pattern` `asPredicate()`
* String metódusok: 
    * `isBlank()`, `lines()`, `repeat(int)`
    * `strip()`, `stripLeading()`, `stripTrailing()`: Unicode aware
* `Collection` `toArray`
* Files metódusok: `readString()`, `writeString()`, UTF-8
* `FileReader`, `FileWriter` encoding
* `Path.of`
* HTTP Client API átkerült véglegesként
* Java EE és CORBA modulok eltávolítása: JAX-WS, JAXB
* Java forráskód azonnali futtatása  (shebang)
* Nashorn deprecated
* TLS 1.3 részleges támogatás
* Curve25519 és Curve448 kulcsegyeztető algoritmusok: kevésbé támadhatóak
* ChaCha20 és Poly1305 kriptográfiai algoritmusok megvalósítása
* Pack200 és Unpack200 eltávolítása

## JVM újdonságok

* Z Garbage Collector
    * Terabyte nagyságú heap-ek kezelésére
    * 10 ms alatt a megállások
    * Skálázható, memóriaterület nagyságának növekedésével ne nőjön az GC idő
    * Max 15%-ot használjon a futási időből
    * Felkészülés a multi-tiered heapre (flash és non-volatile memory elterjedésével, Non-volatile random-access memory (NVRAM))
    * Színes pointerek
    * Load barrier
* Epsilon Garbage Collector
    * NOP
    * Felhasználási területei: benchmark, memóriaműveletek tesztelésére, apró (parancsori) programoknál, VM teszteléshez
* CONSTANT_Dynamic
* Alacsony költségű heap profile - `SampledObjectAlloc` JVM TI
* Unicode 10.0.0 támogatás (új szkriptek és karakterek)
    * Szkript: betűk és írásjelek csoportja
