(ns day3.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [day3.core :as day3]))

(deftest parse-claim
  (is (= {:id     "#123"
          :left   3
          :right  8
          :top    2
          :bottom 6}
         (day3/parse-claim "#123 @ 3,2: 5x4"))))

(deftest claim->coordinates
  (is (= [[3 2] [4 2] [5 2] [6 2] [7 2]
          [3 3] [4 3] [5 3] [6 3] [7 3]
          [3 4] [4 4] [5 4] [6 4] [7 4]
          [3 5] [4 5] [5 5] [6 5] [7 5]]
         (day3/claim->coordinates {:left 3 :right 8 :top 2 :bottom 6}))))

(deftest part1
  (let [claims ["#1 @ 1,3: 4x4"
                "#2 @ 3,1: 4x4"
                "#3 @ 5,5: 2x2"]]
    (is (= 4 (day3/part1 claims)))))

(deftest part2
  (let [claims ["#1 @ 1,3: 4x4"
                "#2 @ 3,1: 4x4"
                "#3 @ 5,5: 2x2"]]
    (is (= "#3" (day3/part2 claims)))))
