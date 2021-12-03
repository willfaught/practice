// https://www.hackerrank.com/challenges/one-month-preparation-kit-flipping-bits/problem
//
// You will be given a list of 32 bit unsigned integers. Flip all the bits ( and ) and return the result as an unsigned integer.

package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

func flippingBits(n int64) int64 {
    return int64(^uint32(n))
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 16 * 1024 * 1024)

    qTemp, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    checkError(err)
    q := int32(qTemp)

    for qItr := 0; qItr < int(q); qItr++ {
        n, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
        checkError(err)

        result := flippingBits(n)

        fmt.Fprintf(writer, "%d\n", result)
    }

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
