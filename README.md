# 1. O projektu
Tema projekta je kreiranje sistema preporuke koji na osnovu zadatog filma preporučuje druge filmove, koristeći (kao osnovu za preporuku) sličnost filmova zasnovanu na njihovim atributima. 
Drugim rečima, na osnovu filma koji je korisnik zadao, sistem bi trebalo da preporuči određeni broj filmova koji su najsličniji zadatom filmu; preporučeni filmovi bi trebalo da budu sortirani prema izračunatoj sličnosti sa zadatim filmom.

# 2. Dataset

Dataset koji sadrži podatke o filmovima koji su potrebni kako bi se vršila preporuka je preuzet sa servisa [DBpedia](http://dbpedia.org). DBpedia sadrži podatke sa Wikipedia-e, ali u RDF obliku.
Korišćen je sledeći SPARQL upit kako bi se iz mnoštva podataka sa DBpedia-e izdvojio i kreirao dataset sa podacima koji su relevantni za računanje sličnosti filmova:

```
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#><
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>
PREFIX category: <http://dbpedia.org/resource/Category>
PREFIX dcterms: <http://purl.org/dc/terms/>
 
CONSTRUCT
{
       ?film rdf:type dbpedia-owl:Film;
             dbpedia-owl:starring ?actor;
             dcterms:subject ?subject;
             dbpedia-owl:director ?film_director.
}
WHERE {
       ?film rdf:type dbpedia-owl:Film;
            dbpedia-owl:starring ?actor;
            dcterms:subject ?subject;
            dbpedia-owl:director ?film_director.
}
```

Kao što se iz upita vidi, podaci o filmovima na osnovu kojih će se računati sličnost su *režiser filma*, *glumci u filmu*, kao i *kategorije* kojima film pripada.

Dataset koji je kreiran ovim upitom se nalazi na putanji */data/data.rdf* u okviru projekta.

# 3. Rešenje
Osnovna ideja aplikacije je da se korisniku preporuče filmovi slični filmu koji je korisnik zadao. 
Potrebno je da se uporede podaci o režiserima, glumcima i kategorijama filmova i da se kvantifikuje mera sličnosti i poklapanja podataka između unetog filma i ostalih filmova iz dataseta. Algoritam je zasnovan na pristupu koji je korišćen od strane Miricija i ostalih [1].

Prvo se učitavaju svi podaci iz lokalnog RDF dataseta u memoriju kako bi se vršile operacije sa podacima. Zatim se učitani podaci prevode u pogodan oblik kako bi se mogao primeniti odgovarajući algoritam. 
Pošto se koriste tri svojstva na osnovu kojih se ra;una sličnost (režiser, glumci i kategorije), kreirane su 3 posebne binarne matrice se podacima koji se tiču svakog svojstva. 

Matrica se popunjava na sledeći način: Svaki red u matrici predstavlja jedan film iz dataseta, a kolone u matricama predstavljaju sve glumce, režisere i kategorije koji su vezani bar za jedan film u datasetu. Ako je neki npr. glumac glumio u određenom filmu, presek reda matrice koji se odnosi na taj film i kolone matrice koja se odnosi na tog glumca će imati vrednost 1, presek tog filma sa glumcima koji nisu glumili u filmu će imati vrednost 0.

Sledeći korak je primenjivanje [Vector Space Model (VSM) algoritma](https://en.wikipedia.org/wiki/Vector_space_model) na prethodno popunjene matrice. Ideja ovog algoritma je da se svi podaci o jednom filmu i njegovim režiserom, glumcima i kategorijama predstave preko samo jednog vektora u vektorskom prostoru, kako bi se kasnije ti vektori filmova mogli porediti međusobno. 

U okviru VSM algoritma se koristi i [tf-idf (term frequency-inverse document frequency)](https://en.wikipedia.org/wiki/Tf%E2%80%93idf) postupak koji naglašava bitnost pojavljianja retkih atributa u odnosu na one koji se pojavljuju često. Ako se npr. neki glumac pojavljuje (hipotetički) u svakom filmu našeg dataseta, ta informacija nije korisna sa aspekta sličnosti i zbog toga tf-idf minimizuje njen značaj u kalkulaciji. Važi i obrnuto, što je ređe pojavljivanje nekog glumca, veći značaj za računanje sličnosti.

Nakon toga dobijaju se vektori za svaki film i u parovima se porede poklapanje vektora unešenog filma sa vektorima svih ostalih filmova u datasetu. Na osnovu vrednosti koeficijenta poklapanja određuju se najsličniji filmovi.

# 4. Tehnička realizacija
U ovom prejektu je korišćena [Jena](https://jena.apache.org/) biblioteka, kao i ostale biblioteke na kojima se ona zasniva.
Jena je Java biblioteka koja pruža mnoštvo mogućnosti za rad sa RDF-om. Pomoću Jena biblioteke je moguće učitavati postojeće datasetove, kreirati nove datasetove, vršiti SPARQL upite nad podacima kao i dosta drugih stvari. Jena takođe sadrži i API koji znatno olakšava kretanje kroz RDF datasetove i izvačenje podataka iz njih, za to je biblioteka prvenstveno i korišćena u ovom projektu.

# 5. Primer korišćenja
Za potrebe demonstracije rada algoritma, kreiran je prost interfejs kako bi koristnik mogao da testira njegov rad.

Kada korisnik pokrene aplikaciju u konzoli mu se izlistaju svi filmovi iz lokalnog dataseta. Od koristnika se traži da odabere film za želi hoće preporuku.

![alt text](http://s23.postimg.org/pirmwhv3f/image.jpg)

Nakon toga, potrebno je da korisnik unese faktore važnosti režisera, glumaca i kategorija, tj. koliko u ukupnoj oceni sličnosti treba da budu bitni svaki od ova tri parametra. Vrednost faktora važnosti se kreće u raspono od 0 do 1. Takođe je potrebno da unese i broj preporuka koji korisnik želi.

![alt text](http://s23.postimg.org/501sojnhn/image.jpg)

Nakon toga se korisniku ispisuju preporuke u konzoli i nudi mu se opcija da vrši novu preporuku ili da isključi aplikaciju.

![alt text](http://s23.postimg.org/qjrvlmc2z/image.jpg) 

# 6. Priznanja
Ovaj projekat je nastao za potrebe predmeta [Inteligentni sistemi](http://is.fon.rs/) na [Fakultetu organizacionih nauka](http://www.fon.bg.ac.rs/), Univerzitet u Beogradu.

# 7. Reference
[1] Mirizzi, R., Di Noia, T., Ostuni, V. C., & Ragone, A. (2012). Linked Open Data for content-based recommender systems.
