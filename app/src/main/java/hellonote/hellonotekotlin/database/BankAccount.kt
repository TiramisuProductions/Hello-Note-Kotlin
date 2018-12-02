package hellonote.hellonotekotlin.database

import com.orm.SugarRecord

class BankAccount
 : SugarRecord<BankAccount> {

    var name : String? = null
    var accountNo : String? = null
     var ifscCode: String?=null
     var calledNumber: String? = null
     var calledName: String? = null
     var incoming: Boolean = false
     var tsMilli: String? = null
     var isSavedFromApp: Boolean = false
    var isBackedUp: Boolean = false


    constructor() {}

    constructor(
        name: String?,
        accountNo: String?,
        ifscCode: String?,
        tsMilli: String?,
        isSavedFromApp: Boolean,
        isBackedUp: Boolean
    ) : super() {
        this.name = name
        this.accountNo = accountNo
        this.ifscCode = ifscCode
        this.tsMilli = tsMilli
        this.isSavedFromApp = isSavedFromApp
        this.isBackedUp = isBackedUp
    }

    constructor(
        name: String?,
        accountNo: String?,
        ifscCode: String?,
        calledNumber: String?,
        calledName: String?,
        incoming: Boolean,
        tsMilli: String?,
        isBackedUp: Boolean
    ) : super() {
        this.name = name
        this.accountNo = accountNo
        this.ifscCode = ifscCode
        this.calledNumber = calledNumber
        this.calledName = calledName
        this.incoming = incoming
        this.tsMilli = tsMilli
        this.isBackedUp = isBackedUp
    }

    constructor(
        name: String?,
        accountNo: String?,
        ifscCode: String?,
        calledNumber: String?,
        calledName: String?,
        incoming: Boolean,
        tsMilli: String?,
        isSavedFromApp: Boolean,
        isBackedUp: Boolean
    ) : super() {
        this.name = name
        this.accountNo = accountNo
        this.ifscCode = ifscCode
        this.calledNumber = calledNumber
        this.calledName = calledName
        this.incoming = incoming
        this.tsMilli = tsMilli
        this.isSavedFromApp = isSavedFromApp
        this.isBackedUp = isBackedUp
    }


}

