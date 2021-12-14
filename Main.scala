extension (x: Int) inline def toby(inline y: Int) = x to y by y.compareTo(x)|1

@scala.annotation.tailrec // median as argmin of absolute deviation via bisection method on its derivative a la radix sort
def median[T: Integral](array: Seq[T])(left: T, right: T): (T,T) = {
  import math.Integral.Implicits.infixIntegralOps
  import math.Ordering.Implicits.infixOrderingOps

  def ZERO = Integral[T].fromInt(0)
  def ONE = Integral[T].fromInt(1)
  def TWO = Integral[T].fromInt(2)

  val mid = (left+right)/TWO
  val ddx = array.map(a => (mid-a).sign).sum

  def adv(b: T): T = array.map(a => (b-a).abs).sum

  def fit(lmax: T, rmin: T) =
    val (l, r) = (array.filter(_<=lmax).max, array.filter(_>=rmin).min)
    val (adl, adr) = (adv(l), adv(r))
    if(adl < adr) (l,l) else
    if(adl > adr) (r,r) else
      (l,r)

  if(right - left <= ONE) fit(left,right)
  else if(ddx < ZERO) median(array)(mid,right)
  else if(ddx > ZERO) median(array)(left,mid)
  else fit(mid,mid)
}

def median[T: Integral](array: Seq[T]): (T,T) = median(array)(array.min, array.max)

inline def day = 14
@main def aoc: Any =
  SeqMacro(day,1)
    (
      One,      // 1791,    1822
      Two,      // 1693300, 1857958050
      Three,    // 2648450, 2845944
      Four,     // 41668,   10478
      Five,     // 6283,    18864
      Six,      // 380243,  1708791884591
      Seven,    // 355764,  99634572
      Eight,    // 344,     1048410
      Nine,     // 566,     891684
      Ten,      // 387363,  4330777059
      Eleven,   // 1613,    510
      Twelve,   // 3679,    107395
      Thirteen, // 785,     FJAHJGAH
      Fourteen, //
    )
