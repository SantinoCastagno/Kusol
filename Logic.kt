
val values = List(9, { i -> i + 1 })
var charged: Boolean = false
var board_sudoku = Array(9, { IntArray(9) })
var countValues: Int = 0

fun main() {
    println("---------------------------")
    chargeSudokuExample1()
    printBoard()
    println("---------------------------")
    solveBoard()
    printBoard()
    println("---------------------------")
}

//Metodo que retorna los valores que ya fueron utilizados en la fila.
fun getRow(fila: Int): Set<Int> {
    return board_sudoku[fila].filter { i -> i != 0 }.sorted().toSet()
}

//Metodo que retorna los valores que ya fueron utilizados en la columna.
fun getColumn(columna: Int): Set<Int> {
    var res: MutableList<Int> = MutableList(0, { 0 })
    var temp: Int
    for (i in 0..8) {
        temp = (board_sudoku[i][columna])
        if (temp != 0)
            res.add(temp)
    }
    return res.sorted().toSet()
}

//Metodo que retorna los valores que ya fueron utilizados en la cuadricula.
fun getGrid(fila: Int, columna: Int): Set<Int> {
    var f_min: Int = -1;
    var c_min: Int = -1
    var res: MutableList<Int> = MutableList(0, { 0 })
    when (fila) {
        in 0..2 -> f_min = 0
        in 3..5 -> f_min = 3
        in 6..8 -> f_min = 6
    }
    when (columna) {
        in 0..2 -> c_min = 0
        in 3..5 -> c_min = 3
        in 6..8 -> c_min = 6
    }
    var f_max = f_min + 2
    var c_max = c_min + 2
    for (i in f_min..f_max) {
        res.addAll(board_sudoku[i].toList().subList(c_min, c_max + 1).filter { j -> j != 0 })
    }
    return res.sorted().toSet()
}

//Metodo que retorna la union de todos los valores utilizados.
fun getUsedValues(fila: Int, columna: Int): Set<Int> {
    val f_values = getRow(fila)
    val c_values = getColumn(columna)
    val t_values = getGrid(fila, columna)
    return f_values.union(c_values.union(t_values)).sorted().toSet()
}

//Metodo que retorna el complemento de los valores utilizados.
fun getNonUsedValues(fila: Int, columna: Int): Set<Int> {
    val us_va = getUsedValues(fila, columna)
    return values.filterNot { i -> us_va.contains(values[i - 1]) }.toSet()
}

//Metodo que va resolviendo el arreglo.
fun solveBoard() {
    countChargedValues()
    while (countValues < board_sudoku.size * board_sudoku[0].size) {
        //Recorro cada fila.
        for (i in 0..8) {
            //Recorro cada columna.
            for (j in 0..8) {
                corroborationAndEnter(i, j)
            }
        }
    }
}

//Metodo que cuenta todos los valores cargados.
fun countChargedValues(){
    for (i in 0..8) {
        for (j in 0..8) {
            if (board_sudoku[i][j] != 0) {
                countValues++
            }
        }
    }
}

//Metodo que verifica si una casilla se puede resolver y de ser posible lo resuelve.
fun corroborationAndEnter(fila: Int, columna: Int) {
    if (board_sudoku[fila][columna] == 0 && getNonUsedValues(fila, columna).size == 1) {
        board_sudoku[fila][columna] = getNonUsedValues(fila, columna).first()
        countValues++
    }
}

//Metodo que carga el tablero con el ejemplo 1.
fun chargeSudokuExample1() {
    board_sudoku[0][0] = 5; board_sudoku[0][1] = 3; board_sudoku[0][4] = 7
    board_sudoku[1][0] = 6; board_sudoku[1][3] = 1; board_sudoku[1][4] = 9;board_sudoku[1][5] = 5
    board_sudoku[2][1] = 9; board_sudoku[2][2] = 8; board_sudoku[2][7] = 6
    board_sudoku[3][0] = 8; board_sudoku[3][4] = 6; board_sudoku[3][8] = 3
    board_sudoku[4][0] = 4; board_sudoku[4][3] = 8; board_sudoku[4][5] = 3;board_sudoku[4][8] = 1
    board_sudoku[5][0] = 7; board_sudoku[5][4] = 2; board_sudoku[5][8] = 6
    board_sudoku[6][1] = 6; board_sudoku[6][6] = 2; board_sudoku[6][7] = 8
    board_sudoku[7][3] = 4; board_sudoku[7][4] = 1; board_sudoku[7][5] = 9;board_sudoku[7][8] = 5
    board_sudoku[8][4] = 8; board_sudoku[8][7] = 7; board_sudoku[8][8] = 9
    charged = true;
}

//Metodo que imprime el sudoku.
fun printBoard() {
    for (i in 0..8) {
        println(board_sudoku[i].toList())
    }
}

class ValueToCharge {
    private var value = Int
    private val road = Int
    private val column = Int

    class valueToCharge constructor(v: Int, r: Int, c: Int) {
    }
}