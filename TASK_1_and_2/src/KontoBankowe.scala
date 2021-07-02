class KontoBankowe(wartoscPoczatkowa: Double = 0) {
  private var _stanKonta: Double= wartoscPoczatkowa
  def wplata(kwota : Double) = {
    _stanKonta  += kwota
  }
  def wyplata(kwota : Double) = {
    _stanKonta-=kwota
  }

  def stanKonta = _stanKonta // No () in declaration
}
