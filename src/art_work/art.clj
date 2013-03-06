(ns art-work.art
  (:use [clojure.math.numeric-tower :only (round)]))

(declare get-num generate color-cell? neigbors populate-random grow not-nil? extract-cell)

(defn get-num []
  (cond
   (= :true (color-cell?)) (round (rand 6))
   :else nil))

(defn not-nil? [x] (not (nil? x)))

(defn only-set [board]
  (filter (fn [[x y val]] (not-nil? val)) board))

(defn any-neighbors-set? [x y board]
  (reduce (fn [any-set? [x1 y1 val1]]
            (or (not-nil? val1) any-set?))
          false
          (get-neigbors x y board)))

(defn grow [board]
  (map (fn [[x y val]] 
         (cond
          (any-neighbors-set? x y board) [x y 1]
          :else [x y val]))
       board))

(defn generate [size]
  (grow (populate-random size)))

(defn populate-random [size]
  (for [x (range size) y (range size)]
    (let [val (get-num)]
      (cond
       (nil? val) [x y nil]
       :else [x y val]))))

(defn color-cell? []
  (cond
   (> (rand 1) 0.99) :true
   :else :false))

(defn neigbors [[x y]]
  (for [dx [-1 0 1] dy (if (zero? dx) [-1 1] [-1 0 1])]
    [(+ dx x) (+ dy y)]))

(defn get-neigbors [x y board]
  (filter not-nil? (map (fn [cell] (extract-cell cell board)) (neigbors [x y]))))

(defn extract-cell [[x y] board]
  (let [match (filter (fn [[x1 y1 _]] (and (= x1 x) (= y1 y))) board)]
    (cond
     (= 1 (count match)) (first match)
     :else nil)))

