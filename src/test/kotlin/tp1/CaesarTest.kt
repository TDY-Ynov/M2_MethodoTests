package tp1

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.char.shouldBeInRange
import io.kotest.matchers.shouldBe

class CaesarTest : FunSpec({

    test("should success Caesar algorithm") {
        Caesar.cypher('A', 2) shouldBe 'C'
        Caesar.cypher('A', 5) shouldBe 'F'
        Caesar.cypher('Z', 1) shouldBe 'A'
        Caesar.cypher('A', 27) shouldBe 'B'
    }


    test("should fail on lowercase") {
        val exception = shouldThrow<IllegalArgumentException> {
            Caesar.cypher('a', 2) shouldBe 'B'
        }
        exception.message shouldBe ("Character must be a capital letter [A-Z]")
    }


    test("should fail on negative key") {
        val exception = shouldThrow<IllegalArgumentException> {
            Caesar.cypher('A', -2) shouldBe 'B'
        }
        exception.message shouldBe ("Key must be a positive or null number")
    }

    ('A'..'Z').forEach {
        test("should return same char ($it) when cypher key is 0") {
            Caesar.cypher(it, 0) shouldBe it
        }
    }

    (0..100).forEach {
        test("should return a capital letter, whatever the cypher key value is ($it)") {
            Caesar.cypher('A', it) shouldBeInRange 'A'..'Z'
        }
    }

})
