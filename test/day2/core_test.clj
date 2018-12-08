(ns day2.core-test
  (:require [clojure.test :refer [testing deftest is]]
            [day2.core :as day2]))

(deftest part1
  (let [boxids ["abcdef"
                "bababc"
                "abbcde"
                "abcccd"
                "aabcdd"
                "abcdee"
                "ababab"]]
    (is (= 12 (day2/part1 boxids)))))

(deftest similar?
  (is (= "fgij" (day2/similar? "fghij" "fguij")))
  (is (nil? (day2/similar? "abcde" "axcye"))))

(deftest part2
  (let [boxids ["abcde"
                "fghij"
                "klmno"
                "pqrst"
                "fguij"
                "axcye"
                "wvxyz"]]
    (is (= "fgij" (day2/part2 boxids)))))
