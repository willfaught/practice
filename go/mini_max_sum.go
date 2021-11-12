// https://www.hackerrank.com/challenges/one-month-preparation-kit-mini-max-sum/problem
//
// Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.

package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

// Tests:
// 1 1 1 1 1
// 1 2 3 4 5
func miniMaxSum(arr []int32) {
    var min, max = arr[0], arr[0]
    var sum = int64(arr[0])
    for _, x := range arr[1:] {
        sum += int64(x)
        if x < min {
            min = x
        }
        if x > max {
            max = x
        }
    }
    fmt.Printf("%d %d\n", sum-int64(max), sum-int64(min))
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    arrTemp := strings.Split(strings.TrimSpace(readLine(reader)), " ")

    var arr []int32

    for i := 0; i < 5; i++ {
        arrItemTemp, err := strconv.ParseInt(arrTemp[i], 10, 64)
        checkError(err)
        arrItem := int32(arrItemTemp)
        arr = append(arr, arrItem)
    }

    miniMaxSum(arr)
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
