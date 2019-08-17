package models

sealed trait Temperature

case object Hot extends Temperature

case object Cold extends Temperature
