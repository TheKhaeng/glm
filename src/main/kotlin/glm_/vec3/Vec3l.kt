package glm_.vec3

import glm_.*
import glm_.buffer.longBufferBig
import glm_.vec2.Vec2bool
import glm_.vec2.Vec2l
import glm_.vec2.Vec2t
import glm_.vec3.operators.vec3l_operators
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4t
import java.nio.*

/**
 * Created by elect on 09/10/16.
 */

class Vec3l(var ofs: Int, var array: LongArray) : Vec3t<Long>() {

    constructor(x: Long, y: Long, z: Long) : this(0, longArrayOf(x, y, z))

    override inline var x: Long
        get() = array[ofs]
        set(value) = array.set(ofs, value)
    override inline var y: Long
        get() = array[ofs + 1]
        set(value) = array.set(ofs + 1, value)
    override inline var z: Long
        get() = array[ofs + 2]
        set(value) = array.set(ofs + 2, value)

    // -- Explicit basic, conversion other main.and conversion vector constructors --

    constructor() : this(0)

    constructor(v: Vec2t<out Number>) : this(v.x, v.y, 0)
    constructor(v: Vec2t<out Number>, z: Number) : this(v.x, v.y, z)
    constructor(x: Number, v: Vec2t<out Number>) : this(x, v.x, v.y)
    constructor(v: Vec3t<out Number>) : this(v.x, v.y, v.z)
    constructor(v: Vec4t<out Number>) : this(v.x, v.y, v.z)

    constructor(v: Vec2bool) : this(v.x.L, v.y.L, 0)
    constructor(v: Vec3bool) : this(v.x.L, v.y.L, v.z.L)
    constructor(v: Vec4bool) : this(v.x.L, v.y.L, v.z.L)

    constructor(bytes: ByteArray, index: Int = 0, oneByteOneLong: Boolean = false, bigEndian: Boolean = true) : this(
            if (oneByteOneLong) bytes[index].L else bytes.getLong(index, bigEndian),
            if (oneByteOneLong) bytes[index + 1].L else bytes.getLong(index + Long.BYTES, bigEndian),
            if (oneByteOneLong) bytes[index + 2].L else bytes.getLong(index + Long.BYTES * 2, bigEndian))

    constructor(chars: CharArray, index: Int = 0) : this(chars[index].L, chars[index + 1].L, chars[index + 2].L)
    constructor(shorts: ShortArray, index: Int = 0) : this(shorts[index], shorts[index + 1], shorts[index + 2])
    constructor(ints: IntArray, index: Int = 0) : this(ints[index], ints[index + 1], ints[index + 2])
    constructor(longs: LongArray, index: Int = 0) : this(longs[index], longs[index + 1], longs[index + 2])
    constructor(floats: FloatArray, index: Int = 0) : this(floats[index], floats[index + 1], floats[index + 2])
    constructor(doubles: DoubleArray, index: Int = 0) : this(doubles[index], doubles[index + 1], doubles[index + 2])
    constructor(booleans: BooleanArray, index: Int = 0) : this(booleans[index].L, booleans[index + 1].L, booleans[index + 2].L)

    constructor(numbers: Array<out Number>, index: Int = 0) : this(numbers[index], numbers[index + 1], numbers[index + 2])
    constructor(chars: Array<Char>, index: Int = 0) : this(chars[index].L, chars[index + 1].L, chars[index + 2].L)
    constructor(booleans: Array<Boolean>, index: Int = 0) : this(booleans[index].L, booleans[index + 1].L, booleans[index + 2].L)

    constructor(list: Iterable<*>, index: Int = 0) : this(list.elementAt(index)!!.toLong, list.elementAt(index + 1)!!.toLong,
            list.elementAt(index + 2)!!.toLong)

    constructor(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneLong: Boolean = false) : this(
            if (oneByteOneLong) bytes[index].L else bytes.getLong(index),
            if (oneByteOneLong) bytes[index + 1].L else bytes.getLong(index + Long.BYTES),
            if (oneByteOneLong) bytes[index + 2].L else bytes.getLong(index + Long.BYTES * 2))

    constructor(chars: CharBuffer, index: Int = chars.position()) : this(chars[index].L, chars[index + 1].L, chars[index + 2].L)
    constructor(shorts: ShortBuffer, index: Int = shorts.position()) : this(shorts[index], shorts[index + 1], shorts[index + 2])
    constructor(ints: IntBuffer, index: Int = ints.position()) : this(ints[index], ints[index + 1], ints[index + 2])
    constructor(longs: LongBuffer, index: Int = longs.position()) : this(longs[index], longs[index + 1], longs[index + 2])
    constructor(floats: FloatBuffer, index: Int = floats.position()) : this(floats[index], floats[index + 1], floats[index + 2])
    constructor(doubles: DoubleBuffer, index: Int = doubles.position()) : this(doubles[index], doubles[index + 1], doubles[index + 2])

    constructor(block: (Int) -> Long) : this(block(0), block(1), block(2))

    constructor(s: Number) : this(s, s, s)
    constructor(x: Number, y: Number, z: Number) : this(x.L, y.L, z.L)


    fun set(bytes: ByteArray, index: Int = 0, oneByteOneLong: Boolean = false, bigEndian: Boolean = true) {
        x = if (oneByteOneLong) bytes[index].L else bytes.getLong(index, bigEndian)
        y = if (oneByteOneLong) bytes[index + 1].L else bytes.getLong(index + Long.BYTES, bigEndian)
        z = if (oneByteOneLong) bytes[index + 2].L else bytes.getLong(index + Long.BYTES * 2, bigEndian)
    }

    fun set(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneLong: Boolean = false) {
        x = if (oneByteOneLong) bytes[index].L else bytes.getLong(index)
        y = if (oneByteOneLong) bytes[index + 1].L else bytes.getLong(index + Long.BYTES)
        z = if (oneByteOneLong) bytes[index + 2].L else bytes.getLong(index + Long.BYTES * 2)
    }


    fun put(x: Long, y: Long, z: Long) {
        this.x = x
        this.y = y
        this.z = z
    }

    fun invoke(x: Long, y: Long, z: Long): Vec3l {
        this.x = x
        this.y = y
        this.z = z
        return this
    }

    override fun put(x: Number, y: Number, z: Number) {
        this.x = x.L
        this.y = y.L
        this.z = z.L
    }

    override fun invoke(x: Number, y: Number, z: Number): Vec3l {
        this.x = x.L
        this.y = y.L
        this.z = z.L
        return this
    }

    fun to(bytes: ByteArray, index: Int) = to(bytes, index, true)
    override fun to(bytes: ByteArray, index: Int, bigEndian: Boolean): ByteArray {
        bytes.setLong(index, x)
        bytes.setLong(index + Long.BYTES, y)
        bytes.setLong(index + Long.BYTES * 2, z)
        return bytes
    }

    override fun to(bytes: ByteBuffer, index: Int): ByteBuffer {
        bytes.putLong(index, x)
        bytes.putLong(index + Long.BYTES, y)
        bytes.putLong(index + Long.BYTES * 2, z)
        return bytes
    }

    fun toLongArray() = to(LongArray(length), 0)
    infix fun to(longs: LongArray) = to(longs, 0)
    fun to(longs: LongArray, index: Int): LongArray {
        longs[index] = x
        longs[index + 1] = y
        longs[index + 2] = z
        return longs
    }

    fun toLongBuffer() = to(longBufferBig(length), 0)
    infix fun to(longs: LongBuffer) = to(longs, longs.position())
    fun to(longs: LongBuffer, index: Int): LongBuffer {
        longs[index] = x
        longs[index + 1] = y
        longs[index + 2] = z
        return longs
    }

    // -- Component accesses --

    operator fun set(index: Int, value: Long) = when (index) {
        0 -> x = value
        1 -> y = value
        2 -> z = value
        else -> throw ArrayIndexOutOfBoundsException()
    }

    override operator fun set(index: Int, value: Number) = when (index) {
        0 -> x = value.L
        1 -> y = value.L
        2 -> z = value.L
        else -> throw ArrayIndexOutOfBoundsException()
    }


    // -- Unary arithmetic operators --

    operator fun unaryPlus() = this

    operator fun unaryMinus() = Vec3l(-x, -y, -z)

    // -- Increment main.and decrement operators --

    operator fun inc(res: Vec3l = Vec3l()) = plus(res, this, 1, 1, 1)
    fun incAssign() = plus(this, this, 1, 1, 1)


    operator fun dec(res: Vec3l = Vec3l()) = minus(res, this, 1, 1, 1)
    fun decAssign() = minus(this, this, 1, 1, 1)


    // -- Specific binary arithmetic operators --

    operator fun plus(b: Long) = plus(Vec3l(), this, b, b, b)
    operator fun plus(b: Vec3l) = plus(Vec3l(), this, b.x, b.y, b.z)

    fun plus(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = plus(res, this, bX, bY, bZ)
    fun plus(b: Long, res: Vec3l = Vec3l()) = plus(res, this, b, b, b)
    fun plus(b: Vec3l, res: Vec3l = Vec3l()) = plus(res, this, b.x, b.y, b.z)

    fun plusAssign(bX: Long, bY: Long, bZ: Long) = plus(this, this, bX, bY, bZ)
    infix operator fun plusAssign(b: Long) {
        plus(this, this, b, b, b)
    }

    infix operator fun plusAssign(b: Vec3l) {
        plus(this, this, b.x, b.y, b.z)
    }


    operator fun minus(b: Long) = minus(Vec3l(), this, b, b, b)
    operator fun minus(b: Vec3l) = minus(Vec3l(), this, b.x, b.y, b.z)

    fun minus(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = minus(res, this, bX, bY, bZ)
    fun minus(b: Long, res: Vec3l = Vec3l()) = minus(res, this, b, b, b)
    fun minus(b: Vec3l, res: Vec3l = Vec3l()) = minus(res, this, b.x, b.y, b.z)

    fun minusAssign(bX: Long, bY: Long, bZ: Long) = minus(this, this, bX, bY, bZ)
    infix operator fun minusAssign(b: Long) {
        minus(this, this, b, b, b)
    }

    infix operator fun minusAssign(b: Vec3l) {
        minus(this, this, b.x, b.y, b.z)
    }


    operator fun times(b: Long) = times(Vec3l(), this, b, b, b)
    operator fun times(b: Vec3l) = times(Vec3l(), this, b.x, b.y, b.z)

    fun times(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = times(res, this, bX, bY, bZ)
    fun times(b: Long, res: Vec3l = Vec3l()) = times(res, this, b, b, b)
    fun times(b: Vec3l, res: Vec3l = Vec3l()) = times(res, this, b.x, b.y, b.z)

    fun timesAssign(bX: Long, bY: Long, bZ: Long) = times(this, this, bX, bY, bZ)
    infix operator fun timesAssign(b: Long) {
        times(this, this, b, b, b)
    }

    infix operator fun timesAssign(b: Vec3l) {
        times(this, this, b.x, b.y, b.z)
    }


    operator fun div(b: Long) = div(Vec3l(), this, b, b, b)
    operator fun div(b: Vec3l) = div(Vec3l(), this, b.x, b.y, b.z)

    fun div(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = div(res, this, bX, bY, bZ)
    fun div(b: Long, res: Vec3l) = div(res, this, b, b, b)
    fun div(b: Vec3l, res: Vec3l) = div(res, this, b.x, b.y, b.z)

    fun divAssign(bX: Long, bY: Long, bZ: Long) = div(this, this, bX, bY, bZ)
    infix operator fun divAssign(b: Long) {
        div(this, this, b, b, b)
    }

    infix operator fun divAssign(b: Vec3l) {
        div(this, this, b.x, b.y, b.z)
    }


    operator fun rem(b: Long) = rem(Vec3l(), this, b, b, b)
    operator fun rem(b: Vec3l) = rem(Vec3l(), this, b.x, b.y, b.z)

    fun rem(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = rem(res, this, bX, bY, bZ)
    fun rem(b: Long, res: Vec3l) = rem(res, this, b, b, b)
    fun rem(b: Vec3l, res: Vec3l) = rem(res, this, b.x, b.y, b.z)

    fun remAssign(bX: Long, bY: Long, bZ: Long) = rem(this, this, bX, bY, bZ)
    infix operator fun remAssign(b: Long) {
        rem(this, this, b, b, b)
    }

    infix operator fun remAssign(b: Vec3l) {
        rem(this, this, b.x, b.y, b.z)
    }


    // -- Generic binary arithmetic operators --

    operator fun plus(b: Number) = plus(Vec3l(), this, b.L, b.L, b.L)
    operator fun plus(b: Vec3t<out Number>) = plus(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    fun plus(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = plus(res, this, bX.L, bY.L, bZ.L)
    fun plus(b: Number, res: Vec3l = Vec3l()) = plus(res, this, b.L, b.L, b.L)
    fun plus(b: Vec3t<out Number>, res: Vec3l = Vec3l()) = plus(res, this, b.x.L, b.y.L, b.z.L)

    fun plusAssign(bX: Number, bY: Number, bZ: Number) = plus(this, this, bX.L, bY.L, bZ.L)
    infix operator fun plusAssign(b: Number) {
        plus(this, this, b.L, b.L, b.L)
    }

    infix operator fun plusAssign(b: Vec3t<out Number>) {
        plus(this, this, b.x.L, b.y.L, b.z.L)
    }


    operator fun minus(b: Number) = minus(Vec3l(), this, b.L, b.L, b.L)
    operator fun minus(b: Vec3t<out Number>) = minus(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    fun minus(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = minus(res, this, bX.L, bY.L, bZ.L)
    fun minus(b: Number, res: Vec3l = Vec3l()) = minus(res, this, b.L, b.L, b.L)
    fun minus(b: Vec3t<out Number>, res: Vec3l = Vec3l()) = minus(res, this, b.x.L, b.y.L, b.z.L)

    fun minusAssign(bX: Number, bY: Number, bZ: Number) = minus(this, this, bX.L, bY.L, bZ.L)
    infix operator fun minusAssign(b: Number) {
        minus(this, this, b.L, b.L, b.L)
    }

    infix operator fun minusAssign(b: Vec3t<out Number>) {
        minus(this, this, b.x.L, b.y.L, b.z.L)
    }


    operator fun times(b: Number) = times(Vec3l(), this, b.L, b.L, b.L)
    operator fun times(b: Vec3t<out Number>) = times(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    fun times(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = times(res, this, bX.L, bY.L, bZ.L)
    fun times(b: Number, res: Vec3l = Vec3l()) = times(res, this, b.L, b.L, b.L)
    fun times(b: Vec3t<out Number>, res: Vec3l = Vec3l()) = times(res, this, b.x.L, b.y.L, b.z.L)

    fun timesAssign(bX: Number, bY: Number, bZ: Number) = times(this, this, bX.L, bY.L, bZ.L)
    infix operator fun timesAssign(b: Number) {
        times(this, this, b.L, b.L, b.L)
    }

    infix operator fun timesAssign(b: Vec3t<out Number>) {
        times(this, this, b.x.L, b.y.L, b.z.L)
    }


    operator fun div(b: Number) = div(Vec3l(), this, b.L, b.L, b.L)
    operator fun div(b: Vec3t<out Number>) = div(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    fun div(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = div(res, this, bX.L, bY.L, bZ.L)
    fun div(b: Number, res: Vec3l) = div(res, this, b.L, b.L, b.L)
    fun div(b: Vec3t<out Number>, res: Vec3l) = div(res, this, b.x.L, b.y.L, b.z.L)

    fun divAssign(bX: Number, bY: Number, bZ: Number) = div(this, this, bX.L, bY.L, bZ.L)
    infix operator fun divAssign(b: Number) {
        div(this, this, b.L, b.L, b.L)
    }

    infix operator fun divAssign(b: Vec3t<out Number>) {
        div(this, this, b.x.L, b.y.L, b.z.L)
    }


    operator fun rem(b: Number) = rem(Vec3l(), this, b.L, b.L, b.L)
    operator fun rem(b: Vec3t<out Number>) = rem(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    fun rem(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = rem(res, this, bX.L, bY.L, bZ.L)
    fun rem(b: Number, res: Vec3l) = rem(res, this, b.L, b.L, b.L)
    fun rem(b: Vec3t<out Number>, res: Vec3l) = rem(res, this, b.x.L, b.y.L, b.z.L)

    fun remAssign(bX: Number, bY: Number, bZ: Number) = rem(this, this, bX.L, bY.L, bZ.L)
    infix operator fun remAssign(b: Number) {
        rem(this, this, b.L, b.L, b.L)
    }

    infix operator fun remAssign(b: Vec3t<out Number>) {
        rem(this, this, b.x.L, b.y.L, b.z.L)
    }


    // -- Specific bitwise operators --

    infix fun and(b: Long) = and(Vec3l(), this, b, b, b)
    infix fun and(b: Vec3l) = and(Vec3l(), this, b.x, b.y, b.z)

    infix fun andAssign(b: Long) = and(this, this, b, b, b)
    infix fun andAssign(b: Vec3l) = and(this, this, b.x, b.y, b.z)

    fun and(b: Long, res: Vec3l) = and(res, this, b, b, b)
    fun and(b: Vec3l, res: Vec3l) = and(res, this, b.x, b.y, b.z)

    fun and(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = and(res, this, bX, bY, bZ)

    fun andAssign(bX: Long, bY: Long, bZ: Long) = and(this, this, bX, bY, bZ)


    infix fun or(b: Long) = or(Vec3l(), this, b, b, b)
    infix fun or(b: Vec3l) = or(Vec3l(), this, b.x, b.y, b.z)

    infix fun orAssign(b: Long) = or(this, this, b, b, b)
    infix fun orAssign(b: Vec3l) = or(this, this, b.x, b.y, b.z)

    fun or(b: Long, res: Vec3l) = or(res, this, b, b, b)
    fun or(b: Vec3l, res: Vec3l) = or(res, this, b.x, b.y, b.z)

    fun or(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = or(res, this, bX, bY, bZ)

    fun orAssign(bX: Long, bY: Long, bZ: Long) = or(this, this, bX, bY, bZ)


    infix fun xor(b: Long) = xor(Vec3l(), this, b, b, b)
    infix fun xor(b: Vec3l) = xor(Vec3l(), this, b.x, b.y, b.z)

    infix fun xorAssign(b: Long) = xor(this, this, b, b, b)
    infix fun xorAssign(b: Vec3l) = xor(this, this, b.x, b.y, b.z)

    fun xor(b: Long, res: Vec3l) = xor(res, this, b, b, b)
    fun xor(b: Vec3l, res: Vec3l) = xor(res, this, b.x, b.y, b.z)

    fun xor(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = xor(res, this, bX, bY, bZ)

    fun xorAssign(bX: Long, bY: Long, bZ: Long) = xor(this, this, bX, bY, bZ)


    infix fun shl(b: Long) = shl(Vec3l(), this, b, b, b)
    infix fun shl(b: Vec3l) = shl(Vec3l(), this, b.x, b.y, b.z)

    infix fun shlAssign(b: Long) = shl(this, this, b, b, b)
    infix fun shlAssign(b: Vec3l) = shl(this, this, b.x, b.y, b.z)

    fun shl(b: Long, res: Vec3l) = shl(res, this, b, b, b)
    fun shl(b: Vec3l, res: Vec3l) = shl(res, this, b.x, b.y, b.z)

    fun shl(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = shl(res, this, bX, bY, bZ)

    fun shlAssign(bX: Long, bY: Long, bZ: Long) = shl(this, this, bX, bY, bZ)


    infix fun shr(b: Long) = shr(Vec3l(), this, b, b, b)
    infix fun shr(b: Vec3l) = shr(Vec3l(), this, b.x, b.y, b.z)

    infix fun shrAssign(b: Long) = shr(this, this, b, b, b)
    infix fun shrAssign(b: Vec3l) = shr(this, this, b.x, b.y, b.z)

    fun shr(b: Long, res: Vec3l) = shr(res, this, b, b, b)
    fun shr(b: Vec3l, res: Vec3l) = shr(res, this, b.x, b.y, b.z)

    fun shr(bX: Long, bY: Long, bZ: Long, res: Vec3l = Vec3l()) = shr(res, this, bX, bY, bZ)

    fun shrAssign(bX: Long, bY: Long, bZ: Long) = shr(this, this, bX, bY, bZ)


    fun inv(res: Vec3l = Vec3l()) = inv(res, this)
    fun invAssign() = inv(this, this)


    // -- Generic bitwise operators --

    infix fun and(b: Number) = and(Vec3l(), this, b.L, b.L, b.L)
    infix fun and(b: Vec3t<out Number>) = and(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    infix fun andAssign(b: Number) = and(this, this, b.L, b.L, b.L)
    infix fun andAssign(b: Vec3t<out Number>) = and(this, this, b.x.L, b.y.L, b.z.L)

    fun and(b: Number, res: Vec3l) = and(res, this, b.L, b.L, b.L)
    fun and(b: Vec3t<out Number>, res: Vec3l) = and(res, this, b.x.L, b.y.L, b.z.L)

    fun and(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = and(res, this, bX.L, bY.L, bZ.L)

    fun andAssign(bX: Number, bY: Number, bZ: Number) = and(this, this, bX.L, bY.L, bZ.L)


    infix fun or(b: Number) = or(Vec3l(), this, b.L, b.L, b.L)
    infix fun or(b: Vec3t<out Number>) = or(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    infix fun orAssign(b: Number) = or(this, this, b.L, b.L, b.L)
    infix fun orAssign(b: Vec3t<out Number>) = or(this, this, b.x.L, b.y.L, b.z.L)

    fun or(b: Number, res: Vec3l) = or(res, this, b.L, b.L, b.L)
    fun or(b: Vec3t<out Number>, res: Vec3l) = or(res, this, b.x.L, b.y.L, b.z.L)

    fun or(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = or(res, this, bX.L, bY.L, bZ.L)

    fun orAssign(bX: Number, bY: Number, bZ: Number) = or(this, this, bX.L, bY.L, bZ.L)


    infix fun xor(b: Number) = xor(Vec3l(), this, b.L, b.L, b.L)
    infix fun xor(b: Vec3t<out Number>) = xor(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    infix fun xorAssign(b: Number) = xor(this, this, b.L, b.L, b.L)
    infix fun xorAssign(b: Vec3t<out Number>) = xor(this, this, b.x.L, b.y.L, b.z.L)

    fun xor(b: Number, res: Vec3l) = xor(res, this, b.L, b.L, b.L)
    fun xor(b: Vec3t<out Number>, res: Vec3l) = xor(res, this, b.x.L, b.y.L, b.z.L)

    fun xor(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = xor(res, this, bX.L, bY.L, bZ.L)

    fun xorAssign(bX: Number, bY: Number, bZ: Number) = xor(this, this, bX.L, bY.L, bZ.L)


    infix fun shl(b: Number) = shl(Vec3l(), this, b.L, b.L, b.L)
    infix fun shl(b: Vec3t<out Number>) = shl(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    infix fun shlAssign(b: Number) = shl(this, this, b.L, b.L, b.L)
    infix fun shlAssign(b: Vec3t<out Number>) = shl(this, this, b.x.L, b.y.L, b.z.L)

    fun shl(b: Number, res: Vec3l) = shl(res, this, b.L, b.L, b.L)
    fun shl(b: Vec3t<out Number>, res: Vec3l) = shl(res, this, b.x.L, b.y.L, b.z.L)

    fun shl(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = shl(res, this, bX.L, bY.L, bZ.L)

    fun shlAssign(bX: Number, bY: Number, bZ: Number) = shl(this, this, bX.L, bY.L, bZ.L)


    infix fun shr(b: Number) = shr(Vec3l(), this, b.L, b.L, b.L)
    infix fun shr(b: Vec3t<out Number>) = shr(Vec3l(), this, b.x.L, b.y.L, b.z.L)

    infix fun shrAssign(b: Number) = shr(this, this, b.L, b.L, b.L)
    infix fun shrAssign(b: Vec3t<out Number>) = shr(this, this, b.x.L, b.y.L, b.z.L)

    fun shr(b: Number, res: Vec3l) = shr(res, this, b.L, b.L, b.L)
    fun shr(b: Vec3t<out Number>, res: Vec3l) = shr(res, this, b.x.L, b.y.L, b.z.L)

    fun shr(bX: Number, bY: Number, bZ: Number, res: Vec3l = Vec3l()) = shr(res, this, bX.L, bY.L, bZ.L)

    fun shrAssign(bX: Number, bY: Number, bZ: Number) = shr(this, this, bX.L, bY.L, bZ.L)


    override fun createInstance(x: Long, y: Long) = Vec2l(x, y)
    override fun createInstance(x: Long, y: Long, z: Long) = Vec3l(x, y, z)


    companion object : vec3l_operators() {
        const val length = Vec3t.length
        @JvmField
        val size = length * Long.BYTES
    }

    override fun size() = size

    override fun equals(other: Any?) = other is Vec3l && this[0] == other[0] && this[1] == other[1] && this[2] == other[2]
    override fun hashCode() = 31 * (31 * x.hashCode() + y.hashCode()) + z.hashCode()
}