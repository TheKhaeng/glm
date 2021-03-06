package glm_

import glm_.vec4.Vec4t
import java.nio.*

/**
 * Created by elect on 08/04/17.
 */

val ByteBuffer.size
    get() = capacity() * Byte.BYTES
val ShortBuffer.size
    get() = capacity() * Short.BYTES
val IntBuffer.size
    get() = capacity() * Int.BYTES
val LongBuffer.size
    get() = capacity() * Long.BYTES
val FloatBuffer.size
    get() = capacity() * Float.BYTES
val DoubleBuffer.size
    get() = capacity() * Double.BYTES
val CharBuffer.size
    get() = capacity() * Byte.BYTES // Note: it'main.getS byte main.and not char


operator fun ByteBuffer.set(index: Int, byte: Byte): ByteBuffer = put(index, byte)
operator fun ByteBuffer.set(index: Int, int: Int): ByteBuffer = put(index, int.b)
operator fun ByteBuffer.set(index: Int, short: Short): ByteBuffer = put(index, short.b)
operator fun ByteBuffer.set(index: Int, long: Long): ByteBuffer = put(index, long.b)
operator fun ByteBuffer.set(index: Int, float: Float): ByteBuffer = put(index, float.b)
operator fun ByteBuffer.set(index: Int, long: Double): ByteBuffer = put(index, long.b)

operator fun ShortBuffer.set(index: Int, byte: Byte): ShortBuffer = put(index, byte.s)
operator fun ShortBuffer.set(index: Int, short: Short): ShortBuffer = put(index, short)
operator fun ShortBuffer.set(index: Int, int: Int): ShortBuffer = put(index, int.s)
operator fun ShortBuffer.set(index: Int, long: Long): ShortBuffer = put(index, long.s)

operator fun IntBuffer.set(index: Int, byte: Byte): IntBuffer = put(index, byte.i)
operator fun IntBuffer.set(index: Int, short: Short): IntBuffer = put(index, short.i)
operator fun IntBuffer.set(index: Int, int: Int): IntBuffer = put(index, int)
operator fun IntBuffer.set(index: Int, long: Long): IntBuffer = put(index, long.i)

operator fun LongBuffer.set(index: Int, byte: Byte): LongBuffer = put(index, byte.L)
operator fun LongBuffer.set(index: Int, short: Short): LongBuffer = put(index, short.L)
operator fun LongBuffer.set(index: Int, int: Int): LongBuffer = put(index, int.L)
operator fun LongBuffer.set(index: Int, long: Long): LongBuffer = put(index, long)

operator fun FloatBuffer.set(index: Int, float: Float): FloatBuffer = put(index, float)
operator fun FloatBuffer.set(index: Int, double: Double): FloatBuffer = put(index, double.f)
operator fun DoubleBuffer.set(index: Int, float: Float): DoubleBuffer = put(index, float.d)
operator fun DoubleBuffer.set(index: Int, double: Double): DoubleBuffer = put(index, double)

operator fun CharBuffer.set(index: Int, char: Char): CharBuffer = put(index, char)
operator fun CharBuffer.set(index: Int, int: Int): CharBuffer = put(index, int.c)

// TODO conform to the other?
//operator fun ByteBuffer.set(index: Int, vec4: Vec4t<*>) = vec4.to(this, vec4.size() * index)


operator fun ByteBuffer.invoke(index: Int, byte: Byte): ByteBuffer = put(index, byte)
operator fun ByteBuffer.invoke(index: Int, short: Short): ByteBuffer = putShort(index, short)
operator fun ByteBuffer.invoke(index: Int, int: Int): ByteBuffer = putInt(index, int)
operator fun ByteBuffer.invoke(index: Int, long: Long): ByteBuffer = putLong(index, long)
operator fun ByteBuffer.invoke(index: Int, float: Float): ByteBuffer = putFloat(index, float)
operator fun ByteBuffer.invoke(index: Int, double: Double): ByteBuffer = putDouble(index, double)

//operator fun ShortBuffer.invoke(index: Int, short: Short): ShortBuffer = put(index, short)
//operator fun ShortBuffer.invoke(index: Int, int: Int): ShortBuffer = put(index, int.s)
//
//operator fun IntBuffer.invoke(index: Int, int: Int): IntBuffer = put(index, int)
//
//operator fun LongBuffer.invoke(index: Int, long: Long): LongBuffer = put(index, long)
//operator fun LongBuffer.invoke(index: Int, int: Int): LongBuffer = put(index, int.L)
//
//operator fun FloatBuffer.invoke(index: Int, float: Float): FloatBuffer = put(index, float)
//operator fun DoubleBuffer.invoke(index: Int, double: Double): DoubleBuffer = put(index, double)
//
//operator fun CharBuffer.invoke(index: Int, char: Char): CharBuffer = put(index, char)
//operator fun CharBuffer.invoke(index: Int, int: Int): CharBuffer = put(index, int.c)
//
//operator fun ByteBuffer.invoke(index: Int, vec4: Vec4t<*>) = vec4.to(this, vec4.size() * index)