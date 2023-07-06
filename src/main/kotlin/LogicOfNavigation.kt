import java.util.*
import kotlin.system.exitProcess

//СДЕЛАТЬ РЕФАКТОРИНГ
class LogicOfNavigation {
    private var mapOfArchive: MutableMap<Int, Archive> =
        HashMap()//КОЛЛЕКЦИЯ АРХИВОВ - MAP БЫЛ ВЫБРАН ПОТОМУ ЧТО УДОБНО НУМЕРОВАТЬ ПУНКТЫ МЕНЮ))

    fun startMenu(objectOfInput: Any) {//СТАРТ ГЛАВНОГО МЕНЮ ПРОГРАММЫ
        if (objectOfInput is LogicOfNavigation) {
            println("Выберите необходимый пункт меню или архив! Для этого введите цифру")
            println("===Главное меню===")
            println("0. Выход из программы")
            println("1. Создать архив")
            if (mapOfArchive.isEmpty()) {//ПРОВЕРЯЕМ НА НЕПУСТОЙ MAP АРХИВОВ
                println("Вы не создали ни одного архива. Введите 1, чтобы это и справить! :)")
            } else {
                for (key in mapOfArchive.keys) {
                    val currArchive = mapOfArchive[key]
                    println("${key}. ${currArchive!!.nameOfArchive}")
                }
            }
            chechMenuPoint(this)//ЗАПУСКАЕМ ПРОВЕРКУ ВВОДА ПОЛЬЗОВАТЕЛЯ
        } else if (objectOfInput is Archive) {
            println("===Выбран архив: ${objectOfInput.nameOfArchive}===")
            println("0. Выход в главное меню")
            println("1. Создать заметку в архиве")
            if (objectOfInput.mapOfNotes.isEmpty()) { //ПРОВЕРЯЕМ ПУСТОЙ ЛИ MAP У ЭТОГО ОБЪЕКТА
                println("Вы не создали ни одной заметки. Введите 1, чтобы это и справить! :)")
            } else { //ЕСЛИ НЕ ПУСТОЙ, ТО ВЫВОДИМ ОСТАЛЬНЫЕ ПУНКТЫ МЕНЮ - ИМЕЮЩИЕСЯ АРХИВЫ
                for (key in objectOfInput.mapOfNotes.keys) {
                    val currNote = objectOfInput.mapOfNotes[key]
                    println("${key}. ${currNote!!.nameOfNote}")
                }
            }
            startProgramm.chechMenuPoint(objectOfInput)
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
                        0 -> startProgramm.startMenu(this) //ВОЗВРАЩАЕТ В МЕНЮ ВЫБОРА АРХИВОВ
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

    private fun addNewArchive() {//МЕТОД ДОБАВЛЕНИЯ АРХИВА В MAP
        var keysMax = 1//НАЧАЛО НУМЕРАЦИИ КЛЮЧА АРХИВОВ
        println("Введите имя для вашего архива")
        var userInputNameOfArchive = readln()
        while (true) {
            if (!userInputNameOfArchive.equals("") && !userInputNameOfArchive.trim().equals("")
            ) {//ЕСЛИ НЕ ПУСТАЯ СТРОКА И ПОСЛЕ ТРИМА ОНА ТОЖЕ НЕ ПУСТА, ТО ОК
                for (keys in mapOfArchive.keys) {
                    keysMax = keys
                }
                ++keysMax
                mapOfArchive[keysMax] = Archive(userInputNameOfArchive)
                return
            } else {
                println("Вы ничего не ввели или ввели пробелы. Попробуйте еще!")
                userInputNameOfArchive = readln()
            }
        }
    }

    private fun changeOfArchiv(numberOfArchive: Int) {
        if (mapOfArchive.containsKey(numberOfArchive)) {
            val currArchive = mapOfArchive[numberOfArchive]
            if (currArchive != null) {
                startMenu(currArchive)
            }
        } else {
            println("Такого пункта меню или архива не существует!")
        }
    }
}