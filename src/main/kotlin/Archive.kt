class Archive(val nameOfArchive: String) {
    var notes: MutableList<Notes> = mutableListOf()
    fun addNewNotes() {
        var userInputNameOfNote = ""//ПОЛЬЗОВАТЕЛЬСКИЙ ВВОД ИМЕНИ ЗАМЕТКИ
        var userInputTextOfNote = ""//ПОЛЬЗОВАТЕЛЬСКИЙ ВВОД ТЕКСТА ЗАМЕТКИ
        println("Введите имя для вашей заметки")
        while (true) {
            userInputNameOfNote = readln()//ЧИТАЕМ СТРОКУ
            if (userInputNameOfNote != "") {
                println("Введите текст заметки")
                userInputTextOfNote = readln()//ЧИТАЕМ СТРОКУ
                if (userInputTextOfNote != "") {
                    notes.add(
                        Notes(
                            userInputNameOfNote,
                            userInputTextOfNote
                        )
                    )
                    Singlton.startProgramm.startMenu(this)//ВОЗВРАЩАЕМСЯ К МЕНЮ ВЫБОРА АРХИВА
                    break
                } else {
                    println("Вы ничего не ввели. Попробуйте еще!")
                    userInputTextOfNote = readln()
                }
            } else {
                println("Вы ничего не ввели или ввели цифру. Попробуйте еще!")
                userInputNameOfNote = readln()
            }
        }
    }

    fun showTextOfNote(numberOfNote: Int) {
        try {
            val currNote =
                notes[numberOfNote - 2]//-2, тк помним про строку 19, где в выводе +2 из-за значений по-умолчанию
            println("===Выбрана заметка: ${currNote.nameOfNote}===")//ПОЛУЧАЕМ ИМЯ ЗАМЕТКИ
            println("+++Текст заметки+++")
            println("${currNote.textOfNote}")
        } catch (e: java.lang.IndexOutOfBoundsException) {
            println("Такого пункта меню или заметки не существует!")
        }
    }
}