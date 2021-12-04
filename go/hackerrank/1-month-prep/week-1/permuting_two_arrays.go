// https://www.hackerrank.com/challenges/one-month-preparation-kit-two-arrays/problem
//
// There are two n-element arrays of integers, A and B. Permute them into some A' and B' such that the relation A'[i] + B'[i] >= k holds for all i where 0 <= i < n. There will be q queries consisting of A, B, and k. For each query, return YES if some permutation A', B' satisfying the relation exists. Otherwise, return NO.

package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "sort"
    "strconv"
    "strings"
)

func twoArrays(k int32, A []int32, B []int32) string {
    sort.Slice(A, func(i, j int) bool {
        return A[i] < A[j]
    })
    sort.Slice(B, func(i, j int) bool {
        return B[i] > B[j]
    })
    for i, n := 0, len(A); i < n; i++ {
        if A[i] + B[i] < k {
            return "NO"
        }
    }
    return "YES"
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
        firstMultipleInput := strings.Split(strings.TrimSpace(readLine(reader)), " ")

        nTemp, err := strconv.ParseInt(firstMultipleInput[0], 10, 64)
        checkError(err)
        n := int32(nTemp)

        kTemp, err := strconv.ParseInt(firstMultipleInput[1], 10, 64)
        checkError(err)
        k := int32(kTemp)

        ATemp := strings.Split(strings.TrimSpace(readLine(reader)), " ")

        var A []int32

        for i := 0; i < int(n); i++ {
            AItemTemp, err := strconv.ParseInt(ATemp[i], 10, 64)
            checkError(err)
            AItem := int32(AItemTemp)
            A = append(A, AItem)
        }

        BTemp := strings.Split(strings.TrimSpace(readLine(reader)), " ")

        var B []int32

        for i := 0; i < int(n); i++ {
            BItemTemp, err := strconv.ParseInt(BTemp[i], 10, 64)
            checkError(err)
            BItem := int32(BItemTemp)
            B = append(B, BItem)
        }

        result := twoArrays(k, A, B)

        fmt.Fprintf(writer, "%s\n", result)
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
