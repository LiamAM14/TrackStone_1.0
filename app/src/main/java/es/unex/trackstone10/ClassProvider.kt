package es.unex.trackstone10

class ClassProvider {
    companion object {
        val classMap = mapOf<String, ClassHS>(
            "DH" to ClassHS(
                "demonhunter",
                14,
                "Demon Hunter",
                56550,
                60224,
                arrayOf(60238, 32491, 64697, 71077, 71078, 71079),
                R.drawable.demonhunter
            ),
            "Druid" to ClassHS(
                "druid",
                2,
                "Druid",
                274,
                1123,
                arrayOf(50484, 56358, 57761, 60375, 64698),
                R.drawable.druid
            ),
            "Hunt" to ClassHS(
                "hunter",
                3,
                "Hunter",
                31,
                0,
                arrayOf(2826, 60335, 61597, 64699, 71080),
                R.drawable.hunter
            ),
            "Mage" to ClassHS(
                "mage",
                4,
                "Mage",
                637,
                807,
                arrayOf(2829, 39117, 57765, 61598, 62772, 64844, 64845, 64846, 64847),
                R.drawable.mage
            ),
            "Paladin" to ClassHS(
                "paladin",
                5,
                "Paladin",
                671,
                472,
                arrayOf(2827, 46116, 53187, 57757, 61596, 61886, 64732, 67519),
                R.drawable.paladin
            ),
            "Priest" to ClassHS(
                "priest",
                6,
                "Priest",
                813,
                479,
                arrayOf(41887, 54816, 57767, 64733, 67523, 67710, 71074, 71075, 71076),
                R.drawable.priest
            ),
            "Rogue" to ClassHS(
                "rogue",
                7,
                "Rogue",
                930,
                730,
                arrayOf(40195, 57755, 64734, 67522, 67846),
                R.drawable.rogue
            ),
            "Shaman" to ClassHS(
                "shaman",
                8,
                "Shaman",
                1066,
                687,
                arrayOf(40183, 53237, 55963, 57753, 60673, 64736, 64848, 64849, 64850, 71058),
                R.drawable.rogue
            ),
            "Warlock" to ClassHS(
                "warlock",
                9,
                "Warlock",
                893,
                300,
                arrayOf(47817, 51834, 57763, 64685, 64738),
                R.drawable.warlock
            ),
            "Warrior" to ClassHS(
                "warrior",
                10,
                "Warrior",
                7,
                725,
                arrayOf(2828, 57751, 58787, 61595, 61923, 64739, 67521, 71071, 71072, 71073),
                R.drawable.warrior
            )
        )
    }
}