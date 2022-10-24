package es.unex.trackstone10

class CardProvider {
    companion object{
        val cardsList = listOf<Cards>(Cards(
            52119,
            "52119-arch-villain-rafaam",
            null,
            "Arch-Villain Rafaam",
            4,
            7,
            7,
            8,
            5,
            1130,
            "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/6d36d48478cd253ada632922cc29cd6fece44dd5df5554837eeed2bc978f526d.png",
            9,
            "<b><b>Taunt</b> Battlecry:</b> Replace your hand and deck with <b>Legendary</b> minions.",
            "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/88803effaa821c7d76f5f0c3b1fd044ba62a884dcc207cb318d32583986fe9a6.png",
            arrayOf(1,8))
            ,
            Cards(
                60003,
                "60003-ace-hunter-keen",
                arrayOf(14,3),
                "Ace Hunter Keen",
                4,
                3,
                4,
                2,
                5,
                1443,
                "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/69c8d0969e25e0318eff0d9fec92b284e0037a99453d0ad99f2a92c6cd25a5b2.png",
                14,
                "Your other characters are <b>Immune</b> while attacking.",
                "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/7e3d10913c2c95ac813aa30f68d18317503d306f941ef3cdcd76a7ee0cbdb094.png",
                arrayOf(17)
            )
        )
    }
}