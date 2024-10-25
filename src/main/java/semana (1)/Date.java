package Semana1;

public class Date {
  private int month, day, year;

  public Date() {
    this(1, 1, 1970);
  }

  public Date(int month, int day, int year) {
    if(!validDate (month, day, year)) throw new IllegalArgumentException ("Algum dos argumentos da data está incorreto. Por favor, verifique e tente novamente");

    this.month = month;
    this.day = day;
    this.year = year;
  }

  private boolean validDate(int month, int day, int year) {
    try {
      if(month < 1 || month > 12) throw new IllegalArgumentException ("O mês tem que estar entre 1 e 12");

      if(day < 1) throw new IllegalArgumentException ("O dia não pode ser negativo");

      if(day > daysInMonth (month, year)) throw new IllegalArgumentException ("Não há tantos dias assim nesse mês...");
    } catch (IllegalArgumentException e) {
      System.out.println (e.getMessage ());

      return false;
    }

    return true;
  }

  private int daysInMonth(int month, int year) {
    if(month == 4 || month == 6 || month == 9 || month == 11) return 30;

    if(month == 2)
      if(year % 4 != 0 || (year % 4 == 0 && year % 100 == 0 && year % 400 != 0)) return 28;
      else return 29;

    return 31;
  }

  public int month() {
    return this.month;
  }

  public int day() {
    return this.day;
  }

  public int year() {
    return this.year;
  }

  public String toString() {
    return this.month() + "/" + this.day() + "/" + this.year();
  }

  public boolean before(Date other) {
    try {
      if (other == null) throw new IllegalArgumentException("Nao se pode comparar com data nula");

      if(this.year() < other.year()) return true;
      else if (this.year() > other.year()) return false;

      if(this.month () < other.month ()) return true;
      else if (this.month() > other.month()) return false;

      return this.day() < other.day();
    } catch(IllegalArgumentException e) {
      System.out.println(e.getMessage());

      return false;
    }
  }

  public int daysSinceBeginYear() {
    int days = 0;

    for(int i = 1; i < this.month(); i++) {
      days += daysInMonth (i, this.year());
    }

    return days + this.day ();
  }

  public int daysUntilEndYear() {
    int days = 0;

    days += daysInMonth(this.month(), this.year()) - this.day();

    for(int i = this.month() + 1; i <= 12; i++)
      days += daysInMonth(i, this.year());

    return days;
  }

  public int daysBetween(Date other) {
    Date first = before(other) ? this : other;
    Date second = before(other) ? other : this;

    int days = 0;

    for(int year = first.year() + 1; year < second.year(); year++)
      for(int month = 1; month <= 12; month++) {
        days += daysInMonth(month, year);
      }

    if(first.year != second.year())
      days += second.daysSinceBeginYear() + first.daysUntilEndYear() - 1;
    else
      days += second.daysSinceBeginYear() - first.daysSinceBeginYear();

    return days;
  }
}
