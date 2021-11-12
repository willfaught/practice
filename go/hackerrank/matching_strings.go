// https://www.hackerrank.com/challenges/one-month-preparation-kit-sparse-arrays/problem
//
// There is a collection of input strings and a collection of query strings. For each query string, determine how many times it occurs in the list of input strings. Return an array of the results.

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
// [a] [b] > [0]
// [a] [a] > [1]
// [a] [a, a] > [1, 1]
// [a, a, b, b, b] [b, a] > [2, 3]
func matchingStrings(strings []string, queries []string) []int32 {
    set := make(map[string]int, len(strings))
    for _, s := range strings {
        set[s]++
    }
    counts := make([]int32, 0, len(queries))
    for _, q := range queries {
        counts = append(counts, int32(set[q]))
    }
    return counts
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 16 * 1024 * 1024)

    stringsCount, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    checkError(err)

    var strs []string

    for i := 0; i < int(stringsCount); i++ {
        stringsItem := readLine(reader)
        strs = append(strs, stringsItem)
    }

    queriesCount, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    checkError(err)

    var queries []string

    for i := 0; i < int(queriesCount); i++ {
        queriesItem := readLine(reader)
        queries = append(queries, queriesItem)
    }

    res := matchingStrings(strs, queries)

    for i, resItem := range res {
        fmt.Fprintf(writer, "%d", resItem)

        if i != len(res) - 1 {
            fmt.Fprintf(writer, "\n")
        }
    }

    fmt.Fprintf(writer, "\n")

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
