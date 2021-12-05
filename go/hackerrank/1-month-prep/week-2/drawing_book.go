// https://www.hackerrank.com/challenges/one-month-preparation-kit-drawing-book/problem
//
// A teacher asks the class to open their books to a page number. A student can either start turning pages from the front of the book or from the back of the book. They always turn pages one at a time. When they open the book, page 1 is always on the right side.
//
// When they flip page 1, they see pages 2 and 3. Each page except the last page will always be printed on both sides. The last page may only be printed on the front, given the length of the book. If the book is n pages long, and a student wants to turn to page p, what is the minimum number of pages to turn? They can start at the beginning or the end of the book.
//
// Given n and p, find and print the minimum number of pages that must be turned in order to arrive at page p.

package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

func pageCount(n int32, p int32) int32 {
    /*
    n p c
    1 1 0
    2 1 0
    2 2 1 lr
    3 2 1 lr
    3 3 1 lr
    4 4 0
    5 4 1 r
    5 5 1 r
    6 5 1 r
    6 6 0
    front:
    if p 1 then c 0
    back:
    if p last and n even then c 0
    if p last and n odd then c 1
    frontTurns p = p/2
    backTurns n p = if even n then (n-p+1)/2 else (n-p)/2
    turns n p = min (frontTurns p) (backTurns n p)
    n p l r f b
    1 1 0 0 0 0

    2 1 0 1 0 1
    2 2 1 0 1 0

    3 1 0 1 0 1
    3 2 1 0 1 0
    3 3 1 0 1 0

    4 1 0 2 0 2
    4 2 1 1 1 1
    4 3 1 1 1 1
    4 4 2 0 2 0

    5 1 0 2 0 2
    5 2 1 1 1 1
    5 3 1 1 1 1
    5 4 2 0 2 0
    5 5 2 0 2 0
    */
    front := p / 2
    var back int32
    if n % 2 == 0 {
        back = (n-p+1) / 2
    } else {
        back = (n-p) / 2
    }
    if front < back {
        return front
    }
    return back
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

    pTemp, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    checkError(err)
    p := int32(pTemp)

    result := pageCount(n, p)

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
