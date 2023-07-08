import java.util.*
import kotlin.system.exitProcess

class LogicOfNavigation {
    private var archives: MutableList<Archive> = mutableListOf()


    fun startMenu(objectOfInput: Any) {//СТАРТ ГЛАВНОГО МЕНЮ ПРОГРАММЫ
        if (objectOfInput is LogicOfNavigation) {
            println("Выберите необходимый пункт меню или архив! Для этого введите цифру")
            println("===Главное меню===")
            println("0. Выход из программы")
            println("1. Создать архив")
            if (archives.isEmpty()) {//ПРОВЕРЯЕМ НА НЕПУСТОЙ MAP АРХИВОВ
                println("Вы не создали ни одного архива. Введите 1, чтобы это и справить! :)")
            } else {
                archives.forEachIndexed { key, value -> if (value != null) println("${key + 2}. ${value.nameOfArchive}") }//Вывод +2, тк 0 и 1 пункты заняты значением по-умолчанию
            }
            chechMenuPoint(this)//ЗАПУСКАЕМ ПРОВЕРКУ ВВОДА ПОЛЬЗОВАТЕЛЯ
        } else if (objectOfInput is Archive) {
            println("===Выбран архив: ${objectOfInput.nameOfArchive}===")
            println("0. Выход в главное меню")
            println("1. Создать заметку в архиве")
            if (objectOfInput.notes.isEmpty()) { //ПРОВЕРЯЕМ ПУСТОЙ ЛИ MAP У ЭТОГО ОБЪЕКТА
                println("Вы не создали ни одной заметки. Введите 1, чтобы это и справить! :)")
            } else { //ЕСЛИ НЕ ПУСТОЙ, ТО ВЫВОДИМ ОСТАЛЬНЫЕ ПУНКТЫ МЕНЮ - ИМЕЮЩИЕСЯ АРХИВЫ
                objectOfInput.notes.forEachIndexed { index, notes -> if (notes != null) println("${index + 2}. ${notes.nameOfNote}") }//Вывод +2, тк 0 и 1 пункты заняты значением по-умолчанию
            }
            Singlton.startProgramm.chechMenuPoint(objectOfInput)
        }
    }

    private fun chechMenuPoint(objectOfInput: Any) {
        if (objectOfInput is LogicOfNavigation) {
            try {
                val command = Scanner(System.`in`).nextInt()
                while (true) {
                    when (command) {
                        0 -> exitProcess(0)
                        1 -> {
                            addNewArchive()//ЗАПУСКАЕМ ДОБАВЛЕНИЕ АРХИВА
                            startMenu(this)
                            break
                        }
                        else -> {
                            changeOfArchiv(command)
                            startMenu(this)
                            break
                        }
                    }
                }
            } catch (e: InputMismatchException) {
                println("Похоже, вы ввели буквы, а надо цифры. Попробуйте снова)!")
                startMenu(this)
            }
        } else if (objectOfInput is Archive) {
            try {
                val command = Scanner(System.`in`).nextInt()
                while (true) {
                    when (command) {
                        0 -> Singlton.startProgramm.startMenu(this)//ВОЗВРАЩАЕТ В МЕНЮ ВЫБОРА АРХИВОВ
                        1 -> {
                            objectOfInput.addNewNotes() //ДОБАВЛЯЕМ ЗАМЕТКУ К ТЕКУЩЕМУ ЭКЗЕМПЛЯРУ
                            return
                        }
                        else -> { //ЕСЛИ НЕ 0 ИЛИ 1, ТО ЗАПУСКАЕМ ПРОЦЕДУРУ ПО ПОИСКУ ЗАМЕТОК
                            objectOfInput.showTextOfNote(command) //ПОКАЗЫВАЕТ ЗАМЕТКУ
                            startMenu(objectOfInput)
                            return
                        }
                    }
                }
            } catch (e: InputMismatchException) {
                println("Похоже, вы ввели буквы, а надо цифры. Попробуйте снова)!")
                startMenu(objectOfInput)
            }
        }
    }

    private fun addNewArchive() {
        println("Введите имя для вашего архива")
        var userInputNameOfArchive = readln()
        while (true) {
            if ((userInputNameOfArchive != "") && (userInputNameOfArchive.trim() != "")
            ) {//ЕСЛИ НЕ ПУСТАЯ СТРОКА И ПОСЛЕ ТРИМА ОНА ТОЖЕ НЕ ПУСТА, ТО ОК
                archives.add(Archive(userInputNameOfArchive))
                return
            } else {
                println("Вы ничего не ввели или ввели пробелы. Попробуйте еще!")
                userInputNameOfArchive = readln()
            }
        }
    }

    private fun changeOfArchiv(numberOfArchive: Int) {
        try {
            val currArchive =
                archives[numberOfArchive - 2]//-2, тк помним про строку 19, где в выводе +2 из-за значений по-умолчанию
            startMenu(currArchive)
        } catch (e: java.lang.IndexOutOfBoundsException) {
            println("Такого пункта меню или архива не существует!")
        }
    }
}
