package hellonote.hellonotekotlin.database

import com.orm.SugarRecord

class CallRecord : SugarRecord<CallRecord>{
     var RecordName: String? = null;
     var RecordPath: String? = null;
     var calledNumber: String? = null;
     var calledName: String? = null;
     var incoming: Boolean = false
     var tsMilli: String? = null;

    constructor(){}
    constructor(
        RecordName: String?,
        RecordPath: String?,
        calledNumber: String?,
        calledName: String?,
        incoming: Boolean,
        tsMilli: String?
    ) : super() {
        this.RecordName = RecordName
        this.RecordPath = RecordPath
        this.calledNumber = calledNumber
        this.calledName = calledName
        this.incoming = incoming
        this.tsMilli = tsMilli
    }


}