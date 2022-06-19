package ink.ptms.adyeshach.common.util.serializer.type

import com.google.common.base.Enums
import com.google.gson.*
import ink.ptms.adyeshach.common.bukkit.data.VillagerData
import ink.ptms.adyeshach.common.util.getEnum
import ink.ptms.adyeshach.common.util.serializer.SerializerType
import org.bukkit.entity.Villager
import java.lang.reflect.Type

/**
 * @author sky
 * @since 2020-08-20 20:10
 */
@SerializerType(baseClass = VillagerData::class)
class TypeVillagerData : JsonSerializer<VillagerData>, JsonDeserializer<VillagerData> {

    override fun serialize(a: VillagerData, p1: Type, p2: JsonSerializationContext): JsonElement {
        return JsonObject().run {
            addProperty("type", a.type.name)
            addProperty("profession", a.profession.name)
            this
        }
    }

    override fun deserialize(a: JsonElement, p1: Type?, p2: JsonDeserializationContext): VillagerData {
        return VillagerData(
            Villager.Type::class.java.getEnum(a.asJsonObject.get("type").asString),
            Villager.Profession::class.java.getEnum(a.asJsonObject.get("profession").asString)
        )
    }
}