(ns day1.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [day1.core :as day1]))

(deftest part1
  (testing "should return total after all changes are applied"
    (is (= 3 (day1/part1 [1 -2 3 1])))
    (is (= 3 (day1/part1 [1 1 1])))
    (is (= 0 (day1/part1 [1 1 -2])))
    (is (= -6 (day1/part1 [-1 -2 -3])))))

(deftest freqs
  (testing "should return intermediate sums and their frequencies from a list of numbers"
    (let [nums   [1 -2 3 1 1 -2]
          result (day1/freqs 0 nums)]
      (is (= [1 -1 2 3 4 2] (:sums result)))
      (is (= {1  1
              -1 1
              2  2
              3  1
              4  1}
             (:freqs result))))))

(deftest dupe
  (testing "should return dupe value if found"
    (let [input {:sums  [0 1 -1 2 3 4 2]
                 :freqs {0  1
                         1  1
                         -1 1
                         2  2
                         3  1
                         4  1}}]
      (is (= 2 (day1/dupe input))))))

(deftest part2
  (testing "should return first duplicated frequency"
    (is (= 2 (day1/part2 [1 -2 3 1])))
    (is (= 0 (day1/part2 [1 -1])))
    (is (= 10 (day1/part2 [3 3 4 -2 -4])))
    (is (= 5 (day1/part2 [-6 3 8 5 -6])))
    (is (= 14 (day1/part2 [7 7 -2 -7 -4])))))
