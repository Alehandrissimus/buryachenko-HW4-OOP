package com.example.buryachenko_hw4

fun main() {
    val person1 = Person("Astolf", 76)
    val person2 = Person("Grun", 78)
    val person3 = Person("Lambo", 66)
    val person4 = Person("Genry", 64)
    val person5 = Person("Adele", 54, mother = person1, father = person2)
    val person6 = Person("Ole", 49, mother = person3, father = person4)
    val person7 = Person("Idelia", 20, mother = person5, father = person6)
    val person8 = Person("Gronda", 15, mother = person5, father = person6)
    val person9 = Person("Grapf", 11, mother = person5, father = person6)
    val person10 = Person("Treng", 25, mother = person5, father = person6)
    val person11 = Person("Asders", 17, mother = person5, father = person6)
    val person12 = Person("Loing", 24, mother = person5, father = person6)
    val person13 = Person("Ltings", 2, mother = person11, father = person12)

    person1.children = listOf(person5)
    person2.children = listOf(person5)
    person3.children = listOf(person5)
    person4.children = listOf(person5)
    person5.children = listOf(person7, person8, person9, person10, person11, person12)
    person6.children = listOf(person7, person8, person9, person10, person11, person12)
    person11.children = listOf(person13)
    person12.children = listOf(person13)

    println(person13.getFamiliarCount())
}

class Person(
    val name: String,
    val age: Int,
    val isAdult: Boolean = age >= 18,
    val mother: Person? = null,
    val father: Person? = null,
    var children: List<Person> = ArrayList(),
    var marked: Boolean = false
) {
    fun getFamiliarCount(): Int {
        val set: HashSet<Person> = hashSetOf()
        set.add(this)
        set.addAll(this.getDirectFamiliars())

        return set.size
    }

    private fun getDirectFamiliars(): HashSet<Person> {
        val set: HashSet<Person> = hashSetOf()
        if(marked) {
            return set
        }
        this.marked = true

        set.add(this)
        mother?.let { set.addAll(it.getDirectFamiliars()) }
        father?.let { set.addAll(it.getDirectFamiliars()) }
        children.forEach { set.addAll(it.getDirectFamiliars()) }

        this.marked = true
        return set
    }
}