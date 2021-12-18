package com.example.roulette.integration.setup

import com.example.roulette.player.Player.Username
import com.example.roulette.request.Request

import scala.concurrent.duration.{DurationInt, FiniteDuration}

final case class PlayerConnection(username: Username,
                                  requests: List[Request],
                                  msgLimit: Long,
                                  delay: FiniteDuration = 0.seconds,
                                  stayConnected: FiniteDuration = 0.seconds
                                 )
