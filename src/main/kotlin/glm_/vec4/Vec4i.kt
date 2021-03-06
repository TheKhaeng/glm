package glm_.vec4

import glm_.*
import glm_.buffer.intBufferBig
import glm_.vec2.Vec2bool
import glm_.vec2.Vec2i
import glm_.vec2.Vec2t
import glm_.vec3.Vec3bool
import glm_.vec3.Vec3i
import glm_.vec3.Vec3t
import glm_.vec4.operators.vec4i_operators
import java.nio.*

/**
 * Created by elect on 09/10/16.
 */

class Vec4i(var ofs: Int, var array: IntArray) : Vec4t<Int>() {

    constructor(x: Int, y: Int, z: Int, w: Int) : this(0, intArrayOf(x, y, z, w))

    override inline var x: Int
        get() = array[ofs]
        set(value) = array.set(ofs, value)
    override inline var y: Int
        get() = array[ofs + 1]
        set(value) = array.set(ofs + 1, value)
    override inline var z: Int
        get() = array[ofs + 2]
        set(value) = array.set(ofs + 2, value)
    override inline var w: Int
        get() = array[ofs + 3]
        set(value) = array.set(ofs + 3, value)

    // -- Explicit basic, conversion other main.and conversion vector constructors --

    constructor() : this(0)

    constructor(v: Vec2t<out Number>) : this(v.x, v.y, 0, 1)
    constructor(v: Vec2t<out Number>, z: Number, w: Number) : this(v.x, v.y, z, w)
    constructor(v: Vec3t<out Number>) : this(v, 1)
    constructor(v: Vec3t<out Number>, w: Number) : this(v.x, v.y, v.z, w)
    constructor(x: Number, v: Vec3t<out Number>) : this(x, v.x, v.y, v.z)
    constructor(v: Vec4t<out Number>) : this(v.x, v.y, v.z, v.w)

    constructor(v: Vec2bool) : this(v.x.i, v.y.i, 0, 1)
    constructor(v: Vec3bool) : this(v.x.i, v.y.i, v.z.i, 1)
    constructor(v: Vec4bool) : this(v.x.i, v.y.i, v.z.i, v.w.i)

    constructor(bytes: ByteArray, index: Int = 0, oneByteOneInt: Boolean = false, bigEndian: Boolean = true) : this(
            if (oneByteOneInt) bytes[index].i else bytes.getInt(index, bigEndian),
            if (oneByteOneInt) bytes[index + 1].i else bytes.getInt(index + Int.BYTES, bigEndian),
            if (oneByteOneInt) bytes[index + 2].i else bytes.getInt(index + Int.BYTES * 2, bigEndian),
            if (oneByteOneInt) bytes[index + 3].i else bytes.getInt(index + Int.BYTES * 3, bigEndian))

    constructor(chars: CharArray, index: Int = 0) : this(chars[index].i, chars[index + 1].i, chars[index + 2].i, chars[index + 3].i)
    constructor(shorts: ShortArray, index: Int = 0) : this(shorts[index], shorts[index + 1], shorts[index + 2], shorts[index + 3])
    constructor(ints: IntArray, index: Int = 0) : this(ints[index], ints[index + 1], ints[index + 2], ints[index + 3])
    constructor(longs: LongArray, index: Int = 0) : this(longs[index], longs[index + 1], longs[index + 2], longs[index + 3])
    constructor(floats: FloatArray, index: Int = 0) : this(floats[index], floats[index + 1], floats[index + 2], floats[index + 3])
    constructor(doubles: DoubleArray, index: Int = 0) : this(doubles[index], doubles[index + 1], doubles[index + 2], doubles[index + 3])
    constructor(booleans: BooleanArray, index: Int = 0) : this(booleans[index].i, booleans[index + 1].i, booleans[index + 2].i, booleans[index + 3].i)

    constructor(numbers: Array<out Number>, index: Int = 0) : this(numbers[index], numbers[index + 1], numbers[index + 2], numbers[index + 3])
    constructor(chars: Array<Char>, index: Int = 0) : this(chars[index].i, chars[index + 1].i, chars[index + 2].i, chars[index + 3].i)
    constructor(booleans: Array<Boolean>, index: Int = 0) : this(booleans[index].i, booleans[index + 1].i, booleans[index + 2].i, booleans[index + 2].i)

    constructor(list: Iterable<*>, index: Int = 0) : this(list.elementAt(index)!!.toInt, list.elementAt(index + 1)!!.toInt,
            list.elementAt(index + 2)!!.toInt, list.elementAt(index + 3)!!.toInt)

    constructor(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneInt: Boolean = false) : this(
            if (oneByteOneInt) bytes[index].i else bytes.getInt(index),
            if (oneByteOneInt) bytes[index + 1].i else bytes.getInt(index + Int.BYTES),
            if (oneByteOneInt) bytes[index + 2].i else bytes.getInt(index + Int.BYTES * 2),
            if (oneByteOneInt) bytes[index + 3].i else bytes.getInt(index + Int.BYTES * 3))

    constructor(chars: CharBuffer, index: Int = chars.position()) : this(chars[index].i, chars[index + 1].i, chars[index + 2].i, chars[index + 3].i)
    constructor(shorts: ShortBuffer, index: Int = shorts.position()) : this(shorts[index], shorts[index + 1], shorts[index + 2], shorts[index + 3])
    constructor(ints: IntBuffer, index: Int = ints.position()) : this(ints[index], ints[index + 1], ints[index + 2], ints[index + 3])
    constructor(longs: LongBuffer, index: Int = longs.position()) : this(longs[index], longs[index + 1], longs[index + 2], longs[index + 3])
    constructor(floats: FloatBuffer, index: Int = floats.position()) : this(floats[index], floats[index + 1], floats[index + 2], floats[index + 3])
    constructor(doubles: DoubleBuffer, index: Int = doubles.position()) : this(doubles[index], doubles[index + 1], doubles[index + 2], doubles[index + 2])

    constructor(block: (Int) -> Int) : this(block(0), block(1), block(2), block(3))

    constructor(s: Number) : this(s, s, s, s)
    constructor(x: Number, y: Number, z: Number, w: Number) : this(x.i, y.i, z.i, w.i)


    fun set(bytes: ByteArray, index: Int = 0, oneByteOneInt: Boolean = false, bigEndian: Boolean = true) {
        x = if (oneByteOneInt) bytes[index].i else bytes.getInt(index, bigEndian)
        y = if (oneByteOneInt) bytes[index + 1].i else bytes.getInt(index + Int.BYTES, bigEndian)
        z = if (oneByteOneInt) bytes[index + 2].i else bytes.getInt(index + Int.BYTES * 2, bigEndian)
        w = if (oneByteOneInt) bytes[index + 3].i else bytes.getInt(index + Int.BYTES * 3, bigEndian)
    }

    fun set(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneInt: Boolean = false) {
        x = if (oneByteOneInt) bytes[index].i else bytes.getInt(index)
        y = if (oneByteOneInt) bytes[index + 1].i else bytes.getInt(index + Int.BYTES)
        z = if (oneByteOneInt) bytes[index + 2].i else bytes.getInt(index + Int.BYTES * 2)
        w = if (oneByteOneInt) bytes[index + 3].i else bytes.getInt(index + Int.BYTES * 3)
    }


    fun put(x: Int, y: Int, z: Int, w: Int) {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
    }

    fun invoke(x: Int, y: Int, z: Int, w: Int): Vec4i {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
        return this
    }

    override fun put(x: Number, y: Number, z: Number, w: Number) {
        this.x = x.i
        this.y = y.i
        this.z = z.i
        this.w = w.i
    }

    override fun invoke(x: Number, y: Number, z: Number, w: Number): Vec4i {
        this.x = x.i
        this.y = y.i
        this.z = z.i
        this.w = w.i
        return this
    }

    fun to(bytes: ByteArray, index: Int) = to(bytes, index, true)
    override fun to(bytes: ByteArray, index: Int, bigEndian: Boolean): ByteArray {
        bytes.setInt(index, x)
        bytes.setInt(index + Int.BYTES, y)
        bytes.setInt(index + Int.BYTES * 2, z)
        bytes.setInt(index + Int.BYTES * 3, w)
        return bytes
    }

    override fun to(bytes: ByteBuffer, index: Int): ByteBuffer {
        bytes.putInt(index, x)
        bytes.putInt(index + Int.BYTES, y)
        bytes.putInt(index + Int.BYTES * 2, z)
        bytes.putInt(index + Int.BYTES * 3, w)
        return bytes
    }

    fun toIntArray() = to(IntArray(length), 0)
    infix fun to(ints: IntArray) = to(ints, 0)
    fun to(ints: IntArray, index: Int): IntArray {
        ints[index] = x
        ints[index + 1] = y
        ints[index + 2] = z
        ints[index + 3] = w
        return ints
    }

    fun toIntBuffer() = to(intBufferBig(length), 0)
    infix fun to(ints: IntBuffer) = to(ints, ints.position())
    fun to(ints: IntBuffer, index: Int): IntBuffer {
        ints[index] = x
        ints[index + 1] = y
        ints[index + 2] = z
        ints[index + 3] = w
        return ints
    }

    // -- Component accesses --

    operator fun set(index: Int, value: Int) = when (index) {
        0 -> x = value
        1 -> y = value
        2 -> z = value
        3 -> w = value
        else -> throw ArrayIndexOutOfBoundsException()
    }

    override operator fun set(index: Int, value: Number) = when (index) {
        0 -> x = value.i
        1 -> y = value.i
        2 -> z = value.i
        3 -> w = value.i
        else -> throw ArrayIndexOutOfBoundsException()
    }




    // -- Unary arithmetic operators --

    operator fun unaryPlus() = this

    operator fun unaryMinus() = Vec4i(-x, -y, -z, -w)

    // -- Increment main.and decrement operators --

    operator fun inc(res: Vec4i = Vec4i()) = plus(res, this, 1, 1, 1, 1)
    fun incAssign() = plus(this, this, 1, 1, 1, 1)


    operator fun dec(res: Vec4i = Vec4i()) = minus(res, this, 1, 1, 1, 1)
    fun decAssign() = minus(this, this, 1, 1, 1, 1)


    // -- Specific binary arithmetic operators --

    operator fun plus(b: Int) = plus(Vec4i(), this, b, b, b, b)
    operator fun plus(b: Vec4i) = plus(Vec4i(), this, b.x, b.y, b.z, b.w)

    fun plus(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = plus(res, this, bX, bY, bZ, bW)
    fun plus(b: Int, res: Vec4i = Vec4i()) = plus(res, this, b, b, b, b)
    fun plus(b: Vec4i, res: Vec4i = Vec4i()) = plus(res, this, b.x, b.y, b.z, b.w)

    fun plusAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = plus(this, this, bX, bY, bZ, bW)
    infix operator fun plusAssign(b: Int) {
        plus(this, this, b, b, b, b)
    }

    infix operator fun plusAssign(b: Vec4i) {
        plus(this, this, b.x, b.y, b.z, b.w)
    }


    operator fun minus(b: Int) = minus(Vec4i(), this, b, b, b, b)
    operator fun minus(b: Vec4i) = minus(Vec4i(), this, b.x, b.y, b.z, b.w)

    fun minus(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = minus(res, this, bX, bY, bZ, bW)
    fun minus(b: Int, res: Vec4i = Vec4i()) = minus(res, this, b, b, b, b)
    fun minus(b: Vec4i, res: Vec4i = Vec4i()) = minus(res, this, b.x, b.y, b.z, b.w)

    fun minusAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = minus(this, this, bX, bY, bZ, bW)
    infix operator fun minusAssign(b: Int) {
        minus(this, this, b, b, b, b)
    }

    infix operator fun minusAssign(b: Vec4i) {
        minus(this, this, b.x, b.y, b.z, b.w)
    }


    operator fun times(b: Int) = times(Vec4i(), this, b, b, b, b)
    operator fun times(b: Vec4i) = times(Vec4i(), this, b.x, b.y, b.z, b.w)

    fun times(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = times(res, this, bX, bY, bZ, bW)
    fun times(b: Int, res: Vec4i = Vec4i()) = times(res, this, b, b, b, b)
    fun times(b: Vec4i, res: Vec4i = Vec4i()) = times(res, this, b.x, b.y, b.z, b.w)

    fun timesAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = times(this, this, bX, bY, bZ, bW)
    infix operator fun timesAssign(b: Int) {
        times(this, this, b, b, b, b)
    }

    infix operator fun timesAssign(b: Vec4i) {
        times(this, this, b.x, b.y, b.z, b.w)
    }


    operator fun div(b: Int) = div(Vec4i(), this, b, b, b, b)
    operator fun div(b: Vec4i) = div(Vec4i(), this, b.x, b.y, b.z, b.w)

    fun div(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = div(res, this, bX, bY, bZ, bW)
    fun div(b: Int, res: Vec4i) = div(res, this, b, b, b, b)
    fun div(b: Vec4i, res: Vec4i) = div(res, this, b.x, b.y, b.z, b.w)

    fun divAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = div(this, this, bX, bY, bZ, bW)
    infix operator fun divAssign(b: Int) {
        div(this, this, b, b, b, b)
    }

    infix operator fun divAssign(b: Vec4i) {
        div(this, this, b.x, b.y, b.z, b.w)
    }


    operator fun rem(b: Int) = rem(Vec4i(), this, b, b, b, b)
    operator fun rem(b: Vec4i) = rem(Vec4i(), this, b.x, b.y, b.z, b.w)

    fun rem(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = rem(res, this, bX, bY, bZ, bW)
    fun rem(b: Int, res: Vec4i) = rem(res, this, b, b, b, b)
    fun rem(b: Vec4i, res: Vec4i) = rem(res, this, b.x, b.y, b.z, b.w)

    fun remAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = rem(this, this, bX, bY, bZ, bW)
    infix operator fun remAssign(b: Int) {
        rem(this, this, b, b, b, b)
    }

    infix operator fun remAssign(b: Vec4i) {
        rem(this, this, b.x, b.y, b.z, b.w)
    }


    // -- Generic binary arithmetic operators --

    operator fun plus(b: Number) = plus(Vec4i(), this, b.i, b.i, b.i, b.i)
    operator fun plus(b: Vec4t<out Number>) = plus(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun plus(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = plus(res, this, bX.i, bY.i, bZ.i, bW.i)
    fun plus(b: Number, res: Vec4i = Vec4i()) = plus(res, this, b.i, b.i, b.i, b.i)
    fun plus(b: Vec4t<out Number>, res: Vec4i = Vec4i()) = plus(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun plusAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = plus(this, this, bX.i, bY.i, bZ.i, bW.i)
    infix operator fun plusAssign(b: Number) {
        plus(this, this, b.i, b.i, b.i, b.i)
    }

    infix operator fun plusAssign(b: Vec4t<out Number>) {
        plus(this, this, b.x.i, b.y.i, b.z.i, b.w.i)
    }


    operator fun minus(b: Number) = minus(Vec4i(), this, b.i, b.i, b.i, b.i)
    operator fun minus(b: Vec4t<out Number>) = minus(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun minus(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = minus(res, this, bX.i, bY.i, bZ.i, bW.i)
    fun minus(b: Number, res: Vec4i = Vec4i()) = minus(res, this, b.i, b.i, b.i, b.i)
    fun minus(b: Vec4t<out Number>, res: Vec4i = Vec4i()) = minus(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun minusAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = minus(this, this, bX.i, bY.i, bZ.i, bW.i)
    infix operator fun minusAssign(b: Number) {
        minus(this, this, b.i, b.i, b.i, b.i)
    }

    infix operator fun minusAssign(b: Vec4t<out Number>) {
        minus(this, this, b.x.i, b.y.i, b.z.i, b.w.i)
    }


    operator fun times(b: Number) = times(Vec4i(), this, b.i, b.i, b.i, b.i)
    operator fun times(b: Vec4t<out Number>) = times(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun times(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = times(res, this, bX.i, bY.i, bZ.i, bW.i)
    fun times(b: Number, res: Vec4i = Vec4i()) = times(res, this, b.i, b.i, b.i, b.i)
    fun times(b: Vec4t<out Number>, res: Vec4i = Vec4i()) = times(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun timesAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = times(this, this, bX.i, bY.i, bZ.i, bW.i)
    infix operator fun timesAssign(b: Number) {
        times(this, this, b.i, b.i, b.i, b.i)
    }

    infix operator fun timesAssign(b: Vec4t<out Number>) {
        times(this, this, b.x.i, b.y.i, b.z.i, b.w.i)
    }


    operator fun div(b: Number) = div(Vec4i(), this, b.i, b.i, b.i, b.i)
    operator fun div(b: Vec4t<out Number>) = div(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun div(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = div(res, this, bX.i, bY.i, bZ.i, bW.i)
    fun div(b: Number, res: Vec4i) = div(res, this, b.i, b.i, b.i, b.i)
    fun div(b: Vec4t<out Number>, res: Vec4i) = div(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun divAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = div(this, this, bX.i, bY.i, bZ.i, bW.i)
    infix operator fun divAssign(b: Number) {
        div(this, this, b.i, b.i, b.i, b.i)
    }

    infix operator fun divAssign(b: Vec4t<out Number>) {
        div(this, this, b.x.i, b.y.i, b.z.i, b.w.i)
    }


    operator fun rem(b: Number) = rem(Vec4i(), this, b.i, b.i, b.i, b.i)
    operator fun rem(b: Vec4t<out Number>) = rem(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun rem(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = rem(res, this, bX.i, bY.i, bZ.i, bW.i)
    fun rem(b: Number, res: Vec4i) = rem(res, this, b.i, b.i, b.i, b.i)
    fun rem(b: Vec4t<out Number>, res: Vec4i) = rem(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun remAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = rem(this, this, bX.i, bY.i, bZ.i, bW.i)
    infix operator fun remAssign(b: Number) {
        rem(this, this, b.i, b.i, b.i, b.i)
    }

    infix operator fun remAssign(b: Vec4t<out Number>) {
        rem(this, this, b.x.i, b.y.i, b.z.i, b.w.i)
    }


    // -- Specific bitwise operators --

    infix fun and(b: Int) = and(Vec4i(), this, b, b, b, b)
    infix fun and(b: Vec4i) = and(Vec4i(), this, b.x, b.y, b.z, b.w)

    infix fun andAssign(b: Int) = and(this, this, b, b, b, b)
    infix fun andAssign(b: Vec4i) = and(this, this, b.x, b.y, b.z, b.w)

    fun and(b: Int, res: Vec4i) = and(res, this, b, b, b, b)
    fun and(b: Vec4i, res: Vec4i) = and(res, this, b.x, b.y, b.z, b.w)

    fun and(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = and(res, this, bX, bY, bZ, bW)

    fun andAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = and(this, this, bX, bY, bZ, bW)


    infix fun or(b: Int) = or(Vec4i(), this, b, b, b, b)
    infix fun or(b: Vec4i) = or(Vec4i(), this, b.x, b.y, b.z, b.w)

    infix fun orAssign(b: Int) = or(this, this, b, b, b, b)
    infix fun orAssign(b: Vec4i) = or(this, this, b.x, b.y, b.z, b.w)

    fun or(b: Int, res: Vec4i) = or(res, this, b, b, b, b)
    fun or(b: Vec4i, res: Vec4i) = or(res, this, b.x, b.y, b.z, b.w)

    fun or(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = or(res, this, bX, bY, bZ, bW)

    fun orAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = or(this, this, bX, bY, bZ, bW)


    infix fun xor(b: Int) = xor(Vec4i(), this, b, b, b, b)
    infix fun xor(b: Vec4i) = xor(Vec4i(), this, b.x, b.y, b.z, b.w)

    infix fun xorAssign(b: Int) = xor(this, this, b, b, b, b)
    infix fun xorAssign(b: Vec4i) = xor(this, this, b.x, b.y, b.z, b.w)

    fun xor(b: Int, res: Vec4i) = xor(res, this, b, b, b, b)
    fun xor(b: Vec4i, res: Vec4i) = xor(res, this, b.x, b.y, b.z, b.w)

    fun xor(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = xor(res, this, bX, bY, bZ, bW)

    fun xorAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = xor(this, this, bX, bY, bZ, bW)


    infix fun shl(b: Int) = shl(Vec4i(), this, b, b, b, b)
    infix fun shl(b: Vec4i) = shl(Vec4i(), this, b.x, b.y, b.z, b.w)

    infix fun shlAssign(b: Int) = shl(this, this, b, b, b, b)
    infix fun shlAssign(b: Vec4i) = shl(this, this, b.x, b.y, b.z, b.w)

    fun shl(b: Int, res: Vec4i) = shl(res, this, b, b, b, b)
    fun shl(b: Vec4i, res: Vec4i) = shl(res, this, b.x, b.y, b.z, b.w)

    fun shl(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = shl(res, this, bX, bY, bZ, bW)

    fun shlAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = shl(this, this, bX, bY, bZ, bW)


    infix fun shr(b: Int) = shr(Vec4i(), this, b, b, b, b)
    infix fun shr(b: Vec4i) = shr(Vec4i(), this, b.x, b.y, b.z, b.w)

    infix fun shrAssign(b: Int) = shr(this, this, b, b, b, b)
    infix fun shrAssign(b: Vec4i) = shr(this, this, b.x, b.y, b.z, b.w)

    fun shr(b: Int, res: Vec4i) = shr(res, this, b, b, b, b)
    fun shr(b: Vec4i, res: Vec4i) = shr(res, this, b.x, b.y, b.z, b.w)

    fun shr(bX: Int, bY: Int, bZ: Int, bW: Int, res: Vec4i = Vec4i()) = shr(res, this, bX, bY, bZ, bW)

    fun shrAssign(bX: Int, bY: Int, bZ: Int, bW: Int) = shr(this, this, bX, bY, bZ, bW)


    fun inv(res: Vec4i = Vec4i()) = inv(res, this)
    fun invAssign() = inv(this, this)


    // -- Generic bitwise operators --

    infix fun and(b: Number) = and(Vec4i(), this, b.i, b.i, b.i, b.i)
    infix fun and(b: Vec4t<out Number>) = and(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    infix fun andAssign(b: Number) = and(this, this, b.i, b.i, b.i, b.i)
    infix fun andAssign(b: Vec4t<out Number>) = and(this, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun and(b: Number, res: Vec4i) = and(res, this, b.i, b.i, b.i, b.i)
    fun and(b: Vec4t<out Number>, res: Vec4i) = and(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun and(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = and(res, this, bX.i, bY.i, bZ.i, bW.i)

    fun andAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = and(this, this, bX.i, bY.i, bZ.i, bW.i)


    infix fun or(b: Number) = or(Vec4i(), this, b.i, b.i, b.i, b.i)
    infix fun or(b: Vec4t<out Number>) = or(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    infix fun orAssign(b: Number) = or(this, this, b.i, b.i, b.i, b.i)
    infix fun orAssign(b: Vec4t<out Number>) = or(this, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun or(b: Number, res: Vec4i) = or(res, this, b.i, b.i, b.i, b.i)
    fun or(b: Vec4t<out Number>, res: Vec4i) = or(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun or(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = or(res, this, bX.i, bY.i, bZ.i, bW.i)

    fun orAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = or(this, this, bX.i, bY.i, bZ.i, bW.i)


    infix fun xor(b: Number) = xor(Vec4i(), this, b.i, b.i, b.i, b.i)
    infix fun xor(b: Vec4t<out Number>) = xor(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    infix fun xorAssign(b: Number) = xor(this, this, b.i, b.i, b.i, b.i)
    infix fun xorAssign(b: Vec4t<out Number>) = xor(this, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun xor(b: Number, res: Vec4i) = xor(res, this, b.i, b.i, b.i, b.i)
    fun xor(b: Vec4t<out Number>, res: Vec4i) = xor(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun xor(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = xor(res, this, bX.i, bY.i, bZ.i, bW.i)

    fun xorAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = xor(this, this, bX.i, bY.i, bZ.i, bW.i)


    infix fun shl(b: Number) = shl(Vec4i(), this, b.i, b.i, b.i, b.i)
    infix fun shl(b: Vec4t<out Number>) = shl(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    infix fun shlAssign(b: Number) = shl(this, this, b.i, b.i, b.i, b.i)
    infix fun shlAssign(b: Vec4t<out Number>) = shl(this, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun shl(b: Number, res: Vec4i) = shl(res, this, b.i, b.i, b.i, b.i)
    fun shl(b: Vec4t<out Number>, res: Vec4i) = shl(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun shl(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = shl(res, this, bX.i, bY.i, bZ.i, bW.i)

    fun shlAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = shl(this, this, bX.i, bY.i, bZ.i, bW.i)


    infix fun shr(b: Number) = shr(Vec4i(), this, b.i, b.i, b.i, b.i)
    infix fun shr(b: Vec4t<out Number>) = shr(Vec4i(), this, b.x.i, b.y.i, b.z.i, b.w.i)

    infix fun shrAssign(b: Number) = shr(this, this, b.i, b.i, b.i, b.i)
    infix fun shrAssign(b: Vec4t<out Number>) = shr(this, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun shr(b: Number, res: Vec4i) = shr(res, this, b.i, b.i, b.i, b.i)
    fun shr(b: Vec4t<out Number>, res: Vec4i) = shr(res, this, b.x.i, b.y.i, b.z.i, b.w.i)

    fun shr(bX: Number, bY: Number, bZ: Number, bW: Number, res: Vec4i = Vec4i()) = shr(res, this, bX.i, bY.i, bZ.i, bW.i)

    fun shrAssign(bX: Number, bY: Number, bZ: Number, bW: Number) = shr(this, this, bX.i, bY.i, bZ.i, bW.i)


    override fun createInstance(x: Int, y: Int) = Vec2i(x, y)
    override fun createInstance(x: Int, y: Int, z: Int) = Vec3i(x, y, z)
    override fun createInstance(x: Int, y: Int, z: Int, w: Int) = Vec4i(x, y, z, w)


    companion object : vec4i_operators() {
        const val length = Vec4t.length
        @JvmField
        val size = length * Int.BYTES
    }

    override fun size() = size


    override fun equals(other: Any?) = other is Vec4i && this[0] == other[0] && this[1] == other[1] && this[2] == other[2] && this[3] == other[3]
    override fun hashCode() = 31 * (31 * (31 * x.hashCode() + y.hashCode()) + z.hashCode()) + w.hashCode()
}