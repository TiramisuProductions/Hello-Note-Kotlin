package hellonote.hellonotekotlin.database

import com.orm.SugarRecord

public class Note : SugarRecord<Note>
{
     var note: String? = null;
     var calledName: String? = null;
     var calledNumber: String? = null;
     var tsMilli: String? = null;
     var incoming: Boolean = false
     var isSavedFromApp: Boolean = false
     var isBackedUp: Boolean = false


    constructor() {}


    constructor(
        note: String?,
        calledName: String?,
        calledNumber: String?,
        tsMilli: String?,
        incoming: Boolean,
        isSavedFromApp: Boolean,
        isBackedUp: Boolean
    ) : super() {
        this.note = note
        this.calledName = calledName
        this.calledNumber = calledNumber
        this.tsMilli = tsMilli
        this.incoming = incoming
        this.isSavedFromApp = isSavedFromApp
        this.isBackedUp = isBackedUp
    }

    constructor(
        note: String?,
        calledName: String?,
        calledNumber: String?,
        tsMilli: String?,
        incoming: Boolean
    ) : super() {
        this.note = note
        this.calledName = calledName
        this.calledNumber = calledNumber
        this.tsMilli = tsMilli
        this.incoming = incoming
    }

    constructor(note: String?, tsMilli: String?, isSavedFromApp: Boolean, isBackedUp: Boolean) : super() {
        this.note = note
        this.tsMilli = tsMilli
        this.isSavedFromApp = isSavedFromApp
        this.isBackedUp = isBackedUp
    }




}