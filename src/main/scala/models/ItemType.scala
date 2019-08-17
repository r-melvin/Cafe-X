package models

sealed trait ItemType

case object Drink extends ItemType

case object Food extends ItemType

case object Premium extends ItemType

