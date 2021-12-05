// https://www.hackerrank.com/challenges/one-month-preparation-kit-the-birthday-bar/problem
//
// Two children, Lily and Ron, want to share a chocolate bar. Each of the squares has an integer on it. Lily decides to share a contiguous segment of the bar selected such that:
// - The length of the segment matches Ron's birth month, and,
// - The sum of the integers on the squares is equal to his birth day.
// Determine how many ways she can divide the chocolate.

package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

func birthday(s []int32, d int32, m int32) int32 {
    n := len(s)
    targetSum := d
    targetLength := int(m)
    if n < targetLength {
        return 0
    }
    var count int32
    var sum int32
    for i := 0; i < targetLength; i++ {
        sum += s[i]
    }
    if sum == targetSum {
        count++
    }
    for i := targetLength; i < n; i++ {
        sum -= s[i - targetLength]
        sum += s[i]
        if sum == targetSum {
            count++
        }
    }
    return count
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

    sTemp := strings.Split(strings.TrimSpace(readLine(reader)), " ")

    var s []int32

    for i := 0; i < int(n); i++ {
        sItemTemp, err := strconv.ParseInt(sTemp[i], 10, 64)
        checkError(err)
        sItem := int32(sItemTemp)
        s = append(s, sItem)
    }

    firstMultipleInput := strings.Split(strings.TrimSpace(readLine(reader)), " ")

    dTemp, err := strconv.ParseInt(firstMultipleInput[0], 10, 64)
    checkError(err)
    d := int32(dTemp)

    mTemp, err := strconv.ParseInt(firstMultipleInput[1], 10, 64)
    checkError(err)
    m := int32(mTemp)

    result := birthday(s, d, m)

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
