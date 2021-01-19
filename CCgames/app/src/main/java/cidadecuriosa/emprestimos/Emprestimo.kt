package cidadecuriosa.emprestimos

import java.util.*

class Emprestimo {
    var itemId      : String? = null
    var name   : String? = null
    var requesitante   : String? = null
    var email    : String? = null
    var mobilePhone : String? = null
    var date   : Date? = null
    var dateMax : Date? = null

    constructor(
        itemId: String?,
        name: String?,
        requesitante: String?,
        email: String?,
        mobilePhone: String?,
        date: Date?,
        dateMax: Date?
    ) {

        this.itemId = itemId
        this.name = name
        this.requesitante = requesitante
        this.email = email
        this.mobilePhone = mobilePhone
        this.date = date
        this.dateMax = dateMax

    }

    constructor(
            name: String?,
            requesitante: String?,
            email: String?,
            mobilePhone: String?,
            date: Date?,
            dateMax: Date?
    ) {
        this.name = name
        this.requesitante = requesitante
        this.email = email
        this.mobilePhone = mobilePhone
        this.date = date
        this.dateMax = dateMax
    }

}