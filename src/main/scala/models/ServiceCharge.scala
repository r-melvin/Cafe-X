package models

enum ServiceCharge(val value: BigDecimal):
  
  case NoServiceCharge extends ServiceCharge(0)
  case TenPercent extends ServiceCharge(0.1)
  case TwentyPercent extends ServiceCharge(0.2)
  case TwentyFivePercent extends ServiceCharge(0.25)
  case MaxServiceCharge extends ServiceCharge(20)
  case MaxPremiumServiceCharge extends ServiceCharge(40)


