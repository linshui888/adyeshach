package ink.ptms.adyeshach.impl.manager

import ink.ptms.adyeshach.core.entity.manager.EventBus
import org.bukkit.entity.Player

/**
 * Adyeshach
 * ink.ptms.adyeshach.impl.manager.DefaultPlayerManager
 *
 * @author 坏黑
 * @since 2022/6/28 15:10
 */
open class DefaultPlayerManager(val player: Player) : DefaultManager() {

    override fun getPlayers(): List<Player> {
        return listOf(player)
    }

    override fun getEventBus(): EventBus? {
        return null
    }
}