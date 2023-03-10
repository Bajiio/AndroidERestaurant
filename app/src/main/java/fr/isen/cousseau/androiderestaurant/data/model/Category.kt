package fr.isen.cousseau.androiderestaurant.data.model

data class Category(var name_fr: String,
                    var name_en: String,
                    var items: Array<Plat>): java.io.Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (name_fr != other.name_fr) return false
        if (name_en != other.name_en) return false
        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name_fr.hashCode()
        result = 31 * result + name_en.hashCode()
        result = 31 * result + items.contentHashCode()
        return result
    }
}