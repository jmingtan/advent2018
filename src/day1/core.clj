(ns day1.core
  (:require [clojure.string :refer [split-lines]]))

(def input (slurp "resources/day1/input"))

(def nums (map #(Integer/parseInt %) (split-lines input)))

(defn part1 [nums]
  (reduce + nums))

(defn freqs [initial nums]
  (let [sums  (rest (reductions + initial nums)) ; discard first result which is just the initial value
        freqs (frequencies sums)]
    {:sums  sums
     :freqs freqs}))

(defn dupe [{:keys [sums freqs]}]
  (first (remove #(= 1 (freqs %)) sums)))

(defn frequency-reducer [state changelist]
  (let [result (freqs (:init state) changelist)]
    {:freqs (reduce (fn [agg [k v]]
                      (assoc agg k (+ v (get agg k 0))))
                    (:freqs state)
                    (:freqs result))
     :init  (last (:sums result))
     :sums  (:sums result)}))

(defn part2 [nums]
  (let [freq-changes          (repeat nums) ; create infinite sequence of freq change lists
        initial-state         {:freqs {0 1} ; include the initial frequency which is 0
                               :init  0
                               :sums  []}
        freq-stream           (reductions frequency-reducer
                                          initial-state
                                          freq-changes)
        result                (drop-while #(nil? (dupe %)) ; process the stream until we find a duplicate freq
                                          freq-stream)]
    (dupe (first result))))

(defn -main []
  (println (part1 nums))
  (println (part2 nums)))
