(ns day2.core
  (:require [clojure.string :refer [split-lines join]]))

(def input (split-lines (slurp "resources/day2/input")))

(defn part1 [boxids]
  (let [freqs (map frequencies boxids)
        freqcheck (fn [num freqs] (not-empty (filter #(= num %) (vals freqs))))
        twos (count (filter (partial freqcheck 2) freqs))
        threes (count (filter (partial freqcheck 3) freqs))]
    (* twos threes)))

(defn similar? [a b]
  (let [diff (map #(= %1 %2) a b)]
    (if (<= (count (filter false? diff)) 1)
      (->> (map #(if %2 %1 nil) a diff)
           (remove nil?)
           (join))
      nil)))

(defn part2 [boxids]
  (->> (for [i (range (count boxids))]
         (for [j (range (inc i) (count boxids))]
           (similar? (nth boxids i) (nth boxids j))))
       (flatten)
       (remove nil?)
       (first)))

(defn -main []
  (println (part1 input))
  (println (part2 input)))
