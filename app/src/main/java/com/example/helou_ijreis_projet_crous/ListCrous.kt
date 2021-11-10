package com.example.helou_ijreis_projet_crous

class ListCrous {
    private val crousMap = HashMap<String, Crous>()

    fun addCrous(aCrous: Crous) {
        crousMap[aCrous.id] = aCrous
    }

    fun getCrous(id: String): Crous? {
        return crousMap[id]
    }

    fun getAllCrous(): ArrayList<Crous> {
        return ArrayList(crousMap.values.sortedBy { it.title })

    }

    fun setFavorite(id: String) {
        crousMap[id]?.favorite = !crousMap[id]?.favorite!!
    }
    fun getCrousByType(type: String): List<Crous> {
        return crousMap
            .filterValues { it.type == type }
            .values
            .sortedBy { it.title }
            .toList()
    }

    fun getTotalNumberOfCrous(): Int {
        return crousMap.size;
    }

    fun clear() {
        crousMap.clear()
    }

}