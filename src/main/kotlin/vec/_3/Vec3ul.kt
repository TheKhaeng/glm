package vec._3

import main.*
import unsigned.Ulong
import vec.Vec2t
import vec.Vec3t
import vec.Vec4t
import vec._3.operators.vec3ul_operators
import vec.bool.Vec2bool
import vec.bool.Vec3bool
import vec.bool.Vec4bool
import java.nio.*

/**
 * Created by elect on 09/10/16.
 */

data class Vec3ul(override var x: Ulong, override var y: Ulong, override var z: Ulong) : Vec3t<Ulong>() {

    // -- Explicit basic, conversion other main.and conversion vector constructors --

    constructor() : this(0)

    constructor(v: Vec2t<out Number>) : this(v.x, v.y, 0)
    constructor(v: Vec3t<out Number>) : this(v.x, v.y, v.z)
    constructor(v: Vec4t<out Number>) : this(v.x, v.y, v.z)

    constructor(v: Vec2bool) : this(v.x.ul, v.y.ul, 0)
    constructor(v: Vec3bool) : this(v.x.ul, v.y.ul, v.z.ul)
    constructor(v: Vec4bool) : this(v.x.ul, v.y.ul, v.z.ul)

    constructor(bytes: ByteArray, index: Int = 0, oneByteOneUlong: Boolean = true, bigEndianess: Boolean = true) : this(
            if (oneByteOneUlong) bytes[index].ul else bytes.getUlong(index, bigEndianess),
            if (oneByteOneUlong) bytes[index + 1].ul else bytes.getUlong(index + Ulong.BYTES, bigEndianess),
            if (oneByteOneUlong) bytes[index + 2].ul else bytes.getUlong(index + Ulong.BYTES * 2, bigEndianess))

    constructor(chars: CharArray, index: Int = 0) : this(chars[index].ul, chars[index + 1].ul, chars[index + 2].ul)
    constructor(shorts: ShortArray, index: Int = 0) : this(shorts[index], shorts[index + 1], shorts[index + 2])
    constructor(ints: IntArray, index: Int = 0) : this(ints[index], ints[index + 1], ints[index + 2])
    constructor(longs: LongArray, index: Int = 0) : this(longs[index], longs[index + 1], longs[index + 2])
    constructor(floats: FloatArray, index: Int = 0) : this(floats[index], floats[index + 1], floats[index + 2])
    constructor(doubles: DoubleArray, index: Int = 0) : this(doubles[index], doubles[index + 1], doubles[index + 2])
    constructor(booleans: BooleanArray, index: Int = 0) : this(booleans[index].ul, booleans[index + 1].ul, booleans[index + 2].ul)

    constructor(numbers: Array<out Number>, index: Int = 0) : this(numbers[index], numbers[index + 1], numbers[index + 2])
    constructor(chars: Array<Char>, index: Int = 0) : this(chars[index].ul, chars[index + 1].ul, chars[index + 2].ul)
    constructor(booleans: Array<Boolean>, index: Int = 0) : this(booleans[index].ul, booleans[index + 1].ul, booleans[index + 2].ul)

    constructor(list: List<Any>, index: Int = 0) : this() {
        Set(list, index)
    }

    constructor(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneUlong: Boolean = true) : this(
            if (oneByteOneUlong) bytes[index].ul else bytes.getLong(index).ul,
            if (oneByteOneUlong) bytes[index + 1].ul else bytes.getLong(index + Ulong.BYTES).ul,
            if (oneByteOneUlong) bytes[index + 2].ul else bytes.getLong(index + Ulong.BYTES * 2).ul)

    constructor(chars: CharBuffer, index: Int = chars.position()) : this(chars[index].ul, chars[index + 1].ul, chars[index + 2].ul)
    constructor(shorts: ShortBuffer, index: Int = shorts.position()) : this(shorts[index], shorts[index + 1], shorts[index + 2])
    constructor(ints: IntBuffer, index: Int = ints.position()) : this(ints[index], ints[index + 1], ints[index + 2])
    constructor(longs: LongBuffer, index: Int = longs.position()) : this(longs[index], longs[index + 1], longs[index + 2])
    constructor(floats: FloatBuffer, index: Int = floats.position()) : this(floats[index], floats[index + 1], floats[index + 2])
    constructor(doubles: DoubleBuffer, index: Int = doubles.position()) : this(doubles[index], doubles[index + 1], doubles[index + 2])

    constructor(s: Number) : this(s, s, s)
    constructor(x: Number, y: Number, z: Number) : this(x.ul, y.ul, z.ul)


    fun set(bytes: ByteArray, index: Int = 0, oneByteOneUlong: Boolean = true, bigEndianess: Boolean = true) {
        x.v = if (oneByteOneUlong) bytes[index].L else bytes.getLong(index, bigEndianess)
        y.v = if (oneByteOneUlong) bytes[index + 1].L else bytes.getLong(index + Ulong.BYTES, bigEndianess)
        z.v = if (oneByteOneUlong) bytes[index + 2].L else bytes.getLong(index + Ulong.BYTES * 2, bigEndianess)
    }

    fun set(bytes: ByteBuffer, index: Int = bytes.position(), oneByteOneUlong: Boolean = true) {
        x.v = if (oneByteOneUlong) bytes[index].L else bytes.getLong(index)
        y.v = if (oneByteOneUlong) bytes[index + 1].L else bytes.getLong(index + Ulong.BYTES)
        z.v = if (oneByteOneUlong) bytes[index + 2].L else bytes.getLong(index + Ulong.BYTES * 2)
    }


    override fun Set(x: Number, y: Number, z: Number): Vec3ul {
        this.x = x.ul
        this.y = y.ul
        this.z = z.ul
        return this
    }


    // -- Component accesses --

    operator fun get(i: Int) = when (i) {
        0 -> x
        1 -> y
        2 -> z
        else -> throw ArrayIndexOutOfBoundsException()
    }

    operator fun set(i: Int, s: Number) = when (i) {
        0 -> x = s.ul
        1 -> y = s.ul
        2 -> y = s.ul
        else -> throw ArrayIndexOutOfBoundsException()
    }


    companion object : vec3ul_operators {
        @JvmField val SIZE = 3 * Ulong.BYTES
    }


    // -- Unary arithmetic operators --

    operator fun unaryPlus() = this

    // no unaryMinus operator, only signed

    // -- Increment main.and decrement operators --

    operator fun inc(res: Vec3ul = Vec3ul()) = add(res, this, 1, 1, 1)
    fun inc_() = add(this, this, 1, 1, 1)


    operator fun dec(res: Vec3ul = Vec3ul()) = sub(res, this, 1, 1, 1)
    fun dec_() = sub(this, this, 1, 1, 1)


    // -- Specific binary arithmetic operators --

    operator fun plus(b: Ulong) = add(Vec3ul(), this, b, b, b)
    operator fun plus(b: Long) = add(Vec3ul(), this, b, b, b)
    operator fun plus(b: Vec3ul) = add(Vec3ul(), this, b.x, b.y, b.z)

    fun add(bX: Ulong, bY: Ulong, bZ: Ulong, res: Vec3ul = Vec3ul()) = add(res, this, bX, bY, bZ)
    fun add(bX: Long, bY: Long, bZ: Long, res: Vec3ul = Vec3ul()) = add(res, this, bX, bY, bZ)
    fun add(b: Ulong, res: Vec3ul = Vec3ul()) = add(res, this, b, b, b)
    fun add(b: Long, res: Vec3ul = Vec3ul()) = add(res, this, b, b, b)
    fun add(b: Vec3ul, res: Vec3ul = Vec3ul()) = add(res, this, b.x, b.y, b.z)

    fun add_(bX: Ulong, bY: Ulong, bZ: Ulong) = add(this, this, bX, bY, bZ)
    fun add_(bX: Long, bY: Long, bZ: Long) = add(this, this, bX, bY, bZ)
    infix fun add_(b: Ulong) = add(this, this, b, b, b)
    infix fun add_(b: Long) = add(this, this, b, b, b)
    infix fun add_(b: Vec3ul) = add(this, this, b.x, b.y, b.z)


    operator fun minus(b: Ulong) = sub(Vec3ul(), this, b, b, b)
    operator fun minus(b: Long) = sub(Vec3ul(), this, b, b, b)
    operator fun minus(b: Vec3ul) = sub(Vec3ul(), this, b.x, b.y, b.z)

    fun sub(bX: Ulong, bY: Ulong, bZ: Ulong, res: Vec3ul = Vec3ul()) = sub(res, this, bX, bY, bZ)
    fun sub(bX: Long, bY: Long, bZ: Long, res: Vec3ul = Vec3ul()) = sub(res, this, bX, bY, bZ)
    fun sub(b: Ulong, res: Vec3ul = Vec3ul()) = sub(res, this, b, b, b)
    fun sub(b: Long, res: Vec3ul = Vec3ul()) = sub(res, this, b, b, b)
    fun sub(b: Vec3ul, res: Vec3ul = Vec3ul()) = sub(res, this, b.x, b.y, b.z)

    fun sub_(bX: Ulong, bY: Ulong, bZ: Ulong) = sub(this, this, bX, bY, bZ)
    fun sub_(bX: Long, bY: Long, bZ: Long) = sub(this, this, bX, bY, bZ)
    infix fun sub_(b: Ulong) = sub(this, this, b, b, b)
    infix fun sub_(b: Long) = sub(this, this, b, b, b)
    infix fun sub_(b: Vec3ul) = sub(this, this, b.x, b.y, b.z)


    operator fun times(b: Ulong) = mul(Vec3ul(), this, b, b, b)
    operator fun times(b: Long) = mul(Vec3ul(), this, b, b, b)
    operator fun times(b: Vec3ul) = mul(Vec3ul(), this, b.x, b.y, b.z)

    fun mul(bX: Ulong, bY: Ulong, bZ: Ulong, res: Vec3ul = Vec3ul()) = mul(res, this, bX, bY, bZ)
    fun mul(bX: Long, bY: Long, bZ: Long, res: Vec3ul = Vec3ul()) = mul(res, this, bX, bY, bZ)
    fun mul(b: Ulong, res: Vec3ul = Vec3ul()) = mul(res, this, b, b, b)
    fun mul(b: Long, res: Vec3ul = Vec3ul()) = mul(res, this, b, b, b)
    fun mul(b: Vec3ul, res: Vec3ul = Vec3ul()) = mul(res, this, b.x, b.y, b.z)

    fun mul_(bX: Ulong, bY: Ulong, bZ: Ulong) = mul(this, this, bX, bY, bZ)
    fun mul_(bX: Long, bY: Long, bZ: Long) = mul(this, this, bX, bY, bZ)
    infix fun mul_(b: Ulong) = mul(this, this, b, b, b)
    infix fun mul_(b: Long) = mul(this, this, b, b, b)
    infix fun mul_(b: Vec3ul) = mul(this, this, b.x, b.y, b.z)


    operator fun div(b: Ulong) = div(Vec3ul(), this, b, b, b)
    operator fun div(b: Long) = div(Vec3ul(), this, b, b, b)
    operator fun div(b: Vec3ul) = div(Vec3ul(), this, b.x, b.y, b.z)

    fun div(bX: Ulong, bY: Ulong, bZ: Ulong, res: Vec3ul = Vec3ul()) = div(res, this, bX, bY, bZ)
    fun div(bX: Long, bY: Long, bZ: Long, res: Vec3ul = Vec3ul()) = div(res, this, bX, bY, bZ)
    fun div(b: Ulong, res: Vec3ul = Vec3ul()) = div(res, this, b, b, b)
    fun div(b: Long, res: Vec3ul = Vec3ul()) = div(res, this, b, b, b)
    fun div(b: Vec3ul, res: Vec3ul = Vec3ul()) = div(res, this, b.x, b.y, b.z)

    fun div_(bX: Ulong, bY: Ulong, bZ: Ulong) = div(this, this, bX, bY, bZ)
    fun div_(bX: Long, bY: Long, bZ: Long) = div(this, this, bX, bY, bZ)
    infix fun div_(b: Ulong) = div(this, this, b, b, b)
    infix fun div_(b: Long) = div(this, this, b, b, b)
    infix fun div_(b: Vec3ul) = div(this, this, b.x, b.y, b.z)


    operator fun rem(b: Ulong) = rem(Vec3ul(), this, b, b, b)
    operator fun rem(b: Long) = rem(Vec3ul(), this, b, b, b)
    operator fun rem(b: Vec3ul) = rem(Vec3ul(), this, b.x, b.y, b.z)

    fun rem(bX: Ulong, bY: Ulong, bZ: Ulong, res: Vec3ul = Vec3ul()) = rem(res, this, bX, bY, bZ)
    fun rem(bX: Long, bY: Long, bZ: Long, res: Vec3ul = Vec3ul()) = rem(res, this, bX, bY, bZ)
    fun rem(b: Ulong, res: Vec3ul = Vec3ul()) = rem(res, this, b, b, b)
    fun rem(b: Long, res: Vec3ul = Vec3ul()) = rem(res, this, b, b, b)
    fun rem(b: Vec3ul, res: Vec3ul = Vec3ul()) = rem(res, this, b.x, b.y, b.z)

    fun rem_(bX: Ulong, bY: Ulong, bZ: Ulong) = rem(this, this, bX, bY, bZ)
    fun rem_(bX: Long, bY: Long, bZ: Long) = rem(this, this, bX, bY, bZ)
    infix fun rem_(b: Ulong) = rem(this, this, b, b, b)
    infix fun rem_(b: Long) = rem(this, this, b, b, b)
    infix fun rem_(b: Vec3ul) = rem(this, this, b.x, b.y, b.z)


    // -- Generic binary arithmetic operators --

    operator fun plus(b: Number) = add(Vec3ul(), this, b.L, b.L, b.L)
    operator fun plus(b: Vec3t<Number>) = add(Vec3ul(), this, b.x.L, b.y.L, b.z.L)

    fun add(bX: Number, bY: Number, bZ: Number, res: Vec3ul = Vec3ul()) = add(res, this, bX.L, bY.L, bZ.L)
    fun add(b: Number, res: Vec3ul = Vec3ul()) = add(res, this, b.L, b.L, b.L)
    fun add(b: Vec3t<Number>, res: Vec3ul = Vec3ul()) = add(res, this, b.x.L, b.y.L, b.z.L)

    fun add_(bX: Number, bY: Number, bZ: Number) = add(this, this, bX.L, bY.L, bZ.L)
    infix fun add_(b: Number) = add(this, this, b.L, b.L, b.L)
    infix fun add_(b: Vec3t<Number>) = add(this, this, b.x.L, b.y.L, b.z.L)


    operator fun minus(b: Number) = sub(Vec3ul(), this, b.L, b.L, b.L)
    operator fun minus(b: Vec3t<Number>) = sub(Vec3ul(), this, b.x.L, b.y.L, b.z.L)

    fun sub(bX: Number, bY: Number, bZ: Number, res: Vec3ul = Vec3ul()) = sub(res, this, bX.L, bY.L, bZ.L)
    fun sub(b: Number, res: Vec3ul = Vec3ul()) = sub(res, this, b.L, b.L, b.L)
    fun sub(b: Vec3t<Number>, res: Vec3ul = Vec3ul()) = sub(res, this, b.x.L, b.y.L, b.z.L)

    fun sub_(bX: Number, bY: Number, bZ: Number) = sub(this, this, bX.L, bY.L, bZ.L)
    infix fun sub_(b: Number) = sub(this, this, b.L, b.L, b.L)
    infix fun sub_(b: Vec3t<Number>) = sub(this, this, b.x.L, b.y.L, b.z.L)


    operator fun times(b: Number) = mul(Vec3ul(), this, b.L, b.L, b.L)
    operator fun times(b: Vec3t<Number>) = mul(Vec3ul(), this, b.x.L, b.y.L, b.z.L)

    fun mul(bX: Number, bY: Number, bZ: Number, res: Vec3ul = Vec3ul()) = mul(res, this, bX.L, bY.L, bZ.L)
    fun mul(b: Number, res: Vec3ul = Vec3ul()) = mul(res, this, b.L, b.L, b.L)
    fun mul(b: Vec3t<Number>, res: Vec3ul = Vec3ul()) = mul(res, this, b.x.L, b.y.L, b.z.L)

    fun mul_(bX: Number, bY: Number, bZ: Number) = mul(this, this, bX.L, bY.L, bZ.L)
    infix fun mul_(b: Number) = mul(this, this, b.L, b.L, b.L)
    infix fun mul_(b: Vec3t<Number>) = mul(this, this, b.x.L, b.y.L, b.z.L)


    operator fun div(b: Number) = div(Vec3ul(), this, b.L, b.L, b.L)
    operator fun div(b: Vec3t<Number>) = div(Vec3ul(), this, b.x.L, b.y.L, b.z.L)

    fun div(bX: Number, bY: Number, bZ: Number, res: Vec3ul = Vec3ul()) = div(res, this, bX.L, bY.L, bZ.L)
    fun div(b: Number, res: Vec3ul = Vec3ul()) = div(res, this, b.L, b.L, b.L)
    fun div(b: Vec3t<Number>, res: Vec3ul = Vec3ul()) = div(res, this, b.x.L, b.y.L, b.z.L)

    fun div_(bX: Number, bY: Number, bZ: Number) = div(this, this, bX.L, bY.L, bZ.L)
    infix fun div_(b: Number) = div(this, this, b.L, b.L, b.L)
    infix fun div_(b: Vec3t<Number>) = div(this, this, b.x.L, b.y.L, b.z.L)


    operator fun rem(b: Number) = rem(Vec3ul(), this, b.L, b.L, b.L)
    operator fun rem(b: Vec3t<Number>) = rem(Vec3ul(), this, b.x.L, b.y.L, b.z.L)

    fun rem(bX: Number, bY: Number, bZ: Number, res: Vec3ul = Vec3ul()) = rem(res, this, bX.L, bY.L, bZ.L)
    fun rem(b: Number, res: Vec3ul = Vec3ul()) = rem(res, this, b.L, b.L, b.L)
    fun rem(b: Vec3t<Number>, res: Vec3ul = Vec3ul()) = rem(res, this, b.x.L, b.y.L, b.z.L)

    fun rem_(bX: Number, bY: Number, bZ: Number) = rem(this, this, bX.L, bY.L, bZ.L)
    infix fun rem_(b: Number) = rem(this, this, b.L, b.L, b.L)
    infix fun rem_(b: Vec3t<Number>) = rem(this, this, b.x.L, b.y.L, b.z.L)


    // -- Specific bitwise operators --

    infix fun and(b: Ulong) = and(Vec3ul(), this, b, b, b)
    infix fun and(b: Long) = and(Vec3ul(), this, b, b, b)
    fun and(bX: Ulong, bY: Ulong, bZ: Ulong) = and(Vec3ul(), this, bX, bY, bZ)
    fun and(bX: Long, bY: Long, bZ: Long) = and(Vec3ul(), this, bX, bY, bZ)
    fun and(b: Vec3ul) = and(Vec3ul(), this, b.x, b.y, b.z)

    infix fun and_(b: Ulong) = and(this, this, b, b, b)
    infix fun and_(b: Long) = and(this, this, b, b, b)
    fun and_(bX: Ulong, bY: Ulong, bZ: Ulong) = and(this, this, bX, bY, bZ)
    fun and_(bX: Long, bY: Long, bZ: Long) = and(this, this, bX, bY, bZ)
    infix fun and_(b: Vec3ul) = and(this, this, b.x, b.y, b.z)

    fun and(b: Ulong, res: Vec3ul) = and(res, this, b, b, b)
    fun and(b: Long, res: Vec3ul) = and(res, this, b, b, b)
    fun and(bX: Ulong, bY: Ulong, bZ: Ulong, res: Vec3ul) = and(res, this, bX, bY, bZ)
    fun and(bX: Long, bY: Long, bZ: Long, res: Vec3ul) = and(res, this, bX, bY, bZ)
    fun and(b: Vec3ul, res: Vec3ul) = and(res, this, b.x, b.y, b.z)


    infix fun or(b: Ulong) = or(Vec3ul(), this, b, b, b)
    infix fun or(b: Long) = or(Vec3ul(), this, b, b, b)
    fun or(bX: Ulong, bY: Ulong, bZ: Ulong) = or(Vec3ul(), this, bX, bY, bZ)
    fun or(bX: Long, bY: Long, bZ: Long) = or(Vec3ul(), this, bX, bY, bZ)
    fun or(b: Vec3ul) = or(Vec3ul(), this, b.x, b.y, b.z)

    infix fun or_(b: Ulong) = or(this, this, b, b, b)
    infix fun or_(b: Long) = or(this, this, b, b, b)
    fun or_(bX: Ulong, bY: Ulong, bZ: Ulong) = or(this, this, bX, bY, bZ)
    fun or_(bX: Long, bY: Long, bZ: Long) = or(this, this, bX, bY, bZ)
    infix fun or_(b: Vec3ul) = or(this, this, b.x, b.y, b.z)

    fun or(b: Ulong, res: Vec3ul) = or(res, this, b, b, b)
    fun or(b: Long, res: Vec3ul) = or(res, this, b, b, b)
    fun or(bX: Ulong, bY: Ulong, bZ: Ulong, res: Vec3ul) = or(res, this, bX, bY, bZ)
    fun or(bX: Long, bY: Long, bZ: Long, res: Vec3ul) = or(res, this, bX, bY, bZ)
    fun or(b: Vec3ul, res: Vec3ul) = or(res, this, b.x, b.y, b.z)


    infix fun xor(b: Ulong) = xor(Vec3ul(), this, b, b, b)
    infix fun xor(b: Long) = xor(Vec3ul(), this, b, b, b)
    fun xor(bX: Ulong, bY: Ulong, bZ: Ulong) = xor(Vec3ul(), this, bX, bY, bZ)
    fun xor(bX: Long, bY: Long, bZ: Long) = xor(Vec3ul(), this, bX, bY, bZ)
    fun xor(b: Vec3ul) = xor(Vec3ul(), this, b.x, b.y, b.z)

    infix fun xor_(b: Ulong) = xor(this, this, b, b, b)
    infix fun xor_(b: Long) = xor(this, this, b, b, b)
    fun xor_(bX: Ulong, bY: Ulong, bZ: Ulong) = xor(this, this, bX, bY, bZ)
    fun xor_(bX: Long, bY: Long, bZ: Long) = xor(this, this, bX, bY, bZ)
    infix fun xor_(b: Vec3ul) = xor(this, this, b.x, b.y, b.z)

    fun xor(b: Ulong, res: Vec3ul) = xor(res, this, b, b, b)
    fun xor(b: Long, res: Vec3ul) = xor(res, this, b, b, b)
    fun xor(bX: Ulong, bY: Ulong, bZ: Ulong, res: Vec3ul) = xor(res, this, bX, bY, bZ)
    fun xor(bX: Long, bY: Long, bZ: Long, res: Vec3ul) = xor(res, this, bX, bY, bZ)
    fun xor(b: Vec3ul, res: Vec3ul) = xor(res, this, b.x, b.y, b.z)


    infix fun shl(b: Int) = shl(Vec3ul(), this, b, b, b)
    fun shl(bX: Int, bY: Int, bZ: Int) = shl(Vec3ul(), this, bX, bY, bZ)

    infix fun shl_(b: Int) = shl(this, this, b, b, b)
    fun shl_(bX: Int, bY: Int, bZ: Int) = shl(this, this, bX, bY, bZ)

    fun shl(b: Int, res: Vec3ul) = shl(res, this, b, b, b)
    fun shl(bX: Int, bY: Int, bZ: Int, res: Vec3ul) = shl(res, this, bX, bY, bZ)


    infix fun shr(b: Int) = shr(Vec3ul(), this, b, b, b)
    fun shr(bX: Int, bY: Int, bZ: Int) = shr(Vec3ul(), this, bX, bY, bZ)

    infix fun shr_(b: Int) = shr(this, this, b, b, b)
    fun shr_(bX: Int, bY: Int, bZ: Int) = shr(this, this, bX, bY, bZ)

    fun shr(b: Int, res: Vec3ul) = shr(res, this, b, b, b)
    fun shr(bX: Int, bY: Int, bZ: Int, res: Vec3ul) = shr(res, this, bX, bY, bZ)


    fun inv(res: Vec3ul = Vec3ul()) = inv(res, this)
    fun inv_() = inv(this, this)


    // -- Generic bitwise operators --

    infix fun and(b: Number) = and(Vec3ul(), this, b.L, b.L, b.L)
    fun and(bX: Number, bY: Number, bZ: Number) = and(Vec3ul(), this, bX.L, bY.L, bZ.L)
    fun and(b: Vec3t<Number>) = and(Vec3ul(), this, b.x.L, b.y.L, b.z.L)

    infix fun and_(b: Number) = and(this, this, b.L, b.L, b.L)
    fun and_(bX: Number, bY: Number, bZ: Number) = and(this, this, bX.L, bY.L, bZ.L)
    infix fun and_(b: Vec3t<Number>) = and(this, this, b.x.L, b.y.L, b.z.L)

    fun and(b: Number, res: Vec3ul) = and(res, this, b.L, b.L, b.L)
    fun and(bX: Number, bY: Number, bZ: Number, res: Vec3ul) = and(res, this, bX.L, bY.L, bZ.L)
    fun and(b: Vec3t<Number>, res: Vec3ul) = and(res, this, b.x.L, b.y.L, b.z.L)


    infix fun or(b: Number) = or(Vec3ul(), this, b.L, b.L, b.L)
    fun or(bX: Number, bY: Number, bZ: Number) = or(Vec3ul(), this, bX.L, bY.L, bZ.L)
    fun or(b: Vec3t<Number>) = or(Vec3ul(), this, b.x.L, b.y.L, b.z.L)

    infix fun or_(b: Number) = or(this, this, b.L, b.L, b.L)
    fun or_(bX: Number, bY: Number, bZ: Number) = or(this, this, bX.L, bY.L, bZ.L)
    infix fun or_(b: Vec3t<Number>) = or(this, this, b.x.L, b.y.L, b.z.L)

    fun or(b: Number, res: Vec3ul) = or(res, this, b.L, b.L, b.L)
    fun or(bX: Number, bY: Number, bZ: Number, res: Vec3ul) = or(res, this, bX.L, bY.L, bZ.L)
    fun or(b: Vec3t<Number>, res: Vec3ul) = or(res, this, b.x.L, b.y.L, b.z.L)


    infix fun xor(b: Number) = xor(Vec3ul(), this, b.L, b.L, b.L)
    fun xor(bX: Number, bY: Number, bZ: Number) = xor(Vec3ul(), this, bX.L, bY.L, bZ.L)
    fun xor(b: Vec3t<Number>) = xor(Vec3ul(), this, b.x.L, b.y.L, b.z.L)

    infix fun xor_(b: Number) = xor(this, this, b.L, b.L, b.L)
    fun xor_(bX: Number, bY: Number, bZ: Number) = xor(this, this, bX.L, bY.L, bZ.L)
    infix fun xor_(b: Vec3t<Number>) = xor(this, this, b.x.L, b.y.L, b.z.L)

    fun xor(b: Number, res: Vec3ul) = xor(res, this, b.L, b.L, b.L)
    fun xor(bX: Number, bY: Number, bZ: Number, res: Vec3ul) = xor(res, this, bX.L, bY.L, bZ.L)
    fun xor(b: Vec3t<Number>, res: Vec3ul) = xor(res, this, b.x.L, b.y.L, b.z.L)


    infix fun shl(b: Number) = shl(Vec3ul(), this, b.L, b.L, b.L)
    fun shl(bX: Number, bY: Number, bZ: Number) = shl(Vec3ul(), this, bX.L, bY.L, bZ.L)

    infix fun shl_(b: Number) = shl(this, this, b.L, b.L, b.L)
    fun shl_(bX: Number, bY: Number, bZ: Number) = shl(this, this, bX.L, bY.L, bZ.L)

    fun shl(b: Number, res: Vec3ul) = shl(res, this, b.L, b.L, b.L)
    fun shl(bX: Number, bY: Number, bZ: Number, res: Vec3ul) = shl(res, this, bX.L, bY.L, bZ.L)


    infix fun shr(b: Number) = shr(Vec3ul(), this, b.L, b.L, b.L)
    fun shr(bX: Number, bY: Number, bZ: Number) = shr(Vec3ul(), this, bX.L, bY.L, bZ.L)

    infix fun shr_(b: Number) = shr(this, this, b.L, b.L, b.L)
    fun shr_(bX: Number, bY: Number, bZ: Number) = shr(this, this, bX.L, bY.L, bZ.L)

    fun shr(b: Number, res: Vec3ul) = shr(res, this, b.L, b.L, b.L)
    fun shr(bX: Number, bY: Number, bZ: Number, res: Vec3ul) = shr(res, this, bX.L, bY.L, bZ.L)
}