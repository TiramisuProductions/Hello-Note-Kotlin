package hellonote.hellonotekotlin.database

import com.orm.SugarRecord

class Task : SugarRecord<Task>
{
     var taskText: String? = null;
     var noteId: String? = null;
     var isDone: Boolean = false
     var noteTimeStamp: String? = null;

    constructor() {}
    constructor(taskText: String?, noteId: String?, isDone: Boolean, noteTimeStamp: String?) : super() {
        this.taskText = taskText
        this.noteId = noteId
        this.isDone = isDone
        this.noteTimeStamp = noteTimeStamp
    }




}