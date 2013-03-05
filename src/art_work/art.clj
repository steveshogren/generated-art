(ns art-work.art
  (:use [clojure.math.numeric-tower :only (round)]))

(declare get-num generate color-cell? neigbors)

(defn get-num []
  (cond
   (= :true (color-cell?)) (round (rand 6))
   :else nil))

(defn not-nill? [x] (not (nil? x)))

(defn generate [size]
  #_(filter not-nill?)
  (for [x (range size) y (range size)]
    (let [val (get-num)]
      (cond
       (nil? val) nil
       :else [x y val]))))

(defn color-cell? []
  (cond
   (> (rand 1) 0.7) :true
   :else :false))

(defn neigbors [[x y]]
  (for [dx [-1 0 1] dy (if (zero? dx) [-1 1] [-1 0 1])]
    [(+ dx x) (+ dy y)]))

(defn get-neigbors [[x y _] board]
  (map (fn [[x y]] [x y]) (neigbors [x y])))
