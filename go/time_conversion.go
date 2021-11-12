// https://www.hackerrank.com/challenges/one-month-preparation-kit-time-conversion/problem
//
// Given a time in 12-hour AM/PM format, convert it to military (24-hour) time.
// Note:
// - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock. 
// - 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.

package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strings"
)

// Tests:
// 12:34:56AM
// 12:34:56PM
// 01:34:56AM
// 01:34:56PM
// 11:34:56PM
// 11:34:56PM
func timeConversion(s string) string {
    // 12p > 12, 1p > 13, 2p > 14, 11a > 11
    // if am and hour is 12, use 0
    // if pm and hour is 12, don't add 12
    bs := []byte(s)
    am := s[len(s)-2] == 'A'
    if am && bs[0] == '1' && bs[1] == '2' {
        bs[0] = '0'
        bs[1] = '0'
    } else if !am && (bs[0] != '1' || bs[1] != '2') {
        bs[0]++
        if x := bs[1] + 2; x <= '9' {
            bs[1] = x
        } else {
            bs[1] = x - 10
            bs[0]++
        }
    }
    return string(bs[:len(bs)-2])
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 16 * 1024 * 1024)

    s := readLine(reader)

    result := timeConversion(s)

    fmt.Fprintf(writer, "%s\n", result)

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
