package com.example.coding_exercise.domain.contact.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "contact")
class Contact() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "full_name", nullable = false)
    var fullName: String? = null

    @Column(name = "birth_date", nullable = false)
    var birthDate: Date? = null

    @Embedded
    var address: Address? = null

    @Column(name = "created_time", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    var createdTime: Date? = null

    @Column(name = "updated_time", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    var updatedTime: Date? = null

    constructor(fullName: String, birthDate: Date, address: Address) : this() {
        this.fullName = fullName
        this.birthDate = birthDate
        this.address = address
    }

    companion object {
        private val serialVersionUID = -5560117521231231232L
    }
}
