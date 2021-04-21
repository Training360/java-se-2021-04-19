Írj egy `Numbers` osztályt, mely képes a következőkre:

* Kap egy List<Integer> (páros számú), visszaad egy List<Integer>, párokba rendezi, összeadja
* Kap egy List<Integer> (páros számú), visszaad egy List<Integer>, párokba rendezi, szorozza

Hozz létre egy `Product` osztályt, aminek van `price`, `bonusPrice` attribútuma!
Hozz létre egy `Products` osztályt, abban egy `int sum(List<Product>, ...)`,
hogy mi alapján összegezzen. (Történhet price, bonusPrice alapján is.)

## Felhő tárhelyek

Különböző felhő tárhely szolgáltatókat szeretnénk összehasonlítani, ezért a `CloudStorage` osztályban 
eltároljuk a különböző adataikat. A tárhely mérete GB-ban adott, az árak pedig mindenhol ugyanabban a pénznemben. 
A `PayPeriod` enum a fizetési gyakoriság, ahol a `length` értéke a hossz hónapokban megadva (lifetime esetén 60 hónap). 
Az ingyenes csomagok esetén a fizetési gyakortiság nincs megadva. A 
`CloudStorage` implementálja a `Comparable` interfészt, a természetes rendezettségét az 1000 GB-ra eső éves díj nagysága adja.

![CloudStorage UML](images/cloud_class.png)

A `Clouds` osztály metódusai a paraméterként kapott listából bizonyos szempont szerint a legjobba(ka)t adják vissza. 
Amennyiben több ugyanolyan van, akkor közülük bármelyik visszaadható.

* `alphabeticallyFirst()`: a szolgáltató neve alapján betűrendben a legelső `CloudStorage`. Kis-nagybetű nem számít.
* `bestPriceForShortestPeriod()`: a legrövidebb időszakra vonatkozó legolcsóbb `CloudStorage`. Ha van 
ingyenes, akkor azok közül bármelyik megadható.
* `worstOffers()`: a természetes rendezettség szerinti 3 legrosszabb ajánlat.

## Alkalmazottak

Hozzatok létre egy `Employee` osztályt, `String name`, `int salary`, `String position` attribútumokkal!
A `findEmployees(List<Employee> employees)` metódus adja vissza a `boss` pozícióban lévő alkalmazottak nevét, fizetés alapján sorbarendezve!

## Számoló

Írjatok egy `counter.Counter` nevezetű osztályt! Legyen benne egy `int countEven(List<Integer> numbers)` metódus.
Számolja meg, hogy a listában hány darab páros szám szerepel! Streamekkel!

Legyen benne egy `CounterResult countNumbers(List<Integer> numbers)`. Ez egy bejárással számolja meg, hogy hány negatív, és hány pozitív
szám van benne! Nullát ne számoljátok sehova! Ciklusokkal. `CounterResult`, immutable legyen! Legyen neki konstruktora, és
csak getterei! Ne legyen settere!

