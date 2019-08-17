import org.scalatest.{MustMatchers, WordSpecLike}
import models._

class BillSpec extends WordSpecLike with MustMatchers {

  "calculateBill" should {
    "return a formatted price with no service charges" when {
      "the order only contains cold drinks" in {
        val testOrder = List(Cola)
        val testPrice = "£0.50"

        object TestBill extends Bill(testOrder)

        val result = TestBill.totalCost

        result mustBe testPrice
      }

      "the order only contains hot drinks" in {
        val testOrder = List(Coffee)
        val testPrice = "£1.00"

        object TestBill extends Bill(testOrder)

        val result = TestBill.totalCost

        result mustBe testPrice
      }

      "the order contains hot and cold drinks" in {
        val testOrder = List(Coffee, Cola)
        val testPrice = "£1.50"

        object TestBill extends Bill(testOrder)

        val result = TestBill.totalCost

        result mustBe testPrice
      }
    }

    "return a formatted price with a 10% service charge" when {
      "the order contains cold food" in {
        val testOrder = List(Cola, CheeseSandwich)
        val testPrice = "£2.75"

        object TestBill extends Bill(testOrder)

        val result = TestBill.totalCost

        result mustBe testPrice
      }
    }

    "return a formatted price with a 20% service charge" when {
      "the order contains hot food" in {
        val testOrder = List(Cola, SteakSandwich)
        val testPrice = "£6.00"

        object TestBill extends Bill(testOrder)

        val result = TestBill.totalCost

        result mustBe testPrice
      }
    }

    "not allow the tip to exceed £20" when {
      "orders contain hot food" in {
        val testOrder = (1 to 25).map(_ => SteakSandwich).toList
        val testPrice = "£132.50"

        object TestBill extends Bill(testOrder)

        val result = TestBill.totalCost

        result mustBe testPrice
      }
    }

    "return a formatted price with a 25% service charge" when {
      "the order contains premium food" in {
        val testOrder = List(Lobster)
        val testPrice = "£31.25"

        object TestBill extends Bill(testOrder)

        val result = TestBill.totalCost

        result mustBe testPrice
      }
    }

    "not allow the tip to exceed £40" when {
      "the order contains premium food" in {
        val testOrder = (1 to 8).map(_ => Lobster).toList
        val testPrice = "£240.00"

        object TestBill extends Bill(testOrder)

        val result = TestBill.totalCost

        result mustBe testPrice
      }
    }

    "return £0.00" when {
      "an empty order is given" in {
        val testOrder = List()
        val testPrice = "£0.00"

        object TestBill extends Bill(testOrder)

        val result = TestBill.totalCost

        result mustBe testPrice
      }
    }

    "return a discounted price" when {
      "a customer has a full loyalty card" in {
        val loyaltyCard = LoyaltyCard(stars = 8)
        val testOrder = List(SteakSandwich, SteakSandwich, Coffee)
        val testPrice = "£9.60"

        object TestBill extends Bill(testOrder, Some(loyaltyCard))

        val result = TestBill.totalCost

        result mustBe testPrice
      }

      "a customer has 3 stars on their loyalty card" in {
        val loyaltyCard = LoyaltyCard(stars = 3)
        val testOrder = List(SteakSandwich, SteakSandwich, Coffee)
        val testPrice = "£11.10"

        object TestBill extends Bill(testOrder, Some(loyaltyCard))

        val result = TestBill.totalCost

        result mustBe testPrice
      }
    }

    "not return a discounted price" when {
      "a customer has no stars on their loyalty card" in {
        val loyaltyCard = LoyaltyCard(stars = 0)
        val testOrder = List(SteakSandwich, SteakSandwich, Coffee)
        val testPrice = "£12.00"

        object TestBill extends Bill(testOrder, Some(loyaltyCard))

        val result = TestBill.totalCost

        result mustBe testPrice
      }

      "a customer has less than 3 stars on their loyalty card" in {
        val loyaltyCard = LoyaltyCard(stars = 2)
        val testOrder = List(SteakSandwich, SteakSandwich, Coffee)
        val testPrice = "£12.00"

        object TestBill extends Bill(testOrder, Some(loyaltyCard))

        val result = TestBill.totalCost

        result mustBe testPrice
      }
    }
  }

}
