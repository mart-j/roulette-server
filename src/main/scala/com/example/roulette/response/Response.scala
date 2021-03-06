package com.example.roulette.response

import cats.effect.kernel.Concurrent
import com.example.roulette.bet.Bet
import com.example.roulette.bet.Bet.Chips
import com.example.roulette.game.GamePhase
import com.example.roulette.player.Player
import com.example.roulette.player.Player.Username
import com.example.roulette.timer.Timer
import io.circe.Codec
import io.circe.generic.extras.semiauto.deriveUnwrappedCodec
import io.circe.generic.extras.{ Configuration, ConfiguredJsonCodec }
import org.http4s.EntityDecoder
import org.http4s.circe.jsonOf
@ConfiguredJsonCodec sealed trait Response

object Response {
  final case class BetPlaced(chipsPlaced: Chips, username: Username, bet: Option[Bet])        extends Response
  final case class BetsCleared(username: Username)                                            extends Response
  final case class PlayerJoinedGame(player: Player)                                           extends Response
  final case class WelcomeToGame(player: Player, gamePhase: GamePhase, players: List[Player]) extends Response
  final case class PlayerLeftGame(username: Username)                                         extends Response
  final case class BadRequest(message: BadRequestMessage)                                     extends Response
  final case class TimerNotification(secTillNextPhase: Timer)                                 extends Response
  final case class PhaseChanged(gamePhase: GamePhase, players: List[Player], luckyNumber: Option[LuckyNumber])
      extends Response
  final case class RegistrationSuccessful(username: Username) extends Response
  final case object PlayerRemoved                             extends Response

  implicit def response[F[_]: Concurrent]: EntityDecoder[F, Response] = jsonOf[F, Response]

  final case class LuckyNumber(value: Int) extends AnyVal
  object LuckyNumber {
    implicit val luckyNumberCodec: Codec[LuckyNumber] = deriveUnwrappedCodec[LuckyNumber]
  }

  implicit val genDevConfig: Configuration = {
    Configuration.default.withDiscriminator("responseType")
  }
}
