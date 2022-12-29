package ink.ptms.adyeshach.core.event

import ink.ptms.adyeshach.core.entity.EntityInstance
import taboolib.platform.type.BukkitProxyEvent

/**
 * @author sky
 * @since 2020-08-14 19:21
 */
class AdyeshachTagUpdateEvent(val entity: EntityInstance, val key: String, var value: Any?) : BukkitProxyEvent()