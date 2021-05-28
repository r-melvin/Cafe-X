import java.text.NumberFormat.getCurrencyInstance
import models._

import scala.math.BigDecimal.RoundingMode.HALF_EVEN

object Bill {

  def getTotalCost(order: List[MenuItem], optLoyaltyCard: Option[LoyaltyCard] = None): String = {

    val preServiceChargeCost: BigDecimal = optLoyaltyCard match {
      case Some(loyalty) if !order.exists(_.itemType == Premium) => order.map(_.price).sum * loyalty.percentageDecrease
      case _ => order.map(_.price).sum
    }

    val serviceCharge: BigDecimal = order.map(_.serviceCharge).maxByOption(_.value) match {
      case Some(TenPercent) => preServiceChargeCost * TenPercent.value
      case Some(TwentyPercent) => (preServiceChargeCost * TwentyPercent.value).min(MaxServiceCharge.value)
      case Some(TwentyFivePercent) => (preServiceChargeCost * TwentyFivePercent.value).min(MaxPremiumServiceCharge.value)
      case _ => 0
    }

    getCurrencyInstance.format((preServiceChargeCost + serviceCharge).setScale(2, HALF_EVEN))
  }

}
