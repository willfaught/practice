// https://www.hackerrank.com/challenges/one-month-preparation-kit-plus-minus/problem
//
// Given an array of integers, calculate the ratios of its elements that are positive, negative, and zero. Print the decimal value of each fraction on a new line with 6 places after the decimal.

package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

func plusMinus(arr []int32) {
    var pos, neg, zero int
    for i := range arr {
        if x := arr[i]; x < 0 {
            neg++
        } else if x > 0 {
            pos++
        } else {
            zero++
        }
    }
    n := float32(len(arr))
    fmt.Printf("%.6f\n", float32(pos)/n)
    fmt.Printf("%.6f\n", float32(neg)/n)
    fmt.Printf("%.6f\n", float32(zero)/n)
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    nTemp, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    checkError(err)
    n := int32(nTemp)

    arrTemp := strings.Split(strings.TrimSpace(readLine(reader)), " ")

    var arr []int32

    for i := 0; i < int(n); i++ {
        arrItemTemp, err := strconv.ParseInt(arrTemp[i], 10, 64)
        checkError(err)
        arrItem := int32(arrItemTemp)
        arr = append(arr, arrItem)
    }

    plusMinus(arr)
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
