(ns day3.core
  (:require [clojure.string :refer [split-lines]]))

(def input (split-lines (slurp "resources/day3/input")))

(defn parse-claim [raw-claim]
  (let [groups (re-matches #"(#\d+) @ (\d+),(\d+): (\d+)x(\d+)" raw-claim)]
    (if (not-empty groups)
      (let [id     (nth groups 1)
            x      (Integer/parseInt (nth groups 2))
            y      (Integer/parseInt (nth groups 3))
            width  (Integer/parseInt (nth groups 4))
            height (Integer/parseInt (nth groups 5))]
        {:id     id
         :left   x
         :right  (+ x width)
         :top    y
         :bottom (+ y height)})
      nil)))

(defn flatten1 [list]
  (reduce #(into %1 %2) [] list))

(defn claim->coordinates [{:keys [left right top bottom]}]
  (-> (for [y (range top bottom)]
        (for [x (range left right)]
          [x y]))
      (flatten1)))

(defn part1 [raw-claims]
  (->> (map #(claim->coordinates (parse-claim %)) raw-claims)
       (flatten1)
       (frequencies)
       (vals)
       (filter #(>= % 2))
       (count)))

(defn part2 [raw-claims]
  (let [claims (map (fn [raw-claim]
                      (let [claim (parse-claim raw-claim)
                            coords (claim->coordinates claim)]
                        (assoc claim :coords coords)))
                    raw-claims)
        claim-area (-> (map :coords claims)
                       (flatten1)
                       (frequencies))]
    (-> (filter (fn [{:keys [coords]}]
                  (every? #(= (claim-area %) 1) coords))
                claims)
        (first)
        (:id))))

(defn -main []
  (println (part1 input))
  (println (part2 input)))
