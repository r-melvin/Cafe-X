package models

import models.ItemType.*
import models.ServiceCharge.*
import models.Temperature.*

sealed trait MenuItem(val price: BigDecimal, val itemType: ItemType, val temperature: Temperature, val serviceCharge: ServiceCharge)

case object Cola extends MenuItem(price = 0.5, itemType = Drink, temperature = Cold, serviceCharge = NoServiceCharge)

case object Coffee extends MenuItem(price = 1, itemType = Drink, temperature = Hot, serviceCharge = NoServiceCharge)

case object CheeseSandwich extends MenuItem(price = 2, itemType = Food, temperature = Cold, serviceCharge = TenPercent)

case object SteakSandwich extends MenuItem(price = 4.5, itemType = Food, temperature = Hot, serviceCharge = TwentyPercent)

case object Lobster extends MenuItem(price = 25, itemType = Premium, temperature = Hot, serviceCharge = TwentyFivePercent)
