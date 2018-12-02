package hellonote.hellonotekotlin.database

import com.orm.SugarRecord

class Email : SugarRecord<Email>
{
     var name: String? = null;
     var emailId: String? = null;
     var calledNumber: String?= null;
     var calledName: String? = null;
     var incoming: Boolean = false;
     var tsMilli: String? = null;
     var isSavedFromApp: Boolean = false
     var isBackedUp: Boolean = false


    constructor() {}

    constructor(
        name: String?,
        emailId: String?,
        calledNumber: String?,
        calledName: String?,
        incoming: Boolean,
        tsMilli: String?,
        isSavedFromApp: Boolean,
        isBackedUp: Boolean
    ) : super() {
        this.name = name
        this.emailId = emailId
        this.calledNumber = calledNumber
        this.calledName = calledName
        this.incoming = incoming
        this.tsMilli = tsMilli
        this.isSavedFromApp = isSavedFromApp
        this.isBackedUp = isBackedUp
    }

    constructor(
        name: String?,
        emailId: String?,
        calledNumber: String?,
        calledName: String?,
        incoming: Boolean,
        tsMilli: String?
    ) : super() {
        this.name = name
        this.emailId = emailId
        this.calledNumber = calledNumber
        this.calledName = calledName
        this.incoming = incoming
        this.tsMilli = tsMilli
    }

    constructor(
        name: String?,
        emailId: String?,
        tsMilli: String?,
        isSavedFromApp: Boolean,
        isBackedUp: Boolean
    ) : super() {
        this.name = name
        this.emailId = emailId
        this.tsMilli = tsMilli
        this.isSavedFromApp = isSavedFromApp
        this.isBackedUp = isBackedUp
    }


}