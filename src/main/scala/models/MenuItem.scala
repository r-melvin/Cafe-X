package models

sealed trait MenuItem {
  val price: BigDecimal
  val itemType: ItemType
  val temperature: Temperature
  val serviceCharge: ServiceCharge
}

case object Cola extends MenuItem {
  override val price: BigDecimal = 0.5
  override val itemType: ItemType = Drink
  override val temperature: Temperature = Cold
  override val serviceCharge: ServiceCharge = NoServiceCharge
}

case object Coffee extends MenuItem {
  override val price: BigDecimal = 1
  override val itemType: ItemType = Drink
  override val temperature: Temperature = Hot
  override val serviceCharge: ServiceCharge = NoServiceCharge
}

case object CheeseSandwich extends MenuItem {
  override val price: BigDecimal = 2
  override val itemType: ItemType = Food
  override val temperature: Temperature = Cold
  override val serviceCharge: ServiceCharge = TenPercent
}

case object SteakSandwich extends MenuItem {
  override val price: BigDecimal = 4.5
  override val itemType: ItemType = Food
  override val temperature: Temperature = Hot
  override val serviceCharge: ServiceCharge = TwentyPercent
}

case object Lobster extends MenuItem {
  override val price: BigDecimal = 25
  override val itemType: ItemType = Premium
  override val temperature: Temperature = Hot
  override val serviceCharge: ServiceCharge = TwentyFivePercent
}