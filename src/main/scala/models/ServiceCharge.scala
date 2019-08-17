package models

sealed trait ServiceCharge{
  val value: BigDecimal
}

case object NoServiceCharge extends ServiceCharge {
  val value = BigDecimal(0)
}

case object TenPercent extends ServiceCharge {
  val value = BigDecimal(0.1)
}

case object TwentyPercent extends ServiceCharge {
  val value = BigDecimal(0.2)
}

case object TwentyFivePercent extends ServiceCharge {
  val value = BigDecimal(0.25)
}

case object MaxServiceCharge extends ServiceCharge {
  val value = BigDecimal(20)
}

case object MaxPremiumServiceCharge extends ServiceCharge {
  val value = BigDecimal(40)
}



