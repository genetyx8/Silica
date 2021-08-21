package org.sandboxpowered.silica.client.server

import akka.actor.typed.ActorRef
import org.sandboxpowered.silica.StateManager
import org.sandboxpowered.silica.server.Network
import org.sandboxpowered.silica.server.SilicaServer
import org.sandboxpowered.silica.world.SilicaWorld

class IntegratedServer(
    override val stateManager: StateManager,
    override val world: ActorRef<SilicaWorld.Command>,
    override val network: ActorRef<Network>
) : SilicaServer() {
}