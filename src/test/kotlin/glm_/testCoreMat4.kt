package glm_

import glm_.mat4x4.Mat4
import glm_.mat4x4.Mat4d
import glm_.mat4x4.operators.times
import glm_.vec4.Vec4
import glm_.vec4.Vec4d
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

/**
 * Created by GBarbieri on 12.12.2016.
 */

class testCoreMat4 : StringSpec() {

    init {

        "inverse mat4" {

            val matrix = Mat4(
                    0.6f, 0.2f, 0.3f, 0.4f,
                    0.2f, 0.7f, 0.5f, 0.3f,
                    0.3f, 0.5f, 0.7f, 0.2f,
                    0.4f, 0.3f, 0.2f, 0.6f)
            val inverse = matrix.inverse()
            val identity = matrix * inverse

            glm.all(glm.epsilonEqual(identity[0], Vec4(1f, 0f, 0f, 0f), Vec4(0.01f))) shouldBe true
            glm.all(glm.epsilonEqual(identity[1], Vec4(0f, 1f, 0f, 0f), Vec4(0.01f))) shouldBe true
            glm.all(glm.epsilonEqual(identity[2], Vec4(0f, 0f, 1f, 0f), Vec4(0.01f))) shouldBe true
            glm.all(glm.epsilonEqual(identity[3], Vec4(0f, 0f, 0f, 1f), Vec4(0.01f))) shouldBe true
        }

        "inverse mat4d" { val matrix = Mat4d(
                    0.6, 0.2, 0.3, 0.4,
                    0.2, 0.7, 0.5, 0.3,
                    0.3, 0.5, 0.7, 0.2,
                    0.4, 0.3, 0.2, 0.6)
            val identity = matrix / matrix

            glm.all(glm.epsilonEqual(identity[0], Vec4d(1.0, 0.0, 0.0, 0.0), Vec4d(0.01))) shouldBe true
            glm.all(glm.epsilonEqual(identity[1], Vec4d(0.0, 1.0, 0.0, 0.0), Vec4d(0.01))) shouldBe true
            glm.all(glm.epsilonEqual(identity[2], Vec4d(0.0, 0.0, 1.0, 0.0), Vec4d(0.01))) shouldBe true
            glm.all(glm.epsilonEqual(identity[3], Vec4d(0.0, 0.0, 0.0, 1.0), Vec4d(0.01))) shouldBe true
        }

        "test operators" {

            val l = Mat4(1f)
            val m = Mat4(1f)
            val u = Vec4(1f)
            val v = Vec4(1f)
            val x = 1f
            val a = m * u
            val b = v * m
            val p = x * m
            val q = m * x
            val r = m != q
            val s = m == l

            (s && !r) shouldBe true
        }

        "rotate" {

            val rotate = glm.rotate(Mat4(), 1f, 2f, 3f, 4f)

            glm.all(glm.epsilonEqual(rotate[0], Vec4(x = 0.60370886, y = 0.7201388, z = -0.34195852, w = 0.0), Vec4(0.01f))) shouldBe true
            glm.all(glm.epsilonEqual(rotate[1], Vec4(x = -0.529919, y = 0.68296707, z = 0.5027342, w = 0.0), Vec4(0.01f))) shouldBe true
            glm.all(glm.epsilonEqual(rotate[2], Vec4(x = 0.59558487, y = -0.12229471, z = 0.7939286, w = 0.0), Vec4(0.01f))) shouldBe true
            glm.all(glm.epsilonEqual(rotate[3], Vec4(x = 0.0, y = 0.0, z = 0.0, w = 1.0), Vec4(0.01f))) shouldBe true
        }

//        namespace cast TODO
//                {
//                    template<typename genType>
//                    int entry()
//                    {
//                        int Error = 0;
//
//                        genType A(1.0f);
//                        glm::mat4x4 B(A);
//                        glm::mat4x4 Identity(1.0f);
//
//                        for(glm::length_t i = 0, length = B.length(); i < length; ++i)
//                        Error += glm::all(glm::epsilonEqual(B[i], Identity[i], glm::epsilon<float>())) ? 0 : 1;
//
//                        return Error;
//                    }
//
//                    int test()
//                    {
//                        int Error = 0;
//
//                        Error += entry<glm::mat2x2>();
//                        Error += entry<glm::mat2x3>();
//                        Error += entry<glm::mat2x4>();
//                        Error += entry<glm::mat3x2>();
//                        Error += entry<glm::mat3x3>();
//                        Error += entry<glm::mat3x4>();
//                        Error += entry<glm::mat4x2>();
//                        Error += entry<glm::mat4x3>();
//                        Error += entry<glm::mat4x4>();
//
//                        return Error;
//                    }
//                }
    }
}