package com.example.roulette.integration

import cats.effect.unsafe.implicits.global
import com.example.roulette.game.GamePhase.{BetsClosed, BetsOpen}
import com.example.roulette.integration.PhaseChangeTest.logs
import com.example.roulette.integration.setup.ClientStarter.connectToWebSocket
import com.example.roulette.integration.setup.PlayerConnection
import com.example.roulette.player.Player
import com.example.roulette.player.Player.{Password, Username}
import com.example.roulette.request.Request.JoinGame
import com.example.roulette.response.Response
import com.example.roulette.response.Response.PhaseChanged
import org.scalatest.Inside.inside
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

// server must be running on port 8080
class PhaseChangeTest extends AnyWordSpec with Matchers {
  "Phase change response" should {
    "change game phase 2 times withing 35 seconds" in {
      val responses = logs.filter(_.isInstanceOf[PhaseChanged])

      inside (responses) {
        case List(PhaseChanged(phase1, _, _), PhaseChanged(phase2, _, _)) =>
          phase1 must (be (BetsOpen) or be (BetsClosed))
          phase2 must (be (BetsOpen) or be (BetsClosed))
          Set(phase1, phase2).size mustBe 2
      }
    }

  }
}

object PhaseChangeTest extends AnyWordSpec with Matchers {
  val username: Username = Username("Player")
  val player: Player = Player(username, Password("12345"))

  val playerConnection: PlayerConnection = setup.PlayerConnection(
    username = username,
    requests = List(JoinGame(username, Password("12345"))),
    msgLimit = 35,
  )

  def logs: List[Response] = connectToWebSocket(List(playerConnection)).unsafeRunSync()
}
