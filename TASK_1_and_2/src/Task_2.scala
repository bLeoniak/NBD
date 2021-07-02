import java.time.DayOfWeek

object Task_2 {
  def main(args:Array[String]): Unit = {
    val days: List[String] = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")

    //Punkt 1
    println("Punkt 1")
    println(days(1) +": " + getDayDescription(days(1)))
    println(days(2) +": " + getDayDescription(days(2)))
    println(days(6) +": " + getDayDescription(days(6)))
    println("Styczeń: " + getDayDescription("Styczeń"))

    //Punkt 2
    println("\nPunkt 2")
    val kontoBankowe = new KontoBankowe(2500)
    val kontoBankowePolaka = new KontoBankowe

    println("Saldo poczatkowe1: " + kontoBankowe.stanKonta)
    println("Saldo poczatkowe2: " + kontoBankowePolaka.stanKonta)

    kontoBankowe.wplata(15000)
    kontoBankowePolaka.wyplata(2.50)

    println("Saldo koncowe1: " + kontoBankowe.stanKonta)
    println("Saldo koncowe2: " + kontoBankowePolaka.stanKonta)

    //Punkt 3
    println("\nPunkt 3")
    val andrzej = new Osoba("Andrzej", "Lepper")
    val grzegorz = new Osoba("Grzegorz", "Schetyna")
    val beata = new Osoba("Beata", "Szydło")
    val donald = new Osoba("Donald", "Tusk")
    val mateusz = new Osoba("Mateusz", "Morawiecki")
    val random  = new Osoba("Jan", "Kowalski")

    przywitaj(andrzej);
    przywitaj(grzegorz);
    przywitaj(beata);
    przywitaj(donald);
    przywitaj(mateusz);
    przywitaj(random);

    //Punkt 4
    println("\nPunkt 4")
    val wartoscPoczatkowa = 3
    println("Wartość początkowa: "+wartoscPoczatkowa )
    println("Wartość koncowa: " + zmodyfikuj(wartoscPoczatkowa, podniesDoKwadratu))

    //Punkt 5
    println("\nPunkt 5")
    val andrzejPracownik = new Osoba("Andrzej", "Lepper") with  Pracownik
    val grzegorzStudent = new Osoba("Grzegorz", "Schetyna")with  Student
    val beataNauczyciel = new Osoba("Beata", "Szydło")with  Nauczyciel
    val donaldOpozycjonista = new Osoba("Donald", "Tusk")with  Pracownik with Student
    val mateuszErudyta = new Osoba("Mateusz", "Morawiecki")with  Student with  Pracownik

    println(f"Podatek Andrzeja: ${andrzejPracownik.podatek*100}%.2f%%")
    println(f"Podatek Grzegorza: ${grzegorzStudent.podatek*100}%.2f%%")
    println(f"Podatek Beaty: ${beataNauczyciel.podatek*100}%.2f%%")
    println(f"Podatek Donalda: ${donaldOpozycjonista.podatek*100}%.2f%%")
    println(f"Podatek Mateusza: ${mateuszErudyta.podatek*100}%.2f%%")
  }

  def getDayDescription(dayOfWeek: String): String ={
    dayOfWeek match {
      case "Poniedziałek" | "Wtorek" | "Środa" | "Czwartek"| "Piątek"=>  "Praca"
      case "Sobota" | "Niedziela"  =>  "Weekend"
      case _ =>   "Nie ma takiego dnia"
    }
  }

  def przywitaj(osoba: Osoba) = {
    osoba match {
      case Osoba("Andrzej", "Lepper") => println("Cześć " + osoba.imie)
      case Osoba("Grzegorz", "Schetyna") => println("Dobrze cie widziec "+ osoba.imie)
      case Osoba("Beata", "Szydło") => println("Jak leci " + osoba.imie)
      case Osoba("Donald", "Tusk") => println("Dawno cie nie widzialem " + osoba.imie)
      case Osoba("Mateusz", "Morawiecki") => println("Siema " + osoba.imie)
      case _ => println("Cześć")
    }
  }
  def podniesDoKwadratu(x: Int): Int = { x * x }

  def zmodyfikuj(number: Int, funkcja: (Int) => Int): Int = {
    funkcja(funkcja(funkcja(number)))
  }
}
