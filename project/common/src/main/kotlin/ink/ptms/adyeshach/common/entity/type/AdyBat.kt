package ink.ptms.adyeshach.common.entity.type

/**
 * @author sky
 * @since 2020-08-04 19:30
 */
interface AdyBat : AdyMob {

    fun setHanging(value: Boolean) {
        setMetadata("isHanging", value)
    }

    fun isHanging(): Boolean {
        return getMetadata("isHanging")
    }
}