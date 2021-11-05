package com.example.helou_ijreis_projet_crous

class ListCrous {
    private val crousMap = HashMap<String, Crous>()

    fun addCrous(aCrous: Crous) {
        crousMap[aCrous.name] = aCrous
    }

    fun getCrous(name: String): Crous? {
        return crousMap[name]
    }

    fun getAllCrous(): ArrayList<Crous> {
        return ArrayList(crousMap.values.sortedBy { it.name })

    }

    fun getCrousByType(type: String): List<Crous> {
        return crousMap
            .filterValues { it.type == type }
            .values
            .sortedBy { it.type }
            .toList()
    }

    fun getTotalNumberOfCrous(): Int {
        return crousMap.size;
    }

    fun clear() {
        crousMap.clear()
    }

}