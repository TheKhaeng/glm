package glm_.vec4

import glm_.glm
import glm_.vec4.operators.vec4bool_operators

/**
 * Created by elect on 09/10/16.
 */

data class Vec4bool(var x: Boolean = false, var y: Boolean = false, var z: Boolean = false, var w: Boolean = false) {

    // -- Explicit basic, conversion other main.and conversion vector constructors --

    constructor(b: Boolean) : this(b, b, b, b)

    constructor(ba: BooleanArray) : this(ba[0], ba[1], ba[2], ba[3])

    constructor(ba: Array<Boolean>) : this(ba[0], ba[1], ba[2], ba[3])

    // -- Component accesses --

    operator fun get(i: Int): Boolean = when (i) {
        0 -> x
        1 -> y
        2 -> z
        3 -> w
        else -> throw IndexOutOfBoundsException()
    }

    operator fun set(i: Int, b: Boolean) = when (i) {
        0 -> x = b
        1 -> y = b
        2 -> z = b
        3 -> w = b
        else -> throw IndexOutOfBoundsException()
    }


    fun put(b: Boolean): Vec4bool {
        x = b
        y = b
        z = b
        w = b
        return this
    }

    fun put(x: Boolean, y: Boolean, z: Boolean): Vec4bool {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
        return this
    }

    fun put(ba: BooleanArray): Vec4bool {
        x = ba[0]
        y = ba[1]
        z = ba[2]
        w = ba[3]
        return this
    }

    fun put(ba: Array<Boolean>): Vec4bool {
        x = ba[0]
        y = ba[1]
        z = ba[2]
        w = ba[3]
        return this
    }


    // -- Unary arithmetic vecOperators --

    operator fun not(): Vec4bool = Vec4bool(!x, !y, !z, !w)

    fun not_(): Vec4bool {
        x = !x
        y = !y
        z = !z
        w = !w
        return this
    }

    infix fun not(res: Vec4bool): Vec4bool {
        res.x = !x
        res.y = !y
        res.z = !z
        res.w = !w
        return this
    }

    companion object : vec4bool_operators()

    val all: Boolean
        get() = glm.all(this)

    // TODO others
    infix fun and(b: Vec4bool) = and(Vec4bool(), this, b.x, b.y, b.z, b.w)
    infix fun or(b: Vec4bool) = or(Vec4bool(), this, b.x, b.y, b.z, b.w)
    infix fun xor(b: Vec4bool) = xor(Vec4bool(), this, b.x, b.y, b.z, b.w)

    override fun equals(other: Any?) = other is Vec4bool && x == other.x && y == other.y && z == other.z && w == other.w
    override fun hashCode() = 31 * (31 * (31 * x.hashCode() + y.hashCode()) + z.hashCode()) + w.hashCode()
}