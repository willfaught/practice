// https://www.hackerrank.com/challenges/one-month-preparation-kit-diagonal-difference/problem
//
// Given a square matrix, calculate the absolute difference between the sums of its diagonals.

package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

func diagonalDifference(arr [][]int32) int32 {
    n := len(arr)
    var sum1, sum2 int32
    for i := 0; i < n; i++ {
        sum1 += arr[i][i]
        sum2 += arr[i][n-i-1]
    }
    abs := sum1 - sum2
    if abs < 0 {
        abs = -abs
    }
    return abs
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 16 * 1024 * 1024)

    nTemp, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    checkError(err)
    n := int32(nTemp)

    var arr [][]int32
    for i := 0; i < int(n); i++ {
        arrRowTemp := strings.Split(strings.TrimRight(readLine(reader)," \t\r\n"), " ")

        var arrRow []int32
        for _, arrRowItem := range arrRowTemp {
            arrItemTemp, err := strconv.ParseInt(arrRowItem, 10, 64)
            checkError(err)
            arrItem := int32(arrItemTemp)
            arrRow = append(arrRow, arrItem)
        }

        if len(arrRow) != int(n) {
            panic("Bad input")
        }

        arr = append(arr, arrRow)
    }

    result := diagonalDifference(arr)

    fmt.Fprintf(writer, "%d\n", result)

    writer.Flush()
}

func readLine(reader *bufio.Reader) string {
    str, _, err := reader.ReadLine()
    if err == io.EOF {
        return ""
    }

    return strings.TrimRight(string(str), "\r\n")
}

func checkError(err error) {
    if err != nil {
        panic(err)
    }
}
