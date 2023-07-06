import java.util.*

class Archive(val nameOfArchive: String) {
    var mapOfNotes: MutableMap<Int, Notes> = HashMap()
    fun addNewNotes() {
        var keysMax = 1//НАЧАЛО НУМЕРАЦИИ КЛЮЧЕЙ В MAP
        var userInputNameOfNote = ""//ПОЛЬЗОВАТЕЛЬСКИЙ ВВОД ИМЕНИ ЗАМЕТКИ
        var userInputTextOfNote = ""//ПОЛЬЗОВАТЕЛЬСКИЙ ВВОД ТЕКСТА ЗАМЕТКИ
        println("Введите имя для вашей заметки")
        while (true) {
            userInputNameOfNote = readln()//ЧИТАЕМ СТРОКУ
            if (!userInputNameOfNote.equals("")) {
                for (keys in mapOfNotes.keys) {//ПЕРЕБИРАЕМ ВСЕ ИМЕЮЩИЕСЯ КЛЮЧИ
                    keysMax = keys//ПОЛУЧАЕМ КРАЙНИЙ ИЗ НИХ
                }
                ++keysMax//УВЕЛИЧИВАЕМ ЗНАЧЕНИЕ НА 1, ЧТОБЫ ПРИСВОИТЬ СЛЕДУЮЩИЙ ПОРЯДКОВЫЙ НОМЕР КЛЮЧУ
                println("Введите текст заметки")
                userInputTextOfNote = readln()//ЧИТАЕМ СТРОКУ
                if (!userInputTextOfNote.equals("")) {
                    mapOfNotes[keysMax] = Notes(
                        userInputNameOfNote,
                        userInputTextOfNote
                    )//PUT ДЛЯ MAP ЗАМЕТОК, СО СЛЕДУЮЩИМ ПО ПОРЯДКУ КЛЮЧОМ
                    startProgramm.startMenu(this)//ВОЗВРАЩАЕМСЯ К МЕНЮ ВЫБОРА АРХИВА
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
        if (mapOfNotes.contains(numberOfNote)) {
            val currentNote =
                mapOfNotes.get(numberOfNote)//ВЫБИРЕМ ПО КЛЮЧУ - ПЕРЕДАННОЙ ЦИФРЕ ИЗ checkArchivMenuPoint
            println("===Выбрана заметка: ${currentNote!!.nameOfNote}===")//ПОЛУЧАЕМ ИМЯ ЗАМЕТКИ
            println("+++Текст заметки+++")
            println("${currentNote.textOfNote}")//ПОЛУЧАЕМ ТЕКСТ ЗАМЕТКИ
        } else {
            println("Такого пункта меню или заметки не существует!")
        }
    }
}