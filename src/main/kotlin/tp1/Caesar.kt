package tp1

class Caesar {
    companion object {
        fun cypher(character: Char, key: Int): Char {
            require(character in 'A'..'Z') {"Character must be a capital letter [A-Z]"}
            require(key>=0) {"Key must be a positive or null number"}
            return (character+key).code
                .minus(65)
                .mod(26)
                .plus(65)
                .toChar()

        }
    }
}
