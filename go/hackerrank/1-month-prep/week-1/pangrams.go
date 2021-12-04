// https://www.hackerrank.com/challenges/one-month-preparation-kit-pangrams/problem
//
// A pangram is a string that contains every letter of the alphabet. Given a sentence determine whether it is a pangram in the English alphabet. Ignore case. Return either pangram or not pangram as appropriate.

package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strings"
)

func pangrams(s string) string {
    var letters int
    for _, b := range s {
        if b == ' ' {
            continue
        }
        if 'a' <= b && b <= 'z' {
            b -= 'a'
        } else if 'A' <= b && b <= 'Z' {
            b -= 'A'
        }
        letters |= 1 << b
    }
    if letters == (1<<26) - 1 {
        return "pangram"
    } else {
        return "not pangram"
    }
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 16 * 1024 * 1024)

    s := readLine(reader)

    result := pangrams(s)

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
