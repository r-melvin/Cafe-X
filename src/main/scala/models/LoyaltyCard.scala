package models

case class LoyaltyCard(stars: Int) {
  private def apply(): LoyaltyCard = {
    stars match {
      case num if num < 3 => LoyaltyCard(stars = 0)
      case num if num > 8 => LoyaltyCard(stars = 8)
      case _ => LoyaltyCard(stars)
    }
  }

  lazy val percentageDecrease: BigDecimal = {
    1 - (apply().stars * 0.025)
  }
}
