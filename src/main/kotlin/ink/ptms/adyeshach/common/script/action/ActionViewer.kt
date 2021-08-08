package ink.ptms.adyeshach.common.script.action

import ink.ptms.adyeshach.common.script.ScriptHandler.entitySelected
import ink.ptms.adyeshach.common.script.ScriptHandler.getEntities
import ink.ptms.adyeshach.common.script.ScriptHandler.getManager
import ink.ptms.adyeshach.common.script.ScriptHandler.loadError
import org.bukkit.Bukkit
import openapi.kether.ArgTypes
import openapi.kether.ParsedAction
import taboolib.module.kether.*
import java.util.concurrent.CompletableFuture

/**
 * @author IzzelAliz
 */
class ActionViewer(val symbol: Symbol, val viewer: ParsedAction<*>?): ScriptAction<Void>() {

    enum class Symbol {

        ADD, REMOVE, RESET
    }

    override fun run(frame: ScriptFrame): CompletableFuture<Void> {
        val s = frame.script()
        if (s.getManager() == null) {
            error("No manager selected.")
        }
        if (!s.entitySelected()) {
            error("No entity selected.")
        }
        return frame.newFrame(viewer).run<Any>().thenAccept { viewer ->
            s.getEntities()!!.filterNotNull().forEach {
                when (symbol) {
                    Symbol.ADD -> {
                        Bukkit.getPlayerExact(viewer.toString())?.run {
                            it.addViewer(this)
                        }
                    }
                    Symbol.REMOVE -> {
                        Bukkit.getPlayerExact(viewer.toString())?.run {
                            it.removeViewer(this)
                        }
                    }
                    Symbol.RESET -> it.clearViewer()
                }
            }
        }
    }

    internal object Parser {

        @KetherParser(["viewer"], namespace = "adyeshach", shared = true)
        fun parser() = scriptParser {
            val symbol = when (val type = it.nextToken()) {
                "add" -> Symbol.ADD
                "remove" -> Symbol.REMOVE
                "reset" -> Symbol.RESET
                else -> throw loadError("Unknown viewer operator $type")
            }
            ActionViewer(symbol, if (symbol != Symbol.RESET) it.next(ArgTypes.ACTION) else null)
        }
    }
}