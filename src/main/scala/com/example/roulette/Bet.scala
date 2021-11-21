package com.example.roulette

import io.circe.Codec
import io.circe.generic.extras.semiauto.deriveUnwrappedCodec
import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec}

@ConfiguredJsonCodec sealed trait Bet
  object Bet {
    final case class Straight(position: BetPosition, betAmount: Chips) extends Bet
    final case class Odd(betAmount: Chips) extends Bet
    final case class Even(betAmount: Chips) extends Bet
    final case class High(betAmount: Chips) extends Bet
    final case class Low(betAmount: Chips) extends Bet
    final case class Row(position: BetPosition, betAmount: Chips) extends Bet
    final case class Dozen(position: BetPosition, betAmount: Chips) extends Bet
    final case class Red(betAmount: Chips) extends Bet
    final case class Black(betAmount: Chips) extends Bet
    final case class Split(positions: List[BetPosition], betAmount: Chips) extends Bet
    final case class Street(position : BetPosition, betAmount: Chips) extends Bet
    final case class SixLine(position: BetPosition, betAmount: Chips) extends Bet
    final case class Corner(positions: List[BetPosition], betAmount: Chips) extends Bet
    final case class Trio(position: BetPosition, betAmount: Chips) extends Bet
    final case class Basket(betAmount: Chips) extends Bet

    implicit val genDevConfig: Configuration =
      Configuration.default.withDiscriminator("betType")

    final case class BetPosition(value: Int) extends AnyVal
    object BetPosition {
      implicit val betCodec: Codec[BetPosition] = deriveUnwrappedCodec[BetPosition]
    }

    final case class Chips(value: Int) extends AnyVal
    object Chips {
      implicit val chipCodec: Codec[Chips] = deriveUnwrappedCodec[Chips]
    }
  }