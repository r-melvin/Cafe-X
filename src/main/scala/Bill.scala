import java.text.NumberFormat.getCurrencyInstance
import models._

import scala.math.BigDecimal.RoundingMode.HALF_EVEN

class Bill(order: List[MenuItem], optLoyaltyCard: Option[LoyaltyCard] = None) {

  private val getServiceCharge: ServiceCharge = order.map(_.serviceCharge).maxByOption(_.value).getOrElse(NoServiceCharge)

  private val cost: BigDecimal = order.map(_.price).sum

  private val preServiceChargeCost: BigDecimal = optLoyaltyCard match {
    case Some(loyalty) if !order.exists(_.itemType == Premium) => cost * loyalty.percentageDecrease
    case _ => cost
  }

  private val serviceCharge: BigDecimal = getServiceCharge match {
    case TenPercent => preServiceChargeCost * TenPercent.value
    case TwentyPercent => (preServiceChargeCost * TwentyPercent.value).min(MaxServiceCharge.value)
    case TwentyFivePercent => (preServiceChargeCost * TwentyFivePercent.value).min(MaxPremiumServiceCharge.value)
    case _ => 0
  }

  private def format(bill: BigDecimal): String = getCurrencyInstance.format(bill.setScale(2, HALF_EVEN))

  val totalCost: String = format(preServiceChargeCost + serviceCharge)
}
