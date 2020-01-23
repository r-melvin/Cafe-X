import java.text.NumberFormat.getCurrencyInstance
import models._

import scala.math.BigDecimal.RoundingMode.HALF_EVEN

object Bill {

  private def format(bill: BigDecimal): String = getCurrencyInstance.format(bill.setScale(2, HALF_EVEN))

  def getTotalCost(order: List[MenuItem], optLoyaltyCard: Option[LoyaltyCard] = None): String = {

    val getServiceCharge: ServiceCharge = order.map(_.serviceCharge).maxByOption(_.value).getOrElse(NoServiceCharge)
    val cost: BigDecimal = order.map(_.price).sum

    val preServiceChargeCost: BigDecimal = optLoyaltyCard match {
      case Some(loyalty) if !order.exists(_.itemType == Premium) => cost * loyalty.percentageDecrease
      case _ => cost
    }

    val serviceCharge: BigDecimal = getServiceCharge match {
      case TenPercent => preServiceChargeCost * TenPercent.value
      case TwentyPercent => (preServiceChargeCost * TwentyPercent.value).min(MaxServiceCharge.value)
      case TwentyFivePercent => (preServiceChargeCost * TwentyFivePercent.value).min(MaxPremiumServiceCharge.value)
      case _ => 0
    }

    format(preServiceChargeCost + serviceCharge)
  }

}
