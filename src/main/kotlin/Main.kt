object Singlton {
    val startProgramm: LogicOfNavigation = LogicOfNavigation()
}

fun main(args: Array<String>) {
    Singlton.startProgramm.startMenu(Singlton.startProgramm)
}